import java.io.Serializable;

//ID username balance status
public class BankAccount implements Serializable {
	protected String username;
	protected String ID;
	protected double balance;
	protected String status;
	
	//Simple constructor to use when a new account is applied for
	public BankAccount(String username, String ID) {
		this.username = username;
		this.ID = ID;
		balance = 0;
		status = "pending";
	}
	
	public BankAccount(String ID, String user, double balance, String status) {
		this.username = user;
		this.ID = ID;
		this.balance = balance;
		this.status = status;
	}
	
	public void open() {
		status = "active";
	}
	
	public void close() {
		status = "cancelled";
	}
	
	public String toString() {
		return "BankAccount[" + ID + "]";
	}
	
}
