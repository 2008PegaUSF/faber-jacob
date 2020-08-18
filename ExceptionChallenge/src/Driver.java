
public class Driver {
	
	public static void throwException() throws CustomBadTime {
		throw new CustomBadTime("You've done goofed, son");
	}
	
	public static void main(String[] args){
		try {
			throwException();
		}
		catch(CustomBadTime e) {
			System.out.println("Or have you?");
		}
	}
}
