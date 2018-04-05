package game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest
{
   @Test
   public void createEmptyBoard()
   {
      Board board = Board.emptyWithSize(5);

      assertEquals(5, board.getSize());
      assertEquals(Cell.EMPTY, board.getCellAt(new Coordinate(0, 0)));
      assertEquals(Cell.EMPTY, board.getCellAt(new Coordinate(2, 3)));
   }

   @Test(expected=IllegalArgumentException.class)
   public void accessCoordinatesOutOfBounds()
   {
      Board board = Board.emptyWithSize(3);

      board.getCellAt(new Coordinate(3, 1));
   }
}
