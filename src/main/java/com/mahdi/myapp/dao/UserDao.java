package com.mahdi.myapp.dao;

import org.springframework.stereotype.Repository;

import com.mahdi.myapp.model.UserProfile;

@Repository
public class UserDao extends BaseDao<UserProfile> implements Dao<UserProfile> {
	
	public UserDao() {
		super(UserProfile.class);
	}

		

}
