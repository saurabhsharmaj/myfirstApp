package com.mahdi.myapp.service;

import java.util.List;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.Bookings;
import com.mahdi.myapp.model.PasswordResetToken;
import com.mahdi.myapp.model.UserProfile;

public interface IUserService extends IService<UserProfile> {

	List<UserProfile> findUser(String keyword) throws DocException;	
	
	List<Bookings> getBookingList(Integer userId, boolean isDoctor) throws DocException;

	List<Bookings>  findAppointment(String keyword, UserProfile doctorProfile) throws DocException;

	UserProfile validate(UserProfile userProfile) throws DocException;

	boolean isEmailExist(String emailId) throws DocException;

	Boolean isUserNameExist(String username) throws DocException;
	
	int savePasswordResetToken(PasswordResetToken t) throws DocException;

	UserProfile getUserProfileByToken(String token) throws DocException;

}
