
public class Calculator {
	public int calculate(String ops) throws IllegalArgumentException {
		String[] symbols = ops.split("\\s");
		if(symbols.length != 3) {
			throw new IllegalArgumentException("Incorrect input string format. Proper format: x <opertor> y");
		}
			switch(symbols[1]) {
			case "+":
				return Integer.parseInt(symbols[0]) + Integer.parseInt(symbols[2]);
			case "-":
				return Integer.parseInt(symbols[0]) - Integer.parseInt(symbols[2]);
			case "*":
				return Integer.parseInt(symbols[0]) * Integer.parseInt(symbols[2]);
			case "/":
				return Integer.parseInt(symbols[0]) / Integer.parseInt(symbols[2]);
			case "%":
				return Integer.parseInt(symbols[0]) % Integer.parseInt(symbols[2]);
			default:
				System.out.println("Invalid operator.");
				return -1;
			}
	}
}
