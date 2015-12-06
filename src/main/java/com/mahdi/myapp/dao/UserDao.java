package com.mahdi.myapp.dao;

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

@Repository
public class UserDao extends BaseDao<UserProfile> implements Dao<UserProfile> {
	
	public UserDao() {
		super(UserProfile.class);
	}

	public List<UserProfile> findUser(String keyword)throws DocException {

		try {
			Session session = getSession();
			@SuppressWarnings("unchecked")
			DetachedCriteria deCriteria = DetachedCriteria.forClass(typeParameterClass);
			Criteria criteria = deCriteria.getExecutableCriteria(session);
			criteria.add(Restrictions.eq("role", String.valueOf(2)));
			criteria.add(
					   Restrictions.disjunction()
					      .add(Restrictions.like("fullname", "%"+keyword+"%"))
					      .add(Restrictions.like("specialty", "%"+keyword+"%"))
					      .add(Restrictions.like("email", "%"+keyword+"%"))
					      .add(Restrictions.like("contact", "%"+keyword+"%"))
					);
			
			
			List<UserProfile> list = criteria.list();
			return (List<UserProfile>) list;
		} catch (HibernateException ex) {
			throw new DocException(HttpStatus.EXPECTATION_FAILED, ex);
		} catch (Exception ex) {
			throw new DocException(HttpStatus.FAILED_DEPENDENCY, ex);
		}
	
	}	
		

}
