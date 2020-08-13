/**
 * 
 */
package com.onboard.sso.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.onboard.sso.service.SessionUtils;
import com.onboard.sso.util.JwtTokenUtil;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 12-Aug-2020
 */
//@CrossOrigin
@RestController
public class LogoutController {
	
	
	@Autowired
	private SessionUtils sesionUtil;
	
	@RequestMapping(path = "/logout.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object doLogout(HttpServletRequest request) {
		sesionUtil.blackListUserToken(request.getHeader("x-user-onboard-token"));
		Map<String, String> response=new HashMap<String, String>();
		response.put("message", "Logout successful!");
		response.put("statusCode", "200");
		return response;
	}
	
}
