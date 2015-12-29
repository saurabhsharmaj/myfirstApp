package com.mahdi.myapp.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AmazonS3Example {
	
	private static final String SUFFIX = "/";
	
	public static void main(String[] args) {
		// credentials object identifying user for authentication
		// user must have AWSConnector and AmazonS3FullAccess for 
		// this example to work
		/*
		 *  greenhouse
Access Key ID:
AKIAJBD36EJ3MGSYQZEA
Secret Access Key:
qhY/fxz+3ywFryXNH7njS87XZtOUnvw1J6vve9Cr
		 */
		AWSCredentials credentials = new BasicAWSCredentials(
				"AKIAJI4ODZBCUCMCVOEA", 
				"QJk7E7N/U40CbnAmhypPzJTMGKooNx1j0ruj9bob");
		
		// create a client connection based on credentials
		AmazonS3 s3client = new AmazonS3Client(credentials);
		
		/*// create bucket - name must be unique for all S3 users
		String bucketName = "greenhousevideo";
		//s3client.createBucket(bucketName);
		
		// list buckets
		for (Bucket bucket : s3client.listBuckets()) {
			bucketName = bucket.getName();
			System.out.println(" - " + bucket.getName());
		}
		
		// create folder into bucket
		String folderName = "greenhousevideo";
		createFolder(bucketName, folderName, s3client);
		
		// upload file to folder and set it to public
		String fileName = "Koala.jpg";
		
		PutObjectResult result =s3client.putObject(new PutObjectRequest(bucketName, fileName, new File("C:\\prem\\a_overfriendly.mp4")).withCannedAcl(CannedAccessControlList.PublicRead));
		*/
		URL url = s3client.generatePresignedUrl("greenhousevideo/a_overfriendly.mp4", "AKIAJI4ODZBCUCMCVOEA", null, HttpMethod.GET);
		System.out.println(url);
		//deleteFolder(bucketName, folderName, s3client);
		
		// deletes bucket
		//s3client.deleteBucket(bucketName);
	}
	
	public static void createFolder(String bucketName, String folderName, AmazonS3 client) {
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);

		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
				folderName + SUFFIX, emptyContent, metadata);

		// send request to S3 to create folder
		client.putObject(putObjectRequest);
	}

	/**
	 * This method first deletes all the files in given folder and than the
	 * folder itself
	 */
	public static void deleteFolder(String bucketName, String folderName, AmazonS3 client) {
		List<S3ObjectSummary> fileList = 
				client.listObjects(bucketName, folderName).getObjectSummaries();
		for (S3ObjectSummary file : fileList) {
			client.deleteObject(bucketName, file.getKey());
		}
		client.deleteObject(bucketName, folderName);
	}
}