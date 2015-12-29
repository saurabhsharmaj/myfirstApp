package com.mahdi.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahdi.myapp.dao.BookingDao;
import com.mahdi.myapp.dao.UserDao;
import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.Bookings;
import com.mahdi.myapp.model.PasswordResetToken;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.util.DocUtils;

@Service
@Transactional
public class UserService implements IUserService {
		
	@Autowired
	UserDao userDao;
		
	@Autowired
	BookingDao bookingDao;
	
	
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
			u.setEnabled((byte) 1);
			insertRow(u);
		}else{
			updateRow(u);
		}
	
		
	}

	public UserProfile getRowByName(String columnName, String value) {
		return userDao.getRowByColumnName(columnName, value);
	}

	public List<UserProfile> findUser(String keyword) throws DocException {
		List<UserProfile> searchUsers =  userDao.findUser(keyword);
		for (UserProfile userProfile : searchUsers) {
			List<Bookings> bookedSlots = bookingDao.getAppointmentList(userProfile.getId(), true);
			userProfile.setAllBooking(DocUtils.getBookings(userProfile, bookedSlots));
		}
		return searchUsers;
	}	

	public List<Bookings> getBookingList(Integer userId, boolean isDoctor) throws DocException {
		return bookingDao.getAppointmentList(userId,isDoctor);
	}

	public List<Bookings> findAppointment(String keyword, UserProfile doctorProfile) throws DocException {		
		return bookingDao.findAppointment(keyword, doctorProfile);
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

	public List<UserProfile> getRowsByName(String columnName, String value) {
		return userDao.getRowsByColumnName(columnName, value);
	}

	public int savePasswordResetToken(PasswordResetToken t) throws DocException {
		return userDao.savePasswordResetToken(t);
	}

	public UserProfile getUserProfileByToken(String token) throws DocException {		
		PasswordResetToken passwordResetToken = userDao.getUserProfileByToken(token);
		return passwordResetToken != null ? passwordResetToken.getUserProfile() : null;
	}
	
}
