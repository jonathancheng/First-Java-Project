package ttt.ui;

import ttt.game.Cell;
import ttt.game.Coordinate;
import ttt.game.Grid;

import java.util.function.IntFunction;

public class ConsoleBoxRenderer
{
   private static final char
           // Corners
           CHAR_LEFT_TOP = '╔',
           CHAR_RIGHT_TOP = '╗',
           CHAR_LEFT_BOTTOM = '╚',
           CHAR_RIGHT_BOTTOM = '╝',
   // Edge junctions
   CHAR_LEFT_JUNCTION = '╠',
           CHAR_RIGHT_JUNCTION = '╣',
           CHAR_TOP_JUNCTION = '╦',
           CHAR_BOTTOM_JUNCTION = '╩',
   // Inner
   CHAR_HORIZONTAL = '═',
           CHAR_VERTICAL = '║',
           CHAR_JUNCTION = '╬';

   public static void draw(Grid grid)
   {
      final int size = grid.getSize();

      drawTop(size);

      for (int y = 0; y < size; ++y)
      {
         drawRow(grid, y);

         if (y != size - 1)
            drawRowSeparator(size);
      }

      drawBottom(size);
   }

   private static void drawTop(int size)
   {
      drawRowWithSeparator(size,
              CHAR_LEFT_TOP,
              i -> CHAR_HORIZONTAL,
              CHAR_TOP_JUNCTION,
              CHAR_RIGHT_TOP);
   }

   private static void drawRow(Grid grid, int y)
   {
      drawRowWithSeparator(grid.getSize(),
              CHAR_VERTICAL,
              x -> getCellExpression(grid.getCellAt(new Coordinate(x, y))),
              CHAR_VERTICAL,
              CHAR_VERTICAL);
   }

   private static void drawRowSeparator(int size)
   {
      drawRowWithSeparator(size,
              CHAR_LEFT_JUNCTION,
              i -> CHAR_HORIZONTAL,
              CHAR_JUNCTION,
              CHAR_RIGHT_JUNCTION);
   }

   private static void drawBottom(int size)
   {
      drawRowWithSeparator(size,
              CHAR_LEFT_BOTTOM,
              i -> CHAR_HORIZONTAL,
              CHAR_BOTTOM_JUNCTION,
              CHAR_RIGHT_BOTTOM);
   }

   private static void drawRowWithSeparator(
           int size,
           char begin,
           IntFunction<Character> dataFunc,
           char separator,
           char end)
   {
      System.out.print(begin);

      for (int i = 0; i < size; ++i)
      {
         System.out.print(dataFunc.apply(i));

         if (i != size - 1)
            System.out.print(separator);
      }

      System.out.println(end);
   }

   private static char getCellExpression(Cell cell)
   {
      switch (cell)
      {
         case A:
            return 'O';
         case B:
            return 'X';
         default:
            return ' ';
      }
   }
}
