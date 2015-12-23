package com.mahdi.myapp.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.AppointmentSchedule;

@Repository
public class AppointmentDao extends BaseDao<AppointmentSchedule>{

	public AppointmentDao() {
		super(AppointmentSchedule.class);
	}
	
	public Integer saveAppointment(AppointmentSchedule appintment) throws DocException {
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
	
	public List<AppointmentSchedule> getAppointmentList(Integer id, boolean isDoctor) throws DocException {
		try {
			Session session = getSession();
			List<AppointmentSchedule> list = null ;
			@SuppressWarnings("unchecked")
			DetachedCriteria deCriteria = DetachedCriteria
					.forClass(typeParameterClass);	
			Criteria criteria = deCriteria.getExecutableCriteria(session);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			if(isDoctor){
				criteria.createAlias("usersByDoctorId", "doctor");
				list = criteria.add(Restrictions.eq("doctor.id", id)).list();
			} else {
				criteria.createAlias("usersByPatientId", "patient");
				list = criteria.add(Restrictions.eq("patient.id", id)).list();
			}
			return (List<AppointmentSchedule>) list;
		} catch (HibernateException ex) {
			throw new DocException(HttpStatus.EXPECTATION_FAILED, ex);
		} catch (Exception ex) {
			throw new DocException(HttpStatus.FAILED_DEPENDENCY, ex);
		}
	}

	public List<AppointmentSchedule> findAppointment(String keyword, boolean isDoctor) throws DocException {
		try {
			Session session = getSession();
			List<AppointmentSchedule> list = null ;
			@SuppressWarnings("unchecked")
			DetachedCriteria deCriteria = DetachedCriteria
					.forClass(typeParameterClass);	
			Criteria criteria = deCriteria.getExecutableCriteria(session);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			if(isDoctor){
				criteria.createAlias("usersByDoctorId", "doctor");
				criteria.add(
						 Restrictions.disjunction()
					      .add(Restrictions.like("doctor.fullname", "%"+keyword+"%"))
					      .add(Restrictions.like("doctor.email", "%"+keyword+"%"))
					      .add(Restrictions.like("doctor.contact", "%"+keyword+"%"))
							);
				list = criteria.list();
			} else {
				criteria.createAlias("usersByPatientId", "patient");
				criteria.add(
						 Restrictions.disjunction()
					      .add(Restrictions.like("patient.fullname", "%"+keyword+"%"))
					      .add(Restrictions.like("patient.email", "%"+keyword+"%"))
					      .add(Restrictions.like("patient.contact", "%"+keyword+"%"))
							);
				list = criteria.list();
			}
			return (List<AppointmentSchedule>) list;
		} catch (HibernateException ex) {
			throw new DocException(HttpStatus.EXPECTATION_FAILED, ex);
		} catch (Exception ex) {
			throw new DocException(HttpStatus.FAILED_DEPENDENCY, ex);
		}
	}
	
}
