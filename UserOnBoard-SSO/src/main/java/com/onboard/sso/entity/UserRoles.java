/**
 * 
 */
package com.onboard.sso.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 *         10-Aug-2020
 */
@Entity
@Table(name = "USER_ROLES")
public class UserRoles {

	@EmbeddedId
	private UserRolesPk pk;

	/**
	 * @return the pk
	 */
	public UserRolesPk getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(UserRolesPk pk) {
		this.pk = pk;
	}

}
