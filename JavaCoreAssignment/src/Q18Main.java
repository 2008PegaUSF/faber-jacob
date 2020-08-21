
public class Q18Main {
	public static void main(String[] args) {
		Q18Subclass q18 = new Q18Subclass();
		System.out.println("Method 1: Does helloWORLD contain uppercase letters? " + q18.containsUppercaseLetters("helloWORLD"));
		System.out.println("Method 1: Does hellothere contain uppercase letters? " + q18.containsUppercaseLetters("hellothere"));
		System.out.println("Method 2: Converting abcdef to uppercase: " + q18.toUpperCase("abcdef"));
		System.out.println("Method 3: Converting string 48 to an int plus ten: " + q18.toIntPlusTen("48"));
	}
	
}
