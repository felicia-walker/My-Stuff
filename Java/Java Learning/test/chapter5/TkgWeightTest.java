package chapter5;

import static org.junit.Assert.*;

import org.junit.Test;

import chapter5_measures.TkgWeight;

public class TkgWeightTest {

	@Test
	public void testTkgWeightInt() {
		TkgWeight myWght = new TkgWeight(2000);
		assertEquals(2000, myWght.getWeightGrams());
		assertEquals(2, myWght.getWeightKilograms());
		assertEquals(2000 / TkgWeight.G_IN_T, myWght.getWeightTons());
	}

	@Test
	public void testTkgWeightDouble() {
		TkgWeight myWght = new TkgWeight(2.5);
		assertEquals(2500, myWght.getWeightGrams());
		assertEquals(2.5, myWght.getWeightKilograms());
		assertEquals(2500 / TkgWeight.G_IN_T, myWght.getWeightTons());
	}

	@Test
	public void testTkgWeightAdd() {
		TkgWeight myWght1 = new TkgWeight(2.5);
		TkgWeight myWght2 = new TkgWeight(2000);
		TkgWeight myWght3 = myWght1.add(myWght2);
		assertEquals((2500 + 2000), myWght3.getWeightGrams());
	}

	@Test
	public void testTkgWeightSubtract() {
		TkgWeight myWght1 = new TkgWeight(2.5);
		TkgWeight myWght2 = new TkgWeight(2000);
		TkgWeight myWght3 = myWght1.subtract(myWght2);
		assertEquals((2500 - 2000), myWght3.getWeightGrams());
		
		TkgWeight myWght4 = new TkgWeight(8500);
		TkgWeight myWght5 = myWght2.subtract(myWght4);
		assertEquals(0, myWght5.getWeightGrams());
	}

	@Test
	public void testTkgWeightMultiply() {
		TkgWeight myWght1 = new TkgWeight(2500);
		TkgWeight myWght3 = myWght1.multiply(3);
		assertEquals((2500 * 3), myWght3.getWeightGrams());
	}

	@Test
	public void testTkgWeightDivide() {
		TkgWeight myWght1 = new TkgWeight(2500);
		TkgWeight myWght3 = myWght1.divide(3);
		assertEquals((2500 / 3), myWght3.getWeightGrams());
		
		TkgWeight myWght4 = new TkgWeight();
		TkgWeight myWght5 = myWght1.divide(0);
		assertEquals(0, myWght5.getWeightGrams());
	}

	public void testTkgWeightCompare() {
		TkgWeight myWght1 = new TkgWeight(2000);
		TkgWeight myWght2 = new TkgWeight(2.5);
		TkgWeight myWght3 = new TkgWeight(2.5);
		assertEquals(1, myWght1.compare(myWght2));
		assertEquals(0, myWght2.compare(myWght3));
		assertEquals(-1, myWght2.compare(myWght1));		
	}
}
