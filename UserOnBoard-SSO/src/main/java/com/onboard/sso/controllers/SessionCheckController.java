/**
 * 
 */
package com.onboard.sso.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.onboard.sso.annotations.AdminPermission;
import com.onboard.sso.model.AuthFailureResponse;
import com.onboard.sso.model.SessionStatusResponse;
import com.onboard.sso.service.SessionUtils;
import com.onboard.sso.util.JwtTokenUtil;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 11-Aug-2020
 */

@RestController
public class SessionCheckController {

	@Autowired
	private SessionUtils sesionUtils;
	
	@Autowired
	private JwtTokenUtil tokenUtil;
	
	@RequestMapping(path = "/isActive.json", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object authenticate(HttpServletRequest request) {
		if (request.getHeader("x-user-onboard-token") == null && request.getParameter("x-user-onboard-token")==null) {
			return new AuthFailureResponse(401, "User not found.");
		}
		String token=request.getHeader("x-user-onboard-token")==null?request.getParameter("x-user-onboard-token"):request.getHeader("x-user-onboard-token");
		String userName = tokenUtil.getUsernameFromToken(token);
		if(!sesionUtils.checkItsValid(userName))
			new AuthFailureResponse(400, "Session time out.");
		return new SessionStatusResponse(true);
	}
	
	
	@AdminPermission
	@RequestMapping(path = "/listOfActiveUsers.json", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object activeUsersList(HttpServletRequest request) {
		Map<String, Object> response=new HashMap<>();
		response.put("userList",sesionUtils.getListOfActiveSession() );
		response.put("statusCode", 200);
		return response;
	}
	
}
