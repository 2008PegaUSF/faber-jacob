import java.util.ArrayList;
import java.util.Comparator;

import OtherPackage.ExternalClass;


public class AssignmentFunctions {
	
	//Creates a string that shows all members of an integer array.
	private String IntArrayToString(int arr[]) {
		String s = "[";
		
		for(int i = 0; i < arr.length; i++) {
			s += arr[i];
			if(i < arr.length-1) {
				s += ", ";
			}
		}
		s += "]";
		return s;
	}
	private String FloatArrayToString(float arr[]) {
		String s = "[";
		
		for(int i = 0; i < arr.length; i++) {
			s += arr[i];
			if(i < arr.length-1) {
				s += ", ";
			}
		}
		s += "]";
		return s;
	}
	
	//Returns whether a given string is a palindrome or not.
	public boolean isPalindrome(String str) {
		//Start/end counters for comparing characters in the string.
		int start = 0;
		int end = str.length()-1;
		//Starting from the ends of the string, compare characters and work towards the middle. If there is a mismatch, return false.
		while(start < end) {
			//Compare characters at either counter and move them one position towards the center on each pass.
			if(str.charAt(start++) != str.charAt(end--)) {
				return false;
			}
		}	
		//If no mismatches were found and the start/end counters have passed each other, return true.
		return true;
	}
	
	//Returns whether a given number is a prime. This is an O(n) implementation, but O(sqrt(n)) is possible in other implementations.
	public boolean isPrime(Integer n) {
		//Check each number from 2 to n-1. If n is divisible by one of the numbers i, then return false.
		for(int i = 2; i <= n / 2; i++) {
			if(n % i == 0) {
				return false;
			}
		}
		//If n is not divisible by any number under it, it is a prime.
		return true;
	}
	
	//Takes an integer array as an argument and sorts it
	public void Q1BubbleSort(int arr[]) {
		System.out.println("=== Q1: Bubble Sort ===");
		System.out.println("Before: " + IntArrayToString(arr));
		boolean hasSwapped = true;
		//Run until a pass where no values are swapped
		int passes = 0;
		while(hasSwapped) {
			passes += 1;
			hasSwapped = false;
			for(int i = 0; i < arr.length - 1; i++) {
				if(arr[i+1] < arr[i]) {
					hasSwapped = true;
					//Swap current position w/ next position
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
				}//end if next element is bigger
			}//end for
			System.out.println("Pass " + passes + ": " + IntArrayToString(arr));
		}//end while(hasPassed)
	}//end Q1BubbleSort
	
	
	public int[] Q2FibonacciNumbers() {
		System.out.println("=== Q2: Fibonacci Numbers ===");
		int fibonacciNumbers[] = new int[25];
		//Set up the two initial Fibonacci numbers
		fibonacciNumbers[0] = 0;
		fibonacciNumbers[1] = 1;
		
		//Calculate 23 more Fibonacci numbers to get 25
		for(int i = 2; i < 25; i++) {
			fibonacciNumbers[i] = fibonacciNumbers[i-1] + fibonacciNumbers[i-2];
		}
		String result = "Result: " + IntArrayToString(fibonacciNumbers);
		System.out.println(result);
		return fibonacciNumbers;
	}
	
	public String Q3ReverseString(String str) {
		System.out.println("=== Q3: Reverse String Without Temporary Variable ===");
		System.out.println("Before: " + str);
		//Convert the string to a char array for XOR operations
		char[] charr = str.toCharArray();
		//Dynamically point to start/end of the char array
		int start = 0;
		int end = str.length()-1;
		while(start < end) {
			//Use XOR operations to swap characters starting at the start/end and working towards the middle
			charr[start] ^= charr[end];
			charr[end] ^= charr[start];
			charr[start++] ^= charr[end--];
		}
		System.out.println("After: " + String.valueOf(charr));
		return String.valueOf(charr);
	}
	
	public int Q4Factorial(int n) {
		System.out.println("=== Q4: Factorial ===");
		System.out.println("Input: " + n);
		int nFactorial = 1;
		//Multiply nFactorial by each positive integer from 1 to n
		for(int i = 1; i <= n; i++) {
			nFactorial *= i;
		}
		System.out.println("Output: " + nFactorial);
		return nFactorial;
	}
	
	public String Q5Substring(String str, int idx) {
		System.out.println("=== Q5: Substring ===");
		System.out.println("Inputs: " + str + " " + idx);
		String strout = "";
		for(int i = 0; i < idx; i++) {
			strout += str.charAt(i);
		}
		System.out.println("Result: " + strout);
		return strout;
	}
	
	public boolean Q6IsEven(int n) {
		System.out.println("=== Q6: IsEven ===");
		System.out.println("Input: " + n);
		//Perform bitwise XOR by 1, will equal n+1 if even
		boolean result = (n ^ 1) == n + 1;
		System.out.println("Result: " + result);
		return result;
	}
	
	//Returns the result of a Comparator interface comparison of two Employees.
	public int Q7CompareEmployees(Employee a, Employee b) {
		System.out.println("=== Q7: Compare Employees ===");
		System.out.println("Input: " + a + ", " + b);
		//Create a new EmployeeComparator and return its comparison
		EmployeeComparator ec = new EmployeeComparator();
		int result = ec.compare(a,b);
		System.out.println("Result: " + result);
		return result;
	}
	
	//Returns two ArrayLists: one containing all of the strings sent as parameters, and one containing all of the perameters that are palindromes.
	public ArrayList<ArrayList<String>> Q8StorePalindromes(String...strings) {
		System.out.println("=== Q8: Store Palindromes ===");
		System.out.print("Input: ");
		for(String s: strings) {
			System.out.print(s + " ");
		}
		System.out.println();
		ArrayList<String> all_strings = new ArrayList<String>();
		ArrayList<String> palindromes = new ArrayList<String>();
		//For each string, add it to all_strings, and add it to palindromes if it is a palindrome.
		for(String s : strings) {
			all_strings.add(s);
			if(isPalindrome(s)) {
				palindromes.add(s);
			}
		}
		System.out.println("Result: " + palindromes);
		//Store all_strings and palindromes in an ArrayList to return.
		ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
		lists.add(all_strings);
		lists.add(palindromes);
		return lists;
	}
	
	//Returns two ArrayLists: the list of all numbers from 1 to 100, and a list of all of the prime numbers from 1 to 100.
	public ArrayList<ArrayList<Integer>> Q9DisplayPrimes() {
		System.out.println("=== Q9: Display Primes ===");
		ArrayList<Integer> all_numbers = new ArrayList<Integer>();
		ArrayList<Integer> primes = new ArrayList<Integer>();
		//Store 1 to 100 in all_numbers. If said number is prime, also store it primes.
		for(int i = 1; i <= 100; i++) {
			all_numbers.add(i);
			if(isPrime(i)) {
				primes.add(i);
			}
		}
		//Store all_numbers and primes in an ArrayList to return.
		ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
		lists.add(all_numbers);
		lists.add(primes);
		System.out.println("Result: " + primes);
		return lists;
	}
	
	//Returns a if a <= b, or b if a > b.
	public int Q10MinimumNumber(int a, int b) {
		System.out.println("=== Q10: Mininum Number Of Two ===");
		//Returns the lesser of a and b using a ternary operator. The former int a is returned if both numbers are equal.
		int result = a <= b ? a : b;
		System.out.println("Result: " + result);
		return result;
	}
	
	//Returns an array containing two floats from the ExternalClass class in OtherPackage.
	public float[] Q11AccessOtherPackage() {
		System.out.println("=== Q11: Access Other Package ===");
		//Call constructor for the class located in OtherPackage. Import statement for the class is near the top of this file.
		ExternalClass ec = new ExternalClass(1.5f,2.5f);
		//Store the floats in an array to return.
		float[] result = {ec.getFloat1(),ec.getFloat2()};
		System.out.println("Result: " + FloatArrayToString(result));
		return result;
	}
	
	//Returns an array containing only even numbers.
	//An advanced for loop is used to check each number from 1 to 100, and even numbers are stored in the array to return.
	public int[] Q12PrintEvenNumbers() {
		System.out.println("=== Q12: Print Even Numbers ===");
		int all_numbers[] = new int[100];
		int even_numbers[] = new int[50];
		//Counter to increase whenever a number is stored in even_numbers.
		int even_count = 0;
		//Store values 1 to 100 in all_numbers.
		for(int i = 0; i < 100; i++) {
			all_numbers[i] = i+1;
		}
		//For each number n in all_numbers, if n is even, store it in even_numbers.
		for(int n : all_numbers) {
			if(n % 2 == 0) {
				even_numbers[even_count++] = n;
			}
		}
		System.out.println("Result: " + IntArrayToString(even_numbers));
		return even_numbers;
	}
	
	public String[] Q13PrintTriangle(int size) {
		System.out.println("=== Q13: Print Triangle ===");
		String[] triangle = new String[size];
		for(int i = 0; i < size; i++) {
			String layer = "";
			//My formula to make each layer start in the pattern of 0 1 1 0 0 1 1 0 0 . . .
			layer += (i + 1) % 4 > 1 ? "1 " : "0 ";
			//Run layer-1 times to populate each layer of the triangle with 1s and 0s
			for(int j = 0; j < i; j++) {
				//If the latest character in the layer is 0, append a 1 at the end, or append 0 otherwise
				layer += layer.charAt(layer.length()-2) == '0' ? "1 " : "0 ";
			}
			//Add the layer to the triangle
			triangle[i] = layer;
			
		}
		System.out.println("Result:");
		for(int i = 0; i < triangle.length; i++) {
			System.out.println(triangle[i]);
		}
		return triangle;
	}
	
	
	
}







