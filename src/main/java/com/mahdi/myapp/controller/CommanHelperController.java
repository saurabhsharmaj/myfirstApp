package com.mahdi.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mahdi.myapp.model.DocResponse;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.service.IUserService;

@RestController
public class CommanHelperController {

	private final Logger log = LoggerFactory.getLogger(CommanHelperController.class);
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(value="isEmailExist/{emailId:.+}",method = RequestMethod.GET)
	public @ResponseBody DocResponse isEmailExist(@PathVariable String emailId){
		UserProfile profile = null;//userService.searchByColumn("email",emailId);
		if(profile ==  null){
			return new DocResponse(HttpStatus.OK, "Emailid not exist.", null);
		} else {
			return new DocResponse(HttpStatus.OK, "Emailid is exist.",null);
		}
		
	}
}
