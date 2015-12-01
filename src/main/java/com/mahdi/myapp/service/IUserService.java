package com.mahdi.myapp.service;

import com.mahdi.myapp.model.UserProfile;

public interface IUserService {
	public int addUser(UserProfile u);
	public int updateUser(UserProfile u);
	public UserProfile getProfile(int id);
}
