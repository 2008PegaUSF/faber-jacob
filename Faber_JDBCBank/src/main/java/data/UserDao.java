package data;

import java.util.List;
import java.sql.SQLException;

import beans.User;

public interface UserDao {
	//Get users
	public List<User> getAllUsers()throws SQLException;
	public User getUserByUsername(String username)throws SQLException;
	public List<User> getOwnersOfAccount(int accId) throws SQLException;
	
	//Create user
	public void createNewCustomer(String username, String password, String legalName, int age) throws SQLException;
	
	//Update user info
	public void updateUsername(String oldUsername, String newUsername)throws SQLException;
	public void updatePassword(String username, String newPassword)throws SQLException;
	public void updateLegalName(String username, String newLegalName)throws SQLException;
	public void updateAge(String username, int newAge)throws SQLException;
	
	//Delete user
	public void deleteUserByUsername(String username)throws SQLException;
}
