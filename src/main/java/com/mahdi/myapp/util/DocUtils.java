package com.mahdi.myapp.util;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;

import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.service.IUserService;

public class DocUtils {
	@Autowired
	static IUserService userService;

	public static UserProfile getLoggedInUserProfile(HttpSession session ){
		UserProfile userProfile = (UserProfile)session.getAttribute(DocConstant.USERPROFILE);
		if(null ==userProfile && (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT") != null){
			User activeUser = (User) ((SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getPrincipal();
			userProfile = userService.getRowByName("username", activeUser.getUsername());
			session.setAttribute(DocConstant.USERPROFILE, userProfile);
		}
		return userProfile;
	}
}
