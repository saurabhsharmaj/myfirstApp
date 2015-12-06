package com.mahdi.myapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mahdi.myapp.model.UserStatusEnum;
import com.mahdi.myapp.service.IUserRoleService;
import com.mahdi.myapp.service.IUserService;
import com.mahdi.myapp.util.DocConstant;

@Controller
@RequestMapping("admin")
public class AdminController {

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
	public ModelAndView getProfile(HttpSession session){
		ModelAndView mv = new ModelAndView("adminProfilePage");
		mv.addObject("userRoles", userRoleService.getList());
		mv.addObject("status", new UserStatusEnum[]{UserStatusEnum.ACTIVE, UserStatusEnum.DEACTIVE});
		mv.addObject("userproflie",session.getAttribute(DocConstant.USERPROFILE));
		return mv;
		
	}
	
	@RequestMapping(value={"searchUser"}, method = RequestMethod.POST)
	public ModelAndView searchUser(HttpSession session, @RequestParam("keyword") String keyword){
		System.out.println(keyword);
		ModelAndView mv = new ModelAndView("searchUserPage");		
		mv.addObject("searchResults", userService.findUser(keyword));
		return mv;
	}
	
	@RequestMapping(value={"listUsers"}, method = RequestMethod.GET)
	public ModelAndView listUsers(HttpSession session){
		ModelAndView mv = new ModelAndView("manageUserPage");
		mv.addObject("userRoles", userService.getList());
		return mv;
		
	}
}