package ttt.game;

public interface Grid
{
   int getSize();
   Cell getCellAt(Coordinate location);

   default boolean equals(Grid other)
   {
      if (getSize() != other.getSize())
         return false;

      for (int x = 0; x < other.getSize(); ++x)
         for (int y = 0; y < other.getSize(); ++y)
         {
            Coordinate location = new Coordinate(x, y);
            if (getCellAt(location) != other.getCellAt(location))
               return false;
         }

      return true;
   }

   default boolean isFull()
   {
      // TODO provide default for streams
      for (int x = 0; x < getSize(); ++x)
         for (int y = 0; y < getSize(); ++y)
         {
            Coordinate location = new Coordinate(x, y);
            if (getCellAt(location) == Cell.EMPTY)
               return false;
         }
      return true;
   }
}
