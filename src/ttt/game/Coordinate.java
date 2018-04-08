package ttt.game;

public class Coordinate
{
   public final int x;
   public final int y;

   public Coordinate(int x, int y)
   {
      this.x = x;
      this.y = y;
   }

   @Override
   public boolean equals(Object other)
   {
      if (!(other instanceof Coordinate)) return false;
      Coordinate otherCoord = (Coordinate) other;

      return x == otherCoord.x &&
              y == otherCoord.y;
   }

   @Override
   public String toString()
   {
      return String.format("(%d, %d)", x, y);
   }
}