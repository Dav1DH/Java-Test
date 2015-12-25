/**
 * 
 */
package hibernatetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import hibernatetest.User;

/**
 * @author David
 *
 */
public class Main {
	
		private static String userHome = System.getProperty("user.home");
		private static String userCsv = userHome + "/users.csv";
		private static String updateCsv = userHome + "/users_update.csv";
		private static String deleteCsv = userHome + "/users_delete.csv";
		private static String usersNewCsv = userHome + "/user_new.csv";
		private static BufferedReader br;
		private static String line;
		
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		User user1 = new User("Bill","Gates","billgates@microsoft.com" , 60, "male", "brown");
		create(user1);
		List<User> users = read();
		for(User e: users) {
			System.out.println(e.toString());
		}
	}	

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(builder.build());
		return sessionFactory;
}
	public static Integer create(User e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully created " + e.toString());
		return e.getId();

	}
	public static List<User> read() {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<User> users = session.createQuery("FROM User").list();
		session.close();
		System.out.println("Found " + users.size() + " User");
		return users;
}
		
}