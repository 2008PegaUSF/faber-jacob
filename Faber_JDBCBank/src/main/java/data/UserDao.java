package data;

import java.util.List;
import java.sql.SQLException;

import beans.User;

public interface UserDao {
	//Get users
	public List<User> getAllUsers();
	public User getUserByUsername(String username);
	public User getUserById(int id);
	public List<User> getOwnersOfAccount(int accId);
	
	//Create user
	public void createNewCustomer(String username, String password, String legalName, int age) throws SQLException;
	
	//Update user info
	public void updateUsername(String oldUsername, String newUsername);
	public void updatePassword(String username, String newPassword);
	public void updateLegalName(String username, String newLegalName);
	public void updateAge(String username, int newAge);
	
	//Delete user
	public void deleteUserByUsername(String username);
}
