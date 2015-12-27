package com.mahdi.myapp.webservice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.DocResponse;
import com.mahdi.myapp.service.IUserService;

@RestController
@RequestMapping("/rest")
public class UserValidationController {

	@Autowired
	IUserService userService;
	
	@RequestMapping("test")
	public @ResponseBody DocResponse test() throws DocException{
		return new DocResponse(HttpStatus.OK,new Date(),null);
	}
	
	@RequestMapping(value="isEmailExist/{emailId}/", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody DocResponse isEmailExist(@PathVariable String emailId) throws DocException{		
		return new DocResponse(HttpStatus.OK, userService.isEmailExist(emailId),null);
		
	}
	
	@RequestMapping(value="isUserNameExist/{username}/", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody DocResponse isUserNameExist(@PathVariable String username) throws DocException{		
		return new DocResponse(HttpStatus.OK, userService.isUserNameExist(username),null);
		
	}
}
