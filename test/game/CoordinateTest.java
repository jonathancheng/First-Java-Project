package game;

import org.junit.Test;
import ttt.game.Coordinate;

import static org.junit.Assert.*;

public class CoordinateTest
{
   @Test
   public void comparison()
   {
      Coordinate a = new Coordinate(2, 2);
      Coordinate b = new Coordinate(2, 2);

      assertEquals(a, b);
   }
}