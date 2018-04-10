package ttt.game;

public enum Cell
{
   A,
   B,
   EMPTY;

   public Cell opposite()
   {
      switch (this)
      {
         case A: return B;
         case B: return A;
         default: return EMPTY;
      }
   }
}
