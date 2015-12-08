package com.mahdi.myapp.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.model.UserStatusEnum;
import com.mahdi.myapp.service.IUserRoleService;
import com.mahdi.myapp.service.IUserService;
import com.mahdi.myapp.util.DocConstant;

@Controller
public class LoginController {

	private final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IUserRoleService userRoleService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET )
	public String loginPage(Model model) {
		model.addAttribute("title", "Login");
		model.addAttribute("message", "Enter your username/password:");		
		return "loginPage";
	}	
	
	
	@RequestMapping(value = "login/{error}", method = RequestMethod.GET )
	public String invalidCrendentialPage(@PathVariable String error, Model model) {
		model.addAttribute("title", "Login");
		model.addAttribute("error_message", error);		
		return "loginPage";
	}
	
	@RequestMapping(value = "userInfo", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal, HttpSession session) throws DocException {
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		model.addAttribute("title", "User Info"); 
		model.addAttribute("message","User Info - This is protected page!. Hello " + activeUser.getUsername());
		
		UserProfile userprofile = userService.getRowByName("username", activeUser.getUsername());
		model.addAttribute("user", userprofile);
		model.addAttribute("userRoles", userRoleService.getList());
		model.addAttribute("status", new UserStatusEnum[]{UserStatusEnum.ACTIVE, UserStatusEnum.DEACTIVE});
		session.setAttribute("userprofile", userprofile);
		if(activeUser.getAuthorities().contains(new GrantedAuthorityImpl(DocConstant.ROLE_ADMIN))){
			return "forward:/admin/";
		} else if(activeUser.getAuthorities().contains(new GrantedAuthorityImpl(DocConstant.ROLE_DOCTOR))){
			return "forward:/doctor/";
		} else if(activeUser.getAuthorities().contains(new GrantedAuthorityImpl(DocConstant.ROLE_USER))){
			return "forward:/user/";
		} else {
			return "forward:/403";
		}

	}
}
