/**
 * 
 */
package com.onboard.sso.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.onboard.sso.entity.RegistrationSessionToken;
import com.onboard.sso.entity.Role;
import com.onboard.sso.entity.User;
import com.onboard.sso.model.AuthFailureResponse;
import com.onboard.sso.model.AuthResponse;
import com.onboard.sso.model.AuthSuccessResponse;
import com.onboard.sso.model.LoginModel;
import com.onboard.sso.repos.RegistrationSessionTokenRepo;
import com.onboard.sso.service.SessionRegistrationService;
import com.onboard.sso.service.SessionUtils;
import com.onboard.sso.service.UserDetailsServices;
import com.onboard.sso.util.JwtTokenUtil;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 *         11-Aug-2020
 */
//@CrossOrigin
@RestController
public class AuthenticationController {

	@Autowired
	private UserDetailsServices userdetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RegistrationSessionTokenRepo repo;

	@Autowired
	private SessionRegistrationService service;

	@Autowired
	private JwtTokenUtil tokenUtil;

	@Value("${admin.email}")
	private String adminEmail;

	@Value("${admin.password}")
	private String adminPassword;
	
	@Autowired
	private SessionUtils sesionUtils;

	@RequestMapping(path = "/authenticate.json", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AuthResponse authenticate(@RequestBody LoginModel payload) {
		if (adminEmail.equals(payload.getUserName()) && adminPassword.equals(payload.getPassword())) {
			String token = tokenUtil.generateToken(adminEmail);
			return new AuthSuccessResponse(200, token,1);
		}
		UserDetails details =null;
		try {
		details=userdetailsService.loadUserByUsername(payload.getUserName());}catch (Exception e) {
			e.printStackTrace();
		}
		if (details == null) {
			return new AuthFailureResponse(401, "User not found.");
		}
		if (passwordEncoder.matches(payload.getPassword(), details.getPassword())) {
			System.out.println("Matches");
			String token = tokenUtil.generateToken(details);
			sesionUtils.putTokenInMap(payload.getUserName(), token);
			boolean flag=details.getAuthorities().stream().filter((auth)->{return Role.ADMIN_ROLE.equals(auth.getAuthority());}).findAny().isPresent();
			return new AuthSuccessResponse(200, token,flag?1:0);
		}
		return new AuthFailureResponse(400, "Email/Password do not match.");
	}

	@RequestMapping(path = "/validateToken.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AuthResponse validateToken(HttpServletRequest request) {
		if (request.getHeader("x-user-onboard-token") == null && request.getParameter("x-user-onboard-token")==null) {
			return new AuthFailureResponse(401, "User not found.");
		}
		String token=request.getHeader("x-user-onboard-token")==null?request.getParameter("x-user-onboard-token"):request.getHeader("x-user-onboard-token");
		String userName = tokenUtil.getUsernameFromToken(token);
		
		if (adminEmail.equals(userName)) {
			sesionUtils.putTokenInMap(userName, token);
			return new AuthSuccessResponse(200, request.getHeader("x-user-onboard-token"),1);
		} else {
			User user =null;
			try {
			user=userdetailsService.findUserByEmail(userName);}catch (Exception e) {
				// TODO: handle exception
			}
			if(user==null)
				return new AuthFailureResponse(400, "user not found.");
			if ( user.getActive() && sesionUtils.getTokenAssociatedWithUser(user.getUserDetails().getEmailId())!=null) {
				boolean flag=user.getRoles().stream().filter((auth)->{return Role.ADMIN_ROLE.equals(auth.getName());}).findAny().isPresent();
				return new AuthSuccessResponse(200, request.getHeader("x-user-onboard-token"),flag?1:0);
			} else {
				return new AuthFailureResponse(400, "User does not active.");
			}
		}

	}

	@RequestMapping(path = "/authRegSessiontoken.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AuthResponse validateSessionToken(HttpServletRequest request) throws Exception {
		String tokenStr = request.getHeader("x-registration-token") == null ? request.getParameter("x-registration-token")
				: request.getHeader("x-registration-token");
		System.out.println("Found token:" + tokenStr);
		if (tokenStr == null) {
			return new AuthFailureResponse(401, "User not found.");
		}
		System.out.println("Token decoded:"+new String(Base64.getDecoder().decode(tokenStr.getBytes()),"UTF-8"));
		RegistrationSessionToken token = repo.findByEmail(new String(Base64.getDecoder().decode(tokenStr.getBytes()),"UTF-8"));
		System.out.println("Token object:"+token);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -10);
		System.out.println(cal.getTime().after(token.getLastUpdatedDate()));
		if (token != null)
			if (!cal.getTime().after(token.getLastUpdatedDate())) {
				sesionUtils.putTokenInMap(token.getEmailId(), tokenStr);
				return new AuthSuccessResponse(200, tokenStr,0);
			} else {
				service.deleteToken(token);
			}

		return new AuthFailureResponse(400, "Token not found.");
	}
	
	
}
