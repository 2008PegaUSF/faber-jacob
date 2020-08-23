import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataService {
	
	private ArrayList<User> users;
	private ArrayList<BankAccount> accounts;
	private ArrayList<BankAccount> applications;
	int numBankAccounts;
	int numJointAccounts;
	
	public DataService() {
		users = new ArrayList<User>();
		accounts = new ArrayList<BankAccount>();
		applications = new ArrayList<BankAccount>();
		
	}
	
	public ArrayList<BankAccount> getAccounts() {
		return accounts;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	
	//Read how many BankAccounts and how many JointAccounts have been made from a file.
	public void loadNumAccounts(String filepath) {
		FileInputStream fin;
		ObjectInputStream oin;
		
		try {
			fin = new FileInputStream(filepath);
			oin = new ObjectInputStream(fin);
			
			if(fin.available() > 0) {
				numBankAccounts = numJointAccounts = 0;
			}
			else {
				numBankAccounts = oin.readInt();
				numJointAccounts = oin.readInt();
			}
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveNumAccounts(String filepath) {
		FileOutputStream fout;
		ObjectOutputStream oout;
		
		try {
			fout = new FileOutputStream(filepath);
			oout = new ObjectOutputStream(fout);
			
			oout.writeInt(numBankAccounts);
			oout.writeInt(numJointAccounts);
			
			fout.close();
			oout.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Loads all bank accounts from a file.
	public void loadBankAccounts(String filepath) {
		
		FileInputStream fin_bank;
		ObjectInputStream oin_bank;
		try {
			fin_bank = new FileInputStream(filepath);
			oin_bank = new ObjectInputStream(fin_bank);
			while(fin_bank.available() > 0) {
				BankAccount nextAccount = (BankAccount)oin_bank.readObject();
				if(nextAccount instanceof BankAccount) {
					accounts.add(nextAccount);
					System.out.println(accounts);
				}
				else if(nextAccount instanceof JointAccount) {
					accounts.add(nextAccount);
					System.out.println(accounts);
				}
				else {
					System.out.println("Invalid object loaded from " + filepath);
				}
			}
			
			fin_bank.close();
			oin_bank.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Loads all users from a file.
	public void loadUsers(String filepath) {
		
		FileInputStream fin;
		ObjectInputStream oin;
		try {
			fin = new FileInputStream(filepath);
			oin = new ObjectInputStream(fin);
			
			while(fin.available() > 0) {
				Object nextUser = oin.readObject();
				if(nextUser instanceof Customer) {
					users.add((Customer)nextUser);
				}
				else if(nextUser instanceof Employee) {
					users.add((Employee)nextUser);
				}
				else if(nextUser instanceof Admin) {
					users.add((Admin)nextUser);
				}
				else {
					System.out.println("Invalid object loaded from " + filepath);
				} 
			}
			
			fin.close();
			oin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadApplications(String filepath) {
		
		FileInputStream fin_bank;
		ObjectInputStream oin_bank;
		try {
			fin_bank = new FileInputStream(filepath);
			oin_bank = new ObjectInputStream(fin_bank);
			while(fin_bank.available() > 0) {
				Object nextAccount = oin_bank.readObject();
				if(nextAccount instanceof BankAccount) {
					applications.add((BankAccount)nextAccount);
				}
				else if(nextAccount instanceof JointAccount) {
					applications.add((JointAccount)nextAccount);
				}
				else {
					System.out.println("Invalid object loaded from " + filepath);
				}
			}
			
			fin_bank.close();
			oin_bank.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	//Saves all accounts to a file.
	public void saveBankAccounts(String filepath) {
		FileOutputStream fout;
		ObjectOutputStream oout;
		try {
			fout = new FileOutputStream(filepath);
			oout = new ObjectOutputStream(fout);
			
			for(BankAccount a : accounts) {
				oout.writeObject(a);
			}
			
			fout.close();
			oout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Saves all accounts to a file.
	public void saveApplications(String filepath) {
		FileOutputStream fout;
		ObjectOutputStream oout;
		try {
			fout = new FileOutputStream(filepath);
			oout = new ObjectOutputStream(fout);
			
			for(BankAccount a : applications) {
				oout.writeObject(a);
			}
			
			fout.close();
			oout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Saves all users to a file.
	public void saveUsers(String filepath) {
		FileOutputStream fout;
		ObjectOutputStream oout;
		try {
			fout = new FileOutputStream(filepath);
			oout = new ObjectOutputStream(fout);
			
			for(User u : users) {
				oout.writeObject(u);
			}
			
			fout.close();
			oout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	//Returns all accounts belonging to a customer. Will include inactive accounts if set.
	public ArrayList<BankAccount> getAccountsOfUser(String username, boolean includeInactive) throws InvalidClassException {
		ArrayList<BankAccount> customerAccounts = new ArrayList<BankAccount>();
		for(int i = 0; i < accounts.size(); i++) {
			Object currentAccount = accounts.get(i);
			if(currentAccount instanceof BankAccount) {
				if(((BankAccount) currentAccount).getUsername().equals(username) && !(((BankAccount) currentAccount).getStatus() != "active" && !includeInactive)) {
					customerAccounts.add((BankAccount) currentAccount);
				}
			}
			else if(currentAccount instanceof JointAccount) {
				if(((JointAccount) currentAccount).getUsername().equals(username) && !(((JointAccount) currentAccount).getStatus() != "active" && !includeInactive)) {
					customerAccounts.add((JointAccount) currentAccount);
				}
			}
			else {
				throw new InvalidClassException("Invalid object");
			}
		}
		return customerAccounts;
	}
	
	//One username: create new BankAccount
	public void createAccountApplication(String username) {
		BankAccount newAccount;
		String newID = "C" + ++numJointAccounts;
		newAccount = new BankAccount(newID, username, 0f, "pending");
		applications.add(newAccount);
	}
	
	//Two usernames: create new JointAccount
	public void createAccountApplication(String username, String username2) {
		BankAccount newAccount;
		String newID = "J" + ++numJointAccounts;
		newAccount = new JointAccount(newID, username, username2, 0f, "pending");
		applications.add(newAccount);
	}
	
	public boolean createCustomer(String username, String password, String phoneNumber, int age) {
		for(User u : users) {
			if(username.equals(u.getUsername())) {
				return false;
			}//end if
		}//end for
		//If this line is reached, the username is not taken
		Customer newCustomer = new Customer(username, password, phoneNumber, age);
		users.add(newCustomer);
		return true;
	}
	
}
