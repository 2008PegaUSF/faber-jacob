import java.sql.SQLException;

//import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import data.*;
import beans.*;


//Users with usernames under 5 characters in length cannot be made via the app, making accounts with small names usable in testing.
//The test bank account really shouldn't be tested this way in a real setting, but it belongs to the test user and will be deleted when the test user is.
public class TestCases {
	static int testUserId;
	static int testAccountId;
	//Create an initial Test User.
	@BeforeAll
	public static void createTestUser() throws SQLException {
		UserDaoImpl ud = new UserDaoImpl();
		BankAccountDaoImpl bd = new BankAccountDaoImpl();
		if(ud.getUserByUsername("test") == null) {
			ud.createNewCustomer("test", "password", "Test User", 20);
		}
		testUserId = ud.getUserByUsername("test").getUserID();
		bd.createNewAccount(testUserId);
	}
	
	//Delete the test user after tests are run. This should work even if the test user's username is changed.
	@AfterAll
	public static void deleteTestUser() throws SQLException {
		UserDaoImpl ud = new UserDaoImpl();
		if(ud.getUserById(testUserId) != null) {
			ud.deleteUserByUsername(ud.getUserById(testUserId).getUsername());
		}
	}
	
	// === UserDaoImpl tests ===
	
	//Update the username of the test user.
	@Test
	void testUserUpdateUsername() throws SQLException {
		UserDaoImpl ud = new UserDaoImpl();
		System.out.println("=== Test UserDaoImpl: updateUsername ===");
		System.out.println("Before: " + ud.getUserById(testUserId).getPassword());
		User testUser = ud.getUserById(testUserId);
		ud.updateUsername(testUser.getUsername(), "new");
		User result = ud.getUserById(testUserId);
		System.out.println("After: " + result.getUsername());
		System.out.println("Expected: new");
		Assertions.assertEquals("new",result.getUsername());
	}
	
	//Update the password of the test user.
	@Test
	void testUserUpdatePassword() throws SQLException {
		UserDaoImpl ud = new UserDaoImpl();
		System.out.println("=== Test UserDaoImpl: updatePassword ===");
		System.out.println("Before: " + ud.getUserById(testUserId).getPassword());
		User testUser = ud.getUserById(testUserId);
		ud.updatePassword(testUser.getUsername(), "newpass");
		User result = ud.getUserById(testUserId);
		System.out.println("After: " + result.getPassword());
		System.out.println("Expected: newpass");
		Assertions.assertEquals("newpass",result.getPassword());
	}
	
	//Update the legal name of the test user.
	@Test
	void testUserUpdateLegalName() throws SQLException {
		UserDaoImpl ud = new UserDaoImpl();
		System.out.println("=== Test UserDaoImpl: updateLegalName ===");
		System.out.println("Before: " + ud.getUserById(testUserId).getLegalName());
		User testUser = ud.getUserById(testUserId);
		ud.updateLegalName(testUser.getUsername(), "New Legal Name");
		User result = ud.getUserById(testUserId);
		System.out.println("After: " + result.getLegalName());
		System.out.println("Expected: New Legal Name");
		Assertions.assertEquals("New Legal Name", result.getLegalName());
	}
	
	//Update the age of the test user.
	@Test
	void testUserUpdateAge() throws SQLException {
		UserDaoImpl ud = new UserDaoImpl();
		System.out.println("=== Test UserDaoImpl: updateAge ===");
		System.out.println("Before: " + ud.getUserById(testUserId).getAge());
		User testUser = ud.getUserById(testUserId);
		ud.updateAge(testUser.getUsername(), 30);
		User result = ud.getUserById(testUserId);
		System.out.println("After: " + result.getLegalName());
		System.out.println("Expected: New Legal Name");
		Assertions.assertEquals(30, result.getAge());
	}
	
	// === BankAccountDaoImpl tests ===
	
	//Test if withdrawing from an account works. Input validation is done via java, so it's okay if this causes a negative balance.
	@Test
	void testAccountWithdrawal() throws SQLException {
		BankAccountDaoImpl bd = new BankAccountDaoImpl();
		BankAccount testAccount = bd.getAllBankAccountsByUserId(testUserId).get(0);
		System.out.println("=== Test BankAccountDaoImpl: withdrawFromAccount ===");
		System.out.println("Before: " + testAccount.getBalance() + " (it's okay if this is zero)");
		bd.withdrawFromAccount(testAccount.getID(), 10);
		BankAccount updatedAccount = bd.getAllBankAccountsByUserId(testUserId).get(0);
		System.out.println("After: " + updatedAccount.getBalance());
		double expectedAmount = testAccount.getBalance() - 10;
		System.out.println("Expected: " + expectedAmount);
		Assertions.assertEquals(expectedAmount, updatedAccount.getBalance());
	}
	
	//Test if depositing to an account works. Input validation is done via java, so it's okay if this moves from being a negative balance to zero.
	@Test
	void testAccountDeposit() throws SQLException {
		BankAccountDaoImpl bd = new BankAccountDaoImpl();
		BankAccount testAccount = bd.getAllBankAccountsByUserId(testUserId).get(0);
		System.out.println("=== Test BankAccountDaoImpl: withdrawFromAccount ===");
		System.out.println("Before: " + testAccount.getBalance() + " (it's okay if this is negative)");
		bd.depositToAccount(testAccount.getID(), 10);
		BankAccount updatedAccount = bd.getAllBankAccountsByUserId(testUserId).get(0);
		System.out.println("After: " + updatedAccount.getBalance());
		double expectedAmount = testAccount.getBalance() + 10;
		System.out.println("Expected: " + expectedAmount);
		Assertions.assertEquals(expectedAmount, updatedAccount.getBalance());
	}
}
