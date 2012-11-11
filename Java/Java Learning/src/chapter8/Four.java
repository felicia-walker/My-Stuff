package chapter8;

import java.util.Formatter;

public class Four {

	public static void main(String[] args) {
		double curValue = 0;
		StringBuffer outBuffer = new StringBuffer();
		Formatter formatter = new Formatter(outBuffer);
		
		for (int i = 1; i <= 20; i++) {
			curValue = (Math.random() * 100) - 50;
			formatter.format("%2d) %+6.2f  ", i, curValue);
			
			if (i % 5 == 0) {
				formatter.format("\n");
			}
		}
		
		System.out.println(outBuffer.toString());
	}

}
