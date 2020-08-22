import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DataService {
	
	private ArrayList<User> users;
	private ArrayList<BankAccount> accounts;
	
	public DataService() {
		users = new ArrayList<User>();
		accounts = new ArrayList<BankAccount>();
	}
	
	public void loadBankAccounts() throws IOException, ClassNotFoundException {
		
		FileInputStream fin_bank = new FileInputStream("src/test/resources/BankAccounts.txt");
		ObjectInputStream oin_bank = new ObjectInputStream(fin_bank);
		
		while(fin_bank.available() > 0) {
			accounts.add((BankAccount)oin_bank.readObject());
		}
			
		
		fin_bank.close();
		oin_bank.close();
		
		FileInputStream fin_joint = new FileInputStream("src/test/resources/JointAccounts.txt");
		ObjectInputStream oin_joint = new ObjectInputStream(fin_joint);
		
		while(fin_joint.available() > 0) {
			accounts.add((JointAccount)oin_joint.readObject());
		}
		
		fin_joint.close();
		oin_joint.close();
	}
	
	public void loadUsers() {
		
		FileInputStream fin;
		try {
			fin = new FileInputStream("src/test/resources/Users.txt");
			ObjectInputStream oin = new ObjectInputStream(fin);
			
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
					System.out.println("Invalid object loaded from Users.txt");
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
	
	public ArrayList<BankAccount> getAccounts() {
		return accounts;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
}
