package com.mahdi.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class LogoutController {   
 
	private final Logger log = LoggerFactory.getLogger(LogoutController.class);
	
   @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
   public String logoutSuccessfulPage(Model model) {
       model.addAttribute("title", "Logout");
       return "loginPage";
   } 
    
}