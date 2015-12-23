package com.mahdi.myapp.service;

import java.util.List;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.AppointmentSchedule;
import com.mahdi.myapp.model.UserProfile;

public interface IUserService extends IService<UserProfile> {

	List<UserProfile> findUser(String keyword) throws DocException;	

	public Integer saveAppointment(UserProfile user, UserProfile doctor) throws DocException;
	
	List<AppointmentSchedule> getAppointmentList(Integer userId, boolean isDoctor) throws DocException;

	List<AppointmentSchedule>  findAppointment(String keyword, boolean b) throws DocException;

	UserProfile validate(UserProfile userProfile) throws DocException;

	boolean isEmailExist(String emailId) throws DocException;

	Boolean isUserNameExist(String username) throws DocException;

}
