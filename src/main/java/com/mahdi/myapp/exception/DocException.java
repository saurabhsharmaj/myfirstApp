package com.mahdi.myapp.exception;

import org.springframework.http.HttpStatus;

public class DocException extends RuntimeException {

	private static final long serialVersionUID = -7095311298757576361L;
	
	HttpStatus status;
	Exception ex;
	
	public DocException(){
		super();
	} 

	public DocException(String msg){
		super(msg);
	}

	public DocException(HttpStatus status, Exception ex) {
		super(status.getReasonPhrase(), ex);
		this.status = status;
		this.ex = ex;		
	}

}