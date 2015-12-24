package com.mahdi.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahdi.myapp.dao.BookingDao;
import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.Bookings;

@Service
@Transactional
public class BookingService implements IBookingService {

	@Autowired
	BookingDao bookingDao;
	
	public int insertRow(Bookings t) throws DocException {
		return bookingDao.insertRow(t);
	}

	public List<Bookings> getList() throws DocException {
		return bookingDao.getList();
	}

	public Bookings getRowById(int id) throws DocException {
		return bookingDao.getRowById(id);
	}

	public Bookings getRowByName(String columnName, String value) {
		return bookingDao.getRowByColumnName(columnName, value);
	}

	public int updateRow(Bookings t) throws DocException {
		return bookingDao.updateRow(t);
	}

	public int deleteRow(int id) throws DocException {
		return bookingDao.deleteRow(id);
	}
}
