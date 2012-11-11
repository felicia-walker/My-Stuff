package chapter4;

public abstract class Two {

	public static void main(String[] args) {
		final int MAX_NUM = 12;
		final String SPACE = "     ";
		final int SPACE_LEN = SPACE.length();
		
		int[][] multTable = new int[MAX_NUM][MAX_NUM];
		
		// Make the table
		for (int i = 0; i < MAX_NUM; i++) {
			for (int j = 0; j < MAX_NUM; j++) {
				multTable[i][j] = (i + 1) * (j + 1);
			}
		}
		
		// Print the header row
		System.out.print(SPACE);
		for (int col = 0; col < MAX_NUM; col++) {
			String strNum = String.valueOf(col + 1);
			String tmpString = SPACE.substring(0, SPACE_LEN - strNum.length()) + strNum;
			System.out.print(tmpString);
		}
		System.out.println();
		
		// Print the table
		for (int row = 0; row < MAX_NUM; row++) {
			
			// Print the number first
			String strNum = String.valueOf(row + 1);
			String tmpString = SPACE.substring(0, SPACE_LEN - strNum.length()) + strNum;
			System.out.print(tmpString);
			
			// Print the rest of the row
			for (int col = 0; col < MAX_NUM; col ++) {
				strNum = String.valueOf(multTable[row][col]);
				tmpString = SPACE.substring(0, SPACE_LEN - strNum.length()) + strNum;
				System.out.print(tmpString);
			}
			System.out.println();
		}
	}

}
