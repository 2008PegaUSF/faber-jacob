import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Customer extends User implements Serializable {

	public Customer(String username, String password, String realName, int age) {
		super(username, password, realName, age);
	}

	public String toString() {
		return "Customer[" + username + "]";
	}
	
	public String getPersonalInfo() {
		return "";
	}
	
	public boolean equals(Customer other) {
		return this.username.equals(other.getUsername()) && this.password.equals(other.getPassword()) && this.legalName.equals(other.getLegalName()) && this.age == other.getAge();
	}
	
}
