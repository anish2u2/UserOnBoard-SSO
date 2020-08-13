/**
 * 
 */
package com.onboard.sso.model;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 11-Aug-2020
 */
public class SessionStatusResponse {
	
	private boolean active;
	
	/**
	 * 
	 */
	public SessionStatusResponse(boolean active) {
		// TODO Auto-generated constructor stub
		this.active=active;
	}
	
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
