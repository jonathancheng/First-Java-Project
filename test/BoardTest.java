import game.Board;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest
{
   @Test
   public void createEmptyBoard()
   {
      Board board = Board.emptyWithSize(5);

      assertEquals(5, board.getSize());
   }
}
