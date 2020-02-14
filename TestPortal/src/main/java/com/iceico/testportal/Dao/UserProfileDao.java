/**
 * 
 */
package com.iceico.testportal.Dao;

import java.util.List;

import com.iceico.testportal.Model.UserProfile;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 *
 */
public interface UserProfileDao {
	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);
}
