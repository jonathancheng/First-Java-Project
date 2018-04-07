package ttt.player;

import ttt.game.Coordinate;
import ttt.game.Grid;

public interface Player {
   Coordinate getChoice(Grid gameGrid);

   boolean isHuman();
   String getName();
}
