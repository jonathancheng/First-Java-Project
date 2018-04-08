package ttt.game;

import java.util.List;
import java.util.Optional;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The referee judges whether a player has won the game or the players have tied.
 */
public class Referee
{
   private Grid grid;

   public Referee(Grid grid)
   {
      this.grid = grid;
   }

   public Optional<GameOutcome> getVerdict()
   {
      return Stream.of(
              // All rows
              mapUpToSize(this::coordinatesOfRow),
              // All columns
              mapUpToSize(this::coordinatesOfColumn),
              // Both diagonals
              coordinateStreamsOfDiagonals())

              .map(this::getWinningCell)
              .filter(Optional::isPresent).map(Optional::get)
              .findFirst()
              .map(this::getOutcomeFromWinningCell);
   }

   private Optional<Cell> getWinningCell(Stream<Stream<Coordinate>> streams)
   {
      return streams.map(this::winningCellOfUniformStream)
              .filter(Optional::isPresent)
              .map(Optional::get)
              .findFirst();
   }

   private GameOutcome getOutcomeFromWinningCell(Cell winCell)
   {
      switch (winCell)
      {
         case A: return GameOutcome.WIN_A;
         case B: return GameOutcome.WIN_B;
         default: throw new IllegalArgumentException("Outcome of EMPTY cell queried");
      }
   }
   private Optional<Cell> winningCellOfUniformStream(Stream<Coordinate> stream)
   {
      List<Cell> cells = stream.map(grid::getCellAt)
              .distinct()
              .collect(Collectors.toList());

      final boolean hasWon = cells.size() != 1 || cells.contains(Cell.EMPTY);
      return hasWon ? Optional.empty() : Optional.of(cells.get(0));
   }
   private Stream<Coordinate> coordinatesOfRow(final int y)
   {
      return mapUpToSize(x -> new Coordinate(x, y));
   }
   private Stream<Coordinate> coordinatesOfColumn(final int x)
   {
      return mapUpToSize(y -> new Coordinate(x, y));
   }
   private Stream<Stream<Coordinate>> coordinateStreamsOfDiagonals()
   {
      return Stream.of(
              mapUpToSize(n -> new Coordinate(n, n)),
              mapUpToSize(n -> new Coordinate(grid.getSize() - 1 - n, n))
      );
   }

   private <T> Stream<T> mapUpToSize(IntFunction<T> function)
   {
      return streamUpToGridSize().mapToObj(function);
   }
   private IntStream streamUpToGridSize()
   {
      return IntStream.range(0, grid.getSize());
   }
}
