package game;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import game.Board;
import game.Ship;
import game.ShipIndex;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    
    public void boardValid() {
    		Board b = new Board(4, 4);
    		int row = b.grid.length;
    		int column = b.grid[row-1].length;
    		assertEquals(row, 4);
    		assertEquals(row, column);
    }
    
}
