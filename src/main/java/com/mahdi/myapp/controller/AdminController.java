package com.mahdi.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.model.UserStatusEnum;
import com.mahdi.myapp.service.IUserRoleService;
import com.mahdi.myapp.service.IUserService;
import com.mahdi.myapp.util.DocConstant;
import com.mahdi.myapp.util.DocUtils;

@Controller
@RequestMapping("admin")
public class AdminController {

	private final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	IUserService userService;
		
	@Autowired
	IUserRoleService userRoleService;
	
	
	@RequestMapping(value={"/"}, method = RequestMethod.GET)
	public ModelAndView adminHomePage(){
		
		ModelAndView mv = new ModelAndView("adminPage");
		mv.addObject("title", "This is my Home Page.");
		return mv;
	}
	
	
	@RequestMapping(value={"myprofile"}, method = RequestMethod.GET)
	public ModelAndView getProfile(HttpSession session) throws DocException{
		ModelAndView mv = new ModelAndView("adminProfilePage");
		mv.addObject("userRoles", userRoleService.getList());
		mv.addObject("status", new UserStatusEnum[]{UserStatusEnum.ACTIVE, UserStatusEnum.DEACTIVE});
		mv.addObject("userproflie",DocUtils.getLoggedInUserProfile(session,userService));
		return mv;
		
	}
	
	@RequestMapping(value={"searchUser"}, method = RequestMethod.POST)
	public ModelAndView searchUser(HttpSession session, @RequestParam("keyword") String keyword) throws DocException{
		System.out.println(keyword);
		ModelAndView mv = new ModelAndView("searchUserPage");		
		mv.addObject("searchResults", userService.findUser(keyword));
		return mv;
	}
	
	@RequestMapping(value={"listUsers"}, method = RequestMethod.GET)
	public ModelAndView listUsers(HttpSession session) throws DocException{
		ModelAndView mv = new ModelAndView("manageUserPage");
		List<UserProfile> users = userService.getList();
		users.remove(DocUtils.getLoggedInUserProfile(session,userService));
		mv.addObject("userList",users);
		return mv;
		
	}
}
