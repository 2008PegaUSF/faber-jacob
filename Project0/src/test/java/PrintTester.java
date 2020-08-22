import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class PrintTester {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		DataService ds = new DataService();
		
		//Populate test files with initial data
		
		//BankAccount: ID, user, balance, status
		
		BankAccount acc1 = new BankAccount("C0001", "customer1", 10.0, "active");
		BankAccount acc2 = new BankAccount("C0002", "customer2", 20.0, "pending");
		BankAccount acc3 = new BankAccount("C0003", "customer3", 30.0, "active");
		BankAccount acc4 = new BankAccount("C0004", "customer4", 40.0, "cancelled");
		
		JointAccount jacc1 = new JointAccount("J0001", "customer1", "customer2", 30.0, "active");
		JointAccount jacc2 = new JointAccount("J0002", "customer3", "customer4", 70.0, "pending");
		JointAccount jacc3 = new JointAccount("J0003", "customer1", "customer3", 40.0, "active");
		JointAccount jacc4 = new JointAccount("J0004", "customer2", "customer4", 60.0, "cancelled");
		
		FileOutputStream fout = new FileOutputStream("src/test/resources/BankAccounts.txt");
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		
		oout.writeObject(acc1);
		oout.writeObject(acc2);
		oout.writeObject(acc3);
		oout.writeObject(acc4);
		
		fout.close();
		oout.close();
		
		FileOutputStream fout2 = new FileOutputStream("src/test/resources/JointAccounts.txt");
		ObjectOutputStream oout2 = new ObjectOutputStream(fout2);
		
		oout2.writeObject(jacc1);
		oout2.writeObject(jacc2);
		oout2.writeObject(jacc3);
		oout2.writeObject(jacc4);
		
		fout2.close();
		oout2.close();
		
		FileOutputStream fout3 = new FileOutputStream("src/test/resources/Users.txt");
		ObjectOutputStream oout3 = new ObjectOutputStream(fout3);
		
		oout3.writeObject(new Customer("customer1","password1"));
		oout3.writeObject(new Customer("customer2","password2"));
		oout3.writeObject(new Customer("customer3","password3"));
		
		fout3.close();
		oout3.close();
		
		
		ds.loadBankAccounts();
		ds.loadUsers();
		System.out.println(ds.getAccounts());
		System.out.println(ds.getUsers());
		
	}
}