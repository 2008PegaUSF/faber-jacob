
public abstract class Account {
	protected String username;
	protected String password;
	protected String ID;
	protected boolean isOpen;
	
	public Account(String username, String password, String ID) {
		this.username = username;
		this.password = password;
		this.ID = ID;
		isOpen = false;
	}
	
	public void open() {
		isOpen = true;
	}
	
	public void close() {
		isOpen = false;
	}
	
}


/**
 * C0001 ... C9999
 * 
 * C0001 username_here password_here
 * C0002 ...
 */
