import java.io.IOException;

public class Driver {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		CustomSavedClass csc = new CustomSavedClass();
		csc.gatherInput();
		csc.saveMembers();
		csc.loadMembers("save.txt");
		csc.printValues();
	}
}
