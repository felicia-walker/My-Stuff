package pxc.util.polyominos;

import java.util.ArrayList;
import java.util.Arrays;

public class Polyomino
{
    private static final char BLANK            = '.';
    private static final char SHAPE            = 'x';

    //private int               numrows          = 0;
    //private int               numcols          = 0;
    private Shape             shape;
    private Shape             orig_shape;
    private char              symbol           = 'A';
    private ArrayList<Shape>  all_orientations = new ArrayList<Shape>();
    private boolean           doflip           = false;
    private boolean           dorotate         = false;
    private boolean           lastCollision    = false;

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

    public Polyomino(char symbol, String shape, int numrows, int numcols, Orientation direction, boolean dorotate,
            boolean doflip)
    {

        this.symbol = symbol;
        this.shape = new Shape(numrows, numcols);
        this.dorotate = dorotate;
        this.doflip = doflip;

        // Loop through the shape string to fill the shape array
        int index = 0;
        for (int i = 0; i < numrows; i++)
        {
            for (int j = 0; j < numcols; j++)
            {
                this.shape.setItem(i, j, ((shape.charAt(index) == SHAPE) ? symbol : BLANK));
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
        return shape.getNumRows();
    }

    public int getNumCols()
    {
        return shape.getNumCols();
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
        for (int i = 0; i < shape.getNumRows(); i++)
        {
            for (int j = 0; j < shape.getNumCols(); j++)
            {
                // If there is part of the shape, see if it can be put in the board.
                // If not, sound a collision
                if (shape.getItem(i, j) == symbol)
                {

                    // Will it go out of bounds?
                    if ((row + i > board.rowLength() - 1) || (col + j > board.columnLength() - 1))
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
            Shape tmpShape = null;
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
                tmpShape = shape.clone();
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
                tmpShape.setItem(i, collength - j - 1, origShape.getItem(i,  j));
            }
        }

        return tmpShape;
    }

    private Shape rotate(Shape origShape, int iterations)
    {
        Shape tmpShape;
        Shape finalShape = origShape.clone();
        int numrows = origShape.rowLength();
        int numcols = origShape.colLength();

        for (int k = 0; k < iterations; k++)
        {
            tmpShape = new Shape(numcols, numrows);

            for (int i = 0; i < numrows; i++)
            {
                for (int j = numcols - 1; j >= 0; j--)
                {
                    tmpShape.setItem(numcols - j - 1, i, finalShape.getItem(i, j));
                }
            }

            numrows = tmpShape.getNumRows();
            numcols = tmpShape.getNumCols();
            
            finalShape = tmpShape.clone();
        }

        return finalShape;
    }

    private class Shape
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

        public int getNumRows()
        {
            return numrows;
        }

        public int getNumCols()
        {
            return numcols;
        }

        public void setItem(final int rowindex, final int colindex, final char value)
        {
            data[rowindex][colindex] = value;
        }

        public char getItem(final int rowindex, final int colindex)
        {
            return data[rowindex][colindex];
        }
        
        public int rowLength()
        {
            return data.length;
        }
        
        public int colLength()
        {
            return data[0].length;
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
                    result.setItem(i,  j, data[i][j]);
                }
            }

            return result;
        }

    }
}
