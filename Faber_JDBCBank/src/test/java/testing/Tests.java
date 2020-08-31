package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import beans.User;
import data.UserDaoImpl;

class Tests {

	
	
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
		void testUpdatePassword() throws SQLException {
			UserDaoImpl ud = new UserDaoImpl();
			ud.updatePassword("test", "newpass");
			User result = ud.getUserByUsername("test");
			Assertions.assertEquals("newpass",result.getPassword());
		}

}
