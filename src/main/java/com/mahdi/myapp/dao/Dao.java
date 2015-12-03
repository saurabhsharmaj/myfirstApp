package com.mahdi.myapp.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.mahdi.myapp.exception.DocException;


public interface Dao<T> {
	
	public int insertRow(T t) throws HibernateException;

	public List<T> getList() throws DocException ;

	public T getRowById(int id) throws DocException ;

	public int updateRow(T t) throws DocException ;

	public int deleteRow(int id) ;
	
}
