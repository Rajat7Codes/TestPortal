/**
 * 
 */
package com.iceico.testportal.Service;

import java.util.List;

import com.iceico.testportal.Model.User;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 */
public interface UserService {

	User findById(int id);

	User findBySSO(String sso);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserBySSO(String sso);

	List<User> findAllUsers();

	boolean isUserSSOUnique(Integer id, String sso);

}