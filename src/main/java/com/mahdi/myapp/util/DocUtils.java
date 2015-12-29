package com.mahdi.myapp.util;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.AppointmentSchedule;
import com.mahdi.myapp.model.BookingStatus;
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

	public static Set<Bookings> getBookings(UserProfile userProfile, List<Bookings> bookedSlots) {
		Set<Bookings> bookings = new LinkedHashSet<Bookings>();
		Map<Long, Bookings> alreadBookedMap = new HashMap<Long, Bookings>(0);
		if(bookedSlots != null){
			for (Bookings booking : bookedSlots) {
				alreadBookedMap.put(booking.getDatetimeStartInLong(), booking);
			}
		}
		int maxAvailbleSlot = 0;
		AppointmentSchedule as = userProfile.getAppointmentSchedule();
		if(as!=null){
			final DateTime dt1 = new DateTime(as.getStartTime());
			final DateTime dt2 = new DateTime(as.getEndTime());
	
			maxAvailbleSlot = Minutes.minutesBetween(dt1, dt2).getMinutes() / as.getSlotSize();
			
			Date start = dt1.toDate();
			Date end = dt1.plusMinutes(as.getSlotSize()).toDate();
			
			for (int i = 0; i < maxAvailbleSlot; i++) {
				Bookings booking = new Bookings(start,end);
				if(alreadBookedMap.containsKey(start.getTime())){
					booking = alreadBookedMap.get(start.getTime());
				}
				
				bookings.add(booking);
				start = end;
				end = new DateTime(end).plusMinutes(as.getSlotSize()).toDate();
			}
		}
		//TODO Findout already booked schedule.
		return bookings;
	}

	public static Bookings getBooking(Long appointmentStartTime, UserProfile doctorProfile, UserProfile patientProfile,
			String reason,BookingStatus bookingStatus) {
		return new Bookings(bookingStatus, doctorProfile, patientProfile, reason, new Date(appointmentStartTime), new DateTime(appointmentStartTime).plusMinutes(doctorProfile.getAppointmentSchedule().getSlotSize()).toDate());
	}
	
	public static MimeMessage createMimeMailMessage(JavaMailSender mailSender, UserProfile userProfile, String url) throws MessagingException{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("admin@getDoc.com");
		message.setTo(userProfile.getEmail());
		message.setSubject("Somebody requested a new password for your GetDoc account");		
		message.setSentDate(new Date());	
        message.setText("<html><body><p>Hi "+userProfile.getUsername()+",</p><p>Somebody recently asked to reset your GetDoc password.</p><p><a href="+url+">Click here to change your password.</a></p><p>Didn't request this change?</p><p>If you didn't request a new password, let us know immediately.</p><p>Thanks,</p><p>Admin GetDoc</p></body> </html>");        
        return createMimeMailTemplate(mailSender, message);
	}
	
	public static MimeMessage createMimeMailTemplate(JavaMailSender mailSender, SimpleMailMessage simpleMailMessage) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);		
		helper.setFrom(simpleMailMessage.getFrom());
		helper.setTo(simpleMailMessage.getTo());
		helper.setSubject(simpleMailMessage.getSubject());
		helper.setText(simpleMailMessage.getText());		
		return message;
		
	}
	
}
