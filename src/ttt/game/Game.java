package ttt.game;

import ttt.player.Player;

import java.util.Optional;

public class Game
{
   private Board board;
   private Referee referee;

   private Player playerA;
   private Player playerB;

   // To change the player who starts first, toggle this.
   private boolean isTurnOfA = true;

   public Game(int boardSize, Player playerA, Player playerB)
   {
      board = Board.emptyWithSize(boardSize);
      referee = new Referee(board);
      this.playerA = playerA;
      this.playerB = playerB;
   }

   public GameOutcome play()
   {
      Optional<GameOutcome> outcome;

      for (outcome = Optional.empty();
           !outcome.isPresent();
           outcome = referee.getVerdict())
         markNextMove();

      return outcome.orElse(GameOutcome.TIE);
   }

   // Nullable
   public Player getWinner(GameOutcome outcome)
   {
      switch (outcome)
      {
         case WIN_A:
            return playerA;
         case WIN_B:
            return playerB;
         default:
            return null;
      }
   }

   public void reset()
   {
      board = Board.emptyWithSize(board.getSize());
      referee = new Referee(board);
   }

   public Grid getGrid()
   {
      return board;
   }

   private void markNextMove()
   {
      while (true)
      {
         try
         {
            if (isTurnOfA)
               board.markPlayerMove(playerA.getChoice(board, Cell.A), Cell.A);
            else
               board.markPlayerMove(playerB.getChoice(board, Cell.B), Cell.B);
            break;
         } catch (IllegalAccessException iae)
         {
            System.err.println("Player logic problem: chosen cell already occupied");
         }
      }
      isTurnOfA = !isTurnOfA;
   }

}
