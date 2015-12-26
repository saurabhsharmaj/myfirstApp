package com.mahdi.myapp.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
import com.mahdi.myapp.model.Bookings;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.service.IBookingService;
import com.mahdi.myapp.service.IBookingStatusService;
import com.mahdi.myapp.service.IUserRoleService;
import com.mahdi.myapp.service.IUserService;
import com.mahdi.myapp.util.DocConstant;
import com.mahdi.myapp.util.DocUtils;

@Controller
@RequestMapping("patient")
public class PatientController {

	private final Logger log = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	IUserService userService;

	@Autowired
	IUserRoleService userRoleService;

	@Autowired
	IBookingService bookingService;
	
	@Autowired
	IBookingStatusService bookingStatusService;
	
	@RequestMapping(value={"/"}, method = RequestMethod.GET)
	public ModelAndView userHomePage(){
		ModelAndView mv = new ModelAndView("patientPage");
		return mv;
	}

	@RequestMapping(value={"myprofile"}, method = RequestMethod.GET)
	public ModelAndView getProfile(HttpSession session) throws DocException{
		ModelAndView mv = new ModelAndView("patientViewProfilePage");			
		UserProfile userProfile = DocUtils.getLoggedInUserProfile(session,userService);
		mv.addObject("userproflie",userProfile);
		return mv;

	}
	
	
	@RequestMapping(value={"editViewProfile"}, method = RequestMethod.GET)
	public ModelAndView editViewProfile(HttpSession session) throws DocException{
		ModelAndView mv = new ModelAndView("patientProfilePage");
		mv.addObject("userproflie",DocUtils.getLoggedInUserProfile(session,userService));
		return mv;
		
	}
	
	@RequestMapping(value= "updateProfile", method = RequestMethod.POST)
	public String updateUser(HttpSession session, @ModelAttribute("user") UserProfile userprofile) throws DocException{
		UserProfile savedProfile = userService.getRowById(userprofile.getId());
		
		if(StringUtils.isNotEmpty(userprofile.getFullname())){
			savedProfile.setFullname(userprofile.getFullname());
		}		
		
		if(savedProfile.getAge() != userprofile.getAge()){
			savedProfile.setAge(userprofile.getAge());
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
				
		return "redirect:/patient/myprofile";
		

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
		mv.addObject("user", userProfile);
		mv.addObject("searchResults", userService.findUser(keyword));
		return mv;
	}


	@RequestMapping(value={"saveAppointment/{id}/{timeSlot}"}, method = RequestMethod.GET)
	public ModelAndView saveAppointment(@PathVariable Integer id,@PathVariable Long timeSlot, HttpSession session) throws DocException{	
		ModelAndView mv = new ModelAndView("appointmentSuccessPage");
		
		UserProfile patientProfile = DocUtils.getLoggedInUserProfile(session,userService);
		UserProfile doctorProfile= userService.getRowById(id);
		Bookings booking = DocUtils.getBooking(timeSlot, doctorProfile, patientProfile, "illness",bookingStatusService.getRowById(1));
		Integer appointId = bookingService.insertRow(booking);		
		mv.addObject("doctor", doctorProfile);
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
		ModelAndView mv = new ModelAndView("appointmentListPage");
		UserProfile user = DocUtils.getLoggedInUserProfile(session,userService);
		mv.addObject("appointmentList", userService.getAppointmentList(user.getId(), false));
		return mv;

	}

	
	@RequestMapping(value={"viewDoctorAppointment"}, method = {RequestMethod.GET,RequestMethod.POST})
	public String viewDoctorAppointment() throws DocException{		
		return "viewDoctorAppointmentPage";		
	}

	@RequestMapping(value={"getAppointment/{doctorId}/{userId}"}, method = RequestMethod.GET)
	public String getAppointment(@PathVariable Integer doctorId, @PathVariable Integer userId, Model model) throws DocException{	
		model.addAttribute("user", userService.getRowById(userId));
		//TODO:Paas AlreadyBooking.
		UserProfile doctorProfile = userService.getRowById(doctorId);
		doctorProfile.setAllBooking(DocUtils.getBookings(doctorProfile,null));
		model.addAttribute("doctor", doctorProfile);
		return "viewDoctorAppointmentPage";		
	}

}
