//Simple Employee class used for Q7.
public class Employee {
	private String name;
	private String department;
	private int age;
	
	public Employee(String name, String department, int age) {
		this.name = name;
		this.department = department;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public int getAge() {
		return age;
	}
	
	public String toString() {
		return  "Employee[" +name + "," + department + "," + age + "]";
	}
	
}
