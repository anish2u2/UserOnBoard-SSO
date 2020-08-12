/**
 * 
 */
package com.onboard.sso.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 12-Aug-2020
 */
@Entity
@Table(name = "REGISTRATION_SESSION_TOKEN")
public class RegistrationSessionToken {
	
	@Id
	@SequenceGenerator(initialValue = 1,allocationSize = 1,sequenceName = "REGISTRATION_SESSION_TOKEN_INCREAMENTOR", name = "REGISTRATION_SESSION_TOKEN_INCREAMENTOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGISTRATION_SESSION_TOKEN_INCREAMENTOR")
	private Long id;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "session_id")
	private String sessionId;
	
	@Column(name = "last_updated_date")
	private Date lastUpdatedDate;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "status")
	private String status;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the lastUpdatedDate
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	/**
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
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

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
