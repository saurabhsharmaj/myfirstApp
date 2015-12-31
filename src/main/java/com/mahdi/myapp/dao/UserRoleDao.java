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
import com.mahdi.myapp.model.UserRoles;
import com.mahdi.myapp.util.DocConstant;

@Repository
public class UserRoleDao extends BaseDao<UserRoles>{
	
	public UserRoleDao() {
		super(UserRoles.class);
	}

	public List<UserRoles> getRoleExceptAdmin() throws DocException {

		try {
			Session session = getSession();
			@SuppressWarnings("unchecked")
			DetachedCriteria deCriteria = DetachedCriteria.forClass(typeParameterClass);
			Criteria criteria = deCriteria.getExecutableCriteria(session);
			criteria.add(Restrictions.not(Restrictions.in("code", new String[]{DocConstant.ROLE_ADMIN})));
			List<UserRoles> list = criteria.list();
			return (List<UserRoles>) list;
		} catch (HibernateException ex) {
			throw new DocException(HttpStatus.EXPECTATION_FAILED, ex);
		} catch (Exception ex) {
			throw new DocException(HttpStatus.FAILED_DEPENDENCY, ex);
		}
	
	}	

}
