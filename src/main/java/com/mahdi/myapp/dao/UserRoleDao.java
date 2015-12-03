package com.mahdi.myapp.dao;

import org.springframework.stereotype.Repository;

import com.mahdi.myapp.model.UserRole;

@Repository
public class UserRoleDao extends BaseDao<UserRole> implements Dao<UserRole> {
	
	public UserRoleDao() {
		super(UserRole.class);
	}	

}
