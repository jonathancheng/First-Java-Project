package ttt.game;

import org.junit.Test;
import testutil.MockGrid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

   @Test
   public void markPlayerMove() throws IllegalAccessException
   {
      Board board = Board.emptyWithSize(5);
      board.markPlayerMove(new Coordinate(1, 1), Cell.A);

      assertTrue(new MockGrid(
              "     ",
              " A   ",
              "     ",
              "     ",
              "     "
      ).equals(board));
   }

   @Test(expected = IllegalAccessException.class)
   public void overridePlayerMove() throws IllegalAccessException
   {
      Board board = Board.emptyWithSize(3);
      board.markPlayerMove(new Coordinate(0, 0), Cell.A);
      board.markPlayerMove(new Coordinate(0, 0), Cell.B);
   }
}
