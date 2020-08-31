import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.BankAccountDaoImpl;
import data.UserDaoImpl;

public class TestLab {
	public static void main(String[] args) throws SQLException {
		BankAccountDaoImpl bd = new BankAccountDaoImpl();
		UserDaoImpl ud = new UserDaoImpl();
		
		List<beans.BankAccount> accounts = new ArrayList<beans.BankAccount>();
		/*
		//All accounts
		System.out.println(bd.getAllBankAccounts());
		//All users
		System.out.println(ud.getAllUsers());
		//Accounts of user by ID
		System.out.println(bd.getAllBankAccountsByUserId(10));
		//Get user by username
		System.out.println(ud.getUserByUsername("admin1"));
		*/
		/*
		System.out.println("Before:\n"+ud.getAllUsers());
		ud.createNewCustomer("customer3", "password", "Other Person", 20);
		System.out.println("After:\n"+ud.getAllUsers());
		*/
		//System.out.println(ud.verifyLogin("customer1", "password1"));
		//ud.updateAge("funkierboy", 77);
		//ud.deleteUserByUsername("customer1");
		//bd.createNewAccount(14);
		//bd.depositToAccount(5, 15);
		//bd.withdrawFromAccount(5, 10);
		//bd.deleteAccount(5);
	}
}
