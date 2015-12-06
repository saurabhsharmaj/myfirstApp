package com.mahdi.myapp.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.Appointment;

@Repository
public class AppointmentDao extends BaseDao<Appointment>{

	public AppointmentDao() {
		super(Appointment.class);
	}
	
	public Integer saveAppointment(Appointment appintment) throws DocException {
		try {
			Session session = getSession();
			session.saveOrUpdate(appintment);
			Serializable id = session.getIdentifier(appintment);
			return Integer.valueOf(id.toString());
			
		} catch (HibernateException ex) {
			throw new DocException(HttpStatus.EXPECTATION_FAILED, ex);
		} catch (Exception ex) {
			throw new DocException(HttpStatus.FAILED_DEPENDENCY, ex);
		}
	}
	
	public List<Appointment> getAppointmentListByUserId(Integer id) throws DocException {
		try {
			Session session = getSession();
			@SuppressWarnings("unchecked")
			DetachedCriteria criteria = DetachedCriteria
					.forClass(typeParameterClass);			
			List<Appointment> list = criteria.getExecutableCriteria(session).add(Restrictions.eq("userId", id)).list();
			return (List<Appointment>) list;
		} catch (HibernateException ex) {
			throw new DocException(HttpStatus.EXPECTATION_FAILED, ex);
		} catch (Exception ex) {
			throw new DocException(HttpStatus.FAILED_DEPENDENCY, ex);
		}
	}
	
}
