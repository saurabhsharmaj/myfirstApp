package com.mahdi.myapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahdi.myapp.dao.AppointmentDao;
import com.mahdi.myapp.dao.UserDao;
import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.Appointment;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.util.DocConstant;

@Service
@Transactional
public class UserService implements IUserService {
		
	@Autowired
	UserDao userDao;
	
	@Autowired
	AppointmentDao appointmentDao;
	
	public int insertRow(UserProfile t) throws DocException {
		return userDao.insertRow(t);
	}

	public List<UserProfile> getList() throws DocException {
		return userDao.getList();
	}

	public UserProfile getRowById(int id) throws DocException {
		return userDao.getRowById(id);
	}

	public int updateRow(UserProfile t) throws DocException {
		return userDao.updateRow(t);
	}

	public int deleteRow(int id) throws DocException {
		return userDao.deleteRow(id);
	}

	public void saveUser(UserProfile u) throws DocException {
		if(u.getId() == 0){
			u.setEnabled(1);
			insertRow(u);
		}else{
			updateRow(u);
		}
	
		
	}

	public UserProfile getRowByName(String columnName, String value) {
		return userDao.getRowByColumnName(columnName, value);
	}

	public List<UserProfile> findUser(String keyword) throws DocException {
		return userDao.findUser(keyword);
	}

	public Integer saveAppointment(UserProfile user, UserProfile doctor) throws DocException {		
		return appointmentDao.saveAppointment(new Appointment(doctor,user,new Date(),DocConstant.NEW_REQUEST));
	}

	public List<Appointment> getAppointmentList(Integer userId, boolean isDoctor) throws DocException {
		return appointmentDao.getAppointmentList(userId, isDoctor);
	}

	public List<Appointment> findAppointment(String keyword, boolean isDoctor) throws DocException {
		return appointmentDao.findAppointment(keyword, isDoctor);
	}

	public UserProfile validate(UserProfile userProfile) throws DocException {
		UserProfile profile  =  userDao.validate(userProfile);
		if(profile == null ){
			throw new DocException("Invalid crendentials");
		} else {
			return profile;
		}
	}

	public boolean isEmailExist(String emailId) throws DocException {
		return userDao.getRowByColumnName("email", emailId)==null?false:true;
	}

	public Boolean isUserNameExist(String username) throws DocException {
		return userDao.getRowByColumnName("username", username)==null?false:true;
	}
	
}
