
public class Admin extends StaffUser {

	public Admin(String username, String password, String legalName, int age) {
		super(username, password, legalName, age);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Admin[" + username + "]";
	}
}
