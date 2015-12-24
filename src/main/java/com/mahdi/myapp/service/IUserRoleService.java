package com.mahdi.myapp.service;

import java.util.List;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.UserRoles;

public interface IUserRoleService extends IService<UserRoles> {

	public List<UserRoles> getRoleExceptAdmin() throws DocException;
}
