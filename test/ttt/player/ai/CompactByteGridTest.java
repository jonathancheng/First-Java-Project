package ttt.player.ai;

import org.junit.Test;
import ttt.game.Cell;
import ttt.game.Coordinate;

import static org.junit.Assert.assertEquals;

public class CompactByteGridTest
{
   private CompactByteGrid grid;

   @Test
   public void constructorWithSize()
   {
      grid = new CompactByteGrid(4);
      assertEquals(4, grid.getSize());

      grid = new CompactByteGrid(6);
      assertEquals(6, grid.getSize());
   }

   @Test
   public void setCellPersistence()
   {
      grid = new CompactByteGrid(5);

      grid.setCellAt(new Coordinate(1, 2), Cell.A);
      assertEquals(Cell.A, grid.getCellAt(new Coordinate(1, 2)));
   }

   @Test
   public void emptyCellsOnConstruction()
   {
      grid = new CompactByteGrid(5);

      assertEquals(Cell.EMPTY, grid.getCellAt(new Coordinate(1, 2)));
   }

   @Test(expected = IllegalArgumentException.class)
   public void queryAboveBounds()
   {
      grid = new CompactByteGrid(3);
      grid.setCellAt(new Coordinate(3, 5), Cell.B);
   }

   @Test(expected = IllegalArgumentException.class)
   public void queryBelowBounds()
   {
      grid = new CompactByteGrid(4);
      grid.setCellAt(new Coordinate(-1, -2), Cell.A);
   }
}