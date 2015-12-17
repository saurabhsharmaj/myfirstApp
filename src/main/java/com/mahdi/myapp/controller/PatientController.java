package com.mahdi.myapp.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.model.UserStatusEnum;
import com.mahdi.myapp.service.IUserRoleService;
import com.mahdi.myapp.service.IUserService;
import com.mahdi.myapp.util.DocUtils;

@Controller
@RequestMapping("user")
public class PatientController {

	private final Logger log = LoggerFactory.getLogger(PatientController.class);

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
	public ModelAndView getProfile(HttpSession session) throws DocException{
		ModelAndView mv = new ModelAndView("userProfilePage");
		mv.addObject("userRoles", userRoleService.getList());
		mv.addObject("status", new UserStatusEnum[]{UserStatusEnum.ACTIVE, UserStatusEnum.DEACTIVE});
		UserProfile userProfile = DocUtils.getLoggedInUserProfile(session,userService);
		mv.addObject("userproflie",userProfile);
		return mv;

	}

	@RequestMapping(value={"searchDoctor"}, method = RequestMethod.POST)
	public ModelAndView searchDoctor(HttpSession session, @RequestParam("keyword") String keyword) throws DocException{
		System.out.println(keyword);
		ModelAndView mv =  null;
		UserProfile userProfile = (UserProfile)session.getAttribute("userprofile");
		if(userProfile != null ){			
			mv = new ModelAndView("searchDoctorPageWithLogin");
		} else {
			mv = new ModelAndView("searchDoctorPageWithLogout");
		}
		mv.addObject("searchResults", userService.findUser(keyword));
		return mv;
	}


	@RequestMapping(value={"saveAppointment/{id}"}, method = RequestMethod.GET)
	public ModelAndView saveAppointment(@PathVariable Integer id, HttpSession session) throws DocException{	
		ModelAndView mv = new ModelAndView("appointmentSuccessPage");
		
		UserProfile user = DocUtils.getLoggedInUserProfile(session,userService);
		UserProfile doctor= userService.getRowById(id);
		Integer appointId = userService.saveAppointment(user, doctor);
		mv.addObject("doctor", doctor);
		mv.addObject("appointId",appointId);
		return mv;		
	}

	@RequestMapping(value={"getAppointment/{doctorId}"}, method = RequestMethod.GET)
	public ModelAndView getAppointment(@PathVariable Integer doctorId) throws DocException{	
		ModelAndView mv = new ModelAndView("getPatientAppointmentPage");		
		UserProfile doctor= userService.getRowById(doctorId);
		mv.addObject("user", new UserProfile());
		mv.addObject("doctor", doctor);	
		return mv;		
	}
	
	@RequestMapping(value={"appointmentlist"}, method = RequestMethod.GET)
	public ModelAndView appointmentlist(HttpSession session) throws DocException{
		UserProfile user = DocUtils.getLoggedInUserProfile(session,userService);
		ModelAndView mv = new ModelAndView("appointmentListPage");
		mv.addObject("appointmentList", userService.getAppointmentList(user.getId(), false));
		return mv;

	}

	
	@RequestMapping(value={"viewDoctorAppointment"}, method = RequestMethod.POST)
	public String viewDoctorAppointment() throws DocException{
		
		return "viewDoctorAppointmentPage";		
	}


}
