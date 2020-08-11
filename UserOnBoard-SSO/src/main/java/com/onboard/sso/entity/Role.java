/**
 * 
 */
package com.onboard.sso.entity;

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
 * 10-Aug-2020
 */
@Entity
@Table(name = "ROLE")
public class Role {
	
	public static final String ADMIN_ROLE="ADMIN";
	
	public static final String USER_ROLE="USER";
	
	@Id
	@SequenceGenerator(initialValue = 1,allocationSize = 1,sequenceName = "ROLE_INCREAMENTOR", name = "ROLE_INCREAMENTOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_INCREAMENTOR")
	@Column(name = "ROLE_ID")
	private Long id;
	
	@Column(name = "ROLE_NAME")
	private String name;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
