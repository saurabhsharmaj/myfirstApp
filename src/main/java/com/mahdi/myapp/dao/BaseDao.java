package com.mahdi.myapp.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Abstract class implement Dao interface. This class is responsible for
 * implementing all the method of Dao interface. This class call for perform
 * action on database.
 * 
 * @author SSharma
 * @param <T>
 *            the type of the value being boxed
 * @version 1.0
 */
@Repository
public abstract class BaseDao<T> implements Dao<T> {

	@Autowired
	SessionFactory sessionFactory;

	// pass the Class of the type parameter into the constructor of the generic
	// type
	Class<T> typeParameterClass;

	private final Logger log = LoggerFactory.getLogger(BaseDao.class);

	
	public BaseDao() {
		super();
	}

	// Constructor of BaseDao class accepting Class of the type parameter of
	// generic type
	public BaseDao(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
	}

	/**
	 * Method is used to insert data in database. and used Session interface for
	 * save and update data by calling methos of this interface.
	 * 
	 * @param <T> the type of the value being boxed
	 * @return id
	 * @throws hibernate exception
	 */
	public int insertRow(T t) throws HibernateException {
		Session session = getSession();
		session.saveOrUpdate(t);
		Serializable id = session.getIdentifier(t);
		return Integer.valueOf(id.toString());
	}

	
	

	protected Session getSession() throws HibernateException {
		return sessionFactory.getCurrentSession();
		// return sessionFactory.openSession();
	}

}
