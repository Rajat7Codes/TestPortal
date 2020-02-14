/**
 * 
 */
package com.iceico.testportal.Model;

import java.io.Serializable;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 *
 */
public enum UserProfileType implements Serializable {
	USER("USER"), ADMIN("ADMIN");

	String userProfileType;

	private UserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return userProfileType;
	}

}