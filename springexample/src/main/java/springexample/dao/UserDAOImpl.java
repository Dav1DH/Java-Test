/**
 * 
 */
package springexample.dao;

import springexample.dao.UserDAO;
import springexample.entity.User;
import springexample.util.HibernateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
/**
 * @author David
 *
 */
	@Repository
	public class UserDAOImpl implements UserDAO {
	    
		public UserDAOImpl() {
	    	System.out.println("UserDAOImpl");
	    }
		
		@Autowired
	    private HibernateUtil hibernateUtil;

	    @Override
	    public long createUser(User user) {        
	        return (Long) hibernateUtil.create(user);
	    }
	    
	    @Override
	    public User updateUser(User user) {        
	        return hibernateUtil.update(user);
	    }
	    
	    @Override
	    public void deleteUser(int id) {
	        User user = new User();
	        user.setId(id);
	        hibernateUtil.delete(user);
	    }
	    
	    @Override
	    public List<User> getAllUsers() {        
	        return hibernateUtil.fetchAll(User.class);
	    }
	    
	    @Override
	    public User getUser(int id) {
	        return hibernateUtil.fetchById(id, User.class);
	    }

		@SuppressWarnings("unchecked")
		@Override
		public List<User> getAllUsers(String userName) { 
			String query = "SELECT u.* FROM Users u WHERE u.name like '%"+ userName +"%'";
			List<Object[]> userObjects = hibernateUtil.fetchAll(query);
			List<User> users = new ArrayList<User>();
			for(Object[] userObject: userObjects) {
				User user = new User();
				Integer id = (int) ((Integer) userObject[0]).longValue();			
				String first_name = (String) userObject[1];
				String last_name = (String) userObject[2];
				Integer age = (Integer) userObject[3];
				String email = (String) userObject[4];
				String sex = (String) userObject[5];
				String hair_color = (String) userObject[6];
				
				user.setId(id);
				user.setFirst_name(first_name);
				user.setLast_name(last_name);
				user.setAge(age);
				user.setEmail(email);
				user.setSex(sex);
				user.setHair_color(hair_color);
				users.add(user);
			}
			System.out.println(users);
			return users;
		}
	}
