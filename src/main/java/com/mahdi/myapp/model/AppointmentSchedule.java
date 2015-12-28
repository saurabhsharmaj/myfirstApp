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
	private UserProfile userProfile;
	private String workingDays;
	private Date startTime;
	private Date endTime;
	private int slotSize;

	public AppointmentSchedule() {
	}

	public AppointmentSchedule(UserProfile userProfile, String workingDays, int slotSize) {
		this.userProfile = userProfile;
		this.workingDays = workingDays;
		this.slotSize = slotSize;
	}

	public AppointmentSchedule(UserProfile userProfile, String workingDays, Date startTime, Date endTime, int slotSize) {
		this.userProfile = userProfile;
		this.workingDays = workingDays;
		this.startTime = startTime;
		this.endTime = endTime;
		this.slotSize = slotSize;
	}

	
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "userProfile") )
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public UserProfile getUserProfile() {
		return this.userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Column(name = "working_days", length = 50)
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

	@Column(name = "slot_size")
	public int getSlotSize() {
		return this.slotSize;
	}

	public void setSlotSize(int slotSize) {
		this.slotSize = slotSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + slotSize;
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((userProfile == null) ? 0 : userProfile.hashCode());
		result = prime * result + ((workingDays == null) ? 0 : workingDays.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppointmentSchedule other = (AppointmentSchedule) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (slotSize != other.slotSize)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (userProfile == null) {
			if (other.userProfile != null)
				return false;
		} else if (!userProfile.equals(other.userProfile))
			return false;
		if (workingDays == null) {
			if (other.workingDays != null)
				return false;
		} else if (!workingDays.equals(other.workingDays))
			return false;
		return true;
	}

}
