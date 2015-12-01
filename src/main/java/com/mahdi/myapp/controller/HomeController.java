package com.mahdi.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.model.UserRole;
import com.mahdi.myapp.model.UserStatusEnum;
import com.mahdi.myapp.service.IUserService;

@Controller
public class HomeController {

	@Autowired
	IUserService userService;
	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value={"/","home"}, method = RequestMethod.GET)
	public ModelAndView homePage(){
		log.info("####################### debug ##################");
		ModelAndView mv = new ModelAndView("homePage");
		mv.addObject("title", "This is my Home Page.");
		return mv;
	}
	
	
	@RequestMapping(value={"admin"}, method = RequestMethod.GET)
	public ModelAndView adminHomePage(){
		
		ModelAndView mv = new ModelAndView("adminPage");
		mv.addObject("title", "This is my Home Page.");
		return mv;
	}
	
	@RequestMapping(value={"doctor"}, method = RequestMethod.GET)
	public ModelAndView doctorHomePage(){
		ModelAndView mv = new ModelAndView("doctorPage");
		mv.addObject("title", "This is my Home Page.");
		return mv;
	}
	
	@RequestMapping(value={"user"}, method = RequestMethod.GET)
	public ModelAndView userHomePage(){
		ModelAndView mv = new ModelAndView("userPage");
		mv.addObject("title", "This is my Home Page.");
		return mv;
	}
	
	
	@RequestMapping(value={"register"}, method = RequestMethod.GET)
	public ModelAndView register(){
		ModelAndView mv = new ModelAndView("registrationPage");
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		userRoleList.add(new UserRole(1,"ROLE_ADMIN"));
		userRoleList.add(new UserRole(2,"ROLE_DOC"));
		userRoleList.add(new UserRole(3,"ROLE_PATIENT"));
		mv.addObject("user", new UserProfile());
		mv.addObject("userRoles", userRoleList);
		mv.addObject("status", new UserStatusEnum[]{UserStatusEnum.ACTIVE, UserStatusEnum.DEACTIVE});
		return mv;
	}
	
	@RequestMapping(value={"user/signup"}, method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") UserProfile p){
		
		if(p.getId() == null){
			//new person, add it
			this.userService.addUser(p);
		}else{
			//existing person, call update
			this.userService.updateUser(p);
		}
		
		return "redirect:/loginPage";
		
	}
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public ModelAndView signinPage(){
		ModelAndView mv = new ModelAndView("loginPage");
		return mv;
	}
}
