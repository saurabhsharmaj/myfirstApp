package com.mahdi.myapp.dao;

import com.mahdi.myapp.exception.DocException;


public interface Dao<T> {
	
	public int insertRow(T t) throws DocException;
	
}
