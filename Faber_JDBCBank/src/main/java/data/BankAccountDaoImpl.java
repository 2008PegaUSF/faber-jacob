package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.BankAccount;

public class BankAccountDaoImpl implements BankAccountDao {
	public static ConnFactory cf = ConnFactory.getInstance();

	//Returns a list of every BankAccount in existence.
	public List<BankAccount> getAllBankAccounts() {
		try {
			List<BankAccount> accountList = new ArrayList<BankAccount>();
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from bankaccount order by accid");
			BankAccount b = null;
			while(rs.next()) {
				b = new BankAccount(rs.getInt(1), rs.getDouble(2));
				accountList.add(b);
			}
			return accountList;
		} catch (SQLException e) {
			System.out.println("Database connection failed. Please check your internet connection.");
			return null;
		}
	}

	//Returns a list of every BankAccount owned by a user, so long as they are one of its owners.
	public List<BankAccount> getAllBankAccountsByUserId(int id) {
		try {
			List<BankAccount> accountList = new ArrayList<BankAccount>();
			Connection conn = cf.getConnection();
			String sql = "select * from bankaccount where accid in (select accid from useraccount where uid = ?) order by accid";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			BankAccount b = null;
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				b = new BankAccount(rs.getInt(1), rs.getDouble(2));
				accountList.add(b);
			}
			return accountList;
		} catch (SQLException e) {
			System.out.println("Database connection failed. Pleae check your internet connection.");
			return null;
		}
		
	}
	
	//Subtracts from an account's balance. Input validation is done via the app.
	public void withdrawFromAccount(int id, double amount) throws SQLException {
		try {
			Connection conn = cf.getConnection();
			String sql = "update bankaccount set accbalance = accbalance - ? where accid = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setInt(2, id);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Database connection failed. Pleae check your internet connection.");
		}
		
	}

	//Adds to an account's balance. Input validation is done via the app.
	public void depositToAccount(int id, double amount) {
		try {
			Connection conn = cf.getConnection();
			String sql = "update bankaccount set accbalance = accbalance + ? where accid = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setInt(2, id);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Database connection failed. Pleae check your internet connection.");
		}
		
	}

	//Creates a new BankAccount tied to a user.
	public void createNewAccount(int userId) {
		try {
			Connection conn = cf.getConnection();
			String sql = "select new_single_account(?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Database connection failed. Pleae check your internet connection.");
		}
		
	}
	
	//Creates a new BankAccount tied to two users.
	public void createNewJointAccount(int userId, int jointId) {
		try {
			Connection conn = cf.getConnection();
			String sql = "select new_joint_account(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, jointId);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Database connection failed. Pleae check your internet connection.");
		}
		
	}

	//Deletes an account and its related UserAccount entries.
	public void deleteAccount(int id) throws SQLException {
		try {
			Connection conn = cf.getConnection();
			String sql = "delete from bankaccount where accid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Database connection failed. Pleae check your internet connection.");
		}
		
	}

	//Returns a single BankAccount with a given ID.
	public BankAccount getBankAccountById(int id) {
		try {
			Connection conn = cf.getConnection();
			String sql = "select * from bankaccount where accid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			BankAccount b = null;
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				b = new BankAccount(rs.getInt(1), rs.getDouble(2));
			}
			return b;
		} catch (SQLException e) {
			System.out.println("Database connection failed. Pleae check your internet connection.");
			return null;
		}
		
	}

}

