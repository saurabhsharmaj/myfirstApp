package com.mahdi.myapp.util;

import java.io.File;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

public class AmazonS3Example {
	
	private static final String SUFFIX = "/";
	
	public static void main(String[] args) {		
		AWSCredentials credentials = new BasicAWSCredentials(
				"AKIAJTCYGKNMFKNE4N4Q", 
				"fuVebUAz3G67EzZBRiW10rXrhqR17YxHigVEZOQK");	
		
		AmazonS3 s3client = new AmazonS3Client(credentials);
		
		
		String bucketName ="mys3bucket1985";
		//s3client.createBucket(bucketName);
		
		
		// upload file to folder and set it to public
		String fileName = "Koala.jpg";
		
		PutObjectResult result =s3client.putObject(new PutObjectRequest(bucketName, fileName, new File("C:\\mahdi\\Koala.jpg")).withCannedAcl(CannedAccessControlList.PublicRead));
		
		
		System.out.println(result.toString());
		
	}
}