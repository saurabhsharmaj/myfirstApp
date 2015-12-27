package com.mahdi.myapp.service;

import java.util.List;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.Bookings;

public interface IBookingService extends IService<Bookings> {

	public List<Bookings> getAppointmentList(Integer id, boolean isDoctor) throws DocException;
	
}
