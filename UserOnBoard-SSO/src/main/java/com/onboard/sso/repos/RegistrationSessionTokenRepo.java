/**
 * 
 */
package com.onboard.sso.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.onboard.sso.entity.RegistrationSessionToken;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 12-Aug-2020
 */
public interface RegistrationSessionTokenRepo extends CrudRepository<RegistrationSessionToken, Long>{

	@Query("from RegistrationSessionToken where active=true and emailId=:emailId")
	public RegistrationSessionToken findByEmail(String emailId);
	
	@Query("from RegistrationSessionToken where active=true and sessionId=:sessionId")
	public RegistrationSessionToken findBySessionId(String sessionId);
	
	
}
