package com.mahdi.myapp.service;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.AppointmentSchedule;

public interface IAppointmentScheduleService extends IService<AppointmentSchedule> {

	AppointmentSchedule getAppointmentScheduleByDoctor(Integer doctorId) throws DocException;

}
