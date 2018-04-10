package ttt;

import ttt.game.Game;
import ttt.game.GameOutcome;
import ttt.game.Scoreboard;
import ttt.ui.ConsoleUI;
import ttt.ui.UserInterface;

public class MainMenu
{
   private static final UserInterface ui =
           new ConsoleUI();

   public enum MenuOption
   {
      PLAY_GAME("Play game", () ->
      {
         Scoreboard scores = new Scoreboard();
         final Game game = new Game(ui.getBoardSize(), ui.getPlayerA(), ui.getPlayerB());
         do
         {
            GameOutcome result = game.play();
            ui.showOutcome(result, game.getWinner(result));
            ui.showGrid(game.getGrid());

            scores.markOutcome(result);
            ui.showScoreboard(scores);

            game.reset();

         } while (ui.askPlayAgain());
         ui.announceOverallWinner(scores);
      }),
      VIEW_RULES("View rules", ui::showRules),
      ABOUT_GAME("About game", ui::showAbout);

      public final String label;
      final Runnable runner;

      MenuOption(String label, Runnable runner)
      {
         this.label = label;
         this.runner = runner;
      }
   }

   public static void main(String [] args)
   {
      System.out.println("Welcome to Tic Tac Toe!");
      while (true)
         ui.getMenuChoice(MenuOption.values()).runner.run();
   }

}
