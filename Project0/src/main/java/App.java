import java.io.File;

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
	 * ------Apply for account
	 * ------Withdraw from account
	 * ------Deposit to account
	 * ------Transfer between accounts
	 * ----Employee console
	 * ------View all customers
	 * ------View customer info by username
	 * ------View applications
	 * ------Approve application
	 * ------Deny application
	 * ----Admin console
	 * ------Apply for account
	 * ------Withdraw from account
	 * ------Deposit to account
	 * ------Transfer between accounts
	 * ------Cancel account
	 * ------View all customers
	 * ------View customer info by username
	 * ------View applications
	 * ------Approve application
	 * ------Deny application
	 * --Quit
	 */
	
	static void printSomeMessage() {
		System.out.println("Hello!");
	}
	
	public int register(DataService ds) {
		Scanner in = new Scanner(System.in);
		String uName = "";
		while (uName == "")
		{
			System.out.println("Please enter your username");
			String temp=in.next();	
			if(ds.getUsers().contains(temp)){System.out.println("That username is not avaiable");
			
			}
			else {uName=temp;}
		
				
		}		
		

		System.out.println("Please enter your password");
		String password=in.next();

		System.out.println("Please enter your legal name");
		String lName=in.nextLine();

		System.out.println("Please enter your age");
		int age=in.nextInt();

		ds.createCustomer(uName,password,lName,age);		
		
		in.close();
		return 0;
	}

	static int login(){
	return 0;
	
	}
	
	public static void main(String[] args) {
		Scanner console= new Scanner(System.in);
		Logger log = LogManager.getLogger(App.class);
		Configurator.initialize(null, "log4j2.xml");
		User currentUser;
		
		DataService ds = new DataService();
		ds.loadApplications("src/main/resources/Applications.txt");
		ds.loadBankAccounts("src/main/resources/Accounts.txt");
		ds.loadNumAccounts("src/main/resources/IDCounts.txt");
		ds.loadUsers("src/main/resources/Users.txt");
		
		
		printSomeMessage();
		
		int lInt;
		//1: Login 2: Register 3: Quit
		System.out.println("Hello, thank you for choosing Faber and Warren. Please enter 1 to login or 2 to register");
		lInt=console.nextInt();
		if (lInt==1){
			System.out.println("Please enter your username");
			
		}
		else if(lInt==2){
			///register logic

		}
		else if (lInt==3){//Let the code pass through the login/register block
		
		}

		else {
			System.out.println("Something went wrong");
		}


		


		//if (login goes well and is customer){
	
	//logic for checking user name goes here -->>

	//	System.out.printkln("Account name: " + customer1.getName());

	

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



