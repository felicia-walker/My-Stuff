package pxc.puzzles.queensquandry;
public class QueensQuandry {

	public static Board g_FinalBoard = new Board();
	
	public static void main(String[] args) {
		Board InitBoard = new Board();
		
		InitBoard.Iterate();
		
		System.out.println(g_FinalBoard.NumQueens());
		g_FinalBoard.Print();	
		System.out.println();

	}
}
