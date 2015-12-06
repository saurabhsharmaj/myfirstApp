package com.mahdi.myapp.service;

import java.util.List;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.Appointment;
import com.mahdi.myapp.model.UserProfile;

public interface IUserService extends IService<UserProfile> {

	List<UserProfile> findUser(String keyword);	

	public Integer saveAppointment(UserProfile user, UserProfile doctor) throws DocException;
	
	List<Appointment> getAppointmentListByUserId(Integer userId) throws DocException;
}
