package game;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.Optional;

import static game.GameOutcome.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RefereeTest
{
   private class MockGrid implements Grid
   {
      private String[] rows;

      public MockGrid(String... rows)
      {
         this.rows = rows;
      }

      @Override
      public int getSize()
      {
         return rows.length;
      }

      @Override
      public Cell getCellAt(Coordinate location)
      {
         switch (rows[location.y].charAt(location.x))
         {
            case 'A':
               return Cell.A;
            case 'B':
               return Cell.B;
            default:
               return Cell.EMPTY;
         }
      }
   }

   private final Grid emptyGrid = new MockGrid(
           "   ",
           "   ",
           "   "
   ), ongoingGameGrid = new MockGrid(
           "AB ",
           "B A",
           "  B"
   ), playerAWonByRowGrid = new MockGrid(
           "B B ",
           "AAAA",
           "B BA",
           "BBA "
   ), playerBWonByColumnGrid = new MockGrid(
           "BA A",
           "B AA",
           "BBA ",
           "B  A"
   ), playerAWonByDiagonalGrid1 = new MockGrid(
           "A A A",
           "BBBA ",
           "  ABA",
           "BA  B",
           "A  BB"
   ), playerBWonByDiagonalGrid2 = new MockGrid(
           "BB A",
           " BA ",
           "AAB ",
           "B AB"
   );

   @Test
   public void judgeNoOutcome()
   {
      assertNoOutcome(ongoingGameGrid);
      assertNoOutcome(emptyGrid);
   }

   @Test
   public void judgeWonByRow()
   {
      assertOutcome(playerAWonByRowGrid, WIN_A);
   }

   @Test
   public void judgeWonByColumn()
   {
      assertOutcome(playerBWonByColumnGrid, WIN_B);
   }

   @Test
   public void judgeWonByDiagonals()
   {
      assertOutcome(playerAWonByDiagonalGrid1, WIN_A);
      assertOutcome(playerBWonByDiagonalGrid2, WIN_B);
   }

   // DSL METHODS //////////

   private void assertNoOutcome(Grid grid)
   {
      assertOptionalOutcomeOf(grid, is(Optional.empty()));
   }
   private void assertOutcome(Grid grid, GameOutcome outcome)
   {
      assertOptionalOutcomeOf(grid, is(Optional.of(outcome)));
   }
   private void assertOptionalOutcomeOf(Grid grid, Matcher<Optional<GameOutcome>> matcher)
   {
      final Referee referee = new Referee(grid);
      assertThat(referee.getVerdict(), matcher);
   }
}