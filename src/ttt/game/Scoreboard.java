package ttt.game;

public class Scoreboard
{
   private int scorePlayerA = 0;
   private int scorePlayerB = 0;

   public void markOutcome(GameOutcome outcome)
   {
      switch (outcome)
      {
         case WIN_A:
            scorePlayerA++;
            break;
         case WIN_B:
            scorePlayerB++;
      }
   }

   public int getScorePlayerA()
   {
      return scorePlayerA;
   }

   public int getScorePlayerB()
   {
      return scorePlayerB;
   }
}
