package chapter7;

public class OneTwo {
	
	private static class Blah {
		
		Blah () {
		}
		
		Object xxx() {
			return null;
		}
	}
	
	public static int divide(int[] intArray, int index) throws MyException {
		try {
			return (int)(intArray[index] / 5);
		} catch (IndexOutOfBoundsException e) {
			throw new MyException(intArray, index);
		}
	}
	
	public static void main(String[] args) {
		int[] whee = new int[5];
		
		try {
			Blah testBlah = new Blah();
			testBlah.xxx().toString();
		} catch (NullPointerException e) {
			System.out.println("NullPointerException message: " + e.getMessage());
			e.printStackTrace();
			System.out.println("------------------------");
			System.out.println();
		}
		
		try {
			int[] whee2 = new int[-2];
		} catch (NegativeArraySizeException e) {
			System.out.println("NegativeArraySizeException message: " + e.getMessage());
			e.printStackTrace();
			System.out.println("------------------------");
			System.out.println();
		}
		
		try {
			whee[9] = 3;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException message: " + e.getMessage());
			e.printStackTrace();
			System.out.println("------------------------");
			System.out.println();
		}
		
		try {
			divide(whee, 10);
		} catch (MyException e) {
			System.out.println("Custom message: " + e.getMessage());
		}

		try {
			divide(whee, -2);
		} catch (MyException e) {
			System.out.println("Custom message: " + e.getMessage());
		}
	}
}
