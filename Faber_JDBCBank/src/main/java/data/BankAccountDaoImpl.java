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
	public List<BankAccount> getAllBankAccounts() throws SQLException {
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
	}

	//Returns a list of every BankAccount owned by a user, so long as they are one of its owners.
	public List<BankAccount> getAllBankAccountsByUserId(int id) throws SQLException {
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
	}
	
	//Subtracts from an account's balance. Input validation is done via the app.
	public void withdrawFromAccount(int id, double amount) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "update bankaccount set accbalance = accbalance - ? where accid = ?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDouble(1, amount);
		ps.setInt(2, id);
		ps.execute();
	}

	//Adds to an account's balance. Input validation is done via the app.
	public void depositToAccount(int id, double amount) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "update bankaccount set accbalance = accbalance + ? where accid = ?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDouble(1, amount);
		ps.setInt(2, id);
		ps.execute();
	}

	//Creates a new BankAccount tied to a user.
	public void createNewAccount(int userId) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "select new_single_account(?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userId);
		ps.execute();
	}
	
	//Creates a new BankAccount tied to two users.
	public void createNewJointAccount(int userId, int jointId) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "select new_joint_account(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userId);
		ps.setInt(2, jointId);
		ps.execute();
	}

	//Deletes an account and its related UserAccount entries.
	public void deleteAccount(int id) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "delete from bankaccount where accid = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();
	}

	//Returns a single BankAccount with a given ID.
	public BankAccount getBankAccountById(int id) throws SQLException {
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
	}

}

