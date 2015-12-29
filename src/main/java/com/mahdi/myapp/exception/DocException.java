package com.mahdi.myapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class DocException extends Exception {

	private final Logger log = LoggerFactory.getLogger(DocException.class);
	
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
		log.error("error: ",ex);
	}
	
	public DocException(String message, Exception ex) {
		super(message, ex);		
		this.ex = ex;
		log.error("error: ",ex);
	}

}