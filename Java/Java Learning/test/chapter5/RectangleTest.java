package chapter5;

import static org.junit.Assert.*;

import org.junit.Test;

public class RectangleTest {

	@Test
	public void testRectangle() {
		Rectangle myRect = new Rectangle(10, 20, 30 , 40);
		assertEquals(myRect.getTopLeftX(), 10);
		assertEquals(myRect.getTopLeftY(), 20);
		assertEquals(myRect.getBottomRightX(), 30);
		assertEquals(myRect.getBottomRightY(), 40);

		Rectangle newRect = new Rectangle(myRect);
		assertEquals(newRect.getTopLeftX(), 10);
		assertEquals(newRect.getTopLeftY(), 20);
		assertEquals(newRect.getBottomRightX(), 30);
		assertEquals(newRect.getBottomRightY(), 40);
	}

	@Test
	public void testGetEnclosingRect() {
		Rectangle rect = new Rectangle(10, 20, 30, 40);
		Rectangle rect1 = new Rectangle(5, 20, 30, 40);
		Rectangle rect2 = new Rectangle(10, 15, 30, 40);
		Rectangle rect3 = new Rectangle(10, 20, 35, 40);
		Rectangle rect4 = new Rectangle(10, 20, 30, 45);
		
		System.out.println("Subtest 1");
		Rectangle newRect1 = rect.getEnclosingRect(rect1);
		newRect1.printCorners();
		assertEquals(newRect1.getTopLeftX(), 5);
		assertEquals(newRect1.getTopLeftY(), 20);
		assertEquals(newRect1.getBottomRightX(), 30);
		assertEquals(newRect1.getBottomRightY(), 40);

		System.out.println("Subtest 2");
		newRect1 = rect.getEnclosingRect(rect2);
		newRect1.printCorners();
		assertEquals(newRect1.getTopLeftX(), 10);
		assertEquals(newRect1.getTopLeftY(), 15);
		assertEquals(newRect1.getBottomRightX(), 30);
		assertEquals(newRect1.getBottomRightY(), 40);

		System.out.println("Subtest 3");
		newRect1 = rect.getEnclosingRect(rect3);
		newRect1.printCorners();
		assertEquals(newRect1.getTopLeftX(), 10);
		assertEquals(newRect1.getTopLeftY(), 20);
		assertEquals(newRect1.getBottomRightX(), 35);
		assertEquals(newRect1.getBottomRightY(), 40);

		System.out.println("Subtest 4");
		newRect1 = rect.getEnclosingRect(rect4);
		newRect1.printCorners();
		assertEquals(newRect1.getTopLeftX(), 10);
		assertEquals(newRect1.getTopLeftY(), 20);
		assertEquals(newRect1.getBottomRightX(), 30);
		assertEquals(newRect1.getBottomRightY(), 45);

		System.out.println("Subtest 5");
		newRect1 = rect.getEnclosingRect(rect1);
		Rectangle newRect2 = newRect1.getEnclosingRect(rect2);
		Rectangle newRect3 = newRect2.getEnclosingRect(rect3);
		Rectangle newRect4 = newRect3.getEnclosingRect(rect4);
		assertEquals(newRect4.getTopLeftX(), 5);
		assertEquals(newRect4.getTopLeftY(), 15);
		assertEquals(newRect4.getBottomRightX(), 35);
		assertEquals(newRect4.getBottomRightY(), 45);
		
	}

}
