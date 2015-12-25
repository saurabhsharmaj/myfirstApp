package com.mahdi.myapp.model;
// Generated Dec 24, 2015 4:41:42 PM by Hibernate Tools 4.3.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * AppointmentSchedule generated by hbm2java
 */
@Entity
@Table(name = "appointment_schedule", catalog = "getdoc")
public class AppointmentSchedule implements java.io.Serializable {

	private Integer id;
	private UserProfile users;
	private String workingDays;	
	private Date startTime;
	private Date endTime;	
	private int slotSize;
	private Set<Bookings> bookingses = new HashSet<Bookings>(0);
	private Set<UserProfile> userses = new HashSet<UserProfile>(0);

	public AppointmentSchedule() {
	}

	public AppointmentSchedule(UserProfile users, String workingDays, int slotSize) {
		this.users = users;
		this.workingDays = workingDays;
		this.slotSize = slotSize;
	}

	public AppointmentSchedule(UserProfile users, String workingDays, Date startTime, Date endTime, int slotSize,
			Set<Bookings> bookingses, Set<UserProfile> userses) {
		this.users = users;
		this.workingDays = workingDays;
		this.startTime = startTime;
		this.endTime = endTime;
		this.slotSize = slotSize;
		this.bookingses = bookingses;
		this.userses = userses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name = "doctors_id", nullable = false)
	public UserProfile getUsers() {
		return this.users;
	}

	public void setUsers(UserProfile users) {
		this.users = users;
	}

	@Column(name = "working_days", nullable = false, length = 50)
	public String getWorkingDays() {
		return this.workingDays;
	}

	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
	@Column(name = "start_time", length = 20)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
	@Column(name = "end_time", length = 20)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "slot_size", nullable = false)
	public int getSlotSize() {
		return this.slotSize;
	}

	public void setSlotSize(int slotSize) {
		this.slotSize = slotSize;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appointmentSchedule",cascade = {CascadeType.ALL})
	public Set<Bookings> getBookingses() {
		return this.bookingses;
	}

	public void setBookingses(Set<Bookings> bookingses) {
		this.bookingses = bookingses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appointmentSchedule",cascade = {CascadeType.ALL})
	public Set<UserProfile> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<UserProfile> userses) {
		this.userses = userses;
	}

}
