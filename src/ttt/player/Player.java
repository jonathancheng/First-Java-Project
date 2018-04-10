package ttt.player;

import ttt.game.Cell;
import ttt.game.Coordinate;
import ttt.game.Grid;

public interface Player {
   Coordinate getChoice(Grid gameGrid, Cell ownCell);

   boolean isHuman();
   String getName();
}
