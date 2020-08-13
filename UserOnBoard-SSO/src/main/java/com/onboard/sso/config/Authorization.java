/**
 * 
 */
package com.onboard.sso.config;

import java.util.Collection;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.prepost.PreInvocationAuthorizationAdvice;
import org.springframework.security.access.prepost.PreInvocationAuthorizationAdviceVoter;
import org.springframework.security.core.Authentication;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 13-Aug-2020
 */
public class Authorization extends PreInvocationAuthorizationAdviceVoter{

	/**
	 * @param pre
	 */
	public Authorization(PreInvocationAuthorizationAdvice pre) {
		super(pre);
	}
	
	@Override
	public int vote(Authentication authentication, MethodInvocation method, Collection<ConfigAttribute> attributes) {
		// TODO Auto-generated method stub
		return super.vote(authentication, method, attributes);
	}

}
