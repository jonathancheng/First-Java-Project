package ttt.player.ai;

import org.junit.Test;
import testutil.MockGrid;
import ttt.game.Cell;
import ttt.game.Coordinate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
   public void fromExistingGrid()
   {
      MockGrid originalGrid = new MockGrid(
              "A B ",
              "  BA",
              "A   ",
              "B  A"
      );
      CompactByteGrid copy = CompactByteGrid.from(originalGrid);

      assertTrue(copy.equals(originalGrid));
   }

   @Test
   public void comparison()
   {
      MockGrid template = new MockGrid(
              "A A",
              " B ",
              "  B"
      );
      CompactByteGrid one = CompactByteGrid.from(template),
              two = CompactByteGrid.from(one);

      assertTrue(one.equals(two));
      assertTrue(two.equals(one));
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