package ttt.player.ai;

import ttt.game.Cell;
import ttt.game.Coordinate;
import ttt.game.Grid;

import java.util.Arrays;

/**
 * A Grid implementation based on byte arrays, focused on small memory footprint and quick comparison speed.
 */
public class CompactByteGrid implements Grid
{
   private byte[] array;

   CompactByteGrid(int size)
   {
      array = new byte[size * size];
      clear();
   }

   static CompactByteGrid from(Grid grid)
   {
      CompactByteGrid out = new CompactByteGrid(grid.getSize());

      GridUtils.allCoordinatesOf(grid).forEach(loc ->
              out.setCellAt(loc, grid.getCellAt(loc)));

      return out;
   }

   void clear()
   {
      for (int i = 0; i < array.length; ++i)
         array[i] = toByte(Cell.EMPTY);
   }

   @Override
   public int getSize()
   {
      return (int) Math.sqrt(array.length);
   }

   @Override
   public Cell getCellAt(Coordinate location)
   {
      checkCoordinate(location);
      return cellFromByte(array[toArrayIndex(location)]);
   }

   void setCellAt(Coordinate coordinate, Cell targetValue)
   {
      checkCoordinate(coordinate);
      array[toArrayIndex(coordinate)] = toByte(targetValue);
   }

   public boolean equals(CompactByteGrid other)
   {
      return Arrays.equals(this.array, other.array);
   }

   private byte toByte(Cell cell)
   {
      return (byte) cell.ordinal();
   }

   private void checkCoordinate(Coordinate location)
   {
      final boolean isInvalid = location.x >= getSize() || location.y >= getSize() ||
              location.x < 0 || location.y < 0;

      if (isInvalid)
         throw new IllegalArgumentException("Given coordinate invalid: " + location);
   }

   private Cell cellFromByte(byte ordinal)
   {
      return Cell.values()[ordinal];
   }

   private int toArrayIndex(Coordinate coordinate)
   {
      // [0 1 2]
      //0 0 1 2
      //1 3 4 5
      //2 6 7 8

      return coordinate.y * getSize() + coordinate.x;
   }
}
