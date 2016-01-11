/**
 * 
 */
package springexample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springexample.dao.UserDAO;
import springexample.entity.User;
import springexample.service.UserService;

/**
 * @author David
 *
 */
	@Service
	@Transactional
	public class UserServiceImpl implements UserService {
		
		public UserServiceImpl() {
			System.out.println("UserServiceImpl()");
		}
		
	    @Autowired
	    private UserDAO userDAO;

	    @Override
	    public long createUser(User user) {
	        return userDAO.createUser(user);
	    }
	    @Override
	    public User updateUser(User user) {
	        return userDAO.updateUser(user);
	    }
	    @Override
	    public void deleteUser(int id) {
	        userDAO.deleteUser(id);
	    }
	    @Override
	    public List<User> getAllUsers() {
	        return userDAO.getAllUsers();
	    }
	    @Override
	    public User getUser(int id) {
	        return userDAO.getUser(id);
	    }    
	    @Override
	    public List<User> getAllUsers(String userName) {
	    	return userDAO.getAllUsers(userName);
	    }
	}

