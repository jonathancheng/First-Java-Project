package ui;

import game.Coordinate;
import game.GameOutcome;
import game.Grid;

public interface UserInterface {
   // Ideally, the methods should be called in the order below.
   // This act of temporal coupling will not be referenced frequently elsewhere.

   void showMainMenu();
   void showGrid(Grid grid);
   Coordinate getMoveFromUser();
   void showOutcome(GameOutcome outcome);
}
