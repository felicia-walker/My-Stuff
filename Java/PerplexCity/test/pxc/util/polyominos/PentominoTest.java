package pxc.util.polyominos;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;
import org.junit.BeforeClass;

import pxc.util.polyominos.Board;
import pxc.util.polyominos.Polyomino;
import pxc.util.polyominos.Polyomino.Orientation;

public class PentominoTest
{

    private static final int                  BOARD_ROWS = 5;
    private static final int                  BOARD_COLS = 20;

    private static HashMap<String, Polyomino> pentominos = new HashMap<String, Polyomino>();
    private static Board                      board      = new Board(BOARD_ROWS, BOARD_COLS);

    @BeforeClass
    public static void oneTimeSetUp()
    {
        // Initialize the board
        for (int i = 0; i < BOARD_ROWS; i++)
        {
            for (int j = 0; j < BOARD_COLS; j++)
            {
                board.setValue(i, j, '.');
            }
        }

        // Initialize the pentominos
        pentominos.put("i", new Polyomino('i', "xxxxx", 1, 5, true, true));
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
    }

    @Test
    public void testrotate90()
    {
        Polyomino u = pentominos.get("u");
        char[][] checkShape = { { 'u', 'u' }, { '.', 'u' }, { 'u', 'u' } };

        u.orient(Orientation.normal90);
        assertTrue(u.deepToString().equals(Arrays.deepToString(checkShape)));
    }

    @Test
    public void testrotate180()
    {
        Polyomino w = pentominos.get("w");
        w.resetShape();
        char[][] checkShape = { { 'w', 'w', '.' }, { '.', 'w', 'w' }, { '.', '.', 'w' } };

        w.orient(Orientation.normal180);
        assertTrue(w.deepToString().equals(Arrays.deepToString(checkShape)));
    }

    @Test
    public void testrotate270()
    {
        Polyomino p = pentominos.get("p");
        p.resetShape();
        char[][] checkShape = { { 'p', 'p', 'p' }, { '.', 'p', 'p' } };

        p.orient(Orientation.normal270);
        assertTrue(p.deepToString().equals(Arrays.deepToString(checkShape)));
    }

    @Test
    public void testflip()
    {
        Polyomino n = pentominos.get("n");
        n.resetShape();
        char[][] checkShape = { { '.', '.', 'n', 'n' }, { 'n', 'n', 'n', '.' } };

        n.orient(Orientation.flip);
        assertTrue(n.deepToString().equals(Arrays.deepToString(checkShape)));
    }

    @Test
    public void testBoard() throws CloneNotSupportedException
    {
        Polyomino u = pentominos.get("u");
        u.resetShape();
        board = u.placeAt(board, 2, 3);
        assertFalse(u.getLastCollision());

        Polyomino l = pentominos.get("l");
        l.resetShape();
        board = l.placeAt(board, 2, 4);
        assertTrue(l.getLastCollision());

        Polyomino t = pentominos.get("t");
        t.resetShape();
        board = t.placeAt(board, 0, 3);
        assertFalse(t.getLastCollision());

        board = l.placeAt(board, 14, 0);
        assertTrue(l.getLastCollision());

        char[][] checkBoard = {
                { '.', '.', '.', 't', 't', 't', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', 't', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', 'u', 't', 'u', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', 'u', 'u', 'u', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' } };
        assertTrue(board.deepToString().equals(Arrays.deepToString(checkBoard)));

        board = t.eraseAt(board, 0, 3);
        board = pentominos.get("p").placeAt(board, 0, 10);
        char[][] checkBoard2 = {
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'p', 'p', 'p', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'p', 'p', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', 'u', '.', 'u', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', 'u', 'u', 'u', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' } };
        assertTrue(board.deepToString().equals(Arrays.deepToString(checkBoard2)));
    }
}
