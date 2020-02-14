package com.iceico.testportal.DaoIMPL;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 * 
 */

public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	/*
	 * @Autowired private SessionFactory sessionFactory;
	 * 
	 * protected Session getSession() { return sessionFactory.getCurrentSession(); }
	 */

	@Autowired
	private EntityManager entityManager;

	private Session session = null;

	private Session getSession() {
		session = this.entityManager.unwrap(Session.class);
		return session;
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	@SuppressWarnings("deprecation")
	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

}