package com.mahdi.myapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="appointment_doctor")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	@Column(name="userId")
	private UserProfile user;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	@Column(name="doctorId")
	private UserProfile doctor;
	
	private Date appointmentDate;
	
	private Integer status;

	public Appointment(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

	public UserProfile getDoctor() {
		return doctor;
	}

	public void setDoctor(UserProfile doctor) {
		this.doctor = doctor;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}	
	
}
