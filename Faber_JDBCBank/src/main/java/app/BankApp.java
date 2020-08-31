package app;

import java.io.InvalidClassException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import data.UserDaoImpl;
import data.BankAccountDaoImpl;

import beans.*;

/*
A registered user can login with their username and password (Done!)
An unregistered user can register by creating a username and password (Done!) 
An Admin can view, create, update, and delete all users.

A user can view their own existing accounts and balances. 
A user can create an (banking) account.
A user can delete an account if it is empty.  
A user can add to or withdraw from an account. 
A user can execute multiple deposits or withdrawals in a session.  (Done!)
A user can logout. (Done!)

 */



public class BankApp {
	
	//Takes input from a scanner and only allows a double with up to 2 digits of precision.
		public static Double validateInputDouble(Scanner in) {
			Double output = null;
			while(output == null) {
				try {
					output = Double.parseDouble(in.nextLine());
				}
				catch (NumberFormatException e) {
					//On invalid input, prompt the user to try again
					System.out.println("Invalid input. Please enter a number with up to 2 points of decmial precision.");
					System.out.print( "ICantTypeNumbersProperly>> ");
					output = null;
				}
			}
			return output;
		}
		
		//Validate input from the console, but check an input string first
		public static Integer validateInputInteger(String str, Scanner in) {
			Integer output = null;
			while(output == null) {
				try {
					output = Integer.parseInt(str);
				}
				catch (NumberFormatException e) {
					//On incorrect input, prompt the user to try again until they succeed
					System.out.println("Invalid input. Please enter a number.");
					System.out.print( "ICantTypeNumbersProperly>> ");
					output = null;
				}
			}
			return output;
		}
		
		//Takes input from a scanner and only allows an int.
		public static Integer validateInputInteger(Scanner in) {
			Integer output = null;
			while(output == null) {
				try {
					output = Integer.parseInt(in.nextLine());
				}
				catch (NumberFormatException e) {
					System.out.println("Invalid input. Please enter a number.");
					System.out.print( "ICantTypeNumbersProperly>> ");
					output = null;
				}
			}
			return output;
		}
	
		//Admin method to update a user.
		public static void updateUser(User currentUser, Scanner in) throws SQLException {
			UserDaoImpl ud = new UserDaoImpl();
			//User inputs Id of account to update
			System.out.println("Enter a user to update: ");
			System.out.print(currentUser.getUsername() + ">> ");
			String userToUpdate = in.nextLine();
			User foundUser = ud.getUserByUsername(userToUpdate);
			if(foundUser == null) {
				System.out.println("User not found.");
				return;
			}
			else {//User exists
				System.out.println("Choose a value to update:\n1: Username\n2: Password\n3: Legal Name\n4: Age\nEnter any other number to cancel");
				System.out.print(currentUser.getUsername() + ">> ");
				int userInput = validateInputInteger(in);
				switch(userInput) {
					case 1://Update username
						System.out.println("Enter a new username:");
						System.out.print(currentUser.getUsername() + ">> ");
						String newUsername = in.nextLine();
						if(ud.getUserByUsername(newUsername) == null) {//Username available
							ud.updateUsername(foundUser.getUsername(), newUsername);
							System.out.println("Username updated.");
							//If the admin updated their own username, set the username in the console to match
							if(newUsername.equals(currentUser.getUsername())) {
								currentUser.setUsername(newUsername);
							}
							return;
						}
						else {//Username not available, cancel
							System.out.println("Username already taken.");
							return;
						}
					case 2://Update password
						System.out.println("Enter a new password:");
						System.out.print(currentUser.getUsername() + ">> ");
						String newPassword = in.nextLine();
						if(newPassword.length() < 5) {//New password too short
							System.out.println("New password too short.");
							return;
						}
						else {//New password is long enough
							ud.updatePassword(foundUser.getUsername(), newPassword);
							System.out.println("Password updated.");
							return;
						}
					case 3://Update legal name
						System.out.println("Enter a new legal name:");
						System.out.print(currentUser.getUsername() + ">> ");
						ud.updateLegalName(foundUser.getUsername(), in.nextLine());
						System.out.println("Legal name updated.");
						break;
					case 4://Update age
						System.out.println("Enter a new age:");
						System.out.print(currentUser.getUsername() + ">> ");
						int newAge = validateInputInteger(in);
						if(newAge < 1) {//Ages of 0 or under are not allowed
							System.out.println("I'm not gonna accept that age.");
							return;
						}
						else if(newAge < 14) {//If the admin sets the age to under the minimum for registration, this is allowed as a harmless easter egg
							System.out.println("Darn kids sneaking into our database! Well, if you say so.");
						}
						ud.updateAge(foundUser.getUsername(), newAge);
						System.out.println("Age updated.");
						return;
					default:
						System.out.println("Update cancelled.");
						return;
				}
			}
		}
		
		public static void deleteUser(User currentUser, Scanner in) throws SQLException {
			UserDaoImpl ud = new UserDaoImpl();
			//User enters a username to delete
			System.out.println("Enter username of user to delete. This will also delete all of their bank accounts.");
			System.out.print(currentUser.getUsername() + ">> ");
			User foundUser = ud.getUserByUsername(in.nextLine());
			if(foundUser == null) {//No user with that username found
				System.out.println("User not found.");
			}
			else if(foundUser.getUsername().equals(currentUser.getUsername())) { //Trying to delete themselves
				System.out.println("No, don't delete yourself.");
			}
			else {//Deleting someone else
				ud.deleteUserByUsername(foundUser.getUsername());
				System.out.println("User deleted.");
			}
		}
		
		public static void viewAccounts(User currentUser, Scanner in) throws SQLException {
			BankAccountDaoImpl bd = new BankAccountDaoImpl();
			ArrayList<BankAccount> gatheredAccounts = null;
			switch(currentUser.getType()) {
			case 1://If the user is a customer, query only accounts owned by a customer
				gatheredAccounts = (ArrayList<BankAccount>) bd.getAllBankAccountsByUserId(currentUser.getUserID());
				break;
			case 2://If the user is an admin, query all accounts
				gatheredAccounts = (ArrayList<BankAccount>) bd.getAllBankAccounts();
				break;
			default://There should never be an account that is not a customer or admin
				System.out.println("How did we get here? Invalid account type.");
				return;
			}
			int column = 0;
			System.out.println("Accounts:");
			for(BankAccount acc : gatheredAccounts) {
				System.out.print(acc+" ");
				if(++column % 5 == 0) {//Start a new line after every 5th user printed
					System.out.println();
				}
			}
			System.out.println();
		}
		
		public static void createNewAccount(User currentUser, Scanner in) throws SQLException {
			UserDaoImpl ud = new UserDaoImpl();
			BankAccountDaoImpl bd = new BankAccountDaoImpl();
			System.out.println("Options:\n1: New Single Account\n2: New Joint Account\nOther number: cancel");
			System.out.print(currentUser.getUsername() + ">> ");
			int optionChosen = validateInputInteger(in);
			switch(optionChosen) {
			case 1://Single account, 1 user
				bd.createNewAccount(currentUser.getUserID());
				System.out.println("New account created.");
				break;
			case 2://Joint account, 2 users
				System.out.println("Enter the username of who you will be sharing the account with:");
				System.out.print(currentUser.getUsername() + ">> ");
				String otherUsername = in.nextLine();
				User otherUser = ud.getUserByUsername(otherUsername);
				if(otherUser == null) {//Joint user not found
					System.out.println("That user does not exist. Account creation cancelled.");
				}
				else {//Joint user found
					bd.createNewJointAccount(currentUser.getUserID(), otherUser.getUserID());
					System.out.println("New joint account created.");
				}
				break;
			default:
				System.out.println("Account creation cancelled.");
				break;
			}
			
			
		}
		
		public static void depositToAccount(User currentUser, Scanner in) throws SQLException {
			BankAccountDaoImpl bd = new BankAccountDaoImpl();
			UserDaoImpl ud = new UserDaoImpl();
			//User enters ID of account to deposit to
			System.out.println("Enter ID of account to deposit to: ");
			System.out.print(currentUser.getUsername() + ">> ");
			int accountId = validateInputInteger(in);
			BankAccount acc = bd.getBankAccountById(accountId);
			if(acc == null) {
				System.out.println("Account not found.");
			}
			else {//Account found
				if(currentUser.getType() == 1) {//If the user is a customer, check if they own the account first
					ArrayList<User> accountOwners = (ArrayList<User>) ud.getOwnersOfAccount(acc.getID());
					boolean userOwnsAccount = false;
					for(User owner : accountOwners) {
						if(owner.getUsername().equals(owner.getUsername())) {
							userOwnsAccount = true;
							break;
						}
					}
					if(!userOwnsAccount) {//Customer does not own this account
						System.out.println("You do not own this account.");
						return;
					}
				}
				System.out.println("Account balance: " + acc.getBalance());
				System.out.println("Enter an amount to deposit:");
				System.out.print(currentUser.getUsername() + ">> ");
				double depositAmount = validateInputDouble(in);
				if(depositAmount < 0) {//Negative deposit amount
					System.out.println("Wow, and I thought I was broke. Negative deposit denied.");
				}
				else {//Deposit amount at least zero
					bd.depositToAccount(acc.getID(), depositAmount);
					System.out.println("Deposit made.");
				}
			}
		}
		
		public static void withdrawFromAccount(User currentUser, Scanner in) throws SQLException {
			BankAccountDaoImpl bd = new BankAccountDaoImpl();
			UserDaoImpl ud = new UserDaoImpl();
			System.out.println("Enter ID of account to withdraw from: ");
			System.out.print(currentUser.getUsername() + ">> ");
			int accountId = validateInputInteger(in);
			BankAccount acc = bd.getBankAccountById(accountId);
			if(acc == null) {
				System.out.println("Account not found.");
			}
			else {//Account found
				if(currentUser.getType() == 1) {//If the user is a customer, check if they own the account first
					ArrayList<User> accountOwners = (ArrayList<User>) ud.getOwnersOfAccount(acc.getID());
					boolean userOwnsAccount = false;
					for(User owner : accountOwners) {
						if(owner.getUsername().equals(owner.getUsername())) {
							userOwnsAccount = true;
							break;
						}
					}
					if(!userOwnsAccount) {
						System.out.println("You do not own this account.");
						return;//Customer does not own that account
					}
				}
				System.out.println("Account balance: " + acc.getBalance());
				System.out.println("Enter an amount to withdraw:");
				System.out.print(currentUser.getUsername() + ">> ");
				double withdrawAmount = validateInputDouble(in);
				if(withdrawAmount < 0) {//Negative withdrawal amount
					System.out.println("Nice try. Negative withdrawal denied.");
				}
				else if(withdrawAmount > acc.getBalance()) {
					System.out.println("You cannot afford to withdraw that much. Withdrawal denied.");
				}
				else {//Withdrawal is affordable
					bd.withdrawFromAccount(acc.getID(), withdrawAmount);
					System.out.println("Deposit made.");
				}
			}
		}
		
		public static void deleteAccount(User currentUser, Scanner in) throws SQLException {
			BankAccountDaoImpl bd = new BankAccountDaoImpl();
			UserDaoImpl ud = new UserDaoImpl();
			System.out.println("Enter ID of account to delete: ");
			System.out.print(currentUser.getUsername() + ">> ");
			int accountId = validateInputInteger(in);
			BankAccount acc = bd.getBankAccountById(accountId);
			if(acc == null) {//Account not found
				System.out.println("Account not found.");
			}//Ineligible for deletion
			else if(acc.getBalance() > 0) {//Account found, but still has money
				System.out.println("Account balance is " + acc.getBalance() + ". Please empty the account before deleting.");
			}
			else {//Eligible for deletion
				System.out.println("Are you sure? (y/n)");
				System.out.print(currentUser.getUsername() + ">> ");
				String confirmDelete = in.nextLine();
				if(confirmDelete.equals("y")) {
					bd.deleteAccount(acc.getID());
					System.out.println("Account deleted.");
				}
				else {
					System.out.println("Deletion cancelled.");
				}
			}
		}
		
		public static boolean register(Scanner in, boolean isAdmin) throws SQLException {
			UserDaoImpl ud = new UserDaoImpl();
			//Scanner in = new Scanner(System.in);
			System.out.println("At any time, type 'cancel' to cancel registration.");
			String newUsername = null;
			String newPassword = null;
			String newLegalName = null;
			Integer newAge = null;

			//Select a username
			while(newUsername == null) {
				System.out.println("Please enter a username:");
				String chosenUsername = in.nextLine();
				if(chosenUsername.length() < 5) {
					System.out.println("Username must be at least 5 characters.");
				}
				else if(chosenUsername.equalsIgnoreCase("cancel")) {
					return false;
				}
				else if(ud.getUserByUsername(chosenUsername) == null) {
					System.out.println("Username " + chosenUsername + " is available!");
					newUsername = chosenUsername;
				}
				else {
					System.out.println("Sorry, " + chosenUsername + " is not available.");
				}
			}

			//Select a password
			while(newPassword == null) {
				System.out.println("Please enter a password");
				String chosenPassword = in.nextLine();
				if(chosenPassword.contains(" ")) {
					System.out.println("Passwords cannnot contain spaces.");
				}
				else if(chosenPassword.length() < 5) {
					System.out.println("Password must be at least 5 characters.");
				}
				else if(chosenPassword.equalsIgnoreCase("cancel")) {
					return false;
				}
				else {
					System.out.println("Password set.");
					newPassword = chosenPassword;
				}
			}

			//Enter legal name
			while(newLegalName == null) {
				System.out.println("Please enter your legal name:");
				String chosenLegalName = in.nextLine();
				if(chosenLegalName.length() < 5) {
					System.out.println("Legal name must be at least 5 characters.");
				}
				else if(chosenLegalName.equalsIgnoreCase("cancel")) {
					return false;
				}
				else {
					System.out.println("Legal name set.");
					newLegalName = chosenLegalName;
				}
			}

			//Enter age
			while(newAge == null) {
				System.out.println("Please enter your age:");
				String chosenAge = in.nextLine();
				if(chosenAge.equalsIgnoreCase("cancel")) {
					return false;
				}
				else {
					int validatedAge = validateInputInteger(chosenAge, in);
					if(validatedAge < 14) {//Input was a number, but smaller than 14
						System.out.println(!isAdmin ? "You must be at least 14 to own a bank account." : "Users must be at least 14 years old.");
					}
					else {//Valid age
						newAge = validatedAge;
					}
				}
			}
			System.out.println(isAdmin ? "New user " + newUsername + " registered." : "Thank you for registering with Faber and Warren, " + newLegalName + "! You may now log in.");
			ud.createNewCustomer(newUsername, newPassword, newLegalName, newAge);
			return true;
		}//end register
	
		public static User login(Scanner in) throws SQLException {
			UserDaoImpl ud = new UserDaoImpl();
			while(true) {
				System.out.println("Enter your username and password with a space between them (format: <username> <password>) or enter cancel to return the Main Menu");
				String credentialsIn = in.nextLine();
				if(credentialsIn.equalsIgnoreCase("cancel")) {
					return null;
				}
				else {//didn't cancel
					String[] credentials = credentialsIn.split(" ");
					if(credentials.length != 2) {//2 arguments not given
						System.out.println("Incorrect amonut of arguments given.");
					}
					else {//Username and password given
						User foundUser = ud.getUserByUsername(credentials[0]);
						if(foundUser == null) {
							System.out.println("No user with username " + credentials[0] + " found. Please try again.");
						}
						else {//User with given username was found
							if(foundUser.getPassword().equals(credentials[1])) {//Correct password given
								System.out.println("Login successful. Welcome, "  + foundUser.getLegalName());
								return foundUser;
							}
							else {//Incorrect password given
								System.out.println("Incorrect password. Please try again.");
							}
						}
					}
				}
			}
		}
		
		public static void customerMenu(User currentUser, Scanner in) throws InvalidClassException, SQLException {
			//log.info("Customer " + currentUser.getUsername() + " has logged in");
			Integer userInput = null;
			boolean hasQuit = false;
	
			while(!hasQuit) {
				System.out.println("[Customer Menu]\nAvailable actions:\n1: Create new account\n2: View accounts\n3: Withdraw from account\n4: Deposit to account\n5: Delete account\n6: Quit");
				System.out.print(currentUser.getUsername() + ">> ");
				userInput = validateInputInteger(in);
				switch(userInput) {
				case 1://Create new account
					createNewAccount(currentUser, in);
					break;
				case 2://View existing accounts and balances
					viewAccounts(currentUser, in);
					break;
				case 3://Withdraw from account
					withdrawFromAccount(currentUser, in);
					break;
				case 4://Deposit to account
					depositToAccount(currentUser, in);
					break;
				case 5://Delete empty account
					deleteAccount(currentUser, in);
					break;
				case 6://Logout
					hasQuit = true;
					break;
				default://Invalid input
					System.out.println("Please enter a number from 1 to 6.");
					break;
				}
			}
		}//end customerMenu
	
		public static void adminMenu(User currentUser, Scanner in) throws InvalidClassException, SQLException {
			//log.info("Customer " + currentUser.getUsername() + " has logged in");
			Integer userInput = null;
			boolean hasQuit = false;
	
			while(!hasQuit) {
				System.out.println("[Admin Menu]\nAvailable actions:\n1: View all accounts\n2: Create new user\n3: Update user\n4: Delete user\n5: Withdraw from account\n6: Deposit to account\n7: Delete account\n8: Quit");
				System.out.print(currentUser.getUsername() + ">> ");
				userInput = validateInputInteger(in);
				switch(userInput) {
				case 1://View all accounts and balances
					viewAccounts(currentUser, in);
					break;
				case 2://Create new user
					register(in, true);
					break;
				case 3://Update user
					updateUser(currentUser, in);
					break;
				case 4://Delete user
					deleteUser(currentUser, in);
					break;
				case 5://Withdraw from account
					withdrawFromAccount(currentUser, in);
					break;
				case 6://Deposit to account
					depositToAccount(currentUser, in);
					break;
				case 7://Delete empty account
					deleteAccount(currentUser, in);
					break;
				case 8://Logout
					hasQuit = true;
				default://Invalid input
					System.out.println("Please enter a number from 1 to 8.");
					break;
				}
			}
		}//end adminMenu
		
		public static void main(String[] args) throws SQLException, InvalidClassException {
			User currentUser = null;
			//Logger logger = LogManager.getLogger(BankApp.class);
			//logger.debug("This is a test message!");
			
			//Configurator.init
			Scanner console = new Scanner(System.in);
			boolean hasQuit = false;
			
			while(!hasQuit) {
				System.out.println("[Main Menu]\nAvailable actions:\n1: Login\n2: Register\n3: Quit");
				System.out.print(">> ");
				int consoleInput = validateInputInteger(console);
				switch(consoleInput) {
				case 1://Login
					currentUser = login(console);
					if(currentUser != null) {
						switch(currentUser.getType()) {
						case 1://New customer login
							customerMenu(currentUser, console);
							break;
						case 2://New admin login
							adminMenu(currentUser,console);
							break;
						}
					}
					break;
				case 2://Register
					register(console, false);
					break;
				case 3://Quit
					hasQuit = true;
					break;
				default:
					System.out.println("Please enter a number from 1 to 3.");
					break;
				}
			}//end while !hasQuit
			System.out.println("Thank you for choosing DataBank(tm)!");
		}
}
