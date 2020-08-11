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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 10-Aug-2020
 */
@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {
	
	@Id
	@SequenceGenerator(initialValue = 1,allocationSize = 1,sequenceName = "USER_DETAILS_INCREAMENTOR", name = "USER_DETAILS_INCREAMENTOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_DETAILS_INCREAMENTOR")
	@Column(name = "USER_DETAILS_ID")
	private Long id;
	
	@Column(name = "PHONE_NUMBER")
	private Long mobileNumber;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "BIRTHDAY")
	private Date birthday;
	
	@OneToOne
	@JoinColumn(name = "REGISTRATION_ID")
	@Cascade(CascadeType.ALL)
	private Registration registration;

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
	 * @return the mobileNumber
	 */
	public Long getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the registration
	 */
	public Registration getRegistration() {
		return registration;
	}

	/**
	 * @param registration the registration to set
	 */
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	
	
	
}
