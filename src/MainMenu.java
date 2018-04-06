

import game.Game;
import ttt.ui.ConsoleUI;
import ttt.ui.UserInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu
{
   private static final UserInterface ui =
           new ConsoleUI();

   public enum MenuOption
   {
      PLAY_GAME("Play game", () -> {}),
      VIEW_RULES("View rules", () -> {}),
      ABOUT_GAME("About game", () -> {});

      final String label;
      final Runnable runner;

      MenuOption(String label, Runnable runner)
      {
         this.label = label;
         this.runner = runner;
      }
   }
   
   private static final MenuOption[] OPTIONS = {
           new MenuOption("Play game", () -> {
              Scanner reader = new Scanner(System.in);

              System.out.println();
              int boardSize;

              while(true)
              {
                 try
                 {
                    System.out.print("Enter board size (3 - 10) >> ");

                    boardSize = reader.nextInt();
                    reader.nextLine();

                    if (boardSize < 3 || boardSize > 10)
                    {
                       System.err.println("Out of range.");
                    }
                 }
                 catch (InputMismatchException exception)
                 {
                    System.err.println("Naughty user, I want an int.");
                 }
              }
              System.out.println("Will Player A be a human or a machine (1 for human, 2 for machine)? >>");
              int playerAType = reader.nextInt();
              if (playerAType == 1)
              {

              }
              new Game(boardSize, );
           }),
           new MenuOption("View rules", () -> {

           }),
           new MenuOption("About this game", () -> {

           })
   };

   public static void main(String [] args)
   {
      System.out.println("Welcome to Tic Tac Toe!");
      while (true)
      {
         showMenuOptions();
         getOptionFromChoice(promptUserChoice()).runner.run();
      }
   }

   private static MenuOption getOptionFromChoice(int choice)
   {
      return OPTIONS[choice - 1];
   }

   private static int promptUserChoice()
   {
      Scanner reader = new Scanner(System.in);
      while (true)
      {
         try
         {
            System.out.print("Your choice, -1 to exit >>");

            int choice = reader.nextInt();
            reader.nextLine();

            if (choice == -1)
               System.exit(0);

            if (choice <= 0 || choice > OPTIONS.length)
               System.err.println("Out of range.");
            else
               return choice;
         }
         catch (InputMismatchException exception)
         {
            System.err.println("Naughty user. I want an int.");
         }
      }
   }

   private static void showMenuOptions()
   {
      for (int index = 0; index < OPTIONS.length; ++index)
      {
         System.out.printf("%d. %s\n", index + 1, OPTIONS[index].label);
      }
   }
}
