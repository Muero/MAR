package muehle.players.random;

//import muehle.BoardPanel;
import java.util.Random;
import java.util.Set;

import muehle.model.Board;
import muehle.model.Board.StoneColor;
import muehle.model.Position;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;

public class RandomPlayer implements NineMenMorrisPlayer {
	Position[] positions;
	Random rand;

	public RandomPlayer() {
		Set<Position> set = Position.getAllPositions();
		positions = new Position[set.size()];
		int i = 0;
		for (Position p : set) {
			positions[i++] = p;
		}
		rand = new Random();
	}

	private StoneColor color;

	public String getName() {
		return "Random Player";
	}

	@Override
	public Move layStone(Board board, int move, int numberOfStones) {
		Position from = null;
		Position to = null;
		Position take = null;
		boolean haveLayPosition = true;
		while (haveLayPosition) {
			to = positions[rand.nextInt(positions.length)];
			if (board.getColor(to) == StoneColor.NONE)
				haveLayPosition = false;
		}
		if (board.isMill(to, color)) { // TODO geht das wenn from = null?
			boolean haveTakePosition = true;
			while (haveTakePosition) {
				take = positions[rand.nextInt(positions.length)];
				if (board.getColor(take) == getOppositeColor()
						&& !board.isMill(take, getOppositeColor()))
					// TODO was wenn alle gegn Steine in Mühle
					haveTakePosition = false;
			}
		}
		return new Move(from, to, take);
	}

	@Override
	public Move moveStone(Board board, int move, int numberOfStones) {
		Position from = null;
		Position to = null;
		Position take = null;
		boolean haveFromPosition = true;
		while (haveFromPosition) {
			from = positions[rand.nextInt(positions.length)];
			if (board.getColor(from) == StoneColor.NONE)
				haveFromPosition = false;
		}
		Set<Position> neigh = Position.getNeighboursOf(from);
		Position[] neighbs = new Position[neigh.size()];
		int i = 0;
		for (Position p : neigh) {
			neighbs[i++] = p;
		}
		boolean haveToPosition = true;
		while (haveToPosition) {
			if(board.getNumberOfStones(color)!=3)
			to = neighbs[rand.nextInt(neighbs.length)];
			else
			to = positions[rand.nextInt(positions.length)];			
			if (board.getColor(to) == StoneColor.NONE)
				haveToPosition = false;
		}
		if (board.isMill(to, color)) {
			boolean haveTakePosition = true;
			while (haveTakePosition) {
				take = positions[rand.nextInt(positions.length)];
				if (board.getColor(take) == getOppositeColor()
						&& !board.isMill(take, getOppositeColor()))
					// TODO was wenn alle gegn Steine in Mühle
					haveTakePosition = false;
			}

		}

		return new Move(from, to, take);
	}

	@Override
	public void setColor(StoneColor color) {
		this.color = color;
	}

	@Override
	public StoneColor getColor() {
		return color;
	}

	@Override
	public StoneColor getOppositeColor() {
		if (getColor() == StoneColor.BLACK)
			return StoneColor.WHITE;
		else
			return StoneColor.BLACK;
	}

}
