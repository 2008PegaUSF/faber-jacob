//Main method used to run Q18Subclass' methods.
public class Q18Main {
	public static void main(String[] args) {
		Q18Subclass q18 = new Q18Subclass();
		//Test containsUpperCase letters. One string that should true, one that sholud return false
		System.out.println("Method 1: Does helloWORLD contain uppercase letters? " + q18.containsUppercaseLetters("helloWORLD"));//expecting true
		System.out.println("Method 1: Does hellothere contain uppercase letters? " + q18.containsUppercaseLetters("hellothere"));//expecting false
		//Test toUpperCase and toIntPlusTen
		System.out.println("Method 2: Converting abcdef to uppercase: " + q18.toUpperCase("abcdef"));//expecting "ABCDEF"
		System.out.println("Method 3: Converting string 48 to an int plus ten: " + q18.toIntPlusTen("48"));//expecting 58
	}
	
}
