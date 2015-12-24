package com.mahdi.myapp.dao;

import org.springframework.stereotype.Repository;

import com.mahdi.myapp.model.BookingStatus;

@Repository
public class BookingStatusDao extends BaseDao<BookingStatus> implements Dao<BookingStatus> {

	public BookingStatusDao() {
		super(BookingStatus.class);
	}
}
