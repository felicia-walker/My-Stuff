package pxc.puzzles.escape;

public class Guard extends Person {

	private enum TurnState {
		None, Wait, Complete
	};

	private int m_lookLength;
	private boolean m_turnCW;

	// Force guard to move straight ahead
	private TurnState m_turnState = TurnState.Complete;

	// Constructors ----------------------------------------------------
	Guard(int x, int y, Direction lookDir, int lookLen, boolean turnCW) {
		super(x, y, Symbols.GUARD_CHAR);
		m_direction = lookDir;
		m_turnCW = turnCW;

		// A guard must have a positive look length
		assert (lookLen < 1);
		m_lookLength = lookLen;
	}

	// Public methods -----------------------------------------------------
	public void move(Board board) throws Exception {
		Direction turnDirection;

		try {

			// See if we need to turn, but don't do if we just finished turning.
			// You might go back if you do.
			if (m_turnState != TurnState.Complete) {
				turnDirection = Direction.next(m_direction, m_turnCW);
				if (processTurn(turnDirection, board)) {
					return;
				}
			}

			// If we are not turning, try to move ahead
			char nextChar = board.getCharAt(m_position, Board.SCALE,
					m_direction);
			if (nextChar != Symbols.WALL_CHAR) {
				place(m_position.cloneAdjacent(Board.SCALE, m_direction),
						m_direction, board);
				return;
			}

			// Move the opposite turn direction
			if (m_turnState != TurnState.Complete) {
				turnDirection = Direction.next(m_direction, !m_turnCW);
				processTurn(turnDirection, board);
			}
		} finally {

			// Clear a completed turn flag
			if (m_turnState == TurnState.Complete) {
				m_turnState = TurnState.None;
			}
		}
	}

	public void draw(Board board) throws Exception {

		// Draw the guard
		//char drawChar = erase ? Symbols.EMPTY_CHAR : Symbols.GUARD_CHAR;
		board.setCharAt(m_position, Symbols.GUARD_CHAR);

		// Draw the look line
		for (int offset = Board.SCALE; offset <= m_lookLength * Board.SCALE; offset += Board.SCALE) {
			try {
				board.setCharAt(m_position, offset, m_direction, Symbols.LOOK_CHAR);
			} catch (Exception e) {
				// Ignore any wall collisions
			}
		}
	}

	// Private methods ---------------------------------
	private boolean processTurn(Direction turnDirection, Board board)
			throws Exception {
		boolean ret_val = false;

		// Only do something if we are able to turn
		char nextChar = board.getCharAt(m_position, Board.SCALE, turnDirection);
		switch (nextChar) {
		case Symbols.EMPTY_CHAR:
		case Symbols.LOOK_CHAR:
		case Symbols.GUARD_CHAR: {
			// If we are waiting, complete the turn.
			if (m_turnState == TurnState.Wait) {
				place(m_position.cloneAdjacent(Board.SCALE, turnDirection),
						turnDirection, board);
				m_direction = turnDirection;
				m_turnState = TurnState.Complete;
			}

			// If we are not waiting, go into the wait state
			if (m_turnState == TurnState.None) {
				m_turnState = TurnState.Wait;
			}

			ret_val = true;
		}

		default:
			// Ignore
		}

		return ret_val;
	}
}
