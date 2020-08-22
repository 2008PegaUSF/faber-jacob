import org.junit.Assert;
import org.junit.jupiter.api.Test;

import OtherPackage.ExternalClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

//Test class for Q1 to Q20.
public class Tester {
	AssignmentFunctions af = new AssignmentFunctions();
	Q15Implementation q15i = new Q15Implementation();
	Q18Subclass q18s = new Q18Subclass();
	
	//Q1 should sort the array without the need to return it, allowing it to be compared to a hard-coded sorted array.
	@Test
	public void TestQ1() {
		int input[] = {1,0,5,6,3,2,7,9,8,4};
		af.Q1BubbleSort(input);
		int expectedResult[] = {0,1,2,3,4,5,6,7,8,9};

		Assert.assertTrue(Arrays.equals(expectedResult, input));
	}
	
	//Q2 should return an array of the first 25 Fibonacci numbers.
	@Test
	public void TestQ2() {
		int expectedFibonacciNumbers[] = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368};
		Assert.assertTrue(Arrays.equals(expectedFibonacciNumbers, af.Q2FibonacciNumbers()));
	}
	
	//Q3 should return a reversed version of its given string.
	@Test
	public void TestQ3() {
		Assert.assertEquals("fedcba", af.Q3ReverseString("abcdef"));
	}
	
	//Q4 should return a factorial of its given argument. It is only capable of working within the bounds of an int.
	@Test
	public void TestQ4() {
		Assert.assertEquals(720, af.Q4Factorial(6));
	}
	
	//Q5 should return a substring of its given string up to idx-1.
	@Test
	public void TestQ5() {
		Assert.assertEquals("long", af.Q5Substring("longword", 4));
	}
	
	//Q6 should return true given an even number and false given an odd number.
	@Test
	public void TestQ6True() {
		Assert.assertTrue(af.Q6IsEven(2048));
	}
	
	@Test
	public void TestQ6False() {
		Assert.assertFalse(af.Q6IsEven(2047));
	}
	
	//Q7 should return an int based on the comparison of two Employees.
	//When the names of two Employees are different,the number returned should be their alphabetical difference.
	@Test
	public void TestQ7Name() {
		Employee a = new Employee("Greg", "Accounting", 24);
		Employee b = new Employee("Meg", "Accounting", 24);
		Assert.assertEquals(af.Q7CompareEmployees(a, b), -6);
	}
	
	//When the names of two Employees are the same, the number returned should be the alphabetical difference in their department names.
	@Test
	public void TestQ7Department() {
		Employee a = new Employee("Greg", "Eccounting", 24);
		Employee b = new Employee("Greg", "Accounting", 24);
		Assert.assertEquals(af.Q7CompareEmployees(a, b), 4);
	}
	
	//When only the ages are different, the number returned should be their difference in age.
	@Test
	public void TestQ8Age() {
		Employee a = new Employee("Greg", "Accounting", 28);
		Employee b = new Employee("Greg", "Accounting", 24);
		Assert.assertEquals(af.Q7CompareEmployees(a, b), 4);
	}
	
	//Q8 should return an ArrayList containing two ArrayList results.
	//The first ArrayList should contain all of the strings given as an argument.
	//The second ArrayList should contain all of the given strings that are palindromes.
	@Test
	public void TestQ8() {
		String[] inputStrings = {"karan","madam","tom","civic","radar","jimmy","kayak","john","refer","billy","did"};
		String[] palindromes = {"madam","civic","radar","kayak","refer","did"};
		ArrayList<ArrayList<String>> result = af.Q8StorePalindromes("karan","madam","tom","civic","radar","jimmy","kayak","john","refer","billy","did");
		Assert.assertTrue(Arrays.equals(inputStrings, result.get(0).toArray()) && Arrays.equals(palindromes, result.get(1).toArray()));
	}
	
	//Q9 should return an ArrayList containing two ArrayList results.
	//The first ArrayList should be all of the numbers from 1 to 100.
	//The second ArrayList should be all of the prime numbers between 1 to 100 inclusive.
	@Test
	public void TestQ9() {
		Integer[] all_nums = new Integer[100];
		for(int i = 0; i < 100; i++) {
			all_nums[i] = i+1;
		}
		Integer[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
		ArrayList<ArrayList<Integer>> result = af.Q9DisplayPrimes();
		Assert.assertTrue(Arrays.equals(all_nums, result.get(0).toArray()) && Arrays.equals(primes,result.get(1).toArray()));
	}
	
	//Q10 should return the smaller of two arguments given.
	@Test
	public void TestQ10Former() {
		Assert.assertEquals(5, af.Q10MinimumNumber(5, 10));
	}
	
	@Test
	public void TestQ10Latter() {
		Assert.assertEquals(10, af.Q10MinimumNumber(20, 10));
	}
	
	//Q11 should return floats 1.5f and 2.5f in an array.
	//The proof of completion for Q11 is hard-coded in its method, as it accesses a class in another package.
	@Test
	public void TestQ11() {
		float[] expectedResult = {1.5f, 2.5f};
		Assert.assertTrue(Arrays.equals(expectedResult, af.Q11AccessOtherPackage()));
	}
	
	//Q12 should return an array of all even numbers between 1 and 100 inclusive.
	@Test
	public void TestQ12() {
		int[] expectedResult = new int[50];
		for(int i = 0; i < 50; i++) {
			expectedResult[i] = (i+1)*2;
		}
		Assert.assertTrue(Arrays.equals(expectedResult,af.Q12PrintEvenNumbers()));
	}
	
	//Q13 should return an array representing a binary triangle in the pattern 0 1 0 1 0 1 . . . which can be used to print the triangle line by line.
	@Test
	public void TestQ13() {
		String[] expectedResult = {"0 ","1 0 ","1 0 1 ","0 1 0 1 "};
		Assert.assertTrue(Arrays.equals(expectedResult, af.Q13PrintTriangle(4)));
	}
	
	//Q14 should return a string showing the result of work performed in each case of its switchcase according to instructions.
	@Test
	public void TestQ14Part1() {
		Assert.assertEquals("3.0",af.Q14DemonstrateSwitchCase(1, 9));
	}
	
	@Test
	public void TestQ14Part2() {
		Calendar cal = Calendar.getInstance();
		String expectedResult = cal.getTime().toString();
		Assert.assertEquals(expectedResult,af.Q14DemonstrateSwitchCase(2, 9));
	}
	
	@Test
	public void TestQ14Part3() {
		Assert.assertEquals("[I, am, learning, Core, Java]",af.Q14DemonstrateSwitchCase(3, 9));
	}
	
	//Q15 holds simple methods for integer addition, subtraction, multiplication, and division.
	//Proof of completion is in the three .java files Q15Interface, Q15Implementation, and Q15Main, assuming the question was intended to show competence
	//	in creating and using interfaces.
	@Test
	public void TestQ15Add() {
		Assert.assertEquals(3, q15i.add(-5, 8));
	}
	
	@Test
	public void TestQ15Subtract() {
		Assert.assertEquals(-13, q15i.subtract(-5, 8));
	}
	
	@Test
	public void TestQ15Multiply() {
		Assert.assertEquals(-40, q15i.multiply(-5, 8));
	}
	
	@Test
	public void TestQ15Divide() {
		Assert.assertEquals(5, q15i.divide(25, 5));
	}
	
	//Q16 should return the length of a given string. Use of the command line can be found in Driver.java.
	@Test
	public void TestQ16() {
		Assert.assertEquals(5, af.Q16GetCharactersInString("abcde"));
	}
	
	//Q17 should return the interest based on principal, rate, and time. For ease of use, this test only tests the formula used to calculate the interest.
	@Test
	public void TestQ17() {
		Assert.assertEquals(1.25f,af.Q17Formula(5f, 5f, 5f),0f);
	}
	
	//Q18's containsUppercaseLetters should return true if a given string contains uppercase characters, or false if it does not.
	@Test
	public void TestQ18containsUppercaseStringTrue() {
		Assert.assertTrue(q18s.containsUppercaseLetters("helloWorld"));
	}
	
	@Test
	public void TestQ18containsUppercaseStringFalse() {
		Assert.assertFalse(q18s.containsUppercaseLetters("helloworld"));
	}
	
	//Q18's toUpperCase should return a string that converts lowercase letters a given string to uppercase.
	@Test
	public void TestQ18toUpperCase() {
		Assert.assertEquals("HELLOWORLD", q18s.toUpperCase("helloworld"));
	}
	
	//Q18's toIntPlusTen should return an int of a given string that represents an int, plus ten.
	@Test
	public void TestQ18toIntPlusTen() {
		Assert.assertEquals(25, q18s.toIntPlusTen("15"));
	}
	
	//Q19 should return an ArrayList containing two Integer ArrayLists.
	//The first ArrayList<Integer> should contain all numbers from 1 to 10 that are not primes.
	//The second ArrayList<Integer> should contain the sum of all even numbers from 1 to 10 and the sum of all odd numbers from 1 to 10, in that order.
	@Test
	public void TestQ19() {
		Integer expectedNonPrimes[] = {1,4,6,8,9,10};
		ArrayList<ArrayList<Integer>> result = af.Q19ArrayListManipulation();
		Integer expectedSums[] = {30,25};
		
		System.out.println("Test results:");
		System.out.println(result.get(0));
		System.out.println(result.get(1));
		
		Assert.assertTrue(Arrays.equals(expectedNonPrimes, result.get(0).toArray())
				&& Arrays.equals(expectedSums, result.get(1).toArray()));
	}
	
	//Q20 should return a formatted string based on the given file Data.txt.
	@Test
	public void TestQ20() throws IOException {
		String expectedResult = 
				"Name: Mickey Mouse"
				+ "\nAge: 35 years"
				+ "\nState: Arizona State"
				+ "\nName: Hulk Hogan"
				+ "\nAge: 50 years"
				+ "\nState: Virginia State"
				+ "\nName: Roger Rabbit"
				+ "\nAge: 22 years"
				+ "\nState: California State"
				+ "\nName: Wonder Woman"
				+ "\nAge: 18 years"
				+ "\nState: Montana State\n";
		
		String actual = af.Q20DisplayFileData("Data.txt");
		
		Assert.assertEquals(expectedResult, actual);
	}
	
}
