package com.mahdi.myapp.dao;

import org.springframework.stereotype.Repository;

import com.mahdi.myapp.model.Specialization;

@Repository
public class SpecializationDao extends BaseDao<Specialization>{

	public SpecializationDao() {
		super(Specialization.class);
	}
}
