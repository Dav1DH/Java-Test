/**
 * 
 */
package hibernatetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	private static String users = userHome + File.separator + "users.csv";
	private static String users_update = userHome + File.separator + "users_update.csv";
	private static String users_delete = userHome + File.separator + "users_delete.csv";

	
	
	private static List<User> readUsersCSV(String path) {
		
		List<User> userList = new ArrayList<User>();
        BufferedReader bufReader;
        String line;
        if(path.equals(users)) {
            try {
                bufReader = new BufferedReader(new FileReader(path));
                String csvSplitBy = ",";

                while ((line = bufReader.readLine()) != null) {
                    String[] list = line.split(csvSplitBy);
                    User user = new User();
                    user.setFirst_name(list[0]);
                    user.setLast_name(list[1]);
                    user.setEmail(list[2]);
                    user.setAge(Integer.parseInt(list[3]));
                    user.setSex(list[4]);
                    user.setHair_color(list[5]);
                    userList.add(user);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(path.equals(users_update)){
            try {
                bufReader = new BufferedReader(new FileReader(path));
                String csvSplitBy = ",";

                while ((line = bufReader.readLine()) != null) {
                    String[] list = line.split(csvSplitBy);
                    User user = new User();
                    user.setEmail(list[0]);
                    user.setAge(Integer.parseInt(list[1]));
                    userList.add(user);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(path.equals(users_delete)){
            try {
                bufReader = new BufferedReader(new FileReader(path));
                String csvSplitBy = ",";

                while ((line = bufReader.readLine()) != null) {
                    String[] list = line.split(csvSplitBy);
                    User user = new User();
                    user.setEmail(list[0]);
                    userList.add(user);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return userList;
    }	
	
	private static void writeUsersCSV(String path, List<User> users) {
		
		try
        {
            FileWriter writer = new FileWriter(path);
            for(User user :users){
                writer.append(user.getFirst_name());
                writer.append(',');
                writer.append(user.getLast_name());
                writer.append(',');
                writer.append(user.getEmail());
                writer.append(',');
                writer.append(user.getAge().toString());
                writer.append(',');
                writer.append(user.getSex());
                writer.append(',');
                writer.append(user.getHair_color());
                writer.append('\n');
            }
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
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
		usersUpdate.stream().forEach(Main::update);

		List<User> usersDelete = readUsersCSV(userHome + File.separator + "users_delete.csv");
		usersDelete.stream().forEach(Main::delete);

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
		System.out.println("Found " + users.size() + " User");
		return users;
	
}
	public static void update(User e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("update User set age = :age" + " where email = :email"); 
	    query.setParameter("age", e.getAge());
        query.setParameter("email", e.getEmail());
		query.executeUpdate();
		session.getTransaction().commit();
		System.out.println("Successfully updated " + e.toString());
	}

	
	
	public static void delete(User e)  {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully deleted " + e.toString());

	}
	
}