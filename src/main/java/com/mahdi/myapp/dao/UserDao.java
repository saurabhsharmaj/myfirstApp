package com.mahdi.myapp.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mahdi.myapp.model.UserProfile;

@Repository
public class UserDao extends BaseDao<UserProfile> {

	public UserProfile getProfile(int id) {
		Session session = getSession();
		Criteria cri = session.createCriteria(UserProfile.class);
		cri.add(Restrictions.eq("id", id));
		return (UserProfile)cri.uniqueResult();		
	}
	
	
}
