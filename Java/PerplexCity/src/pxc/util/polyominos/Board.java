package pxc.util.polyominos;

/**
 * This represents a simple character based rectangular board for placing polyominos in. One cell in the board is either
 * a blank character or one of a particular polyomino.
 */
public class Board implements Cloneable
{
    private static final char BLANK   = '.';

    private int               numrows = 0;
    private int               numcols = 0;
    private char[][]          board;

    // Constructors -----------------------------------------------
    /**
     * Default constructor creates a degenerate board of zero size.
     */
    public Board()
    {
        this(0, 0);
    }

    /**
     * Create a board of the size specified and initialize with blank characters.
     * 
     * @param rows
     *            Number of rows in the board
     * @param cols
     *            Number of columns in the board
     */
    public Board(int rows, int cols)
    {
        this.numrows = rows;
        this.numcols = cols;
        this.board = new char[rows][cols];

        reset();
    }

    // Getters and setters -------------------------------------------------------------
    public void setValue(int row, int col, char value)
    {
        board[row][col] = value;
    }

    public char getValue(int row, int col)
    {
        return board[row][col];
    }

    public int numRows()
    {
        return numrows;
    }

    public int numCols()
    {
        return numcols;
    }

    // Public methods ---------------------------------------------------------------
    /**
     * Initialize the board with all blank characters
     */
    public void reset()
    {
        for (int i = 0; i < numrows; i++)
        {
            for (int j = 0; j < numcols; j++)
            {
                board[i][j] = BLANK;
            }
        }
    }

    /**
     * Return a string representation of the board in the form [[row1], [row2], ...]. This is mostly used in unit
     * testing
     * 
     * @return A flat string representation of the board
     */
    public String deepToString()
    {
        StringBuilder result = new StringBuilder();
        result.append("[[");

        for (int i = 0; i < numrows; i++)
        {
            for (int j = 0; j < numcols; j++)
            {
                result.append(board[i][j]);
                result.append(", ");
            }

            result.delete(result.length() - 2, result.length());
            result.append("], [");
        }

        result.delete(result.length() - 3, result.length());
        result.append("]");

        return result.toString();
    }

    /**
     * Print out the board in rectangular character form
     */
    public void printBoard()
    {
        for (int i = 0; i < numrows; i++)
        {
            for (int j = 0; j < numcols; j++)
            {
                System.out.print(board[i][j] + " ");
            }

            System.out.println();
        }

        // Print a spacer to make it easier to see multiple outputs on the same screen
        System.out.println("--------------");
    }

    // Protected methods --------------------------------------------
    /**
     * Creates a new instance of this object that is an exact copy.
     */
    protected Board clone() throws CloneNotSupportedException
    {
        Board clone = new Board(numrows, numcols);

        for (int i = 0; i < numrows; i++)
        {
            for (int j = 0; j < numcols; j++)
            {
                clone.setValue(i, j, board[i][j]);
            }
        }

        return clone;
    }

}
