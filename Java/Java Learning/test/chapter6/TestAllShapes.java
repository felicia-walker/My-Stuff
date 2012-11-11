package chapter6;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestAllShapes {

	@Test
	public void testLine() {
		Line testLine1 = new Line();
		Line testLine2 = new Line(6, 7, 22, 13);
		Line testLine3 = new Line(-1, -2, -3, -4);
		assertEquals("0,0,0,0", testLine1.toString());
		assertEquals("6,7,22,13", testLine2.toString());
		assertEquals("-1,-2,-3,-4", testLine3.toString());
	}
	
	@Test
	public void testLineMove() {
		Line testLine = new Line(6, 7, 22, 13);
		
		testLine.move(3, 2);
		assertEquals("3,2,19,8", testLine.toString());
		testLine.move(15, 17);
		assertEquals("15,17,31,23", testLine.toString());
		testLine.move(-15, -6);
		assertEquals("-15,-6,1,0", testLine.toString());
	}

	@Test
	public void testCircle() {
		Circle testCircle1 = new Circle();
		Circle testCircle2 = new Circle(6, 7, 13);
		Circle testCircle3 = new Circle(-1, -2, -4);
		assertEquals("0,0,0", testCircle1.toString());
		assertEquals("6,7,13", testCircle2.toString());
		assertEquals("-1,-2,0", testCircle3.toString());
	}
	
	@Test
	public void testCircleMove() {
		Circle testCircle = new Circle(6, 7, 13);
		
		testCircle.move(3, 2);
		assertEquals("3,2,13", testCircle.toString());
		testCircle.move(15, 17);
		assertEquals("15,17,13", testCircle.toString());
		testCircle.move(-15, -6);
		assertEquals("-15,-6,13", testCircle.toString());
	}

	@Test
	public void testRectangle() {
		Rectangle testRectangle1 = new Rectangle();
		Rectangle testRectangle2 = new Rectangle(6, 7, 22, 13);
		Rectangle testRectangle3 = new Rectangle(-1, -2, -3, -4);
		assertEquals("0,0,0,0", testRectangle1.toString());
		assertEquals("6,7,22,13", testRectangle2.toString());
		assertEquals("-1,-2,-3,-4", testRectangle3.toString());
	}
	
	@Test
	public void testRectangleMove() {
		Rectangle testRectangle = new Rectangle(6, 7, 22, 13);
		
		testRectangle.move(3, 2);
		assertEquals("3,2,19,8", testRectangle.toString());
		testRectangle.move(15, 17);
		assertEquals("15,17,31,23", testRectangle.toString());
		testRectangle.move(-15, -6);
		assertEquals("-15,-6,1,0", testRectangle.toString());
	}
}
