/**
 * 
 */
package com.onboard.sso.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 * 13-Aug-2020
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Secured("ROLE_ADMIN")
public @interface AdminPermission {

}
