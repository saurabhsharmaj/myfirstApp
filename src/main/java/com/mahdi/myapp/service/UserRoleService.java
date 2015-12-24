package com.mahdi.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahdi.myapp.dao.UserRoleDao;
import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.UserRoles;

@Service
@Transactional
public class UserRoleService implements IUserRoleService {
		
	@Autowired
	UserRoleDao userRoleDao;
	
	public int insertRow(UserRoles t) throws DocException {
		return userRoleDao.insertRow(t);
	}

	public List<UserRoles> getList() throws DocException {
		return userRoleDao.getList();
	}

	public UserRoles getRowById(int id) throws DocException {
		return userRoleDao.getRowById(id);
	}

	public int updateRow(UserRoles t) throws DocException {
		return userRoleDao.updateRow(t);
	}

	public int deleteRow(int id) throws DocException {
		return userRoleDao.deleteRow(id);
	}

	public UserRoles getRowByName(String columnName, String value) {		
		return userRoleDao.getRowByColumnName(columnName, value);
	}
	
	
	public List<UserRoles> getRoleExceptAdmin() throws DocException{
		return userRoleDao.getRoleExceptAdmin();
	}
}
