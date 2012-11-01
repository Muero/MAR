package muehle.players.random;

//import muehle.BoardPanel;
import java.util.Random;
import java.util.Set;

import muehle.model.Board;
import muehle.model.Board.eColor;
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

	private eColor color;

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
			from = positions[rand.nextInt(positions.length)];
			if (board.getColor(from) == eColor.NONE)
				haveLayPosition = false;
		}
		if (board.isMill(from, color)) { // TODO geht das wenn lay = null?
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
			if (board.getColor(from) == eColor.NONE)
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
			if (board.getColor(to) == eColor.NONE)
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
	public void setColor(eColor color) {
		this.color = color;
	}

	@Override
	public eColor getColor() {
		return color;
	}

	@Override
	public eColor getOppositeColor() {
		if (getColor() == eColor.BLACK)
			return eColor.WHITE;
		else
			return eColor.BLACK;
	}

}
