package pxc.puzzles.escape;

public class Point {

	private int m_x;
	private int m_y;
	
	Point (int x, int y)
	{
		m_x = x;
		m_y = y;
	}
	
	public int getX()
	{
		return m_x;
	}
	
	public int getY()
	{
		return m_y;
	}
	
	public void setX(int x)
	{
		m_x = x;
	}
	
	public void setY(int y)
	{
		m_y = y;
	}
	
	public void setXY(int x, int y)
	{
		m_x = x;
		m_y = y;
	}
	
	public Point cloneWithOffset(int offset_x, int offset_y)
	{
		return new Point(m_x + offset_x, m_y + offset_y);
	}
	
	public Point cloneAdjacent(int offset, Direction direction)
	{
		Point ret_val = null;
		
		switch(direction)
		{
			case N:
				ret_val = cloneWithOffset(0, -Board.SCALE);
				break;
			case E:
				ret_val = cloneWithOffset(Board.SCALE, 0);
				break;
			case S:
				ret_val = cloneWithOffset(0, Board.SCALE);
				break;
			case W:
				ret_val = cloneWithOffset(-Board.SCALE, 0);
		}
		
		return ret_val;
	}
}
