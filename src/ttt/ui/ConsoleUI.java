package ttt.ui;

import ;
import game.Coordinate;
import game.GameOutcome;
import game.Grid;

public class ConsoleUI implements UserInterface {


   @Override
   public void showMenu(MainMenu.MenuOption[] options)
   {

   }

   @Override
   public MainMenu.MenuOption getMenuChoice()
   {
      return null;
   }

   @Override
   public void showGrid(Grid grid)
   {

   }

   @Override
   public Coordinate getMoveFromUser()
   {
      return null;
   }

   @Override
   public void showOutcome(GameOutcome outcome)
   {
   }
}
