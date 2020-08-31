import java.io.InvalidClassException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import java.util.*;
//import org.apache.

public class App {


	/*
	 * Main Menu
	 * --Register
	 * --Login
	 * ----Customer console
	 * ------Apply for account (Written)
	 * ------View accounts (Written)
	 * ------Withdraw from account (Written)
	 * ------Deposit to account (Written)
	 * ------Transfer between accounts (Written)
	 * ----Employee console
	 * ------View all customers (Written)
	 * ------View customer info by username (Written)
	 * ------View all applications (Written)
	 * ------Open/close applications (Written)
	 * ----Admin console
	 * ------View all customers (Reused: Employee)
	 * ------Withdraw from account (Written)
	 * ------Deposit to account (Written)
	 * ------Transfer between accounts (Written)
	 * ------Open/close account							(Jacob)
	 * ------Manage applications (Reused: Employee)
	 * --Quit
	 */
	
	//Takes input from a scanner and only allows an int.
	public static Integer validateInputInteger(Scanner in) {
		Integer output = null;
		while(output == null) {
			try {
				output = Integer.parseInt(in.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number.");
				
			}
		}
		return output;
	}
	//Takes input from a scanner and only allows a double with up to 2 digits of precision.
	public static Double validateInputDouble(Scanner in) {
		Double output = null;
		while(output == null) {
			try {
				output = Double.parseDouble(in.nextLine());
				if(output % 0.01 != 0) {
					throw new NumberFormatException();
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number with up to 2 points of decmial precision.");
				
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
				System.out.println("Invalid input. Please enter a number.");
				try {
					output = Integer.parseInt(in.nextLine());
				}
				catch (NumberFormatException e2) {
					System.out.println("Invalid input. Please enter a number.");
				}
			}
		}
		return output;
	}

	public static boolean register(DataService ds, Scanner in) {
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
			else if(ds.isUsernameAvailable(chosenUsername)) {
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
					System.out.println("You must be at least 14 to own a bank account.");
				}
				else {//Valid age
					newAge = validatedAge;
				}
			}
		}
		System.out.println("Thank you for registering with Faber and Warren, " + newUsername + "! You may now log in.");
		ds.createCustomer(newUsername, newPassword, newLegalName, newAge);
		ds.saveUsers("src/main/resources/Users.txt");
		return true;
	}//end register

	public static User login(DataService ds, Scanner in) {

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
					User foundUser = ds.getUserByUsername(credentials[0]);
					if(foundUser == null) {
						System.out.println("No user with username " + credentials[0] + " found. Please try again.");
					}
					else {//User with given username was found
						if(foundUser.getPassword().equals(credentials[1])) {//Correct password given
							System.out.println("Login successful. Welcome, "  + foundUser.getUsername());
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

	public static void customerMenu(User currentUser, DataService ds, Scanner in) throws InvalidClassException {
		Integer userInput = null;
		boolean hasQuit = false;

		while(!hasQuit) {
			System.out.println("[Customer Menu]\nAvailable actions:\n1: Apply for account\n2: View accounts\n3: Withdraw from account\n4: Deposit to account\n5: Transfer between accounts\n6: Quit");
			System.out.print(currentUser.getUsername() + ">> ");
			userInput = validateInputInteger(in);
			switch(userInput) {
			case 1://Apply for account
				if(((Customer) currentUser).applyForAccount(ds, in)) {
					ds.saveApplications("src/main/resources/Applications.txt");
					ds.saveNumAccounts("src/main/resources/IDCounts.txt");
				}
				break;
			case 2://View accounts
				System.out.println(((Customer) currentUser).viewAccounts(ds));
				break;
			case 3://Withdraw from account
				if(((Customer) currentUser).withdrawFromAccount(ds, in)) {
					ds.saveBankAccounts("src/main/resources/Accounts.txt");
				}
				break;
			case 4://Deposit to account
				if(((Customer) currentUser).depositToAccount(ds, in)) {
					ds.saveBankAccounts("src/main/resources/Accounts.txt");
				}
				break;
			case 5://Transfer between accounts
				if(((Customer) currentUser).transferBetweenAccounts(ds, in)) {
					ds.saveBankAccounts("src/main/resources/Accounts.txt");
				}
				break;
			case 6://Quit
				hasQuit = true;
				break;
			default://Invalid input
				break;
			}
		}
	}//end customerMenu

	public static void employeeMenu(User currentUser, DataService ds, Scanner in) {
		Integer userInput = null;
		boolean hasQuit = false;

		while(!hasQuit) {
			System.out.println("[Employee Menu]\nAvailable actions:\n1:View all customers\n2:View customer info\n3:View applications\n4:Approve/Deny Application\n5:Quit");
			System.out.print(currentUser.getUsername() + ">> ");
			userInput = validateInputInteger(in);
			switch(userInput) {
			case 1://View all customers
				System.out.println(((Employee) currentUser).viewAllCustomers(ds));
				break;
			case 2://View customer info
				((Employee) currentUser).viewCustomerInfo(ds, in);
				break;
			case 3://View Applications
				System.out.println(((Employee) currentUser).viewAllApplications(ds));
				break;
			case 4://Approve/Deny Application
				if(((Employee) currentUser).ApproveOrDenyApplication(ds, in)) {
					ds.saveApplications("src/main/resources/Applications.txt");
					ds.saveBankAccounts("src/main/resources/Accounts.txt");
				}
				break;
			case 5://Quit
				hasQuit = true;
				break;
			default://Invalid input
				break;
			}
		}
	}

	public static void adminMenu(User currentUser, DataService ds, Scanner in) {
		Integer userInput = null;
		boolean hasQuit = false;

		while(!hasQuit) {
			System.out.println("[Admin Menu]\nAvailable actions:\n1: View all customers\n2: View customer info \n3: View applications\n4: Appove/deny application"
					+ "+\n5: Withdraw from account\n6: Deposit to account\n7: Transfer between accounts\n8: Open/close account\n9: Quit");
			System.out.print(currentUser.getUsername() + ">> ");
			userInput = validateInputInteger(in);
			switch(userInput) {
			case 1://View all customers
				System.out.println(((Admin) currentUser).viewAllCustomers(ds));
				break;
			case 2://View customer info
				break;
			case 3://View applications
				break;
			case 4://Approve/deny application
				break;
			case 5://Withdraw from account
				break;
			case 6://Deposit to account
				break;
			case 7://Transfer between accounts
				break;
			case 8://Open/close account
				break;
			case 9://Quit
				hasQuit = true;
				break;
			default:
				break;
			}

		}
	}


	

	
	public static void main(String[] args) throws InvalidClassException {
		Scanner console= new Scanner(System.in);
		Logger log = LogManager.getLogger(App.class);
		Configurator.initialize(null, "log4j2.xml");
		User currentUser = null;
		Integer consoleInput = null;

		DataService ds = new DataService();
		ds.loadApplications("src/main/resources/Applications.txt");
		ds.loadBankAccounts("src/main/resources/Accounts.txt");
		ds.loadNumAccounts("src/main/resources/IDCounts.txt");
		ds.loadUsers("src/main/resources/Users.txt");

		System.out.println("[Faber and Warren Banking Services]\n");

		// === Switchcase implementation ===
		boolean hasQuit = false;
		while(!hasQuit) {
			System.out.println("[Main Menu]\nAvailable actions:\n1: Login\n2: Register\n3: Quit");
			System.out.print(">> ");
			consoleInput = validateInputInteger(console);
			switch(consoleInput) {
			case 1://Login
				currentUser = login(ds,console);
				if(currentUser != null) {
					if(currentUser instanceof Customer) {
						customerMenu(currentUser, ds, console);
					}
					else if(currentUser instanceof Employee) {
						//Open Employee menu
						System.out.println("Welcome, employee!");
						employeeMenu(currentUser,ds,console);
					}
					else if(currentUser instanceof Admin) {
						//Open Admin menu
						System.out.println("Welcome, admin!");
						adminMenu(currentUser,ds,console);
					}
				}
				//On successful login, assign currentUser to a User and open a console method based on the class of the user
				break;
			case 2://Register
				register(ds, console);
				//On successful register, let them choose to login, register, or quit
				break;
			case 3://Quit
				hasQuit = true;
				break;
			default://Invalid number
				System.out.println("Invalid input. Please try again");
				break;

			}


		}//end while !hasQuit

		// === Code here runs after quitting the app
		System.out.println("Thank you for choosing Faber and Warren!");
		ds.saveApplications("src/main/resources/Applications.txt");
		ds.saveBankAccounts("src/main/resources/Accounts.txt");
		ds.saveNumAccounts("src/main/resources/IDCounts.txt");
		ds.saveUsers("src/main/resources/Users.txt");
		console.close();
		return;

	}//end main
}//end class
// ===================================================================================== Scrap Code Below ================================================================================

/*
public static User register(DataService ds) {
	Scanner in = new Scanner(System.in);
	String uName = "";
	while (uName == "")
	{
		System.out.println("Please enter your username");
		String temp=in.nextLine();	
		if(ds.getUsers().contains(temp)){System.out.println("That username is not avaiable");

		}
		else {uName=temp;}


	}		

	System.out.println("Please enter your password");
	String password=in.nextLine();

	System.out.println("Please enter your legal name");
	String lName=in.nextLine();

	System.out.println("Please enter your age");
	int age=in.nextInt();


	ds.createCustomer(uName,password,lName,age);		

	return 0;
}
 */

//1: Login 2: Register 3: Quit
/*
System.out.println("Hello, thank you for choosing Faber and Warren. Please enter 1 to login or 2 to register");
lInt=console.nextInt();
if (lInt==1){//Login
	System.out.println("Please enter your username");


}
else if(lInt==2){//Register
	///register logic
	register(ds);
}
else if (lInt==3){//Let the code pass through the login/register block

}

else {
	System.out.println("Something went wrong");
}
 */

//if (login goes well and is customer){

//logic for checking user name goes here -->>

//	System.out.printkln("Account name: " + customer1.getName());

//Previous code for the app console
/*
		System.out.println("1. Apply for a Bank Account");	
		System.out.println("2. View Account Information");	
		System.out.println("3. View Balance Information");	
		System.out.println("4. Deposit Money into an Account");	
		System.out.println("5. Withdraw Money from an Account");	
		System.out.println("6.Transfer Money from an Account");	
		System.out.println("7. Exit the Application");

		int choice=console.nextInt();

		switch(choice){

		case 1:

		//create an new application logic goes here ----->
			//Choice 1: Apply for Individual Account
			//Choice 2: Appl
			System.out.println("You have successfully applied for an account");

		break;


		case 2:

		//
		break;

		case 3:


			//double b=getBalance(); -----> not sure how to call this; 
			//System.out.println("Your balance in the specified account is: " +b);

		break;

		case 4:

			System.out.println("How much money would you like to deposit?");
			//customer1.doDeposit(console.nextDouble());
			//System.out.println("Money added. Your balance is now: " +customer1.getBalance());

		break;

		case 5:

		System.out.println("How much money would you like to withdrawal?");
			//customer1.doWithdraw(console.nextDouble());
			//System.out.println("Money withdrawn. Your balance is now: " +customer1.getBalance());

		break;

		case 6:

			System.out.println("Enter the account number you would like to tranfer to");
			String otherAccount=console.next();
			System.out.println("Enter the amount you would like to transfer.");

		//	**** Transfer Logic Goes Here---->


		break;

		case 7:

			System.out.println("Thank you for visiting Faber and Warren");

		break;

	//	}

	//else {

}










		console.close();
	}


}
 */


/* public class BankApp{
	public static void main(String[] args){
		Scanner console=new Scanner(System.in);
		System.out.println("Hello, thank you for choosing Faber and Warren. Please enter your user name"
 * *Account customer1= new Account();	
 **logic for checking user name
	System.out.printkln("Account name: " + customer1.getName());



		System.out.println("1. Apply for a Bank Account");	
		System.out.println("2. View Account Information");	
		System.out.println("3. View Balance Information");	
		System.out.println("4. Deposit Money into an Account");	
		System.out.println("5. Withdraw Money from an Account");	
		System.out.println("6.Transfer Money from an Account");	
		System.out.println("7. Exit the Application");

		int choice=console.nextInt();

		switch(choice){

		case 1:

 ***	create an new application logic
			System.out.println("You have successfully applied for an account");

		break;


		case 2:

			System.out.print(customer1.getName()+customer1.getAccount());

		break;

		case 3:

			System.out.println("Please enter the account number");
			int n=console.next();
 ***	double=getBalance(n); 
			System.out.println("Your balance in the specified account is: " +b);

		break;

		case 4:

			System.out.println("How much money would you like to deposit?")
			customer1.doDeposit(console.nextDouble());
			System.out.println("Money added. Your balance is now: " +customer1.getBalance());

		break;

		case 5:

		System.out.println("How much money would you like to withdrawal?")
			customer1.doWithdraw(console.nextDouble());
			System.out.println("Money withdrawn. Your balance is now: " +customer1.getBalance());

		break;

		case 6:

			System.out.println("Enter the account number you would like to tranfer to");
			String otherAccount=console.next();
			System.out.println("Enter the amount you would like to transfer.");
 ****
 ****
 ****

		break;

		case 7:

			System.out.println("Thank you for visit Faber and Warren");

		break;

}
?***********************************************************************************************
?***********************************************************************************************
EmployeeUI

System.out.println("Hello, thank you for choosing Faber and Warren. Please enter your user name"
 * *Account customer1= new Account();	
 **logic for checking user name
	System.out.printkln("Account name: " + customer1.getName());



		System.out.println("1. View a Customers Account Information");	
		System.out.println("2. View a Bank Accounts Information");	
		System.out.println("3. Manage Applications");	
		System.out.println("4. Exit the Application");	


		int choice=console.nextInt();

		switch(choice){

		case 1:

		System.out.println("Please enter the Username of the Customer");
		employee1.getAccount(console.next());


		break;


		case 2:
			System.out.println("Please enter the Bank Account ID")
 ********System.out.println(employee1.getID()+BankAccount.getBalance

		break;

		case 3:

 *Logic For Approving and Denying Accounts
 *
 *

		break;

		case 4:

			System.out.println("Thank you for visit Faber and Warren");

		break;



}

?************************************************************************
?************************************************************************
Bank Admin UI


		System.out.println("1. Approve or Deny Accounts");	
		System.out.println("2. View Account Information");	
		System.out.println("3. View Balance Information");	
		System.out.println("4. Deposit Money into an Account");	
		System.out.println("5. Withdraw Money from an Account");	
		System.out.println("6. Transfer Money from an Account");
		System.out.println("7. Delete an Account
		System.out.println("8. Exit the Application");

		int choice=console.nextInt();

		switch(choice){

		case 1:

 ***	Approve or Deny a new application logic


		break;


		case 2:

			System.out.print(customer1.getName()+customer1.getAccount());

		break;

		case 3:

			System.out.println("Please enter the account number");
			int n=console.next();
 ***	double=getBalance(n); 
			System.out.println("Your balance in the specified account is: " +b);

		break;

		case 4:

			System.out.println("How much money would you like to deposit?")
			customer1.doDeposit(console.nextDouble());
			System.out.println("Money added. Your balance is now: " +customer1.getBalance());

		break;

		case 5:

		System.out.println("How much money would you like to withdrawal?")
			customer1.doWithdraw(console.nextDouble());
			System.out.println("Money withdrawn. Your balance is now: " +customer1.getBalance());

		break;

		case 6:**** requires code for specifing which account to which account

			System.out.println("Enter the account number you would like to tranfer to");
			String otherAccount=console.next();
			System.out.println("Enter the amount you would like to transfer.");
 ****
 ****
 ****

		break;

		case 7:
 **** logic for deleting an account

		case 8:

			System.out.println("Thank you for visit Faber and Warren");

		break;



 */ 



