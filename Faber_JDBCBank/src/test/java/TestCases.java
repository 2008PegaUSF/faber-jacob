import java.sql.SQLException;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import data.*;
import beans.*;


//Users with usernames under 5 characters in length cannot be made via the app, making accounts with small names usable in testing.
public class TestCases {
	
	//Create an initial Test User.
	@BeforeAll
	public static void initializeTests() throws SQLException {
		UserDaoImpl ud = new UserDaoImpl();
		if(ud.getUserByUsername("test") == null) {
			ud.createNewCustomer("test", "password", "Test User", 20);
		}
	}
	
	@AfterAll
	public static void deleteTestAccount() throws SQLException {
		UserDaoImpl ud = new UserDaoImpl();
		if(ud.getUserByUsername("test") == null) {
			ud.deleteUserByUsername("test");
		}
		
	}
	
	@Test
	public static void testUpdatePassword() throws SQLException {
		UserDaoImpl ud = new UserDaoImpl();
		ud.updatePassword("test", "newpass");
		User result = ud.getUserByUsername("test");
		Assert.assertEquals("newpass",result.getPassword());
	}
	
	@Test
	public static void testUpdateLegalName() throws SQLException {
		UserDaoImpl ud = new UserDaoImpl();
		ud.updateLegalName("test", "New Legal Name");
		User result = ud.getUserByUsername("test");
		Assert.assertEquals("New Legal Name", result.getLegalName());
	}
	
	@Test
	public static void testUpdateAge() throws SQLException {
		UserDaoImpl ud = new UserDaoImpl();
		ud.updateAge("test", 30);
		User result = ud.getUserByUsername("test");
		Assert.assertEquals(30, result.getAge());
	}
}
