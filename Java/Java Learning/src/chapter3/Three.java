package chapter3;

public class Three {

	public static void main(String[] args) {
		final int MAX_NUM = 49;
		final int MAX_NUMS = 6;
		final int MAX_SETS = 5;
		
		for (int set = 1; set <= MAX_SETS; set++) {
			System.out.print("Set " + set + ":  ");
			
			for (int num = 1; num <= MAX_NUMS; num++) {
				int newNum = 1 + (int)(MAX_NUM * Math.random());
				System.out.print(newNum + " ");
			}
			
			System.out.println();
		}
	}

}
