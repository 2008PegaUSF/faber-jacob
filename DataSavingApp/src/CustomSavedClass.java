import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

public class CustomSavedClass implements Serializable {
	private String s1;
	private String s2;
	private int i1;
	
	public void printValues() {
		System.out.println(s1 + " " + s2 + " " + i1);
	}
	
	public void gatherInput() {
		Scanner in = new Scanner(System.in);
		try {
			
			System.out.println("Enter value for String 1:");
			s1 = in.nextLine();
			System.out.println("Enter value for String 2:");
			s2 = in.nextLine();
			System.out.println("Enter value for int 1:");
			i1 = in.nextInt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			in.close();
		}

	}
	
	public void saveMembers() throws IOException {
		FileOutputStream fout = new FileOutputStream("save.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(s1 + "\n" + s2 + "\n" + i1);
		fout.close();
		oos.close();
	}
	
	public void loadMembers(String filepath) throws IOException, ClassNotFoundException {
		try {
		FileInputStream fin = new FileInputStream(filepath);
		//If file does exist:
		ObjectInputStream iis = new ObjectInputStream(fin);
		String loaded = (String)iis.readObject();
		System.out.println(loaded);
		
		Scanner loader = new Scanner(loaded);
		s1 = loader.nextLine();
		s2 = loader.nextLine();
		i1 = loader.nextInt();
		loader.close();
		} catch(FileNotFoundException e) {
			//If file doesn't exist:
			System.out.println("File not found: " + filepath);
		}
	}
}
