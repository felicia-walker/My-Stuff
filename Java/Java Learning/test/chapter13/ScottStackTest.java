package chapter13;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScottStackTest {

	@Test
	public void testStringStack() {
		ScottStack<String> stringStack = new ScottStack<String>();
		
		stringStack.push("Larna Evans");
		stringStack.push("Sheila Vixen");
		stringStack.push("Minerva Mink");
		stringStack.listAll();
		
		assertEquals("Minerva Mink", stringStack.pop());
		assertEquals("Sheila Vixen", stringStack.pop());
		assertEquals("Larna Evans", stringStack.pop());
		assertNull(stringStack.pop());
		stringStack.listAll();
	}

	@Test
	public void testDoubleStack() {
		ScottStack<Double> doubleStack = new ScottStack<Double>();
		
		doubleStack.push(3.14159265);
		doubleStack.push(1.1111);
		doubleStack.push(.141);
		doubleStack.listAll();
		
		assertEquals((Double).141, (Double)doubleStack.pop());
		doubleStack.listAll();
		assertEquals((Double)1.1111, doubleStack.pop());
		assertEquals((Double)3.14159265, doubleStack.pop());
		assertNull(doubleStack.pop());
	}
}
