package ttt.player.ai;

import ttt.game.Coordinate;
import ttt.game.Grid;
import ttt.player.Player;

public class ABPruningAI implements Player
{
   @Override
   public Coordinate getChoice(Grid gameGrid)
   {
      return null;
   }

   @Override
   public boolean isHuman()
   {
      return false;
   }

   @Override
   public String getName()
   {
      return "Alpha-Beta Pruning AI";
   }
}
