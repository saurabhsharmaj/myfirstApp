package com.mahdi.myapp.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.UserProfile;
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
		return mv;
	}
	
	@RequestMapping(value={"myprofile"}, method = RequestMethod.GET)
	public ModelAndView getProfile(HttpSession session) throws DocException{
		ModelAndView mv = new ModelAndView("doctorViewProfilePage");		
		mv.addObject("userproflie",DocUtils.getLoggedInUserProfile(session,userService));
		return mv;
		
	}
	
	@RequestMapping(value={"editViewProfile"}, method = RequestMethod.GET)
	public ModelAndView editViewProfile(HttpSession session) throws DocException{
		ModelAndView mv = new ModelAndView("doctorProfilePage");		
		mv.addObject("userproflie",DocUtils.getLoggedInUserProfile(session,userService));
		return mv;
		
	}
	
	@RequestMapping(value= "updateProfile", method = RequestMethod.POST)
	public String updateUser(HttpSession session, @ModelAttribute("user") UserProfile userprofile) throws DocException{
		UserProfile savedProfile = userService.getRowById(userprofile.getId());
		
		if(StringUtils.isNotEmpty(userprofile.getFullname())){
			savedProfile.setFullname(userprofile.getFullname());
		}
		
		if(StringUtils.isNotEmpty(userprofile.getSpecialty())){
			savedProfile.setSpecialty(userprofile.getSpecialty());
		}
		
		if(savedProfile.getAge() != userprofile.getAge()){
			savedProfile.setAge(userprofile.getAge());
		}
		
		if(savedProfile.getExpirence() != userprofile.getExpirence()){
			savedProfile.setExpirence(userprofile.getExpirence());
		}
		
		if(StringUtils.isNotEmpty(userprofile.getEmail())){
			savedProfile.setEmail(userprofile.getEmail());
		}
		
		if(StringUtils.isNotEmpty(userprofile.getContact())){
			savedProfile.setContact(userprofile.getContact());
		}
		
		if(StringUtils.isNotEmpty(userprofile.getUsername())){
			savedProfile.setUsername(userprofile.getUsername());
		}
		
		if(StringUtils.isNotEmpty(userprofile.getPassword())){
			savedProfile.setPassword(userprofile.getPassword());
		}
		
		if(StringUtils.isNotEmpty(userprofile.getSummary())){
			savedProfile.setSummary(userprofile.getSummary());
		}
		
		userService.insertRow(savedProfile);	
		session.setAttribute(DocConstant.USERPROFILE, savedProfile);		
				
		return "redirect:/doctor/myprofile";
		

	}
	
	@RequestMapping(value={"searchPatient"}, method = RequestMethod.POST)
	public ModelAndView searchPatient(HttpSession session, @RequestParam("keyword") String keyword) throws DocException{
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
