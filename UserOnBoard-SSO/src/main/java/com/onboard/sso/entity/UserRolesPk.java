/**
 * 
 */
package com.onboard.sso.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 10-Aug-2020
 */
@Embeddable
public class UserRolesPk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 354351L;

	@Column(name  ="USER_ID")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "ROLE_ID")
	private Role role;

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
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
