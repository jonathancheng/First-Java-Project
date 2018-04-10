package ttt.player.ai;

import ttt.game.Cell;
import ttt.game.Coordinate;
import ttt.game.Grid;

import java.util.stream.IntStream;
import java.util.stream.Stream;

class GridUtils
{
   static Stream<Coordinate> coordinatesOfEmptyCells(Grid grid)
   {
      return allCoordinatesOf(grid)
              .filter(coordinate -> grid.getCellAt(coordinate) == Cell.EMPTY);
   }

   static Stream<Coordinate> allCoordinatesOf(Grid grid)
   {
      return sizeRange(grid)
              .mapToObj(x -> sizeRange(grid).mapToObj(y -> new Coordinate(x, y)))
              .flatMap(i -> i);
   }

   static CompactByteGrid gridAfterMove(Grid grid, Coordinate location, Cell mover)
   {
      CompactByteGrid newGrid = CompactByteGrid.from(grid);
      newGrid.setCellAt(location, mover);

      return newGrid;
   }

   static Stream<CompactByteGrid> afterEachMove(Grid grid, Cell move)
   {
      return coordinatesOfEmptyCells(grid)
              .map(coordinate -> gridAfterMove(grid, coordinate, move));
   }

   private static IntStream sizeRange(Grid grid)
   {
      return IntStream.range(0, grid.getSize());
   }
}
