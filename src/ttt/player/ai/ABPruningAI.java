package ttt.player.ai;

import ttt.game.*;
import ttt.player.Player;

public class ABPruningAI implements Player
{
   private static final int DEPTH = 8;

   @Override
   public Coordinate getChoice(Grid gameGrid, Cell ownCell)
   {
      Coordinate bestMove = null;
      int bestScore = Integer.MAX_VALUE;

      for (Coordinate emptyCoord : GridUtils.coordinatesOfEmptyCells(gameGrid).toArray(Coordinate[]::new))
      {
         CompactByteGrid root = GridUtils.gridAfterMove(gameGrid, emptyCoord, ownCell);
         int score = runNegamax(root, ownCell.opposite());

         if (score < bestScore)
         {
            bestMove = emptyCoord;
            bestScore = score;
         }
      }

      if (bestMove == null)
         throw new IllegalArgumentException("Board full");
      return bestMove;
   }

   @Override
   public boolean isHuman()
   {
      return false;
   }

   @Override
   public String getName()
   {
      return "Alpha-Beta Negamax AI";
   }

   private Integer runNegamax(CompactByteGrid root, Cell nextPlayer)
   {
      return negamax(root, DEPTH - root.getSize(), Integer.MIN_VALUE, Integer.MAX_VALUE, nextPlayer);
   }

   // Translation of pseudocode from Wikipedia on Negamax
   private int negamax(CompactByteGrid node, int depth, int alpha, int beta, Cell color)
   {
      if (depth == 0 || new Referee(node).getVerdict().isPresent())
         return evaluate(node, color);

      int bestValue = Integer.MIN_VALUE;
      int ownAlpha = alpha;

      for (CompactByteGrid child :
              GridUtils.afterEachMove(node, color).toArray(CompactByteGrid[]::new))
      {
         int value = -negamax(child, depth - 1, -beta, -alpha, color.opposite());
         bestValue = Math.max(bestValue, value);
         ownAlpha = Math.max(ownAlpha, value);
         if (ownAlpha >= beta)
            break;
      }
      return bestValue;
   }

   private int evaluate(CompactByteGrid grid, Cell color)
   {

      int emptyCoordCount = (int) GridUtils.coordinatesOfEmptyCells(grid).count();

      return new Referee(grid).getVerdict()
              .map(outcome -> getOutcomeDesirability(outcome, color) * (emptyCoordCount + 1) * 100)
              .orElse(emptyCoordCount);
   }

   private int getOutcomeDesirability(GameOutcome outcome, Cell perspective)
   {
      switch (outcome)
      {
         case WIN_A: return perspective == Cell.A ? 1 : -1;
         case WIN_B: return perspective == Cell.B ? 1 : -1;
         default: return 0;
      }
   }
}
