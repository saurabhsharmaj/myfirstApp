package com.mahdi.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahdi.myapp.dao.SpecializationDao;
import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.Specialization;

@Service
@Transactional
public class SpecializationService implements ISpecializationService {

	@Autowired
	SpecializationDao specializationDao;
	
	public int insertRow(Specialization t) throws DocException {
		return specializationDao.insertRow(t);
	}

	public List<Specialization> getList() throws DocException {
		return specializationDao.getList();
	}

	public Specialization getRowById(int id) throws DocException {
		return specializationDao.getRowById(id);
	}

	public Specialization getRowByName(String columnName, String value) {
		return specializationDao.getRowByColumnName(columnName, value);
	}

	public int updateRow(Specialization t) throws DocException {
		return specializationDao.updateRow(t);
	}

	public int deleteRow(int id) throws DocException {
		return specializationDao.deleteRow(id);
	}

	public List<Specialization> getRowsByName(String columnName, String value) {
		return specializationDao.getRowsByColumnName(columnName, value);
	}

}
