/*Create a class that extends Exception
 * Create constructors that call parent constructors
 * Create a function that throws your exception
 * Catch your exception in main
*/
public class CustomBadTime extends Exception {
	
	//No-parameter constructor
	public CustomBadTime() {
		super();
	}
	
	public CustomBadTime(String message) {
		super(message);
	}	
}
