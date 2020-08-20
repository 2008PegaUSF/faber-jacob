import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
	static Calculator c = new Calculator();
	
	@Test
	public void CalcTestAdd() {
		Assertions.assertEquals(10, c.calculate("5 + 5"));
	}

	@Test
	public void CalcTestSubtract() {
		Assertions.assertEquals(3, c.calculate("6 - 3"));
	}
	
	@Test
	public void CalcTestMultiply() {
		Assertions.assertEquals(55, c.calculate("5 * 11"));
	}
	
	@Test
	public void CalcTestDivide() {
		Assertions.assertEquals(3, c.calculate("21 / 7"));
	}
	
	@Test
	public void CalcTestModule() {
		Assertions.assertEquals(3, c.calculate("15 % 12"));
	}
	
}
