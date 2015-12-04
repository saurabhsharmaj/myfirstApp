package com.mahdi.myapp.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.util.DocConstant;

@Controller
public class FileUploadController implements ServletContextAware {

	private final Logger log = LoggerFactory.getLogger(FileUploadController.class);
	
	private ServletContext servletContext;

	@RequestMapping(value = "saveProfilePic", method = RequestMethod.POST)
	public String saveProfilePic(
			HttpSession session,
			@RequestParam(value = "profilePic", required = false) MultipartFile image) {
		
		UserProfile userprofile = (UserProfile) session.getAttribute("userprofile");
		
		if (!image.isEmpty()) {
			try {
				validateImage(image);

			} catch (RuntimeException re) {
				return "redirect:myprofile";
			}

			try {
				saveImage(userprofile.getId(), image);				
			} catch (IOException e) {
				return "redirect:myprofile";
			} catch (Exception e) {
				return "redirect:myprofile";
			}
		}
		
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

	private void validateImage(MultipartFile image) {
		if (!image.getContentType().equals("image/jpeg")) {
			throw new RuntimeException("Only JPG images are accepted");
		}
	}
	

	private void saveImage(Integer filename, MultipartFile image)
			throws RuntimeException, IOException {
		try {
			String uploadedPath = servletContext.getRealPath("/")
					+ "resources/profilepic/" + filename + ".jpg";
			File file = new File(uploadedPath);
			FileUtils.writeByteArrayToFile(file, image.getBytes());
			log.debug("Go to the location:  "
							+ file.toString()
							+ " on your computer and verify that the image has been stored.");
		} catch (IOException e) {
			throw e;
		}
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}	
}
