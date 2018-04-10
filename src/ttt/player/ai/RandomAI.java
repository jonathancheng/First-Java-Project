package ttt.player.ai;

import ttt.game.Cell;
import ttt.game.Coordinate;
import ttt.game.Grid;
import ttt.player.Player;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomAI implements Player
{
   private static final Random RNG = new Random();

   @Override
   public Coordinate getChoice(Grid gameGrid, Cell b)
   {
      return getRandomPick(GridUtils.coordinatesOfEmptyCells(gameGrid));
   }

   @Override
   public boolean isHuman()
   {
      return false;
   }

   @Override
   public String getName()
   {
      return "Random AI";
   }

   private static <T> T getRandomPick(Stream<T> choices)
   {
      List<T> choiceList = choices.collect(Collectors.toList());

      return choiceList.get(RNG.nextInt(choiceList.size()));
   }
}
