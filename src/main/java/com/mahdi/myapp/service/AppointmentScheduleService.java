package com.mahdi.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahdi.myapp.dao.AppointmentScheduleDao;
import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.AppointmentSchedule;

@Service
@Transactional
public class AppointmentScheduleService implements IAppointmentScheduleService {

	@Autowired
	AppointmentScheduleDao appointmentScheduleDao;
	
	public int insertRow(AppointmentSchedule t) throws DocException {
		return appointmentScheduleDao.insertRow(t);
	}

	public List<AppointmentSchedule> getList() throws DocException {
		return appointmentScheduleDao.getList();
	}

	public AppointmentSchedule getRowById(int id) throws DocException {
		return appointmentScheduleDao.getRowById(id);
	}

	public AppointmentSchedule getRowByName(String columnName, String value) {
		return appointmentScheduleDao.getRowByColumnName(columnName, value);
	}

	public int updateRow(AppointmentSchedule t) throws DocException {
		return appointmentScheduleDao.updateRow(t);
	}

	public int deleteRow(int id) throws DocException {
		return appointmentScheduleDao.deleteRow(id);
	}

	public AppointmentSchedule getAppointmentScheduleByDoctor(Integer doctorId) throws DocException {
		return appointmentScheduleDao.getRowByColumnName("doctors_id", doctorId.toString());
	}

}
