/**
 * 
 */
package com.onboard.sso.model;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 11-Aug-2020
 */
public class AuthSuccessResponse implements AuthResponse{
	
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

	
	private String token;
	
	/**
	 * 
	 */
	public AuthSuccessResponse(int code,String token) {
		super();
		this.setStatusCode(code);this.setToken(token);
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
