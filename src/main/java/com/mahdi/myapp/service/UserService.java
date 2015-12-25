package com.mahdi.myapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahdi.myapp.dao.AppointmentDao;
import com.mahdi.myapp.dao.UserDao;
import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.AppointmentSchedule;
import com.mahdi.myapp.model.Bookings;
import com.mahdi.myapp.model.UserProfile;

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
			userProfile.setAllBooking(getBookings(userProfile, true));
		}
		return searchUsers;
	}

	private Set<Bookings> getBookings(UserProfile userProfile, boolean b) {
		Set<Bookings> bookings = new LinkedHashSet<Bookings>();
		int maxAvailbleSlot = 0;
		AppointmentSchedule as = userProfile.getAppointmentSchedule();

		final DateTime dt1 = new DateTime(as.getStartTime());
		final DateTime dt2 = new DateTime(as.getEndTime());

		maxAvailbleSlot = Minutes.minutesBetween(dt1, dt2).getMinutes() / as.getSlotSize();
		
		Date start = dt1.toDate();
		Date end = dt1.plusMinutes(as.getSlotSize()).toDate();
		
		for (int i = 0; i < maxAvailbleSlot; i++) {
			bookings.add(new Bookings(as,start,end));
			start = end;
			end = new DateTime(end).plusMinutes(30).toDate();
		}

		//TODO Findout already booked schedule.
		return bookings;
	}

	/*public static void main(String[] args) {
		final String dateStart = "01/14/2012 10:00:00";
		final String dateStop = "01/14/2012 14:00:00";
		
		final DateTimeFormatter format = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
		
		final DateTime dt1 = format.parseDateTime(dateStart);
		final DateTime dt2 = format.parseDateTime(dateStop);
		int  maxAvailbleSlot = Minutes.minutesBetween(dt1, dt2).getMinutes() / 30;
		Date start = dt1.toDate();
		Date end = dt1.plusMinutes(30).toDate();

		for (int i = 0; i < maxAvailbleSlot; i++) {			
			System.out.println(start +" - "+ end);
			start = end;
			end = new DateTime(end).plusMinutes(30).toDate();
		}
	}*/
	
	public Integer saveAppointment(UserProfile user, UserProfile doctor) throws DocException {		
		//TODO change return appointmentDao.saveAppointment(new Appointment(doctor,user,new Date(),DocConstant.NEW_REQUEST));
		return 0;
	}

	public List<AppointmentSchedule> getAppointmentList(Integer userId, boolean isDoctor) throws DocException {
		//TODO change return appointmentDao.getAppointmentList(userId, isDoctor);
		return new ArrayList<AppointmentSchedule>();
	}

	public List<AppointmentSchedule> findAppointment(String keyword, boolean isDoctor) throws DocException {
		//TODO change return appointmentDao.findAppointment(keyword, isDoctor);
		return new ArrayList<AppointmentSchedule>();
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
