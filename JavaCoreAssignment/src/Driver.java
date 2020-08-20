
public class Driver {
	public static void main(String[] args) {
		AssignmentFunctions af = new AssignmentFunctions();
		
		int arrQ1[] = {1,0,5,6,3,2,7,9,8,4};
		Employee e1 = new Employee("Greg", "Accounting", 38);
		Employee e2 = new Employee("Josh", "Sales", 32);
		
		
		af.Q1BubbleSort(arrQ1);
		af.Q2FibonacciNumbers();
		af.Q3ReverseString("palindrome");
		af.Q4Factorial(5);
		af.Q5Substring("ABCDEF", 3);
		af.Q6IsEven(6);
		af.Q7CompareEmployees(e1, e2);
		af.Q8StorePalindromes("karan","madam","tom","civic","radar","jimmy","kayak","john","refer","billy","did");
		af.Q9DisplayPrimes();
		af.Q10MinimumNumber(5, 10);
		af.Q11AccessOtherPackage();
		af.Q12PrintEvenNumbers();
		af.Q13PrintTriangle(6);
		af.Q14DemonstrateSwitchCase(1, 4);
		af.Q14DemonstrateSwitchCase(2, 4);
		af.Q14DemonstrateSwitchCase(3, 4);
		if(args.length > 0) {
			af.Q16GetCharactersInString(args[0]);
		}
		else {
			System.out.println("No command line arguments given for Q16.");
		}
		af.Q17CalculateInterest();
		
	}
}
