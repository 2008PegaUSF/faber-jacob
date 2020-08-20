import java.util.Comparator;
public class EmployeeComparator implements Comparator<Employee> {

	//Compares two employees and returns the integer result.
	//Names are prioritized first, then departments if the names are equal, then ages if both names and departments are equal.
	public int compare(Employee a, Employee b) {
		//Compare names of employees first
				int comp = a.getName().compareTo(b.getName());
				if(comp == 0) {
					//If the names are equal, compare the employees' departments
					comp = a.getDepartment().compareTo(b.getDepartment());
					if(comp == 0) {
						//If names and departments are the same, return their age comparison
						return a.getAge() - b.getAge();
					}
					//If the departments are different, return the comparison
					else return comp;
				}
				//If the names are not equal, return the comparison
				else return comp;
	}
}
