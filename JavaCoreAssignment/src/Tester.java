import org.junit.Assert;
import org.junit.jupiter.api.Test;

import OtherPackage.ExternalClass;

import java.util.ArrayList;
import java.util.Arrays;

//Test class for Q1 to Q20.
public class Tester {
	AssignmentFunctions af = new AssignmentFunctions();
	
	@Test
	public void TestQ1() {
		int input[] = {1,0,5,6,3,2,7,9,8,4};
		af.Q1BubbleSort(input);
		int expectedResult[] = {0,1,2,3,4,5,6,7,8,9};

		Assert.assertTrue(Arrays.equals(expectedResult, input));
	}
	
	@Test
	public void TestQ2() {
		int expectedFibonacciNumbers[] = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368};
		Assert.assertTrue(Arrays.equals(expectedFibonacciNumbers, af.Q2FibonacciNumbers()));
	}
	
	@Test
	public void TestQ3() {
		Assert.assertEquals("fedcba", af.Q3ReverseString("abcdef"));
	}
	
	@Test
	public void TestQ4() {
		Assert.assertEquals(720, af.Q4Factorial(6));
	}
	
	@Test
	public void TestQ5() {
		Assert.assertEquals("long", af.Q5Substring("longword", 4));
	}
	
	@Test
	public void TestQ6() {
		Assert.assertTrue(af.Q6IsEven(2048));
	}
	
	//Names are compared first. M comes 6 letters after G.
	@Test
	public void TestQ7Former() {
		Employee a = new Employee("Greg", "Accounting", 24);
		Employee b = new Employee("Meg", "Accounting", 24);
		Assert.assertEquals(af.Q7CompareEmployees(a, b), -6);
	}
	
	//If the names are equal, the department is compared. E comes 4 letters after A.
	@Test
	public void TestQ7Latter() {
		Employee a = new Employee("Greg", "Eccounting", 24);
		Employee b = new Employee("Greg", "Accounting", 24);
		Assert.assertEquals(af.Q7CompareEmployees(a, b), 4);
	}
	
	@Test
	public void TestQ8() {
		String[] inputStrings = {"karan","madam","tom","civic","radar","jimmy","kayak","john","refer","billy","did"};
		String[] palindromes = {"madam","civic","radar","kayak","refer","did"};
		ArrayList<ArrayList<String>> result = af.Q8StorePalindromes("karan","madam","tom","civic","radar","jimmy","kayak","john","refer","billy","did");
		Assert.assertTrue(Arrays.equals(inputStrings, result.get(0).toArray()) && Arrays.equals(palindromes, result.get(1).toArray()));
	}
	
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
	
	@Test
	public void TestQ10Former() {
		Assert.assertEquals(5, af.Q10MinimumNumber(5, 10));
	}
	
	@Test
	public void TestQ10Latter() {
		Assert.assertEquals(10, af.Q10MinimumNumber(20, 10));
	}
	
	
	@Test
	public void TestQ11() {
		float[] expectedResult = {1.5f, 2.5f};
		Assert.assertTrue(Arrays.equals(expectedResult, af.Q11AccessOtherPackage()));
	}
	
}
