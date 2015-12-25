package com.mahdi.myapp.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.service.IUserRoleService;
import com.mahdi.myapp.service.IUserService;
import com.mahdi.myapp.util.DocConstant;
import com.mahdi.myapp.util.DocUtils;

@Controller
@RequestMapping("admin")
public class AdminController {

	private final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	IUserService userService;
		
	@Autowired
	IUserRoleService userRoleService;
	
	
	@RequestMapping(value={"/"}, method = RequestMethod.GET)
	public ModelAndView adminHomePage(){		
		ModelAndView mv = new ModelAndView("adminPage");
		return mv;
	}
	
	
	
	@RequestMapping(value={"myprofile"}, method = RequestMethod.GET)
	public ModelAndView getMyProfile(HttpSession session) throws DocException{
		ModelAndView mv = new ModelAndView("adminViewProfilePage");		
		mv.addObject("userproflie",DocUtils.getLoggedInUserProfile(session,userService));
		return mv;
		
	}
	
	@RequestMapping(value={"editViewProfile"}, method = RequestMethod.GET)
	public ModelAndView editViewProfile(HttpSession session) throws DocException{
		ModelAndView mv = new ModelAndView("adminProfilePage");		
		mv.addObject("userproflie",DocUtils.getLoggedInUserProfile(session,userService));
		return mv;
		
	}
	
	@RequestMapping(value= "updateProfile", method = RequestMethod.POST)
	public String updateProfile(HttpSession session, @ModelAttribute("user") UserProfile userprofile) throws DocException{
		UserProfile savedProfile = userService.getRowById(userprofile.getId());
		
		if(StringUtils.isNotEmpty(userprofile.getFullname())){
			savedProfile.setFullname(userprofile.getFullname());
		}
		
		if(savedProfile.getSpecialization() != userprofile.getSpecialization()){
			savedProfile.setSpecialization(userprofile.getSpecialization());
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
				
		return "redirect:/admin/myprofile";
		

	}
	
	@RequestMapping(value={"searchUser"}, method = RequestMethod.POST)
	public ModelAndView searchUser(HttpSession session, @RequestParam("keyword") String keyword) throws DocException{
		System.out.println(keyword);
		ModelAndView mv = new ModelAndView("searchUserPage");		
		mv.addObject("searchResults", userService.findUser(keyword));
		return mv;
	}
	
	@RequestMapping(value={"listUsers"}, method = RequestMethod.GET)
	public ModelAndView listUsers(HttpSession session) throws DocException{
		ModelAndView mv = new ModelAndView("manageUserPage");
		List<UserProfile> users = userService.getList();
		users.remove(DocUtils.getLoggedInUserProfile(session,userService));
		mv.addObject("userList",users);
		return mv;		
	}
	
	@RequestMapping(value={"editUser/{id}"}, method=RequestMethod.GET)
	public ModelAndView editUser(@PathVariable Integer id) throws DocException{
		ModelAndView mv = new ModelAndView("manageEditUserPage");
		UserProfile user = userService.getRowById(id);		
		mv.addObject("user",user);
		return mv;		
	}
	
	@RequestMapping(value={"deleteUser/{id}"}, method=RequestMethod.GET)
	public @ResponseBody String deleteUser(@PathVariable Integer id, HttpSession session) throws DocException{
		
		UserProfile user =  userService.getRowById(id);
		userService.deleteRow(id);		
		return user.getUsername() +" has been deleted.";
	}
	
	@RequestMapping(value= "updateManagedUserProfile", method = RequestMethod.POST)
	public String updateManagedUserProfile(HttpSession session, @ModelAttribute("user") UserProfile userprofile) throws DocException{
		UserProfile savedProfile = userService.getRowById(userprofile.getId());
		
		if(StringUtils.isNotEmpty(userprofile.getFullname())){
			savedProfile.setFullname(userprofile.getFullname());
		}
		
		if(savedProfile.getSpecialization() != userprofile.getSpecialization()){
			savedProfile.setSpecialization(userprofile.getSpecialization());
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
				
		return "redirect:/admin/listUsers";
		

	}
}
