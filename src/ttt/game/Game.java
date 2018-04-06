package ttt.game;

import ttt.player.Player;

public class Game
{
   private Board board;

   private Player playerA;
   private Player playerB;

   private boolean isTurnOfA = true;

   public Game(int boardSize, Player playerA, Player playerB)
   {
      board = Board.emptyWithSize(boardSize);
      this.playerA = playerA;
      this.playerB = playerB;
   }
}
