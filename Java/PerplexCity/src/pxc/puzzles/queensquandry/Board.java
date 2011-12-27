package pxc.puzzles.queensquandry;

public class Board {
	private char[][] achrBoard = new char[8][8];
	private int intNumQueens = 0;
	
	Board() {
		for (int intRow = 0; intRow < 8; intRow += 1)
			for (int intCol = 0; intCol < 8; intCol +=1)
				achrBoard[intRow][intCol] = '.';
	}

	Board(Board pBoard) {
		for (int intRow = 0; intRow < 8; intRow += 1)
			for (int intCol = 0; intCol < 8; intCol +=1)
				achrBoard[intRow][intCol] = pBoard.Item(intRow,intCol);
		
		intNumQueens = pBoard.NumQueens();
	}

	public int NumQueens() {
		return intNumQueens;
	}
	
	public char Item(int pRow, int pCol) {
		return achrBoard[pRow][pCol];
	}
	
	void Print() {
		for (int intRow = 0; intRow < 8; intRow += 1) {
			for (int intCol = 0; intCol < 8; intCol +=1) {
				System.out.print(achrBoard[intRow][intCol]);
			}
			
			System.out.println();
		}
	}
	
	public void PlaceQueen(int pRow, int pCol) {
		if (achrBoard[pRow][pCol] == '.') {
			
			for (int intIndex = -7; intIndex < 8; intIndex += 1) {
				if ((pRow - intIndex >= 0) && (pRow - intIndex < 8) && (pCol - intIndex >= 0) && (pCol - intIndex < 8)) {
					achrBoard[pRow-intIndex][pCol-intIndex]='X';
				}	
				
				if ((pCol - intIndex >= 0) && (pCol - intIndex < 8)) {
					achrBoard[pRow][pCol-intIndex]='X';
				}	
				
				if ((pRow - intIndex >= 0) && (pRow - intIndex < 8)) {
					achrBoard[pRow-intIndex][pCol]='X';
				}	
			
				if ((pRow + intIndex >= 0) && (pRow + intIndex < 8) && (pCol - intIndex >= 0) && (pCol - intIndex < 8)) {
					achrBoard[pRow+intIndex][pCol-intIndex]='X';
				}		
			}
			
			achrBoard[pRow][pCol] = 'O';
			intNumQueens += 1;
		}
	}

	public void Iterate() {
		Board CurBoard = new Board(this);
		
		for (int intRow = 0; intRow < 8; intRow += 1 ) {
			for (int intCol = 0; intCol < 8; intCol += 1) {	
				
				if (intNumQueens == 0) {
					System.out.println("Processing main board:  Row " + intRow + " Col " + intCol);
				}
				
				if (this.Item(intRow,intCol) == '.') {
					CurBoard = new Board(this);			
					CurBoard.PlaceQueen(intRow,intCol);
					CurBoard.Iterate();
				}
			}
		}

		if (CurBoard.NumQueens() > QueensQuandry.g_FinalBoard.NumQueens()) {
			QueensQuandry.g_FinalBoard= CurBoard;
			
			System.out.println(QueensQuandry.g_FinalBoard.NumQueens());
			QueensQuandry.g_FinalBoard.Print();	
			System.out.println();

		}
	}
}
