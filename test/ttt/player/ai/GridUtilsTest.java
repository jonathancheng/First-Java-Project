package ttt.player.ai;

import org.junit.Test;
import testutil.MockGrid;
import ttt.game.Coordinate;
import ttt.game.Grid;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class GridUtilsTest
{
   private static final MockGrid
           grid1 = new MockGrid(
           "A A BB",
           "AB A A",
           "B  AB ",
           "AAA BB",
           "B B BA",
           "   AA ");

   @Test
   public void coordinatesOfEmptyCells()
   {
      assertStreamEquals(grid1, GridUtils::coordinatesOfEmptyCells, toCoordinateList(
              1, 0,
              3, 0,
              2, 1,
              4, 1,
              1, 2,
              2, 2,
              5, 2,
              3, 3,
              1, 4,
              3, 4,
              0, 5,
              1, 5,
              2, 5,
              5, 5));
   }

   @Test
   public void allCoordinatesOf()
   {
   }

   private void assertStreamEquals
           (Grid grid, Function<Grid, Stream<Coordinate>> function, Coordinate[] expected)
   {
      assertEquals(Set.of(expected), function.apply(grid).collect(Collectors.toSet()));
   }

   private Coordinate[] toCoordinateList(int... components)
   {
      assert components.length % 2 == 0;

      return IntStream.range(0, components.length / 2)
              .map(i -> i * 2)
              .mapToObj(xIndex -> new Coordinate(components[xIndex], components[xIndex + 1]))
              .toArray(Coordinate[]::new);
   }
}