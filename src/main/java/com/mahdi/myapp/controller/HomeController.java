package com.mahdi.myapp.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.service.IUserRoleService;
import com.mahdi.myapp.service.IUserService;
import com.mahdi.myapp.util.DocConstant;

@Controller
public class HomeController {

	@Autowired
	IUserService userService;
	
	@Autowired
	IUserRoleService userRoleService;
	
	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value={"/","home"}, method = RequestMethod.GET)
	public ModelAndView homePage(){		
		ModelAndView mv = new ModelAndView("homePage");
		mv.addObject("title", "This is my Home Page.");
		return mv;
	}
	
	@RequestMapping(value={"register"}, method = RequestMethod.GET)
	public ModelAndView register(){
		ModelAndView mv = new ModelAndView("registrationPage");		
		mv.addObject("user", new UserProfile());
		mv.addObject("userRoles", userRoleService.getRoleExceptAdmin());
//		mv.addObject("status", new UserStatusEnum[]{UserStatusEnum.ACTIVE, UserStatusEnum.DEACTIVE});
		return mv;
	}
	
	
	
	@RequestMapping(value= "registerUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") UserProfile u) throws DocException{
		u.setEnabled(1);//TODO: Fix Later
		userService.insertRow(u);		
		return "redirect:/login";


	}
	
	
	@RequestMapping(value={"searchDoctor"}, method = RequestMethod.POST)
	public ModelAndView searchDoctor(HttpSession session, @RequestParam("keyword") String keyword){
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
	
	@RequestMapping(value={"doctorDetail/{id}"}, method = RequestMethod.GET)
	public ModelAndView getDoctorProfile(@PathVariable Integer id){		
		ModelAndView mv = new ModelAndView("viewDoctorProfilePage");
		mv.addObject("profile", userService.getRowById(id));
		return mv;
	}
	
	
	
	@RequestMapping(value= "updateProfile", method = RequestMethod.POST)
	public String updateUser(HttpSession session, @ModelAttribute("user") UserProfile userprofile) throws DocException{
		UserProfile savedProfile = userService.getRowById(userprofile.getId());
		
		if(StringUtils.isNotEmpty(userprofile.getFullname())){
			savedProfile.setFullname(userprofile.getFullname());
		}
		
		userService.insertRow(savedProfile);	
		session.setAttribute(DocConstant.USERPROFILE, savedProfile);
		
		if(userprofile.getRole().equals("1")){			
			return "redirect:admin/myprofile";
		} else if(userprofile.getRole().equals("2")){			
			return "redirect:doctor/myprofile";
		} else if(userprofile.getRole().equals("3")){			
			return "redirect:user/myprofile";
		} else {
			return "errorPage";
		}

	}
	
	
}