package com.mahdi.myapp.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.mahdi.myapp.exception.DocException;

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
		session.save(t);
		Serializable id = session.getIdentifier(t);
		return Integer.valueOf(id.toString());
	}

	
	/**
	 * Override method for get list from DB. and used Detached criteria for execute query.
	 * @return list of generic type
	 * @throw exception
	 */
	@SuppressWarnings("unchecked")
	public List<T> getList() throws DocException {
		try {
			Session session = getSession();
			@SuppressWarnings("unchecked")
			DetachedCriteria criteria = DetachedCriteria
					.forClass(typeParameterClass);
			List<T> list = criteria.getExecutableCriteria(session).list();
			return (List<T>) list;
		} catch (HibernateException ex) {
			throw new DocException(HttpStatus.EXPECTATION_FAILED, ex);
		} catch (Exception ex) {
			throw new DocException(HttpStatus.FAILED_DEPENDENCY, ex);
		}
	}

	/**
	 * Method is used for get a row from DB. 
	 * @param id
	 * @return Generic type object
	 * @throws Exception
	 */
	public T getRowById(int id) throws DocException {
		Session session = getSession();
		DetachedCriteria criteria = DetachedCriteria
				.forClass(typeParameterClass);
		criteria.add(Restrictions.eq("id", id));
		T t = (T) criteria.getExecutableCriteria(session).uniqueResult();

		return t;
	}

	public T getRowByColumnName(String fieldName, String value) {
		Session session = getSession();
		DetachedCriteria criteria = DetachedCriteria
				.forClass(typeParameterClass);
		criteria.add(Restrictions.eq(fieldName, value));
		T t = (T) criteria.getExecutableCriteria(session).uniqueResult();
		return t;
	}
	
	public List<T> getRowsByColumnName(String fieldName, String value) {
		Session session = getSession();
		DetachedCriteria criteria = DetachedCriteria
				.forClass(typeParameterClass);
		criteria.add(Restrictions.eq(fieldName, value));
		List<T> t = (List<T>) criteria.getExecutableCriteria(session).list();
		return t;
	}
	/**
	 * Method is used to update data in database. and used Session interface for
	 * save and update data by calling methos of this interface.
	 * 
	 * @param <T> the type of the value being boxed
	 * @return id
	 * @throws hibernate exception
	 */
	public int updateRow(T t) throws DocException {
		try {
			Session session = getSession();
			session.saveOrUpdate(t);
			Serializable id = session.getIdentifier(t);
			return (Integer) id;
		} catch (HibernateException ex) {
			throw new DocException(HttpStatus.EXPECTATION_FAILED, ex);
		} catch (Exception ex) {
			throw new DocException(HttpStatus.FAILED_DEPENDENCY, ex);
		}

	}

	/**
	 * Delete row from DB according to id.
	 * @param id
	 * @throws Exception 
	 */
	public int deleteRow(int id) throws HibernateException {
		Session session = getSession();
		@SuppressWarnings("unchecked")
		T t = (T) session.load(typeParameterClass, id);
		session.delete(t);
		Serializable ids = session.getIdentifier(t);
		return (Integer) ids;
	}

	protected Session getSession() throws HibernateException {
		return sessionFactory.getCurrentSession();
		// return sessionFactory.openSession();
	}

	// abstract public List<T> searchByUserName(String queryString) throws
	// ByanException ;
}
