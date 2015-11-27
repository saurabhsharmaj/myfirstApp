package com.mahdi.myapp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahdi.myapp.dao.UserDao;
import com.mahdi.myapp.model.UserProfile;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired
	UserDao dao;
	
	public int addUser(UserProfile u) {
		return dao.insertRow(u);
	}

	public int updateUser(UserProfile u) {
		return updateUser(u);
	}

}
