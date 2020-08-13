/**
 * 
 */
package com.onboard.sso.model;

import java.util.Date;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 13-Aug-2020
 */
public class ActiveSessionResponse {
	
	private String userName;
	
	private String token;
	
	private Date lastAccessTime;
	
	
	
	/**
	 * @return the lastAccessTime
	 */
	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	/**
	 * @param lastAccessTime the lastAccessTime to set
	 */
	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	/**
	 * 
	 */
	public ActiveSessionResponse(String userName,String token,Date lastAcDate) {
		this.userName=userName;
		this.token=token;
		this.lastAccessTime=lastAcDate;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
