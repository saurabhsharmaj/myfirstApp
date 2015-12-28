
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mahdi.myapp.model.AppointmentSchedule;
import com.mahdi.myapp.model.BookingStatus;
import com.mahdi.myapp.model.Bookings;
import com.mahdi.myapp.model.Specialization;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.model.UserRoles;

public class TestHibernate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		
		SessionFactory factor = createSessionFactory();
		Session session = factor.openSession();
		String hql = "select u from UserProfile u join fetch u.userRoles ur where ur.id  = 2 and u.id=2";
		Query query = session.createQuery(hql);
		List<UserProfile> list = query.list();
		UserProfile doctor = null;
		for (UserProfile userProfile : list) {
							
			doctor= userProfile;
			
		}
	try{
		System.out.print(doctor.getId() +" - "+ doctor.getUsername());
		System.out.println(" -  "+doctor.getAppointmentSchedule().getSlotSize());
	}catch(Exception ex){
		System.out.println();
	}
	session.getTransaction().begin();
	session.evict(doctor);
	AppointmentSchedule as = new AppointmentSchedule();
	as.setId(doctor.getId());
	as.setUserProfile(doctor);
	as.setSlotSize(400);
	doctor.setAppointmentSchedule(as);
//	doctor.setFullname("doctor"+new Date().getTime());
	
		session.saveOrUpdate(doctor);
	
	session.getTransaction().commit();	
	
	}

	private static List<Bookings> test(Session session, String keyword, UserProfile doctorProfile){

		
		List<Bookings> list = null ;
		@SuppressWarnings("unchecked")
		DetachedCriteria deCriteria = DetachedCriteria
				.forClass(Bookings.class);	
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
	
	}

	
	private static SessionFactory createSessionFactory() {
	      AnnotationConfiguration configuration = new AnnotationConfiguration();
	      configuration.addAnnotatedClass(AppointmentSchedule.class)
	      .addAnnotatedClass(Bookings.class)
	      .addAnnotatedClass(BookingStatus.class)
	      .addAnnotatedClass(Specialization.class)
	      .addAnnotatedClass(UserProfile.class)
	        .addAnnotatedClass(UserRoles.class);
	      configuration.setProperty("hibernate.dialect",
	        "org.hibernate.dialect.MySQL5Dialect");
	      configuration.setProperty("hibernate.connection.driver_class",
	        "com.mysql.jdbc.Driver");
	      configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/getdoc");
	      configuration.setProperty("hibernate.connection.username", "root");
	      configuration.setProperty("hibernate.connection.password", "Dex@123");
	      configuration.setProperty("hibernate.show_sql", "true");
	      configuration.setProperty("hibernate.format_sql", "true");
	     
	      SessionFactory sessionFactory = configuration.buildSessionFactory();
	      return sessionFactory;
	     }
}
