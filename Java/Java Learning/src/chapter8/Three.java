package chapter8;

public class Three {

	public static void main(String[] args) {
		double curValue = 0;
		
		for (int i = 1; i <= 20; i++) {
			curValue = (Math.random() * 100) - 50;
			System.out.printf("%2d) %+6.2f  ", i, curValue);
			
			if (i % 5 == 0) {
				System.out.println();
			}
		}
	}

}
