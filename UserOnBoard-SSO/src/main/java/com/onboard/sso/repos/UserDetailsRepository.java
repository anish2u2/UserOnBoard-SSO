/**
 * 
 */
package com.onboard.sso.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.onboard.sso.entity.User;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 11-Aug-2020
 */
@Repository
public interface UserDetailsRepository extends CrudRepository<User, Long>{
	
	@Query("from User where userName=:userName")
	public User fetchUser(String userName);
	
	@Query("from  User user where user.userDetails.emailId=:email")
	public User findUserBasedOnEmail(String email);
	
}
