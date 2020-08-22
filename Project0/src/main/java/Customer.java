import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Customer extends User implements Serializable {

	public Customer(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}
	
	public String applyForAccount() {
		//TODO
		return "";
	}
	
	public String toString() {
		return "Customer[" + username + "]";
	}
	
}
