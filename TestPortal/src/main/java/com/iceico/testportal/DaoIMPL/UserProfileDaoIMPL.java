package com.iceico.testportal.DaoIMPL;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.iceico.testportal.Dao.UserProfileDao;
import com.iceico.testportal.Model.UserProfile;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 *
 */

@Repository("userProfileDao")
public class UserProfileDaoIMPL extends AbstractDao<Integer, UserProfile> implements UserProfileDao {

	public UserProfile findById(int id) {
		return getByKey(id);
	}

	public UserProfile findByType(String type) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("type", type));
		return (UserProfile) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("type"));
		return (List<UserProfile>) crit.list();
	}

}