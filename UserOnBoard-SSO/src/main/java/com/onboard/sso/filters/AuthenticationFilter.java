/**
 * 
 */
package com.onboard.sso.filters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.onboard.sso.service.UserDetailsServices;
import com.onboard.sso.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 *         10-Aug-2020
 */

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
	private static final String TOKEN_HEADER = "x-user-onboard-token";

	@Value("${white.list.urls}")
	private String whiteListingUrls;

	private String[] urls = null;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private JwtTokenUtil tokenUtil;

	@Autowired
	private UserDetailsServices userdetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String registrationToken = getRegistrationToken(request);
		System.out.println(StringUtils.isEmpty(registrationToken)+" "+registrationToken+" "+isRegistrationRequest(request));
		if (!StringUtils.isEmpty(registrationToken) && isRegistrationRequest(request)) {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("TEMP_USER",
					"TEMP_PASSWORD", new ArrayList<>());
			SecurityContext context = SecurityContextHolder.getContext();
			context.setAuthentication(authentication);
		} else if (!isUrlWhiteListed(request)) {
			try {
				if (!validateToken(request)) {
					response.sendError(401, "User not authorize.");
					return;
				}
				String userName = tokenUtil.getUsernameFromToken(request.getHeader(TOKEN_HEADER));

				UserDetails userDetails = userdetailsService.loadUserByUsername(userName);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName,
						userDetails.getPassword(), userDetails.getAuthorities());
				SecurityContext context = SecurityContextHolder.getContext();
				context.setAuthentication(authentication);
			} catch (ExpiredJwtException e) {
				response.sendError(401, "Token Expired");
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	/**
	 * Validate token
	 * 
	 * @param request
	 * @return
	 */
	public boolean validateToken(HttpServletRequest request) {
		String token = request.getHeader(TOKEN_HEADER);
		if (!StringUtils.isEmpty(token)) {
			try {
				return tokenUtil.isTokenExpired(token) ? false : true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Checks whit listed urls.
	 * 
	 * @param request
	 * @return
	 */
	public boolean isUrlWhiteListed(HttpServletRequest request) {
		if (urls == null)
			urls = whiteListingUrls.split(",");
		System.out.println(request.getRequestURI());
		for (String url : urls) {
			System.out.println(request.getRequestURI() + " white listed " + url);
			if (url.equals(request.getRequestURI())) {
				return true;
			}
		}
		return false;
	}
	
	public String getRegistrationToken(HttpServletRequest request) {
		return request.getHeader("tempToken")==null?request.getParameter("tempToken"):request.getHeader("tempToken");
	}

	public boolean isRegistrationRequest(HttpServletRequest request) {
		return "/authRegSessiontoken.json".equals(request.getRequestURI());
	}

}
