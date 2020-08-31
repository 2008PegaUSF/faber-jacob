package data;

import java.util.List;
import java.sql.SQLException;

import beans.BankAccount;

public interface BankAccountDao {
	//Simple get methods
	public List<BankAccount> getAllBankAccounts() throws SQLException;
	public BankAccount getBankAccountById(int id) throws SQLException;
	public List<BankAccount> getAllBankAccountsByUserId(int id) throws SQLException;
	
	public void withdrawFromAccount(int id, double amount)throws SQLException;
	public void depositToAccount(int id, double amount)throws SQLException;
	//No transfers mentioned in assignment
	public void createNewAccount(int userId)throws SQLException;
	public void createNewJointAccount(int userId, int jointId) throws SQLException;
	public void deleteAccount(int id)throws SQLException;
	
	
	
}
