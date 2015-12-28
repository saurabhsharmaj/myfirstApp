package com.mahdi.myapp.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.util.DocConstant;

@Repository
public class UserDao extends BaseDao<UserProfile> implements Dao<UserProfile> {

	public UserDao() {
		super(UserProfile.class);
	}
	
	

	@Override
	public int insertRow(UserProfile t) throws HibernateException {
		Session session = getSession();		
		session.saveOrUpdate(t);
		Serializable id = session.getIdentifier(t);
		return Integer.valueOf(id.toString());
	}



	public List<UserProfile> findUser(String keyword)throws DocException {

		try {
			Session session = getSession();
			@SuppressWarnings("unchecked")
			DetachedCriteria deCriteria = DetachedCriteria.forClass(typeParameterClass);
			Criteria criteria = deCriteria.getExecutableCriteria(session);
			criteria.add(Restrictions.eq("u.code", DocConstant.ROLE_DOCTOR));
			criteria.add(
					Restrictions.disjunction()
					.add(Restrictions.like("fullname", "%"+keyword+"%"))
					.add(Restrictions.like("email", "%"+keyword+"%"))
					.add(Restrictions.like("contact", "%"+keyword+"%"))
					);

			criteria.createAlias("userRoles", "u");
			List<UserProfile> list = criteria.list();
			return (List<UserProfile>) list;
		} catch (HibernateException ex) {
			throw new DocException(HttpStatus.EXPECTATION_FAILED, ex);
		} catch (Exception ex) {
			throw new DocException(HttpStatus.FAILED_DEPENDENCY, ex);
		}

	}

	public UserProfile validate(UserProfile userProfile) throws DocException {
		try {
			Session session = getSession();
			@SuppressWarnings("unchecked")
			DetachedCriteria deCriteria = DetachedCriteria.forClass(typeParameterClass);
			Criteria criteria = deCriteria.getExecutableCriteria(session);
			criteria.add(Restrictions.eq("u.code", DocConstant.ROLE_PATIENT));
			criteria.add(Restrictions.eq("username", userProfile.getUsername()).ignoreCase());
			criteria.add(Restrictions.eq("password", userProfile.getPassword()));
			criteria.createAlias("userRoles", "u");
			UserProfile profile = (UserProfile) criteria.uniqueResult();
			return profile;
		} catch (HibernateException ex) {
			throw new DocException(HttpStatus.EXPECTATION_FAILED, ex);
		} catch (Exception ex) {
			throw new DocException(HttpStatus.FAILED_DEPENDENCY, ex);
		}
	}
	
	@Override
	public UserProfile getRowByColumnName(String fieldName, String value) {
		Session session = getSession();
		DetachedCriteria criteria = DetachedCriteria
				.forClass(typeParameterClass);
		criteria.add(Restrictions.eq(fieldName, value));
		UserProfile t = (UserProfile) criteria.getExecutableCriteria(session).uniqueResult();
		return t;
	}

}
