package com.iceico.testportal.ServiceIMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iceico.testportal.Dao.UserProfileDao;
import com.iceico.testportal.Model.UserProfile;
import com.iceico.testportal.Service.UserProfileService;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 * 
 */
@Service("userProfileService")
@Transactional
public class UserProfileServiceIMPL implements UserProfileService {

	@Autowired
	UserProfileDao dao;

	public UserProfile findById(int id) {
		return dao.findById(id);
	}

	public UserProfile findByType(String type) {
		return dao.findByType(type);
	}

	public List<UserProfile> findAll() {
		return dao.findAll();
	}
}