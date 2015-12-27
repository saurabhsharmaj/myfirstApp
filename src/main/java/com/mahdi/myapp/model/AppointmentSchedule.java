package com.mahdi.myapp.model;
// Generated Dec 25, 2015 6:16:27 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * AppointmentSchedule generated by hbm2java
 */
@Entity
@Table(name = "appointment_schedule")
public class AppointmentSchedule implements java.io.Serializable {

	private Integer id;
	private UserProfile users;
	private String workingDays;
	private Date startTime;
	private Date endTime;
	private int slotSize;

	public AppointmentSchedule() {
	}

	public AppointmentSchedule(UserProfile users, String workingDays, int slotSize) {
		this.users = users;
		this.workingDays = workingDays;
		this.slotSize = slotSize;
	}

	public AppointmentSchedule(UserProfile users, String workingDays, Date startTime, Date endTime, int slotSize) {
		this.users = users;
		this.workingDays = workingDays;
		this.startTime = startTime;
		this.endTime = endTime;
		this.slotSize = slotSize;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "users") )
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
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
	@DateTimeFormat(pattern="dd/MM/yyyy hh:mm a")
	@Column(name = "start_time", length = 19)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy hh:mm a")
	@Column(name = "end_time", length = 19)
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

}
