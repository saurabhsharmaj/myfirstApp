package com.mahdi.myapp.dao;

import java.util.List;

import javax.print.DocPrintJob;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.mahdi.myapp.exception.DocException;
import com.mahdi.myapp.model.Bookings;
import com.mahdi.myapp.model.UserProfile;

@Repository
public class BookingDao extends BaseDao<Bookings> implements Dao<Bookings> {

	public BookingDao() {
		super(Bookings.class);
	}

	public List<Bookings> getAppointmentList(Integer id, boolean isDoctor) throws DocException {
		try {
			Session session = getSession();
			List<Bookings> list = null ;
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
			return (List<Bookings>) list;
		} catch (HibernateException ex) {
			throw new DocException(HttpStatus.EXPECTATION_FAILED, ex);
		} catch (Exception ex) {
			throw new DocException(HttpStatus.FAILED_DEPENDENCY, ex);
		}
	}

	public List<Bookings> findAppointment(String keyword, UserProfile doctorProfile) throws DocException {
		try {
			Session session = getSession();
			List<Bookings> list = null ;
			@SuppressWarnings("unchecked")
			DetachedCriteria deCriteria = DetachedCriteria
					.forClass(typeParameterClass);	
			Criteria criteria = deCriteria.getExecutableCriteria(session);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);			
			criteria.createAlias("usersByPatientId", "patient");
			criteria.createAlias("usersByDoctorId", "doctor");
			criteria.add(
						 Restrictions.disjunction()
						 .add(Restrictions.like("patient.username", "%"+keyword+"%"))
					      .add(Restrictions.like("patient.fullname", "%"+keyword+"%"))
					      .add(Restrictions.like("patient.email", "%"+keyword+"%"))
					      .add(Restrictions.like("patient.contact", "%"+keyword+"%"))
						);
			criteria.add(Restrictions.eq("doctor.id", doctorProfile.getId()));
				list = criteria.list();
			
			return (List<Bookings>) list;
		} catch (HibernateException ex) {
			throw new DocException(HttpStatus.EXPECTATION_FAILED, ex);
		} catch (Exception ex) {
			throw new DocException(HttpStatus.FAILED_DEPENDENCY, ex);
		}
	}
}
