package com.mahdi.myapp.util;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.service.IUserService;

public class DocUtils {

	public static UserProfile getLoggedInUserProfile(HttpSession session, IUserService userService) throws DocException{
		try{
		UserProfile userProfile = (UserProfile)session.getAttribute(DocConstant.USERPROFILE);
		if(null ==userProfile && (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT") != null){
			User activeUser = (User) ((SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getPrincipal();
			userProfile = userService.getRowByName("username", activeUser.getUsername());
			session.setAttribute(DocConstant.USERPROFILE, userProfile);
		}
		return userProfile;
		}catch(Exception ex){
			throw new DocException(ex.getMessage());
		}
	}

	public static List<String> getWorkDaysList() {
		return Arrays.asList(new String[]{"1","2","3","4","5","6","7"});
	}
}
