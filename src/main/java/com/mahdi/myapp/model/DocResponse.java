package com.mahdi.myapp.model;

import org.springframework.http.HttpStatus;

public class DocResponse {
	
	HttpStatus status;
	Object data;
	String errorMsg;

	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public DocResponse(){
		
	}

	public DocResponse(HttpStatus status, Object data, String errorMsg){
		this.status = status;
		this.data = data;
		this.errorMsg = errorMsg;
	}

}
