//Main method used to run the Q15Implementation class' methods.
public class Q15Main {
	public static void main(String[] args) {
		Q15Implementation c = new Q15Implementation();
		int a = 10;
		int b = 5;
		//Testing the four methods form Q15Implementation
		System.out.println("Add: " + c.add(a,b));//expecting 15
		System.out.println("Subtract: " + c.subtract(a,b));//expecting 5
		System.out.println("Multiply: " + c.multiply(a, b));//expecting 50
		System.out.println("Divide: " + c.divide(a, b));//expecting 2
	}
}
