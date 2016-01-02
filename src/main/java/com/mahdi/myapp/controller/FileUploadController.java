package com.mahdi.myapp.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.service.IUserRoleService;
import com.mahdi.myapp.service.IUserService;
import com.mahdi.myapp.util.DocConstant;
import com.mahdi.myapp.util.DocUtils;

@Controller
public class FileUploadController implements ServletContextAware {

	private final Logger log = LoggerFactory.getLogger(FileUploadController.class);
	
	private ServletContext servletContext;

	@Autowired
	IUserService userService;
	
	@Autowired
	IUserRoleService roleService;
	
	@RequestMapping(value = "saveProfilePic", method = RequestMethod.POST)
	public String saveProfilePic(
			HttpSession session,
			@RequestParam(value = "profilePic", required = false) MultipartFile image) throws DocException {
		
		UserProfile userprofile = DocUtils.getLoggedInUserProfile(session,userService);
		
		if (!image.isEmpty()) {
			try {
				validateImage(image);

			} catch (RuntimeException re) {
				return "redirect:myprofile";
			}

			try {
				saveImage(userprofile.getId(), image);
				userprofile.setProfilePicUrl(userprofile.getId()+".jpg");
				userService.updateRow(userprofile);
			} catch (IOException e) {
				return "redirect:myprofile";
			} catch (Exception e) {
				return "redirect:myprofile";
			}
		}
		
		if(userprofile.getUserRoles().get(0).getCode().equals(DocConstant.ROLE_ADMIN)){			
			return "redirect:admin/myprofile";
		} else if(userprofile.getUserRoles().get(0).getCode().equals(DocConstant.ROLE_DOCTOR)){			
			return "redirect:doctor/myprofile";
		} else if(userprofile.getUserRoles().get(0).getCode().equals(DocConstant.ROLE_PATIENT)){			
			return "redirect:patient/myprofile";
		} else {
			return "errorPage";
		}
	}

	private void validateImage(MultipartFile image) {
		if (!image.getContentType().equals("image/jpeg")) {
			throw new RuntimeException("Only JPG images are accepted");
		}
	}
	
	//TODO: Replace with code here Amazon code here.

	/**
	 *
	 * @param filename
	 * @param image
	 * @throws RuntimeException
	 * @throws IOException
	 */
	private void saveImage(Integer filename, MultipartFile image)
			throws RuntimeException, IOException {
		try {
			File file = new File(filename.toString()+".jpg");
			FileUtils.writeByteArrayToFile(file, image.getBytes());
			uploadFileOnS3Bucket(file);
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
	
	public void uploadFileOnS3Bucket(File file){
		
		AWSCredentials credentials = new BasicAWSCredentials(
				DocConstant.S3ACCESSKEY, 
				DocConstant.S3SECREATKEY);			
		AmazonS3 s3client = new AmazonS3Client(credentials);		
		String bucketName =DocConstant.S3BUCKETNAME;
		String fileName = file.getName();
		
		PutObjectResult result =s3client.putObject(new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
		
		
	
	}
}
