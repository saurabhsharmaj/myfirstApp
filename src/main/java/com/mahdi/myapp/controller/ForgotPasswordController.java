package com.mahdi.myapp.controller;

import java.security.Principal;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.PasswordResetToken;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.service.IUserService;
import com.mahdi.myapp.util.DocUtils;

@Controller
public class ForgotPasswordController {

	private final Logger log = LoggerFactory.getLogger(ForgotPasswordController.class);

	@Autowired
	IUserService userService;

	@Autowired
	JavaMailSender mailSender;

	@RequestMapping(value = "forgotpassword", method = RequestMethod.GET)
	public ModelAndView userInfo(Model model, Principal principal, HttpSession session) throws DocException {
		ModelAndView mv = new ModelAndView("forgotpasswordPage");
		return mv;
	}


	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
	public ModelAndView userInfo(@RequestParam(value="email", required=false) String email, HttpSession session) throws DocException {
		try{
			ModelAndView mv = new ModelAndView("sucessResetPasswordPage");
			UserProfile userProfile = userService.getRowByName("email", email);
			String token=UUID.randomUUID().toString();
			String URL="http://localhost:8080/MyFirstApp/recoverPassword/"+token+"/";
			MimeMessage msg = DocUtils.createMimeMailMessage(mailSender, userProfile,URL);
			mailSender.send(msg);
			PasswordResetToken t = new PasswordResetToken(userProfile,token);
			userService.savePasswordResetToken(t);
			mv.addObject("successMsg", "Password reset Linke send your email :<b>"+email+"</b>");
			return mv;
		} catch (MessagingException ex) {
			throw new DocException("Error while sending email.", ex);
		}
	}


	@RequestMapping(value = "recoverPassword/{token}", method = RequestMethod.GET)
	public ModelAndView recoverPassword(@PathVariable String token, HttpSession session) throws DocException {
		ModelAndView mv = new ModelAndView("changePasswordPage");
		UserProfile profile  = userService.getUserProfileByToken(token);
		if(profile==null){
			throw new DocException("Token is not valid. Please try again to reset password.");
		}
		System.out.println(profile.getId());
		mv.addObject("userProfile", profile);
		return mv;
	}


	@RequestMapping(value = "saveNewPassword", method = RequestMethod.POST)
	public ModelAndView saveNewPassword(@RequestParam(value="id", required=true) Integer id, @RequestParam(value="password", required=false) String password, HttpSession session) throws DocException {
		ModelAndView mv = new ModelAndView("saveNewPasswordSuccessPage");		
		UserProfile profile = userService.getRowById(id);
		profile.setPassword(password);
		userService.updateRow(profile);
		mv.addObject("successMsg", "Your password has been changed SuccessFully. <br>Please login with your new Crendentials.");		
		return mv;
	}
}
