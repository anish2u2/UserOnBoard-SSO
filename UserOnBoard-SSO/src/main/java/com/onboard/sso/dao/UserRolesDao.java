/**
 * 
 */
package com.onboard.sso.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Component;

import com.onboard.sso.entity.UserRoles;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 11-Aug-2020
 */

@Component
public class UserRolesDao<Role> extends OnBoardDao<Role>{
	
	
	public List<Role> findUserRole(Long userId) {
		
		return getHibernateTemplate().execute(new HibernateCallback<List<Role>>() {

			@Override
			public List<Role> doInHibernate(Session session) throws HibernateException {
				
				return null;
			}
		});
	}	
}
