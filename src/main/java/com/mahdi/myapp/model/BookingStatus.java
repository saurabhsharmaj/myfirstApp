package com.mahdi.myapp.model;
// default package
// Generated Dec 23, 2015 6:53:54 PM by Hibernate Tools 4.3.1.Final

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

/**
 * BookingStatus generated by hbm2java
 */
@Entity
@Table(name = "booking_status")
public class BookingStatus implements java.io.Serializable {

	private Integer id;
	private String name;
	private Set<Bookings> bookingses = new HashSet<Bookings>(0);

	public BookingStatus() {
	}

	public BookingStatus(String name) {
		this.name = name;
	}

	public BookingStatus(String name, Set<Bookings> bookingses) {
		this.name = name;
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

	@Column(name = "name", nullable = false, length = 25)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookingStatus")
	public Set<Bookings> getBookingses() {
		return this.bookingses;
	}

	public void setBookingses(Set<Bookings> bookingses) {
		this.bookingses = bookingses;
	}

}
