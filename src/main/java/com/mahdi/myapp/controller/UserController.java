package com.mahdi.myapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.model.UserStatusEnum;
import com.mahdi.myapp.service.IUserRoleService;
import com.mahdi.myapp.service.IUserService;
import com.mahdi.myapp.util.DocConstant;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	IUserService userService;
	
	@Autowired
	IUserRoleService userRoleService;
	
	@RequestMapping(value={"/"}, method = RequestMethod.GET)
	public ModelAndView userHomePage(){
		ModelAndView mv = new ModelAndView("userPage");
		mv.addObject("title", "This is my Home Page.");
		return mv;
	}
	
	@RequestMapping(value={"myprofile"}, method = RequestMethod.GET)
	public ModelAndView getProfile(HttpSession session){
		ModelAndView mv = new ModelAndView("userProfilePage");
		mv.addObject("userRoles", userRoleService.getList());
		mv.addObject("status", new UserStatusEnum[]{UserStatusEnum.ACTIVE, UserStatusEnum.DEACTIVE});
		mv.addObject("userproflie",session.getAttribute(DocConstant.USERPROFILE));
		return mv;
		
	}
	

	@RequestMapping(value={"saveAppointment/{id}"}, method = RequestMethod.GET)
	public ModelAndView saveAppointment(@PathVariable Integer id, HttpSession session){	
		ModelAndView mv = new ModelAndView("appointmentSuccessPage");
		UserProfile user = (UserProfile) session.getAttribute(DocConstant.USERPROFILE);
		UserProfile doctor= userService.getRowById(id);
		Integer appointId = 1;//userService.saveAppointment(user, doctor);
		mv.addObject("doctor", doctor);
		mv.addObject("appointId",appointId);
		return mv;		
	}
	
	@RequestMapping(value={"appointmentlist"}, method = RequestMethod.GET)
	public ModelAndView appointmentlist(HttpSession session){
		UserProfile user = (UserProfile) session.getAttribute(DocConstant.USERPROFILE);
		ModelAndView mv = new ModelAndView("appointmentListPage");
		//mv.addObject("appointmentList", userService.getAppointmentListByUserId(user.getId()));
		return mv;
		
	}
	
	
	
	
}
