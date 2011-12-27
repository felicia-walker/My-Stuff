package pxc.puzzles.escape;

public abstract class Person {

	protected Point m_origPosition, m_position;
	protected Direction m_origDirection, m_direction;
	protected char m_symbol;

	// Constructors -----------------------------------------------
	Person(int x, int y, char symbol) {
		m_position = new Point(x, y);
		m_origPosition = m_position;
		m_symbol = symbol;
	}

	// Getters and Setters ----------------------------------------
	public Point getPosition() {
		return m_position;
	}

	// Inherited methods ---------------------------------------------
	public void place(Point newPosition, Direction newDirection, Board board)
			throws Exception {
		m_direction = newDirection;
		m_position = newPosition;
	}

	// Abstract methods -----------------------------------------------------
	public abstract void draw(Board board) throws Exception;
	
}
