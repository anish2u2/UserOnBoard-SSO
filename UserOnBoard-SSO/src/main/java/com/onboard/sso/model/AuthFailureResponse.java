/**
 * 
 */
package com.onboard.sso.model;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 11-Aug-2020
 */
public class AuthFailureResponse implements AuthResponse{

	
	private int statusCode;

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	
	private String message;

	
	/**
	 * 
	 */
	public AuthFailureResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public AuthFailureResponse(int code,String messgae){
		super();
		this.setStatusCode(code);
		this.setMessage(messgae);
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
