package com.mahdi.myapp.util;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.AppointmentSchedule;
import com.mahdi.myapp.model.Bookings;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.service.IUserService;

public class DocUtils {

	public static UserProfile getLoggedInUserProfile(HttpSession session, IUserService userService) throws DocException{
		try{
		UserProfile userProfile = (UserProfile)session.getAttribute(DocConstant.USERPROFILE);
		if(null ==userProfile && (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT") != null){
			User activeUser = (User) ((SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getPrincipal();
			userProfile = userService.getRowByName("username", activeUser.getUsername());
			session.setAttribute(DocConstant.USERPROFILE, userProfile);
		}
		return userProfile;
		}catch(Exception ex){
			throw new DocException(ex.getMessage());
		}
	}

	public static List<String> getWorkDaysList() {
		return Arrays.asList(new String[]{"1","2","3","4","5","6","7"});
	}

	public static Set<Bookings> getBookings(UserProfile userProfile, List<Bookings> alreadyBookings) {
		Set<Bookings> bookings = new LinkedHashSet<Bookings>();
		int maxAvailbleSlot = 0;
		AppointmentSchedule as = userProfile.getAppointmentSchedule();
		if(as!=null){
			final DateTime dt1 = new DateTime(as.getStartTime());
			final DateTime dt2 = new DateTime(as.getEndTime());
	
			maxAvailbleSlot = Minutes.minutesBetween(dt1, dt2).getMinutes() / as.getSlotSize();
			
			Date start = dt1.toDate();
			Date end = dt1.plusMinutes(as.getSlotSize()).toDate();
			
			for (int i = 0; i < maxAvailbleSlot; i++) {
				bookings.add(new Bookings(start,end));
				start = end;
				end = new DateTime(end).plusMinutes(30).toDate();
			}
		}
		//TODO Findout already booked schedule.
		return bookings;
	}
}
