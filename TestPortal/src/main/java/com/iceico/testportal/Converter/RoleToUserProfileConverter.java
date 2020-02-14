package com.iceico.testportal.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.iceico.testportal.Model.UserProfile;
import com.iceico.testportal.Service.UserProfileService;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 *
 */

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {

	static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);

	@Autowired
	UserProfileService userProfileService;

	/**
	 * Gets UserProfile by Id
	 * 
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public UserProfile convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		UserProfile profile = userProfileService.findById(id);
		logger.info("Profile : {}", profile);
		return profile;
	}
}
