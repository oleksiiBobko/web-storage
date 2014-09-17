package com.bobko.album.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import java.lang.reflect.ParameterizedType;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

/**
 * Basic DAO operations dependent with Hibernate's specific classes
 * 
 * @see SessionFactory
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class HibernateDao<E, K extends Serializable> implements
		GenericDao<E, K> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Class<? extends E> daoType;

	@SuppressWarnings("unchecked")
	public HibernateDao() {
		daoType = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void add(E entity) {
		currentSession().save(entity);
	}

	@Override
	public void update(E entity) {
		currentSession().saveOrUpdate(entity);
	}

	@Override
	public void remove(E entity) {
		currentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E find(K key) {
		return (E) currentSession().get(daoType, key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> list() {
		return currentSession().createCriteria(daoType).list();
	}

	@SuppressWarnings("unchecked")
	public List<E> rankList(int shift, int count) {
		Criteria criteria = currentSession().createCriteria(daoType);
		criteria.setFirstResult(shift * count);
		criteria.setMaxResults(count);
		criteria.addOrder(Order.desc("created"));
		return criteria.list();
	}
}