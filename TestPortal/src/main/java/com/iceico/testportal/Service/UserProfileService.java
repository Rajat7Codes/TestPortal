/**
 * 
 */
package com.iceico.testportal.Service;

import java.util.List;

import com.iceico.testportal.Model.UserProfile;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 *
 */
public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);

	List<UserProfile> findAll();

}