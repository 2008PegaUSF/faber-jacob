import java.io.Serializable;

//Superclass for all Users, including Customer, Employee, and Admin.
public abstract class User implements Serializable {
	
	protected String username;
	protected String password;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String toString() {
		return "User[" + username + "]";
	}
	
}
