package com.mahdi.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mahdi.myapp.service.IUserService;

@Controller
public class DoctorController {

	@Autowired
	IUserService userService;
		

	@RequestMapping(value={"doctor"}, method = RequestMethod.GET)
	public ModelAndView doctorHomePage(){
		ModelAndView mv = new ModelAndView("doctorPage");
		mv.addObject("title", "This is my Home Page.");
		return mv;
	}
}
