/**
 * 
 */
package com.onboard.sso.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 11-Aug-2020
 */
@Component
public class OnBoardDao<T> extends HibernateDaoSupport {

	@Autowired
	public void injectSessionFactory(SessionFactory factory){
		super.setSessionFactory(factory);
	}
	
	
}
