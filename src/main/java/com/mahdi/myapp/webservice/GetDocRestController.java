package com.mahdi.myapp.webservice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.DocResponse;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.service.IUserService;

@RestController
@RequestMapping("/rest")
public class GetDocRestController {

	@Autowired
	IUserService userService;
	
	@RequestMapping("test")
	public @ResponseBody DocResponse test() throws DocException{
		return new DocResponse(HttpStatus.OK,new Date(),null);
	}
	
	@RequestMapping("getProfile/all")
	public @ResponseBody List<UserProfile> getProfile() throws DocException{
		List<UserProfile> list = userService.getList();
		/*HibernateAwareObjectMapper mapper = new HibernateAwareObjectMapper();		
		System.out.println(mapper.valueToTree(list));*/
		return list;
	}
	
	
	@RequestMapping("getProfile/get/{id}")
	public @ResponseBody UserProfile getProfile(@PathVariable int id) throws DocException{
		UserProfile profile = userService.getRowById(id);
		return profile;
	}
	
}
