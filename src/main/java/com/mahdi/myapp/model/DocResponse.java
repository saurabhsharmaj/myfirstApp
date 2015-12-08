package com.mahdi.myapp.model;

import org.springframework.http.HttpStatus;

public class DocResponse {
	
	HttpStatus status;
	Object data;
	String errorMsg;

	public DocResponse(){
		
	}

	public DocResponse(HttpStatus status, Object data, String errorMsg){
		this.status = status;
		this.data = data;
		this.errorMsg = errorMsg;
	}

}
