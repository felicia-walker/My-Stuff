package chapter5;

import static org.junit.Assert.*;
import org.junit.Test;

import chapter5_measures.McmLength;

public class McmLengthTest {

	@Test
	public void testMcmLengthInt() {
		McmLength myLen = new McmLength(1234);
		assertEquals(1234, myLen.getLengthMillimeters());
		assertEquals(123, myLen.getLengthCM());
		assertEquals(1, myLen.getLengthCentimeters());
	}

	@Test
	public void testMcmLengthDouble() {
		McmLength myLen = new McmLength(297.545);
		assertEquals(2975, myLen.getLengthMillimeters());
		assertEquals(298, myLen.getLengthCM());
		assertEquals(3, myLen.getLengthCentimeters());
	}

	@Test
	public void testMcmLengthAdd() {
		McmLength myLen1 = new McmLength(297.545);
		McmLength myLen2 = new McmLength(123);
		McmLength myLen3 = myLen1.add(myLen2);
		assertEquals((2975 + 123), myLen3.getLengthMillimeters());
	}

	@Test
	public void testMcmLengthSubtract() {
		McmLength myLen1 = new McmLength(297.545);
		McmLength myLen2 = new McmLength(123);
		McmLength myLen3 = myLen1.subtract(myLen2);
		assertEquals((2975 - 123), myLen3.getLengthMillimeters());
		
		McmLength myLen4 = new McmLength(500);
		McmLength myLen5 = myLen2.subtract(myLen4);
		assertEquals(0, myLen5.getLengthMillimeters());
	}

	@Test
	public void testMcmLengthMultiply() {
		McmLength myLen1 = new McmLength(297.545);
		McmLength myLen3 = myLen1.multiply(123);
		assertEquals((2975 * 123), myLen3.getLengthMillimeters());
	}

	@Test
	public void testMcmLengthDivide() {
		McmLength myLen1 = new McmLength(297.545);
		McmLength myLen3 = myLen1.divide(123);
		assertEquals((2975 / 123), myLen3.getLengthMillimeters());
		
		McmLength myLen4 = new McmLength();
		McmLength myLen5 = myLen1.divide(0);
		assertEquals(0, myLen5.getLengthMillimeters());
	}

	@Test
	public void testMcmLengthArea() {
		McmLength myLen1 = new McmLength(297.545);
		McmLength myLen2 = new McmLength(123);
		double area = myLen1.area(myLen2);
		assertEquals((2975 * 123), area);
	}

	public void testMcmLengthCompare() {
		McmLength myLen1 = new McmLength(297.545);
		McmLength myLen2 = new McmLength(123);
		McmLength myLen3 = new McmLength(123);
		assertEquals(1, myLen1.compare(myLen2));
		assertEquals(0, myLen2.compare(myLen3));
		assertEquals(-1, myLen2.compare(myLen1));		
	}
}
