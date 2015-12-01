package com.mahdi.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mahdi.myapp.service.IUserService;

@Controller
public class UserController {

	@Autowired
	IUserService userService;
	
	@RequestMapping(value="addUser", method = RequestMethod.GET)
	public ModelAndView addUser(){
		ModelAndView mv = new ModelAndView("userPage");
		mv.addObject("user1",userService.getProfile(1));
		return mv;
	}
}
