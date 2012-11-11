package chapter7;

import static org.junit.Assert.*;

import org.junit.Test;

public class OneTwoTest {

	@Test
	public void testDivide() {
		int[] whee = new int[5];
		
		try {
			OneTwo.divide(whee, -1);
		} catch (MyException e) {
			assertEquals("Index was smaller than the lower index of the object.", e.getMessage());
		}
		
		try {
			OneTwo.divide(whee, 125);
		} catch (MyException e) {
			assertEquals("Index was larger than the size of the object.", e.getMessage());
		}
	}
}
