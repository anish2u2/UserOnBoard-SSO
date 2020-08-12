/**
 * 
 */
package com.onboard.sso.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.onboard.sso.entity.Role;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 *         11-Aug-2020
 */
@Component
public class OnBoardDao<T> extends HibernateDaoSupport {

	@Autowired
	public void injectSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<Role> findUserRole(Long userId) {

		return getHibernateTemplate().execute(new HibernateCallback<List<Role>>() {

			@Override
			public List<Role> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(
						"select role_id,name from role where role_id in (select distinct role_id from user_details where user_id=:userId)");
				query.setParameter("userId", userId);
				List<Object[]> results = query.getResultList();
				List<Role> roles = new ArrayList<Role>();
				results.forEach((result) -> {
					Role role = new Role();
					role.setId(Long.valueOf((String) result[0]));
					role.setName((String) result[1]);
				});
				return null;
			}
		});
	}

}
