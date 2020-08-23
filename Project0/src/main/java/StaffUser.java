//StaffUsers can run any method an employee can. This is a superclass for Employee and Admin.
public class StaffUser extends User {

	public StaffUser(String username, String password, String legalName, int age) {
		super(username, password, legalName, age);
	}

	//Returns the account with that given ID. Returns null if no account with that name was found.
	public BankAccount getAccountByID(String id, DataService ds) {
		for(BankAccount account : ds.getAccounts()) {
			if(account.getID().equals(id))
				return account;
		}
		return null;
	}
	
	//Returns the user with that given username. Returns null if no user with that name was found.
	public User getUserByUsername(String username, DataService ds) {
		for(User u : ds.getUsers()) {
			if(u.getUsername().equals(username))
				return u;
		}
		return null;
	}
	
}
