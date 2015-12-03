package com.mahdi.myapp.exception;

import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;

public class DocException extends RuntimeException {

	HttpStatus status;
	Exception ex;
	
	public DocException(){
		super();
	} 

	public DocException(String msg){
		super(msg);
	}

	public DocException(HttpStatus status, Exception ex) {
		this.status = status;
		this.ex = ex;		
	}

}