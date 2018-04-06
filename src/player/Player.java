package player;

import game.Coordinate;
import game.Grid;

public interface Player {
   Coordinate getChoice(Grid gameGrid);

   boolean isHuman();
   String getName();
}
