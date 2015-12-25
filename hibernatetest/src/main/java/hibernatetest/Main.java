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
import java.util.ArrayList;
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

	private static List<User> readUsersCSV(String path) {
		return new ArrayList<>();
	}

	private static void writeUsersCSV(String path, List<User> users) {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// read the home folder
		String userHome = System.getProperty("user.home");

		// read and parse the .csv file
		List<User> userCreate = readUsersCSV(userHome + File.separator + "users.csv");
		// store the users in the database
		userCreate.stream().forEach(Main::create);

		List<User> usersUpdate = readUsersCSV(userHome + File.separator + "users_update.csv");
//		usersUpdate.stream().forEach(Main::update);

		List<User> usersDelete = readUsersCSV(userHome + File.separator + "users_delete.csv");
//		usersDelete.stream().forEach(Main::delete);

		List<User> userExport = read();
		writeUsersCSV(userHome + File.separator + "users_new.csv", userExport);

	}

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static Integer create(User e) {
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		System.out.println("Successfully created " + e.toString());
		return e.getId();

	}

	@SuppressWarnings("unchecked")
	public static List<User> read() {
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<User> users = session.createQuery("FROM User").list();
		session.close();
		System.out.println("Found " + users.size() + " User");
		return users;
	}

}