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
	
	public RandomPlayer(){
		Set<Position> set = Position.getAllPositions();
		positions = new Position[set.size()];
		int i = 0;
		for(Position p : set) {
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
		Position p = positions[rand.nextInt(positions.length)];
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Move moveStone(Board board, int move, int numberOfStones) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setColor(eColor color) {
		this.color = color;
	}

	@Override
	public eColor getColor() {
		return color;
	}

}
