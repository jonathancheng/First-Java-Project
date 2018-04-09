package ttt.ui;

import ttt.MainMenu;
import ttt.game.*;
import ttt.player.HumanPlayer;
import ttt.player.Player;
import ttt.player.ai.ABPruningAI;
import ttt.player.ai.RandomAI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI implements UserInterface {

   private static final Scanner reader = new Scanner(System.in);

   @Override
   public MainMenu.MenuOption getMenuChoice(MainMenu.MenuOption[] options)
   {
      printMenuOptions(options);

      int choice = promptForRangedInt("Your choice >>", 0, 3);
      if (choice == 0)
         System.exit(0);

      return options[choice - 1];
   }

   @Override
   public void showRules()
   {
      System.out.println("Upon choosing the \"Play Game\" option, you will be asked to choose a board size, anywhere from \n" +
              "3 - 10. The board will always be in a square shape, ie. if you choose, 3, then it will be a 3x3 board. \n" +
              "The players will take turn putting a piece down on this board, and the first person to get x amount of pieces \n " +
              "in a row wins. x depends on the board size. If the board size is 3, then the user needs to get 3 pieces in a row, \n" +
              "and 4 in a row for a 4x4 board. The pieces could be vertical, horizontal, or diagonal. Upon winning the game, you \n" +
              "will be scored one point. If the game results in a tie, then neither side earns a point. If you would like to exit \n" +
              "the game at any time, then enter -1 when asked for the x or y coordinate.");
   }

   @Override
   public void showAbout()
   {
      System.out.println("This game was created in 2018 by the programmers Michael Peng and Jonathan Cheng. \nMade with GitHub, IntelliJ, Floobits and <3");
   }

   @Override
   public int getBoardSize()
   {
      return promptForRangedInt("Enter board size (3 - 10) >> ", 3, 10);
   }

   @Override
   public Player getPlayerA()
   {
      return promptForPlayer("Player A");
   }

   @Override
   public Player getPlayerB()
   {
      return promptForPlayer("Player B");
   }

   private void printMenuOptions(MainMenu.MenuOption[] options)
   {
      System.out.println("0. Exit the game");
      for (int index = 0; index < options.length; ++index)
      {
         System.out.printf("%d. %s\n", index + 1, options[index].label);
      }
   }

   @Override
   public Coordinate getMoveFromUser(Grid grid, String userName)
   {
      int x, y;
      while (true)
      {
         ConsoleBoxRenderer.draw(grid);

         x = promptForIntInSizeWithSentinel(userName + ", the x component of your selected cell >>", grid);
         y = promptForIntInSizeWithSentinel("And the y component >>", grid);

         if (grid.getCellAt(new Coordinate(x, y)) != Cell.EMPTY)
            System.out.println("That cell is occupied.");
         else break;
      }
      return new Coordinate(x, y);
   }

   @Override
   public void showOutcome(GameOutcome outcome, Player winner)
   {
      switch (outcome)
      {
         case WIN_A:
            System.out.println("Player A has won! Congrats, " + winner.getName());
            break;
         case WIN_B:
            System.out.println("Player B has won! Nice job, " + winner.getName());
            break;
         case TIE:
            System.out.println("It's a tie!");
      }
   }

   @Override
   public void showScoreboard(Scoreboard scoreboard)
   {
      System.out.printf("=== SCORES ===\nPlayer A: %d points\nPlayer B: %d points\n",
              scoreboard.getScorePlayerA(),
              scoreboard.getScorePlayerB());
   }

   @Override
   public boolean askPlayAgain()
   {
      // TODO stub
      int playAgain = promptForRangedInt("Would you like to play again? (1 for yes, 0 for no) >>", 0, 1);

      return playAgain == 1;
   }

   private Player promptForPlayer(String playerSymbol)
   {
      int choice = promptForRangedInt(
              "What is " + playerSymbol + "? (1 for user, 2 for hard AI, 3 for random) >>", 1, 3);

      if (choice == 1)
      {
         System.out.printf("What is the name of %s? >>", playerSymbol);
         return new HumanPlayer(reader.nextLine(), this);
      } else if (choice == 2)
         return new ABPruningAI();
      else
         return new RandomAI();
   }

   private static int promptForIntInSizeWithSentinel(String prompt, Grid grid)
   {
      int input = promptForRangedInt(prompt, -1, grid.getSize() - 1);

      if (input == -1)
         System.exit(0);

      return input;
   }

   private static int promptForRangedInt(String prompt, int minInclusive, int maxInclusive)
   {
      while (true)
      {
         int choice = promptForInt(prompt);

         final boolean isChoiceInvalid = choice < minInclusive || choice > maxInclusive;

         if (isChoiceInvalid)
            System.err.println("Out of range!");
         else
            return choice;
      }
   }
   private static int promptForInt(String prompt)
   {
      while (true)
      {
         try
         {
            System.out.print(prompt);
            return reader.nextInt();
         }
         catch (InputMismatchException exception)
         {
            System.err.println("Naughty user. I wanted an int.");
         } finally
         {
            reader.nextLine();
         }
      }
   }
}
