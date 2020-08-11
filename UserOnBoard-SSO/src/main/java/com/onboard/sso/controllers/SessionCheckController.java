/**
 * 
 */
package com.onboard.sso.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onboard.sso.model.LoginModel;
import com.onboard.sso.model.SessionStatusResponse;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 11-Aug-2020
 */

@Controller
public class SessionCheckController {

	@RequestMapping(path = "isActive.json", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public SessionStatusResponse authenticate() {
		
		return new SessionStatusResponse();
	}
	
}
