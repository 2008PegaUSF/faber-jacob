package beans;

//Superclass for all Users, including Customer, Employee, and Admin.
public class User{
	private int userID;
	private String username;
	private String password;
	private String legalName;
	private int age;
	private int type;
	
	public User(int userID, String username, String password, String realName, int age,int type) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.legalName = realName;
		this.age = age;
		this.type = type;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getLegalName() {
		return legalName;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getType() {
		return type;
	}
	
	//Used if an admin updates their own username.
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String toString() {
		String accountType = "";
		switch(this.type){
			case 1:
				accountType = "Customer";
				break;
			case 2:
				accountType = "Admin";
				break;
		}
		return accountType+"[Username: "+username+", Password: "+password+", Name: "+legalName+", Age: "+age+"]";
	}
	
	public boolean equals(User other) {
		return this.userID == other.getUserID() 
				&& username.equals(other.getUsername()) 
				&& password.equals(other.getPassword()) && this.legalName == other.getLegalName() 
				&& age == other.getAge() 
				&& type == other.getType();
	}
}
