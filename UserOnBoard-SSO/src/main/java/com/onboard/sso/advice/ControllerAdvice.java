/**
 * 
 */
package com.onboard.sso.advice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 13-Aug-2020
 */
@RestControllerAdvice
public class ControllerAdvice {
	
	private org.slf4j.Logger logger=LoggerFactory.getLogger(ControllerAdvice.class);
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Object> returnResponse(Throwable error){
		logger.error("Exxception occured", error);
		Map<String , String> resposne=new HashMap<String, String>();
		resposne.put("statusCode", "500");
		resposne.put("message", "Unable to process request.");
		return new ResponseEntity<Object>(resposne, HttpStatus.OK);
	}
	
}
