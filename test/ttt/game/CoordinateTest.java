package ttt.game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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