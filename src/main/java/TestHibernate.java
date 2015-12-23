import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.mahdi.myapp.model.AppointmentSchedule;
import com.mahdi.myapp.model.BookingStatus;
import com.mahdi.myapp.model.Bookings;
import com.mahdi.myapp.model.Specialization;
import com.mahdi.myapp.model.UserProfile;
import com.mahdi.myapp.model.UserRole;

public class TestHibernate {

	public static void main(String[] args) {
		SessionFactory factor = createSessionFactory();
		Session session = factor.openSession();
		String hql = "select u from UserProfile u join fetch u.userRole ur";
		Query query = session.createQuery(hql);
		List<UserProfile> list = query.list();
		
		ObjectMapper mapper = new ObjectMapper();
		// for Hibernate 4.x:
		mapper.registerModule(new Hibernate4Module());
		
		
			System.out.println(mapper.valueToTree(list));
		
		
	}

	
	private static SessionFactory createSessionFactory() {
	      AnnotationConfiguration configuration = new AnnotationConfiguration();
	      configuration.addAnnotatedClass(AppointmentSchedule.class)
	      .addAnnotatedClass(Bookings.class)
	      .addAnnotatedClass(BookingStatus.class)
	      .addAnnotatedClass(Specialization.class)
	      .addAnnotatedClass(UserProfile.class)
	        .addAnnotatedClass(UserRole.class);
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
