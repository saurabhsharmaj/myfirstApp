package com.mahdi.myapp.model;

// default package
// Generated Dec 23, 2015 6:53:54 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AppointmentSchedule generated by hbm2java
 */
@Entity
@Table(name = "appointment_schedule")
public class AppointmentSchedule implements java.io.Serializable {

	private Integer id;
	private int doctorsId;
	private String workingDays;
	private Date startTime;
	private Date endTime;
	private int slotSize;
	private Set<Bookings> bookingses = new HashSet<Bookings>(0);

	public AppointmentSchedule() {
	}

	public AppointmentSchedule(int doctorsId, String workingDays, int slotSize) {
		this.doctorsId = doctorsId;
		this.workingDays = workingDays;
		this.slotSize = slotSize;
	}

	public AppointmentSchedule(int doctorsId, String workingDays, Date startTime, Date endTime, int slotSize,
			Set<Bookings> bookingses) {
		this.doctorsId = doctorsId;
		this.workingDays = workingDays;
		this.startTime = startTime;
		this.endTime = endTime;
		this.slotSize = slotSize;
		this.bookingses = bookingses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "doctors_id", nullable = false)
	public int getDoctorsId() {
		return this.doctorsId;
	}

	public void setDoctorsId(int doctorsId) {
		this.doctorsId = doctorsId;
	}

	@Column(name = "working_days", nullable = false, length = 50)
	public String getWorkingDays() {
		return this.workingDays;
	}

	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "start_time", length = 8)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "end_time", length = 8)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appointmentSchedule")
	public Set<Bookings> getBookingses() {
		return this.bookingses;
	}

	public void setBookingses(Set<Bookings> bookingses) {
		this.bookingses = bookingses;
	}

}
