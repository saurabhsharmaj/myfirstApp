package com.mahdi.myapp.service;

import java.util.List;

import com.mahdi.myapp.model.UserProfile;

public interface IUserService extends IService<UserProfile> {

	List<UserProfile> findUser(String keyword);	

}
