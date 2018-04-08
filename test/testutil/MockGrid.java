package testutil;

import ttt.game.Cell;
import ttt.game.Coordinate;
import ttt.game.Grid;

public class MockGrid implements Grid
{
   private String[] rows;

   public MockGrid(String... rows)
   {
      this.rows = rows;
   }

   @Override
   public int getSize()
   {
      return rows.length;
   }

   @Override
   public Cell getCellAt(Coordinate location)
   {
      switch (rows[location.y].charAt(location.x))
      {
         case 'A':
            return Cell.A;
         case 'B':
            return Cell.B;
         default:
            return Cell.EMPTY;
      }
   }
}
