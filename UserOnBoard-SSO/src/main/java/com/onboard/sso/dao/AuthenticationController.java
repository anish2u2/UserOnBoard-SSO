/**
 * 
 */
package com.onboard.sso.controllers;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.onboard.sso.entity.RegistrationSessionToken;
import com.onboard.sso.entity.User;
import com.onboard.sso.model.AuthFailureResponse;
import com.onboard.sso.model.AuthResponse;
import com.onboard.sso.model.AuthSuccessResponse;
import com.onboard.sso.model.LoginModel;
import com.onboard.sso.repos.RegistrationSessionTokenRepo;
import com.onboard.sso.service.SessionRegistrationService;
import com.onboard.sso.service.UserDetailsServices;
import com.onboard.sso.util.JwtTokenUtil;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 11-Aug-2020
 */
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
	
	@RequestMapping(path = "authenticate.json", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AuthResponse authenticate(@RequestBody LoginModel payload) {
		if(adminEmail.equals(payload.getUserName())&& adminPassword.equals(payload.getPassword())) {
			String token=tokenUtil.generateToken(adminEmail);
			return new AuthSuccessResponse(200, token);
		}
		UserDetails details=userdetailsService.loadUserByUsername(payload.getUserName());
		if(details==null) {
			return new AuthFailureResponse(401,"User not found.");
		}
		if(details.getPassword().equals(passwordEncoder.encode(payload.getPassword()))) {
			String token=tokenUtil.generateToken(details);
			return new AuthSuccessResponse(200, token);
		}
		return new AuthFailureResponse(400,"Email/Password do not match.");
	}
	
	
	@RequestMapping(path = "validateToken.json", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AuthResponse validateToken(HttpServletRequest request) {
		if(request.getHeader("x-user-onboard-token")==null) {
			return new AuthFailureResponse(401,"User not found.");
		}
		String userName=tokenUtil.getUsernameFromToken(request.getHeader("x-user-onboard-token"));
		if(adminEmail.equals(userName)) {
			
			return new AuthSuccessResponse(200, request.getHeader("x-user-onboard-token"));
		}else {
			User user= userdetailsService.findUserByEmail(userName);
			if(user.getActive()) {
				return new AuthSuccessResponse(200, request.getHeader("x-user-onboard-token"));
			}else {
				new AuthFailureResponse(400,"User does not active.");
			}
		}
		
		return new AuthFailureResponse(400,"Email/Password do not match.");
	}
	
	
	@RequestMapping(path = "authRegSessiontoken.json", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AuthResponse validateSessionToken(HttpServletRequest request) {
		String tokenStr=request.getHeader("tempToken")==null?request.getParameter("tempToken"):request.getHeader("tempToken");
		System.out.println("Found token:"+tokenStr);
		if(tokenStr==null) {
			return new AuthFailureResponse(401,"User not found.");
		}
		RegistrationSessionToken token=repo.findByEmail(tokenStr);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -10);
		if(token!=null && !cal.getTime().after(token.getLastUpdatedDate())) {
			return new AuthSuccessResponse(200, tokenStr);
		}else {
			service.deleteToken(token);
		}
		
		return new AuthFailureResponse(400,"Token not found.");
	}
}
