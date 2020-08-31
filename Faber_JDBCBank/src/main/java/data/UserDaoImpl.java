package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.BankAccount;
import beans.User;

public class UserDaoImpl implements UserDao {
	public static ConnFactory cf = ConnFactory.getInstance();
	
	public List<User> getAllUsers(){
		try {
			List<User> userList = new ArrayList<User>();
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from bankuser");
			User u = null;
			while(rs.next()) {
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
				userList.add(u);
			}
			return userList;
		}catch (SQLException e) {
			System.out.println("Database connection failed. Please check your internet connection.");
			return null;
		}
		
	}

	public User getUserByUsername(String username){
		try {
			Connection conn = cf.getConnection();
			String sql = "select * from bankuser where uUsername = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			User u = null;
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
			}
			return u;
		}catch (SQLException e) {
			System.out.println("\"Database connection failed. Please check your internet connection.\"");
			return null;
		}
		
	}
	
	public User getUserById(int id){
		try {
			Connection conn = cf.getConnection();
			String sql = "select * from bankuser where uid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			User u = null;
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
			}
			return u;
		} catch (SQLException e) {
			System.out.println("Database connection failed. Please check your internet connection.");
			return null;
		}
	}
	
	
	public List<User> getOwnersOfAccount(int accId){
		try {
			List<User> ownerList = new ArrayList<User>();
			Connection conn = cf.getConnection();
			String sql = "select * from bankuser where uid in (select uid from useraccount where accid = ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accId);
			User u = null;
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
				ownerList.add(u);
			}
			return ownerList;
		}catch (SQLException e) {
			System.out.println("\"Database connection failed. Please check your internet connection.\"");
			return null;
		}
		
	}
	

	public void createNewCustomer(String username, String password, String legalName, int age)throws SQLException {
		try {
			Connection conn = cf.getConnection();
			String sql = "insert into bankuser (uusername, upassword, ulegalname, uage, usertypeid) values(?,?,?,?,1)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, legalName);
			ps.setInt(4, age);
			ps.execute();
		}catch (SQLException e) {
			System.out.println("Database connection failed. Please check your internet connection.");
		}
	}

	public void updateUsername(String oldUsername, String newUsername) {
		try {
			Connection conn = cf.getConnection();
			String sql = "update bankuser set uusername = ? where uusername = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newUsername);
			ps.setString(2, oldUsername);
			ps.execute();
		}catch (SQLException e) {
			System.out.println("Database connection failed. Please check your internet connection.");
		}
		
	}

	public void updatePassword(String username, String newPassword) {
		try {
			Connection conn = cf.getConnection();
			String sql = "update bankuser set uPassword = ? where uusername = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setString(2, username);
			ps.execute();
		}catch (SQLException e) {
			System.out.println("Database connection failed. Please check your internet connection.");
		}
		
	}

	public void updateLegalName(String username, String newLegalName) {
		try {
			Connection conn = cf.getConnection();
			String sql = "update bankuser set uLegalName = ? where uusername = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newLegalName);
			ps.setString(2, username);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Database connection failed. Please check your internet connection.");
		}
		
	}

	public void updateAge(String username, int newAge) {
		try {
			Connection conn = cf.getConnection();
			String sql = "update bankuser set uAge = ? where uusername = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, newAge);
			ps.setString(2, username);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Database connection failed. Please check your internet connection.");
		}
		
	}

	public void deleteUserByUsername(String username) {
		try {
			Connection conn = cf.getConnection();
			String sql = "select delete_user_by_name(?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Database connection failed. Please check your internet connection.");
		}
		
	}

}
