package com.mahdi.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahdi.myapp.dao.BookingStatusDao;
import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.BookingStatus;

@Service
@Transactional
public class BookingStatusService implements IBookingStatusService {

	@Autowired
	BookingStatusDao bookingDao;
	
	public int insertRow(BookingStatus t) throws DocException {
		return bookingDao.insertRow(t);
	}

	public List<BookingStatus> getList() throws DocException {
		return bookingDao.getList();
	}

	public BookingStatus getRowById(int id) throws DocException {
		return bookingDao.getRowById(id);
	}

	public BookingStatus getRowByName(String columnName, String value) {
		return bookingDao.getRowByColumnName(columnName, value);
	}

	public int updateRow(BookingStatus t) throws DocException {
		return bookingDao.updateRow(t);
	}

	public int deleteRow(int id) throws DocException {
		return bookingDao.deleteRow(id);
	}

	public List<BookingStatus> getRowsByName(String columnName, String value) {
		return bookingDao.getRowsByColumnName(columnName, value);
	}
}
