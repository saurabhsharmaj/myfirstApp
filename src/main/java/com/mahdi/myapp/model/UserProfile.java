package com.mahdi.myapp.model;
// Generated Dec 24, 2015 4:41:42 PM by Hibernate Tools 4.3.1.Final

import static javax.persistence.GenerationType.IDENTITY;

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
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", catalog = "getdoc")
@SQLDelete(sql="UPDATE users SET enabled = '0' WHERE id = ?")
@Where(clause="enabled = '1'")
public class UserProfile implements java.io.Serializable {

	private Integer id;
	private AppointmentSchedule appointmentSchedule;
	private UserRoles userRoles;
	private String fullname;
	private Integer specializationId;
	private String clinicName;
	private String address;
	private String qualification;
	private Integer rating;
	private Integer age;
	private Float expirence;
	private String email;
	private String contact;
	private String username;
	private String password;
	private String profilePicUrl;
	private Byte enabled;
	private String summary;
	private Set<Bookings> bookingsesForDoctorId = new HashSet<Bookings>(0);
	private Set<Bookings> bookingsesForPatientId = new HashSet<Bookings>(0);
	private Set<AppointmentSchedule> appointmentSchedules = new HashSet<AppointmentSchedule>(0);
	
	
	private Set<Bookings> allBooking = new HashSet<Bookings>(0);
	

	public UserProfile() {
	}

	public UserProfile(UserRoles userRoles, String username, String password) {
		this.userRoles = userRoles;
		this.username = username;
		this.password = password;
	}

	public UserProfile(AppointmentSchedule appointmentSchedule, UserRoles userRoles, String fullname,
			Integer specializationId, String clinicName, String address, String qualification, Integer rating,
			Integer age, Float expirence, String email, String contact, String username, String password,
			String profilePicUrl, Byte enabled, String summary, Set<Bookings> bookingsesForDoctorId,
			Set<Bookings> bookingsesForPatientId, Set<AppointmentSchedule> appointmentSchedules) {
		this.appointmentSchedule = appointmentSchedule;
		this.userRoles = userRoles;
		this.fullname = fullname;
		this.specializationId = specializationId;
		this.clinicName = clinicName;
		this.address = address;
		this.qualification = qualification;
		this.rating = rating;
		this.age = age;
		this.expirence = expirence;
		this.email = email;
		this.contact = contact;
		this.username = username;
		this.password = password;
		this.profilePicUrl = profilePicUrl;
		this.enabled = enabled;
		this.summary = summary;
		this.bookingsesForDoctorId = bookingsesForDoctorId;
		this.bookingsesForPatientId = bookingsesForPatientId;
		this.appointmentSchedules = appointmentSchedules;
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

	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@JoinColumn(name = "appointmentSchedule")
	public AppointmentSchedule getAppointmentSchedule() {
		return this.appointmentSchedule;
	}

	public void setAppointmentSchedule(AppointmentSchedule appointmentSchedule) {
		this.appointmentSchedule = appointmentSchedule;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role", nullable = false)
	public UserRoles getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(UserRoles userRoles) {
		this.userRoles = userRoles;
	}

	@Column(name = "fullname", length = 50)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "specialization_id")
	public Integer getSpecializationId() {
		return this.specializationId;
	}

	public void setSpecializationId(Integer specializationId) {
		this.specializationId = specializationId;
	}

	@Column(name = "clinic_name", length = 50)
	public String getClinicName() {
		return this.clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	@Column(name = "address", length = 1000)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "qualification", length = 50)
	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	@Column(name = "rating")
	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "expirence", precision = 12, scale = 0)
	public Float getExpirence() {
		return this.expirence;
	}

	public void setExpirence(Float expirence) {
		this.expirence = expirence;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "contact", length = 15)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "username", nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "profilePicUrl", length = 50)
	public String getProfilePicUrl() {
		return this.profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	@Column(name = "enabled")
	public Byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Byte enabled) {
		this.enabled = enabled;
	}

	@Column(name = "summary", length = 65535)
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usersByDoctorId",cascade = {CascadeType.ALL})
	public Set<Bookings> getBookingsesForDoctorId() {
		return this.bookingsesForDoctorId;
	}

	public void setBookingsesForDoctorId(Set<Bookings> bookingsesForDoctorId) {
		this.bookingsesForDoctorId = bookingsesForDoctorId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usersByPatientId",cascade = {CascadeType.ALL})
	public Set<Bookings> getBookingsesForPatientId() {
		return this.bookingsesForPatientId;
	}

	public void setBookingsesForPatientId(Set<Bookings> bookingsesForPatientId) {
		this.bookingsesForPatientId = bookingsesForPatientId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users",cascade = {CascadeType.ALL})
	public Set<AppointmentSchedule> getAppointmentSchedules() {
		return this.appointmentSchedules;
	}

	public void setAppointmentSchedules(Set<AppointmentSchedule> appointmentSchedules) {
		this.appointmentSchedules = appointmentSchedules;
	}

	@Transient
	public Set<Bookings> getAllBooking() {
		return allBooking;
	}

	public void setAllBooking(Set<Bookings> allBooking) {
		this.allBooking = allBooking;
	}

	
}
