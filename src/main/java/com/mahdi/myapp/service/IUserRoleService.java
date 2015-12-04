package com.mahdi.myapp.service;

import java.util.List;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.UserRole;

public interface IUserRoleService extends IService<UserRole> {

	public List<UserRole> getRoleExceptAdmin() throws DocException;
}
