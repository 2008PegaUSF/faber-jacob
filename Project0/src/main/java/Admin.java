import java.io.InvalidClassException;
import java.util.*;

public class Admin extends StaffUser {

	public Admin(String username, String password, String legalName, int age) {
		super(username, password, legalName, age);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Admin[" + username + "]";
	}

	public void depositToAccount( DataService ds, Scanner in) {
			System.out.println("Please enter an account to deposit to:");
			String s=in.next();			
			BankAccount foundAccount = ds.getAccountByID(s);
			if(foundAccount == null) {//No account with that ID found
				System.out.println("No account with that ID was found.");
			}
			else {//Account with that ID found
				System.out.println("Account balance: " + foundAccount.getBalance());
				System.out.println("Enter an amount to deposit: ");
				double depositAmount = validateInputDouble(in);
				if(foundAccount.doWithdrawal(depositAmount) == -1) {
					System.out.println("Invalid deposit amount.");
				}
				else {
					System.out.println("Deposit completed. New account balance: " + foundAccount.getBalance());
				}
			}
		}

	public void withdrawFromAccount(DataService ds, Scanner in) {
			System.out.println("Please enter an account id to withdraw from:");
			String s=in.next();
			BankAccount foundAccount = ds.getAccountByID(s);
			if(ds.getAccountByID(s) == null) {//No account with that ID found
				System.out.println("No account with that ID was found.");
			}
			else {//Account with that ID was found
				System.out.println("Account balance: " + foundAccount.getBalance());
				System.out.println("Enter an amount to withdraw:");
				double withdrawalAmount = validateInputDouble(in);
				if(foundAccount.doWithdrawal(withdrawalAmount) == -1) {
					System.out.println("Invalid withdrawal amount.");
				}
				else {
					System.out.println("Withdraw completed. New account balance: " + foundAccount.getBalance());
				}
			}
	}

	public void transferBetweenAccounts(DataService ds, Scanner in) {
		
			System.out.println("Enter an account to send money from: ");
			String s=in.next();
			BankAccount sender = ds.getAccountByID(s);
			if(sender == null) {
				System.out.println("That account does not exist.");
				return;
			}
			System.out.println("Enter an account to send money to:");
			BankAccount recipient = ds.getAccountByID(in.nextLine());
			if(recipient == null) {
				System.out.println("That account does not exist.");
				return;
			}
			System.out.println("Sender account balance: " + sender.getBalance());
			System.out.println("Enter an amount to transfer:");
			double transferAmount = validateInputDouble(in);
			if(sender.doTransfer(transferAmount, recipient)) {
				System.out.println("Transfer complete. Sender's new account balance: " +  sender.getBalance());
			}
			else {
				System.out.println("Invalid amount.");
			}
			
	}



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


public void viewAccounts(DataService ds) throws InvalidClassException {
		ArrayList<BankAccount> userAccounts = ds.getAccountsOfUser(username, false);
		int i = 0;
		System.out.println("Your accounts: ");
		//Display 5 user accounts per row
		for(BankAccount bac : userAccounts) {
			System.out.print(bac.getID() + " ");
			if(++i > 0 && i % 5 == 0) {
				System.out.println();
			}
		}
	}

public void openOrCloseAccount(DataService ds, Scanner in) {
	System.out.println("Enter \"open <ID>\" or \"close <ID>\":");
	String userInput = in.nextLine();
	String[] params = userInput.split(" ");
	
	if(params.length != 2) {
		System.out.println("Invalid command.");
		in.close();
		return;
	}
	
	BankAccount toManage = ds.getAccountByID(in.nextLine());
	if(toManage == null) {
		System.out.println("Account not found.");
		in.close();
		return;
	}
	
	if(params[0].equalsIgnoreCase("open")) {
		toManage.open();
		System.out.println("Account " + toManage.getID() + "opened.");
	}
	else if(params[0].equalsIgnoreCase("close")) {
		toManage.close();
		System.out.println("Account " + toManage.getID() + "closed.");
	}
}

}






