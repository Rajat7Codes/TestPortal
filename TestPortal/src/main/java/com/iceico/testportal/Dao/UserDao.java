/**
 * 
 */
package com.iceico.testportal.Dao;

import java.util.List;

import com.iceico.testportal.Model.User;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 *
 */
public interface UserDao {

	User findById(int id);

	User findBySSO(String sso);

	void save(User user);

	void deleteBySSO(String sso);

	List<User> findAllUsers();

}
