package ttt.ui;

import ;
import ttt.game.Coordinate;
import ttt.game.GameOutcome;
import ttt.game.Grid;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI implements UserInterface {

   private static final Scanner reader = new Scanner(System.in);

   @Override
   public void showMenu(MainMenu.MenuOption[] options)
   {
      for (int index = 0; index < options.length; ++index)
      {
         System.out.printf("%d. %s\n", index + 1, options[index].label);
      }
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

            int choice = reader.nextInt();
            reader.nextLine();

            return choice;
         }
         catch (InputMismatchException exception)
         {
            System.err.println("Naughty user. I wanted an int.");
         }
      }
   }
}
