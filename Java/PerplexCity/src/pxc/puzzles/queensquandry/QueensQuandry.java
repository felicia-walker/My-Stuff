package pxc.puzzles.queensquandry;
public class QueensQuandry {

	public static Board g_FinalBoard = new Board();
	
	public static void main(String[] args) {
		Board InitBoard = new Board();
		
		InitBoard.iterate();
		
		System.out.println(g_FinalBoard.numQueens());
		g_FinalBoard.printBoard();	
		System.out.println();

	}
}
