package com.mahdi.myapp.dao;

import org.springframework.stereotype.Repository;

import com.mahdi.myapp.model.AppointmentSchedule;

@Repository
public class AppointmentScheduleDao extends BaseDao<AppointmentSchedule> implements Dao<AppointmentSchedule> {

	public AppointmentScheduleDao() {
		super(AppointmentSchedule.class);
	}
}
