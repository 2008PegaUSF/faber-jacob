
public class Q18Subclass extends Q18Superclass {

	@Override
	public boolean containsUppercaseLetters(String str) {
		//For each character, if it is uppercase, return true
		for(int i = 0; i < str.length(); i++) {
			if(Character.isUpperCase(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toUpperCase(String str) {
		String result = "";
		//Convert each character to an uppercase letter if applicable
		for(int i = 0; i < str.length(); i++) {
			result += Character.toUpperCase(str.charAt(i));
		}
		return result;
	}

	@Override
	public int toIntPlusTen(String str) {
		//Use parseInt to convert the string to an int, then return the result + 10
		return Integer.parseInt(str) + 10;
	}

	
}
