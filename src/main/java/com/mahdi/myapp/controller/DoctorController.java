package com.mahdi.myapp.controller;

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
@RequestMapping("doctor")
public class DoctorController {
	
	private final Logger log = LoggerFactory.getLogger(DoctorController.class);
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IUserRoleService userRoleService;
	
	@RequestMapping(value={"/"}, method = RequestMethod.GET)
	public ModelAndView doctorHomePage(){
		ModelAndView mv = new ModelAndView("doctorPage");
		mv.addObject("title", "This is my Home Page.");
		return mv;
	}
	
	@RequestMapping(value={"myprofile"}, method = RequestMethod.GET)
	public ModelAndView getProfile(HttpSession session) throws DocException{
		ModelAndView mv = new ModelAndView("doctorProfilePage");
		mv.addObject("userRoles", userRoleService.getList());
		mv.addObject("status", new UserStatusEnum[]{UserStatusEnum.ACTIVE, UserStatusEnum.DEACTIVE});
		mv.addObject("userproflie",DocUtils.getLoggedInUserProfile(session,userService));
		return mv;
		
	}
	
	
	@RequestMapping(value={"searchPatient"}, method = RequestMethod.POST)
	public ModelAndView searchDoctor(HttpSession session, @RequestParam("keyword") String keyword) throws DocException{
		System.out.println(keyword);
		ModelAndView mv = new ModelAndView("searchPatientPage");		
		mv.addObject("patientList", userService.findAppointment(keyword, true));
		return mv;
	}
	
	@RequestMapping(value={"appointmentlist"}, method = RequestMethod.GET)
	public ModelAndView appointmentlist(HttpSession session) throws DocException{
		UserProfile user = DocUtils.getLoggedInUserProfile(session,userService);
		ModelAndView mv = new ModelAndView("appointmentListDoctorPage");
		mv.addObject("appointmentList", userService.getAppointmentList(user.getId(), true));
		return mv;
		
	}
}
