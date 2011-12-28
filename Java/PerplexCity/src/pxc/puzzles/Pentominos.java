package pxc.puzzles;

import java.util.*;

import pxc.util.polyominos.Board;
import pxc.util.polyominos.Polyomino;
import pxc.util.polyominos.Polyomino.Orientation;

public class Pentominos
{

    private static final int                  BOARD_ROWS = 5;
    private static final int                  BOARD_COLS = 12;

    private static HashMap<String, Polyomino> pentominos = new HashMap<String, Polyomino>();
    private static Board                      board      = new Board(BOARD_ROWS, BOARD_COLS);
    private static int                        count      = 0;

    private static boolean processPentomino(Stack<String> thestack) throws CloneNotSupportedException
    {

        // Get the next pentomino off the stack
        String curKey = thestack.pop();
        Polyomino curPentomino = pentominos.get(curKey);

        // Loop through each board row and column with each orientation
        for (Orientation i_orient : Polyomino.Orientation.values())
        {

            // Skip this iteration if the orientation is symmetric
            if (curPentomino.orient(i_orient))
            {
                continue;
            }

            for (int i_row = 0; i_row < BOARD_ROWS; i_row++)
            {
                for (int i_col = 0; i_col < BOARD_COLS; i_col++)
                {
                    if (++count % 100000 == 0)
                    {
                        System.out.println(count);
                    }
                    
                    board = curPentomino.placeAt(board, i_row, i_col);

                    // If no collision, move to next pentomino in the stack
                    if (!curPentomino.getLastCollision())
                    {
                        if (thestack.size() == 0)
                        {
                            board.printBoard();
                            return true;
                        }
                        else
                        {
                            if (processPentomino(thestack))
                            {
                                return true;
                            }

                            // Erase the current position before moving on
                            board = curPentomino.eraseAt(board, i_row, i_col);
                        }
                    }
                }
            }
        }

        thestack.push(curKey);
        return false;
    }

    public static void main(String[] args) throws CloneNotSupportedException
    {

        // Initialize the pentominos. I is special so don't add to the collection.
        Polyomino i_pentomino = new Polyomino('i', "xxxxx", 1, 5, true, true);
        pentominos.put("x", new Polyomino('x', ".x.xxx.x.", 3, 3, true, true));
        pentominos.put("z", new Polyomino('z', "xx..x..xx", 3, 3, true, false));
        pentominos.put("u", new Polyomino('u', "x.xxxx", 2, 3, true, false));
        pentominos.put("w", new Polyomino('w', "x..xx..xx", 3, 3, false, false));
        pentominos.put("p", new Polyomino('p', "xxxxx.", 3, 2, false, false));
        pentominos.put("f", new Polyomino('f', ".xxxx..x.", 3, 3, false, false));
        pentominos.put("t", new Polyomino('t', "xxx.x..x.", 3, 3, false, true));
        pentominos.put("v", new Polyomino('v', "x..x..xxx", 3, 3, false, true));
        pentominos.put("y", new Polyomino('y', "..x.xxxx", 2, 4, false, false));
        pentominos.put("n", new Polyomino('n', "xx...xxx", 2, 4, false, false));
        pentominos.put("l", new Polyomino('l', "...xxxxx", 2, 4, false, false));

        // Iterate placing the "i" pentomino in the interior. It can only
        // be placed horizontally, so we don't need to orient it at all.
        for (int i_row = 0; i_row < 3; i_row++)
        {
            for (int i_col = 0; i_col < 6; i_col++)
            {
                System.out.println("The I pentomino is currently at (" + i_row + "," + i_col + ")");
                board = i_pentomino.placeAt(board, i_row, i_col);

                // Initialize a stack of the other pentominoes to iterate through. Order the stack to
                // put longer ones in front that will have a better chance of fitting around the I pentomino
                Stack<String> pentominoStack = new Stack<String>();
                pentominoStack.push("x");
                pentominoStack.push("z");
                pentominoStack.push("u");
                pentominoStack.push("w");
                pentominoStack.push("p");
                pentominoStack.push("f");
                pentominoStack.push("t");
                pentominoStack.push("v");
                pentominoStack.push("y");
                pentominoStack.push("n");
                pentominoStack.push("l");

                // Start iterating via recursion and exit if a solution is found
                if (processPentomino(pentominoStack))
                {
                    return;
                }

                // Erase the current location before moving to the next
                board = i_pentomino.eraseAt(board, i_row, i_col);
            }
        }
    }
}
