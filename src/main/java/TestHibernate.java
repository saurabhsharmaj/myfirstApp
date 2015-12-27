import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.mahdi.myapp.model.AppointmentSchedule;
import com.mahdi.myapp.model.BookingStatus;
import com.mahdi.myapp.model.Bookings;
import com.mahdi.myapp.model.Specialization;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.model.UserRoles;

public class TestHibernate {

	public static void main(String[] args) {
		SessionFactory factor = createSessionFactory();
		Session session = factor.openSession();
		/*String hql = "select u from UserProfile u join fetch u.userRole ur";
		Query query = session.createQuery(hql);
		List<UserProfile> list = query.list();
		
		ObjectMapper mapper = new ObjectMapper();
		// for Hibernate 4.x:
		mapper.registerModule(new Hibernate4Module());
		
		
			System.out.println(mapper.valueToTree(list));*/
		UserProfile d = new UserProfile();
		d.setId(3);
		List<Bookings> booking = test(session,"patient",d);
		
		for (Bookings b : booking) {
			System.out.println(b.getUsersByDoctorId().getUsername());
		}
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
