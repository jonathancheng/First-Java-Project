package ttt.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Board implements Grid
{
   private Cell[][] cells;

   private Board(Cell[][] cells)
   {
      this.cells = cells;
   }

   public static Board emptyWithSize(int size)
   {
      Board newBoard = new Board(new Cell[size][size]);
      newBoard.fillWithEmptyCells();
      return newBoard;
   }

   @Override
   public Cell getCellAt(Coordinate location)
   {
      checkCoordinate(location);
      return cells[location.y][location.x];
   }

   @Override
   public int getSize()
   {
      return cells.length;
   }

   private void setCellAt(Coordinate location, Cell cell)
   {
      checkCoordinate(location);
      cells[location.y][location.x] = cell;
   }

   private void fillWithEmptyCells()
   {
      getAllCoordinates()
              .forEach(coordinate -> setCellAt(coordinate, Cell.EMPTY));
   }

   private Stream<Coordinate> getAllCoordinates()
   {
      List<Coordinate> coordinates = new ArrayList<>();

      for (int y = 0; y < getSize(); ++y)
         for (int x = 0; x < getSize(); ++x)
            coordinates.add(new Coordinate(x, y));

      return coordinates.stream();
   }

   private void checkCoordinate(Coordinate location)
   {
      if (location.y >= getSize() || location.x >= getSize())
         throw new IllegalArgumentException("Queried coordinate out of bounds");
   }
}
