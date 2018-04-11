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
      System.out.println();
      printBanner("Rules");
      System.out.println("Upon choosing the \"Play Game\" option, you will be asked to choose a board size, anywhere from 3 - 10. \n" +
              "The board will always be in a square shape, ie. if you choose 3, then it will be a 3x3 board. The players\n" +
              "will take turns putting a piece down on this board, and the first person to get x amount of pieces in a row wins,\n" +
              "where x is the board size. If the board size is 3, then the user needs to get 3 pieces in a row, and 4 in a row\n" +
              "for a 4x4 board, etc. The pieces could line up vertically, horizontally, or diagonally. Upon winning the game, the winner\n" +
              "will be scored one point. If the game results in a tie, then neither side earns a point. If you would like to exit \n" +
              "the game at any time, enter -1 when asked for the x or y coordinate. Be careful when choosing your coordinate. \n" +
              "The coordinates start at 0 instead of 1 from the top left corner.");
      pressEnterToContinue();
   }

   @Override
   public void showAbout()
   {
      System.out.println();
      printBanner("About");
      System.out.println("This game was created in 2018 by the programmers Michael Peng and Jonathan Cheng. " +
              "\nMade with GitHub, IntelliJ, Floobits and <3");
      pressEnterToContinue();
   }

   @Override
   public int getBoardSize()
   {
      return promptForRangedInt("\nEnter board size (3-10) >>", 3, 10);
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
      printBanner("Main Menu");
      System.out.println("0. Exit the game");
      for (int index = 0; index < options.length; ++index)
         System.out.printf("%d. %s\n", index + 1, options[index].label);
   }


   @Override
   public Coordinate getMoveFromUser(Grid grid, String userName)
   {
      int x, y;
      while (true)
      {
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
            System.out.println("Player A has won a point! Congrats, " + winner.getName());
            break;
         case WIN_B:
            System.out.println("Player B has won a point! Nice job, " + winner.getName());
            break;
         case TIE:
            System.out.println("It's a tie! No one gets any points.");
      }
   }

   @Override
   public void showScoreboard(Scoreboard scoreboard)
   {
      System.out.println();
      printBanner("Scores");
      System.out.printf("Player A: %d points\nPlayer B: %d points\n",
              scoreboard.getScorePlayerA(),
              scoreboard.getScorePlayerB());
      System.out.println();
   }

   @Override
   public void showGrid(Grid grid)
   {
      ConsoleBoxRenderer.draw(grid);
   }

   @Override
   public boolean askPlayAgain()
   {
      return 1 == promptForRangedInt("Would you like to play again? (1 for yes, 0 for no) >>", 0, 1);
   }

   @Override
   public void announceOverallWinner(Scoreboard scoreboard) {
      System.out.println();
      int scoreA = scoreboard.getScorePlayerA(),
              scoreB = scoreboard.getScorePlayerB();

      String winner = scoreA > scoreB ? "Player A" : "Player B",
              loser = scoreB > scoreA ? "Player A" : "Player B";

      if (scoreA == scoreB)
         System.out.println("Both players have the same score, so the game is a tie.");
      else
         System.out.printf("%s's score is greater than that of %s, so they have won the game. Congratulations!",
                 winner,
                 loser);

      pressEnterToContinue();
   }

   private Player promptForPlayer(String playerSymbol)
   {
      int choice = promptForRangedInt(
              "What is " + playerSymbol + "? (1 for user, 2 for hard AI, 3 for easy AI) >>", 1, 3);

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
   private static void pressEnterToContinue() {
      System.out.println();
      System.out.println("PRESS ENTER TO CONTINUE...");
      reader.nextLine();
   }

   private static void printBanner(String banner)
   {
      System.out.println("=== " + banner + " ===");
   }
}
