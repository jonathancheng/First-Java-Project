package ttt.ui;

import ttt.MainMenu;
import ttt.game.Coordinate;
import ttt.game.GameOutcome;
import ttt.game.Grid;
import ttt.game.Scoreboard;
import ttt.player.Player;

public interface UserInterface {
   // Ideally, the methods should be called in the order below.
   // This act of temporal coupling will not be referenced frequently elsewhere.

   MainMenu.MenuOption getMenuChoice(MainMenu.MenuOption[] options);

   void showRules();
   void showAbout();

   int getBoardSize();
   Player getPlayerA();
   Player getPlayerB();

   Coordinate getMoveFromUser(Grid grid, String userName);
   void showOutcome(GameOutcome outcome, Player winner);
   void showScoreboard(Scoreboard scoreboard);
   void announceOverallWinner(Scoreboard scoreboard);
   void showGrid(Grid grid);

   boolean askPlayAgain();

}
