package com.mahdi.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mahdi.myapp.service.IUserService;

@Controller
public class DefaultController {
	
	private final Logger log = LoggerFactory.getLogger(DefaultController.class);
	
	@Autowired
	IUserService userService;
		
	@RequestMapping(value={"/403","/error"}, method = RequestMethod.GET)
	public ModelAndView adminHomePage(){
		
		ModelAndView mv = new ModelAndView("errorPage");
		mv.addObject("error_message", "Internal Server error.");
		return mv;
	}
}
