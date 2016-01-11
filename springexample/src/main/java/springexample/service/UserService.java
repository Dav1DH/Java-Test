/**
 * 
 */
package springexample.service;

import java.util.List;

import springexample.entity.User;

/**
 * @author David
 *
 */
public interface UserService {

	public long createUser(User user);
	public User updateUser(User user);
	public void deleteUser(int id);
	public List<User> getAllUsers();
	public User getUser(int id);	
	public List<User> getAllUsers(String userName);
	}
