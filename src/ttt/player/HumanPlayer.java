package ttt.player;

import ttt.game.Cell;
import ttt.game.Coordinate;
import ttt.game.Grid;
import ttt.ui.UserInterface;

public class HumanPlayer implements Player
{
   private String name;
   private UserInterface ui;

   public HumanPlayer(String name, UserInterface ui)
   {
      this.name = name;
      this.ui = ui;
   }

   @Override
   public Coordinate getChoice(Grid gameGrid, Cell b)
   {
      return ui.getMoveFromUser(gameGrid, getName());
   }

   @Override
   public boolean isHuman()
   {
      return true;
   }

   @Override
   public String getName()
   {
      return name;
   }
}
