/**
 * 
 */
package com.onboard.sso.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onboard.sso.dao.OnBoardDao;
import com.onboard.sso.entity.Role;
import com.onboard.sso.entity.User;
import com.onboard.sso.repos.UserDetailsRepository;



/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 11-Aug-2020
 */

@Service
public class UserDetailsServices implements UserDetailsService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsRepository  userDetailsRepo;
	
	@Autowired
	private OnBoardDao<Role> userRoleDao;
	
	@Value("${admin.email}")
	private String adminEmail;
	
	@Value("${admin.password}")
	private String adminPassword;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("loading==");
		if(adminEmail.equals(userName)) {
			return new org.springframework.security.core.userdetails.User(userName,adminPassword,prepareAdminAuthority());
		}
		Optional<com.onboard.sso.entity.User> user=Optional.ofNullable(userDetailsRepo.findUserBasedOnEmail(userName));
		System.out.println("loading==1");
		if(user.isPresent()) {
			System.out.println("loading==3");
			com.onboard.sso.entity.User userD=user.get();
			List<Role> roles= userRoleDao.findUserRole(userD.getId());
			roles.forEach((role)->System.out.println(role.getName()));
			return new org.springframework.security.core.userdetails.User(userName,userD.getPassword(),prepareAuthority(roles));
		}
		throw new UsernameNotFoundException("unable to find user with userName:"+userName);
	}
	
	public List<GrantedAuthority> prepareAuthority(List<Role> roles){
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		roles.forEach((role)->{
			SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority("ROLE_"+role.getName());
			System.out.println("User is having permission:"+role.getName());
			authorities.add(simpleGrantedAuthority);
		});
		return authorities;
	}
	
	public List<GrantedAuthority> prepareAdminAuthority(){
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return authorities;
	}
	
	@Transactional(readOnly = true)
	public User findUserByEmail(String email) {
		return userDetailsRepo.findUserBasedOnEmail(email);
	}
	
}
