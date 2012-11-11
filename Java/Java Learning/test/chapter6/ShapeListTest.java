package chapter6;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ShapeListTest {

	public static ShapeList myShapeList1;
	public static ShapeList myShapeList2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Rectangle rect1 = new Rectangle(1,1,3,3);
		myShapeList1 = new ShapeList();
		myShapeList2 = new ShapeList(rect1);
	}

	@Test
	public void testLength() {
		assertEquals(0, myShapeList1.length());
		assertEquals(1, myShapeList2.length());		
	}

	@Test
	public void testAdd() {
		Rectangle rect1 = new Rectangle(2,3,4,5);
		Circle circle1 = new Circle(7,8,12);
		Line line1 = new Line(8,9,10,11);
		
		myShapeList1.add(line1);
		myShapeList1.add(circle1);
		myShapeList1.add(rect1);
		assertEquals(myShapeList1.length(), 3);

		myShapeList2.add(rect1);
		assertEquals(myShapeList2.length(), 2);
}

	@Test
	public void testGetShapeAt() {
		Shape tmpShape = myShapeList2.getShapeAt(1);
		assertEquals("2,3,4,5", tmpShape.toString());

		tmpShape = myShapeList1.getShapeAt(1);
		assertEquals("7,8,12", tmpShape.toString());
}

	@Test
	public void testRemoveAt() {
		myShapeList1.removeAt(1);
		Shape tmpShape = myShapeList1.getShapeAt(1);
		assertEquals("2,3,4,5", tmpShape.toString());
		tmpShape = myShapeList1.getShapeAt(0);
		assertEquals("8,9,10,11", tmpShape.toString());
	}

	@Test
	public void testPrintList() {
		myShapeList1.printList();
		System.out.println();
		myShapeList2.printList();
	}

}
