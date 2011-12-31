package pxc.util.polyominos;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a polyomino of arbitrary size. It supports the ability to flip and rotate the polyomino and
 * place on a borad.
 */
public class Polyomino
{
    private static final char BLANK            = '.';
    private static final char SHAPE            = 'x';

    private Shape             shape;
    private Shape             orig_shape;
    private ArrayList<Shape>  all_orientations = new ArrayList<Shape>();

    private boolean           doflip           = false;
    private boolean           dorotate         = false;
    private boolean           lastCollision    = false;
    private char              symbol           = 'A';

    // Enums ------------------------------------------------------
    public enum Orientation
    {
        normal, normal90, normal180, normal270, flip, flip90, flip180, flip270
    }

    private enum PlaceMode
    {
        place, erase
    }

    // Constructors -----------------------------------------------
    public Polyomino(char symbol, String shape, int numrows, int numcols, boolean dorotate, boolean doflip)
    {
        this(symbol, shape, numrows, numcols, Orientation.normal, dorotate, doflip);
    }

    /**
     * 
     * @param symbol
     * @param shape
     * @param numrows
     * @param numcols
     * @param direction
     * @param dorotate
     * @param doflip
     */
    public Polyomino(char symbol, String shape, int numrows, int numcols, Orientation direction, boolean dorotate,
            boolean doflip)
    {
        this.shape = new Shape(numrows, numcols);
        this.symbol = symbol;
        this.dorotate = dorotate;
        this.doflip = doflip;

        // Loop through the shape string to fill the shape array
        int index = 0;
        for (int i = 0; i < numrows; i++)
        {
            for (int j = 0; j < numcols; j++)
            {
                this.shape.data[i][j] = (shape.charAt(index) == SHAPE) ? symbol : BLANK;
                index++;
            }
        }

        // Construct all orientations and set the initial orientation
        orientInit();
        orient(direction);

        // Make a backup of the original shape so we can reset later if need be
        this.orig_shape = this.shape.clone();
    }

    // Getters and setters -------------------------------------------------------------
    public boolean getLastCollision()
    {
        return lastCollision;
    }

    public char getSymbol()
    {
        return symbol;
    }

    public int getNumRows()
    {
        return orig_shape.numrows;
    }

    public int getNumCols()
    {
        return orig_shape.numcols;
    }

    public Shape getShape()
    {
        return shape;
    }

    public String deepToString()
    {
        return Arrays.deepToString(shape.data);
    }

    // Public methods ----------------------------------------------------------
    public void resetShape()
    {
        shape = orig_shape.clone();
    }

    public boolean orient(Orientation direction)
    {
        Shape tmpShape = all_orientations.get(direction.ordinal());
        boolean ret_val = true;

        // Exit if the shape is empty, otherwise orient it
        if (!tmpShape.isEmtpy())
        {
            shape = tmpShape.clone();
            ret_val = false;
        }

        return ret_val;
    }

    public Board placeAt(Board board, int row, int col) throws CloneNotSupportedException
    {
        return place(board, row, col, PlaceMode.place);
    }

    public Board eraseAt(Board board, int row, int col) throws CloneNotSupportedException
    {
        return place(board, row, col, PlaceMode.erase);
    }

    // Private methods --------------------------------------------------
    private Board place(Board board, int row, int col, PlaceMode erase) throws CloneNotSupportedException
    {
        boolean collision = false;
        Board tmpBoard = board.clone();

        // Iterate over the shape
        for (int i = 0; i < shape.numrows; i++)
        {
            for (int j = 0; j < shape.numcols; j++)
            {
                // If there is part of the shape, see if it can be put in the board.
                // If not, sound a collision
                if (shape.data[i][j] == symbol)
                {

                    // Will it go out of bounds?
                    if ((row + i > board.numRows() - 1) || (col + j > board.numCols() - 1))
                    {
                        collision = true;
                        break;
                    }

                    // Does it overlap another shape? Don't want to for place mode, want to
                    // for erase mode.
                    int findInt = (erase == PlaceMode.erase) ? symbol : BLANK;
                    if (board.getValue(row + i, col + j) != findInt)
                    {
                        collision = true;
                        break;
                    }
                    else
                    {
                        tmpBoard.setValue(row + i, col + j, (erase == PlaceMode.erase) ? BLANK : symbol);
                    }
                }
            }
        }

        // If there was no collision, copy the new board over the old one
        if (!collision)
        {
            board = tmpBoard.clone();
        }

        // Update our private variable for later retrieval
        lastCollision = collision;

        return board;
    }

    private void orientInit()
    {
        for (Orientation direction : Orientation.values())
        {
            Shape tmpShape = shape.clone();

            switch (direction)
            {
            case normal90:
                if (!(doflip && dorotate))
                {
                    tmpShape = rotate(shape, 1);
                }
                break;

            case normal180:
                if (!dorotate)
                {
                    tmpShape = rotate(shape, 2);
                }
                break;

            case normal270:
                tmpShape = rotate(shape, 3);
                break;

            case flip:
                if (!doflip)
                {
                    tmpShape = flip(shape);
                }
                break;

            case flip90:
                if (!doflip)
                {
                    tmpShape = flip(shape);
                    tmpShape = rotate(tmpShape, 1);
                }
                break;

            case flip180:
                if (!doflip)
                {
                    tmpShape = flip(shape);
                    tmpShape = rotate(tmpShape, 2);
                }
                break;

            case flip270:
                if (!doflip)
                {
                    tmpShape = flip(shape);
                    tmpShape = rotate(tmpShape, 3);
                }
                break;

            case normal:
            default:
                // Already done in initalization
            }

            all_orientations.add(tmpShape.clone());
        }
    }

    private Shape flip(Shape origShape)
    {
        int rowlength = origShape.rowLength();
        int collength = origShape.colLength();
        Shape tmpShape = new Shape(rowlength, collength);

        for (int i = 0; i < rowlength; i++)
        {
            for (int j = 0; j < collength; j++)
            {
                tmpShape.data[i][collength - j - 1] = origShape.data[i][j];
            }
        }

        return tmpShape;
    }

    private Shape rotate(Shape origShape, int iterations)
    {
        Shape tmpShape;
        Shape finalShape = origShape.clone();
        int numrows = origShape.numrows;
        int numcols = origShape.numcols;

        for (int k = 0; k < iterations; k++)
        {
            tmpShape = new Shape(numcols, numrows);

            for (int i = 0; i < numrows; i++)
            {
                for (int j = numcols - 1; j >= 0; j--)
                {
                    tmpShape.data[numcols - j - 1][i] = finalShape.data[i][j];
                }
            }

            numrows = tmpShape.numrows;
            numcols = tmpShape.numcols;

            finalShape = tmpShape.clone();
        }

        return finalShape;
    }

    // Inner classes -----------------------------------------------
    class Shape
    {
        private char[][] data;
        private int      numrows = 0;
        private int      numcols = 0;

        Shape(final int numrows, final int numcols)
        {
            this.numrows = numrows;
            this.numcols = numcols;

            this.data = new char[numrows][numcols];
        }

        public boolean isEmtpy()
        {
            return rowLength() == 0 && colLength() == 0;
        }

        public Shape clone()
        {
            if (rowLength() == 0)
            {
                return new Shape(0, 0);
            }

            Shape result = new Shape(numrows, numcols);
            for (int i = 0; i < numrows; i++)
            {
                for (int j = 0; j < numcols; j++)
                {
                    result.data[i][j] = data[i][j];
                }
            }

            return result;
        }

        private int rowLength()
        {
            return data.length;
        }

        private int colLength()
        {
            return data[0].length;
        }
    }
}