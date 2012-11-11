package chapter14;

import java.util.Vector;
import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

public class One {
	public static int MAX_PRIMES = 20;

	public static void main(String[] args) {
		Vector<Long> primes = new Vector<Long>();
		primes.add(2L);
		primes.add(3L);
		
		long number = primes.elementAt(primes.size() - 1);
		int count = primes.size();
		
		while (count < MAX_PRIMES) {
			number += 2L;
			long limit = (long)ceil(sqrt((double)number));
			
			boolean exitFlag = false;
			for (int i = 1; (i < count) && (primes.elementAt(i) <= limit); i++) {
				if (number % primes.elementAt(i) == 0L) {
					exitFlag = true;
					break;
				} 
			}
			
			if (!exitFlag) {
				primes.add(number);
				count++;
			}
		}
		
		for (long n : primes) {
			System.out.println(n);
		}
	}

}
