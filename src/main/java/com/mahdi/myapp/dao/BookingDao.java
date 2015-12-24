package com.mahdi.myapp.dao;

import org.springframework.stereotype.Repository;

import com.mahdi.myapp.model.Bookings;

@Repository
public class BookingDao extends BaseDao<Bookings> implements Dao<Bookings> {

	public BookingDao() {
		super(Bookings.class);
	}
}
