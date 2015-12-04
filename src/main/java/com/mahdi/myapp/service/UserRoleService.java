package com.mahdi.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahdi.myapp.dao.UserRoleDao;
import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.UserRole;

@Service
@Transactional
public class UserRoleService implements IUserRoleService {
		
	@Autowired
	UserRoleDao userRoleDao;
	
	public int insertRow(UserRole t) throws DocException {
		return userRoleDao.insertRow(t);
	}

	public List<UserRole> getList() throws DocException {
		return userRoleDao.getList();
	}

	public UserRole getRowById(int id) throws DocException {
		return userRoleDao.getRowById(id);
	}

	public int updateRow(UserRole t) throws DocException {
		return userRoleDao.updateRow(t);
	}

	public int deleteRow(int id) throws DocException {
		return userRoleDao.deleteRow(id);
	}

	public UserRole getRowByName(String columnName, String value) {		
		return userRoleDao.getRowByName(columnName, value);
	}
	
	
	public List<UserRole> getRoleExceptAdmin() throws DocException{
		return userRoleDao.getRoleExceptAdmin();
	}
}
