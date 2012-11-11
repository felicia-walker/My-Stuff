package chapter3;

public class Two {

	public static void main(String[] args) {
		int nValues = 50;
		boolean isPrime = true;
		
		for (int i = 2; i <= nValues; i++) {
			isPrime = true;
			
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			} 
			
			if (isPrime) {
				System.out.println(i);
			}
		}
	}

}
