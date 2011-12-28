package pxc.puzzles.escape;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Board {

	final public static int SCALE = 2;

	private char[][] m_board;
	private char[][] m_boardOriginal;
	private Point m_boardMaxValues;
	@SuppressWarnings("unused")
    private int m_moveNum = 0;

	// Constructors ----------------------------------------------------------
	Board(String filename) {
		ArrayList<String> tmpBoard = new ArrayList<String>();

		try {

			// Read in the board file
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				tmpBoard.add(line);
			}

			reader.close();

			// Set the board limits
			m_boardMaxValues = new Point(tmpBoard.get(0).length(), tmpBoard
					.size());

			// Construct the final board char array. Note, we flip everything to
			// make indexing easier
			m_board = new char[m_boardMaxValues.getX()][m_boardMaxValues.getY()];
			m_boardOriginal = new char[m_boardMaxValues.getX()][m_boardMaxValues.getY()];
			for (int y = 0; y < m_boardMaxValues.getY(); y++) {
				for (int x = 0; x < m_boardMaxValues.getX(); x++) {
					m_board[x][y] = tmpBoard.get(y).charAt(x);
					m_boardOriginal[x][y] = m_board[x][y];
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Board file not found: " + filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Getters and Setters -----------------------------------------------
	public char getCharAt(Point location) throws Exception {
		int x = location.getX();
		int y = location.getY();

		// Make sure we don't try to go outside the board
		if (x > m_boardMaxValues.getX() || x < 0) {
			throw new Exception("X outside board limits.");
		}

		if (y > m_boardMaxValues.getY() || y < 0) {
			throw new Exception("Y outside board limits.");
		}

		return m_board[x][y];
	}

	public char getCharAt(Point location, int offset, Direction direction)
			throws Exception {
		char ret_val = Symbols.INVALID_CHAR;

		switch (direction) {
		case N:
			ret_val = getCharAt(location.cloneWithOffset(0, -offset));
			break;
		case E:
			ret_val = getCharAt(location.cloneWithOffset(offset, 0));
			break;
		case S:
			ret_val = getCharAt(location.cloneWithOffset(0, offset));
			break;
		case W:
			ret_val = getCharAt(location.cloneWithOffset(-offset, 0));
		}

		return ret_val;
	}

	public void setCharAt(Point location, char newChar) throws Exception {
		int x = location.getX();
		int y = location.getY();

		// Make sure we don't try to go outside the board
		if (x > m_boardMaxValues.getX() || x < 0) {
			throw new Exception("X outside board limits.");
		}

		if (y > m_boardMaxValues.getY() || y < 0) {
			throw new Exception("Y outside board limits.");
		}

		// Make sure we don't try to overwrite a wall
		if (getCharAt(location) == Symbols.WALL_CHAR) {
			throw new Exception("Cannot place on a wall.");
		}

		m_board[x][y] = newChar;
	}

	public void setCharAt(Point location, int offset, Direction direction,
			char newChar) throws Exception {
		switch (direction) {
		case N:
			setCharAt(location.cloneWithOffset(0, -offset), newChar);
			break;
		case E:
			setCharAt(location.cloneWithOffset(offset, 0), newChar);
			break;
		case S:
			setCharAt(location.cloneWithOffset(0, offset), newChar);
			break;
		case W:
			setCharAt(location.cloneWithOffset(-offset, 0), newChar);
		}
	}

	// Public methods -----------------------------------------------
	public void printBoard() {
		for (int y = 0; y < m_boardMaxValues.getY(); y++) {
			for (int x = 0; x < m_boardMaxValues.getX(); x++) {
				try {
					System.out.print(getCharAt(new Point(x, y)));
				} catch (Exception e) {
					assert (false);
				}
			}

			System.out.println();
		}
	}

	public void incrNumMoves() {
		m_moveNum++;
	}

	public void reset() {
		for (int y = 0; y < m_boardMaxValues.getY(); y++) {
			for (int x = 0; x < m_boardMaxValues.getX(); x++) {
				m_board[x][y] = m_boardOriginal[x][y];
			}
		}
	}
}
