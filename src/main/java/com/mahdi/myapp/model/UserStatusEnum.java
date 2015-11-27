package com.mahdi.myapp.model;

public enum UserStatusEnum {
	ACTIVE(1,"ACTIVE"),DEACTIVE(2,"DEACTIVE");
	int code;
	String label;
	
	private UserStatusEnum(int code, String label){
		this.code = code;
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}
	
	
}
