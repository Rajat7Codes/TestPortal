package com.iceico.testportal.ServiceIMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iceico.testportal.Dao.UserDao;
import com.iceico.testportal.Model.User;
import com.iceico.testportal.Service.UserService;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 *
 */
@Service("userService")
@Transactional
public class UserServiceIMPL implements UserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * public User findById(int id) { return dao.findById(id); }
	 * 
	 * public User findBySSO(String sso) { User user = dao.findBySSO(sso); return
	 * user; }
	 * 
	 * public void saveUser(User user) {
	 * user.setPassword(passwordEncoder.encode(user.getPassword())); dao.save(user);
	 * }
	 */
	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with proper
	 * values within transaction. It will be updated in db once transaction ends.
	 */
	/*
	 * public void updateUser(User user) { User entity = dao.findById(user.getId());
	 * if(entity!=null){ entity.setSsoId(user.getSsoId());
	 * if(!user.getPassword().equals(entity.getPassword())){
	 * entity.setPassword(passwordEncoder.encode(user.getPassword())); }
	 * entity.setFirstName(user.getFirstName());
	 * entity.setLastName(user.getLastName()); entity.setEmail(user.getEmail());
	 * entity.setUserProfiles(user.getUserProfiles()); } }
	 */

	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

	/*
	 * public List<User> findAllUsers() { return dao.findAllUsers(); }
	 */

	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = findBySSO(sso);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

	@Override
	public User findById(int id) {
		return dao.findById(id);
	}

	@Override
	public User findBySSO(String sso) {
		User user = dao.findBySSO(sso);
		return user;
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		String ssoId = user.getSsoId();
		ssoId = ssoId.replaceAll(",", "");
		user.setSsoId(ssoId.trim());
		dao.save(user);
	}

	@Override
	public void updateUser(User user) {

		User entity = dao.findById(user.getId());
		if (entity != null) {

			entity.setSsoId(user.getSsoId());
			if (!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
			entity.setMobileNumber(user.getMobileNumber());
			entity.setDescription(user.getDescription());
		}

	}

	@Override
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

}