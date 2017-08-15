/******************************************************************
 *  
 * This code is for the Do Process Practice Management (DPPM) project.
 *
 * 
 * © 2016, Do Process Practice Management All rights reserved. 
 * 
 * 
 ******************************************************************/

package com.kafeneio.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kafeneio.model.RegistrationModel;

/**
 * <h1>Security Utils</h1> Utility class for Spring Security, application can
 * call static methods of this class to get logged in userProfile.
 * 
 */
public final class SecurityUtils {

	/**
	 * Instantiates a new security utils.
	 */
	private SecurityUtils() {
	}

	/**
	 * Get the login of the current user.
	 *
	 * @return the login of the current user
	 */
	public static String getCurrentUserLogin() {

		return getCurrentUserProfile().getLoginModel().getEmail();
	}

	/**
	 * Get the login of the current user profile.
	 *
	 * @return the login of the current user
	 */
	public static RegistrationModel getCurrentUserProfile() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		RegistrationModel springSecurityUserprofile = null;
		if (authentication.getPrincipal() instanceof RegistrationModel) {
			springSecurityUserprofile = (RegistrationModel) authentication.getPrincipal();
		}
		return springSecurityUserprofile;
	}

}
