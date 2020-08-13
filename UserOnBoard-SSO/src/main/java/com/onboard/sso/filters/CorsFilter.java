/**
 * 
 */
package com.onboard.sso.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 13-Aug-2020
 */
@Component("crossOriginFilter")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		 HttpServletRequest request = (HttpServletRequest) req;
		    HttpServletResponse response = (HttpServletResponse) res;

		    response.setHeader("Access-Control-Allow-Origin", "*");
		    response.setHeader("Access-Control-Allow-Credentials", "true");
		    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		    response.setHeader("Access-Control-Max-Age", "3600");
		    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, x-registration-token, x-user-onboard-token");
		    System.out.println(request.getMethod());
		    if(request.getMethod().equals("OPTIONS")) {
		    	System.out.println("request method is option ");
		    	return ;
		    }
	    chain.doFilter(req, res);
	}
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		if(request.getMethod().equals("OPTION")) {
//			System.out.println("Got request");
//			response.setHeader("Access-Control-Allow-Origin", "*");
//			response.setHeader("Origin", "*");
//			response.setHeader("Access-Control-Request-Method", "GET,POST,PUT,DELETE,OPTIONS");
//			response.setHeader("Access-Control-Allow-Headers", "x-registration-token,x-user-onboard-token,Content-Type,Accept");
//			response.setHeader("Access-Control-Max-Age", "86400");
//			return;
//		}
//		filterChain.doFilter(request, response);
//		
//	}

}
