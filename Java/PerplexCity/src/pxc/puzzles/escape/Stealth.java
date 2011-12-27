package pxc.puzzles.escape;

public class Stealth extends Person {
	
	public Stealth(int x, int y) {
		super(x, y, Symbols.STEALTH_CHAR);
		m_direction = Direction.N;
	}

	// Public methods -----------------------------------------
	public boolean move(Board board, Direction direction) throws Exception
	{
		boolean ret_val = false;
		char nextChar = board.getCharAt(m_position, Board.SCALE, direction);
		if (nextChar == Symbols.EMPTY_CHAR || nextChar == Symbols.DOOR_CHAR)
		{
			place(m_position.cloneAdjacent(Board.SCALE, direction), direction, board);
			board.incrNumMoves();
			
			m_direction = direction;
			ret_val = true;
		}
		else
		{
			System.out.println("That's not an empty space.  Try again.");
		}
		
		return ret_val;
	}

	public void draw(Board board) throws Exception
	{
		board.setCharAt(m_position, Symbols.STEALTH_CHAR);
	}
}
