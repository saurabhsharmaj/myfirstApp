package com.mahdi.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.DocResponse;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.service.IUserRoleService;
import com.mahdi.myapp.service.IUserService;
import com.mahdi.myapp.util.DocConstant;
import com.mahdi.myapp.util.DocUtils;

@Controller
public class HomeController {

	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IUserRoleService userRoleService;
	
	@RequestMapping(value={"/","home"}, method = RequestMethod.GET)
	public ModelAndView homePage() throws DocumentException{		
		ModelAndView mv = new ModelAndView("homePage");			
		return mv;
	}
	
	@RequestMapping(value={"register"}, method = RequestMethod.GET)
	public ModelAndView register() throws DocException{
		ModelAndView mv = new ModelAndView("registrationPage");		
		mv.addObject("user", new UserProfile());
		return mv;
	}
	
	
	@RequestMapping(value={"registerDoctor"}, method = RequestMethod.GET)
	public ModelAndView registerDoc() throws DocException{
		ModelAndView mv = new ModelAndView("registrationDocPage");	
		UserProfile profile = new UserProfile();		
		mv.addObject("user", profile);
		return mv;
	}	
	
	@RequestMapping(value={"registerDoctor"}, method = RequestMethod.POST)
	public String saveDoctor(@ModelAttribute UserProfile userProfile) throws DocException{		
		userService.insertRow(userProfile);
		return "redirect:/login";
	}
	
	
	
	@RequestMapping(value= "registerPatient", method = RequestMethod.POST)
	public String registerPatient(@ModelAttribute UserProfile userProfile,BindingResult result) throws DocException{
		userService.insertRow(userProfile);		
		return "redirect:/login";
	}
	
	
	@RequestMapping(value={"searchDoctor"}, method = RequestMethod.POST)
	public ModelAndView searchDoctor(HttpSession session, @RequestParam("keyword") String keyword) throws DocException{
		System.out.println(keyword);
		ModelAndView mv =  null;
		UserProfile userProfile = DocUtils.getLoggedInUserProfile(session,userService);
		if(userProfile != null ){			
			mv = new ModelAndView("searchDoctorPageWithLogin");
		} else {
			mv = new ModelAndView("searchDoctorPageWithLogout");
		}
		mv.addObject("user", userProfile);
		mv.addObject("searchResults", userService.findUser(keyword));
		return mv;
	}
	
	@RequestMapping(value={"doctorDetail/{id}"}, method = {RequestMethod.GET, RequestMethod.POST})	
	public ModelAndView getDoctorProfile(@PathVariable Integer id, HttpSession session) throws DocException{		
		ModelAndView mv = null;
		UserProfile userProfile = DocUtils.getLoggedInUserProfile(session,userService);
		if(userProfile != null ){			
			mv = new ModelAndView("viewDoctorProfilePageWithLogin");
		} else {
			mv = new ModelAndView("viewDoctorProfilePageWithLogout");
		}
		
		mv.addObject("profile", userService.getRowById(id));
		return mv;
	}	
	
	
	@RequestMapping(value={"getAppointment/{doctorId}"}, method = RequestMethod.GET)
	public ModelAndView getAppointment(@PathVariable Integer doctorId) throws DocException{	
		ModelAndView mv = new ModelAndView("getAppointmentPage");		
		UserProfile doctor= userService.getRowById(doctorId);
		mv.addObject("user", new UserProfile());
		mv.addObject("doctor", doctor);	
		return mv;		
	}
		
	@RequestMapping(value={"signin/{doctorId}"}, method = RequestMethod.POST)
	public String saveAppointmentWithDoctorForExistingPatients(@PathVariable Integer doctorId ,@ModelAttribute UserProfile userProfile,Model model, HttpSession session) throws DocException{		
		UserProfile profile = userService.validate(userProfile);
		if( profile != null ){
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(DocConstant.ROLE_PATIENT));
			UserDetails user = new User(userProfile.getUsername(), userProfile.getPassword() , authorities);
			Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			session.setAttribute(DocConstant.USERPROFILE, profile);
			model.addAttribute("user", user);
			model.addAttribute("doctor", userService.getRowById(doctorId));
			return "forward:/patient/viewDoctorAppointment";
		}
		//userService.insertRow(userProfile);
		return "redirect:/login";
	}
	
	@RequestMapping(value={"newRegistration/{doctorId}"}, method = RequestMethod.POST)
	public String saveAppointmentWithDoctorForNewPatient(@PathVariable Integer doctorId ,@ModelAttribute UserProfile userProfile,Model model, HttpSession session) throws DocException{		
		
			int id = userService.insertRow(userProfile);
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(DocConstant.ROLE_PATIENT));
			UserDetails user = new User(userProfile.getUsername(), userProfile.getPassword() , authorities);
			Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			session.setAttribute(DocConstant.USERPROFILE, userService.getRowById(id));
			model.addAttribute("user", user);
			model.addAttribute("doctor", userService.getRowById(doctorId));
			return "forward:/patient/viewDoctorAppointment";
		
	}
	
	@RequestMapping(value="isEmailExist/{emailId}/", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody DocResponse isEmailExist(@PathVariable String emailId) throws DocException{		
		return new DocResponse(HttpStatus.OK, userService.isEmailExist(emailId),null);
		
	}
	
	@RequestMapping(value="isUserNameExist/{username}/", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody DocResponse isUserNameExist(@PathVariable String username) throws DocException{		
		return new DocResponse(HttpStatus.OK, userService.isUserNameExist(username),null);
		
	}
}
