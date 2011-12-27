package pxc.puzzles.escape;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Escape {

	final private static int NUM_GUARDS = 5;
	
	private static Board m_board;
	private static Stealth m_stealth;
	private static Guard[] m_guards = new Guard[NUM_GUARDS];
	private static StringBuilder m_history = new StringBuilder();
	
	public static void main(String[] args) throws Exception {

		// Set up the board
		m_board = new Board("board.txt");
		m_stealth = new Stealth(26, 60);
		m_guards[0] = new Guard(10, 14, Direction.N, 3, true);
		m_guards[1] = new Guard(26, 26, Direction.N, 3, true);
		m_guards[2] = new Guard(2, 32, Direction.S, 3, false);
		m_guards[3] = new Guard(14, 36, Direction.N, 3, true);
		m_guards[4] = new Guard(42, 54, Direction.W, 6, true);

		// Draw the initial board
		drawBoard();

		int totalTime = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {

			// Get keyboard input and loop until a valid character is entered
			System.out.println("You have taken " + totalTime + " seconds so far.");
			System.out.print("Direction (N, S, E, W), wait (X), or quit (Q):  ");
			String line;
			do {
				line = String.valueOf(br.readLine().toUpperCase());
			} while (!"NSEWXQ".contains(line));
			char dir = line.charAt(0);

			// Execute the command
			boolean moveFlag = false;
			switch (dir) {
			case 'X':
				// Do nothing, so we wait in place for 1 sec
				totalTime++;
				m_history.append('X');
				moveGuards();
				
				break;

			case 'Q':
				// Quit, print history and exit
				System.out.println("Move history:");
				System.out.println(m_history);
				
				return;

			default:
				// Moves are the only remaining commands
				
				moveFlag = m_stealth.move(m_board, Direction.fromChar(dir));
				
				// If we actually moved, update time and move guards twice
				if (moveFlag)
				{
					totalTime += 2;
					m_history.append(dir);
					moveGuards();
					moveGuards();
				}
			}

			// Redraw the board
			drawBoard();
		}
	}

	private static void drawBoard() throws Exception {
		m_board.reset();
		m_stealth.draw(m_board);
		
		for (int i = 0; i < NUM_GUARDS; i++) {
			m_guards[i].draw(m_board);
		}	
		
		m_board.printBoard();
	}
	
	private static void moveGuards() throws Exception {
		for (int i = 0; i < NUM_GUARDS; i++) {
			m_guards[i].move(m_board);
		}
	}
}
