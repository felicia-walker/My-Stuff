package pxc.puzzles;

/**
 * This program takes a chess board of a given size and figures out the maximum number of queens that can be placed such
 * that no queen can attack another. It also prints out the board for this solution.
 */
public class QueensQuandry
{
    private static final char   BLANK      = '.';
    private static final char   LINE       = 'x';
    private static final char   QUEEN      = 'Q';
    private static final int    BOARD_SIZE = 8;

    private static boolean      doneFlag   = false;
    public static QueensQuandry finalboard = null;

    private char[][]            board      = new char[BOARD_SIZE][BOARD_SIZE];
    private int                 numQueens  = 0;

    // Constructors ----------------------------------------------------
    /**
     * Create a blank board by iterating over the 2d array and filling with blank characters.
     */
    public QueensQuandry()
    {
        for (int rowindex = 0; rowindex < BOARD_SIZE; rowindex++)
        {
            for (int colindex = 0; colindex < BOARD_SIZE; colindex++)
            {
                this.board[rowindex][colindex] = BLANK;
            }
        }
    }

    /**
     * Create a new board that is a copy of an existing board. This includes the number of queens placed so far.
     * 
     * @param board
     *            The board to copy
     */
    public QueensQuandry(QueensQuandry board)
    {
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                this.board[row][col] = board.getItem(row, col);
            }
        }

        this.numQueens = board.numQueens();
    }

    // Getters and setters ---------------------------------------------------
    public int numQueens()
    {
        return numQueens;
    }

    public char getItem(int row, int col)
    {
        return board[row][col];
    }

    // Public methods ---------------------------------------------------------
    /**
     * Print out a copy of the board to the console.
     */
    public void printBoard()
    {
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                System.out.print(board[row][col]);
            }

            System.out.println();
        }
    }

    /**
     * See if the board has any empty cells. This is used to save time by not processing full boards one cell at a time.
     * 
     * @return True if the board is full, false if not
     */
    public boolean isFull()
    {
        boolean ret_val = true;

        outer: for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                if (board[row][col] == BLANK)
                {
                    ret_val = false;
                    break outer;
                }
            }
        }

        return ret_val;
    }

    /**
     * Place a queen at the specified coordinates. If successful, draw out the attack lines, which will mark those
     * squares off limits for subsequent placings.
     * 
     * @param row
     *            The row index to place the queen
     * @param col
     *            The column index to place the queen
     * @return True if the queen was placed, false if not
     */
    public boolean placeQueen(int row, int col)
    {
        boolean ret_val = false;

        // If the specified space is blank, we can put something there
        if (board[row][col] == BLANK)
        {
            // Iterate through the eight directions around the space. Start adjacent and go out one square in radius
            // until you reach the board size. This marks the line of sights from the queen's location.
            for (int curIndex = -BOARD_SIZE + 1; curIndex < BOARD_SIZE; curIndex += 1)
            {
                // For each direction, only place if we don't go outside the board's bounds
                if ((row - curIndex >= 0) && (row - curIndex < BOARD_SIZE) && (col - curIndex >= 0)
                        && (col - curIndex < BOARD_SIZE))
                {
                    board[row - curIndex][col - curIndex] = LINE;
                }

                if ((col - curIndex >= 0) && (col - curIndex < BOARD_SIZE))
                {
                    board[row][col - curIndex] = LINE;
                }

                if ((row - curIndex >= 0) && (row - curIndex < BOARD_SIZE))
                {
                    board[row - curIndex][col] = LINE;
                }

                if ((row + curIndex >= 0) && (row + curIndex < BOARD_SIZE) && (col - curIndex >= 0)
                        && (col - curIndex < BOARD_SIZE))
                {
                    board[row + curIndex][col - curIndex] = LINE;
                }
            }

            // Place the queen and update our queen count
            board[row][col] = QUEEN;
            numQueens++;

            ret_val = true;
        }

        return ret_val;
    }

    /**
     * For the current board iterate over all empty cells and place queens in them. When a queen is placed, iterate over
     * that new board. This continues until a board is full or the maximum number of queens is placed. This will be the
     * same as the board since since two queens cannot be in the same column.
     */
    public void iterate()
    {
        // Don't bother if the board is full or we have found a solution
        if (isFull() || doneFlag)
        {
            return;
        }

        // Iterate over the board cell by cell
        for (int rowindex = 0; rowindex < BOARD_SIZE; rowindex++)
        {
            for (int colindex = 0; colindex < BOARD_SIZE; colindex++)
            {
                // If the number of queens placed is zero, print out a progress message with the row and column we are
                // starting with
                if (numQueens == 0)
                {
                    System.out.println("Processing main board:  Row " + rowindex + " Col " + colindex);
                }

                // Make a copy of the current board
                QueensQuandry curBoard = new QueensQuandry(this);

                // Try to place the queen. If we could, iterate and try to place the next one
                if (curBoard.placeQueen(rowindex, colindex))
                {
                    // See if we have a solution, and save if we do. Also say we're done since we are not caring about
                    // any particular final solutions right now.
                    if (curBoard.numQueens() == BOARD_SIZE)
                    {
                        finalboard = new QueensQuandry(curBoard);
                        doneFlag = true;
                    }

                    curBoard.iterate();
                }
            }
        }
    }

    // Main methods ------------------------------------------------------------------
    public static void main(String[] args)
    {
        QueensQuandry mainBoard = new QueensQuandry();
        QueensQuandry.finalboard = new QueensQuandry();
        mainBoard.iterate();

        // Print out the final solution
        System.out.println("Final solution:");
        System.out.println(finalboard.numQueens());
        finalboard.printBoard();
        System.out.println();
    }
}
