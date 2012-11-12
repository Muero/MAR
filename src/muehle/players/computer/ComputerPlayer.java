package muehle.players.computer;

import muehle.Linker;
import muehle.model.Board;
import muehle.model.Board.StoneColor;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;
import muehle.players.computer.Minmax.MinimaxResult;

public class ComputerPlayer implements NineMenMorrisPlayer {
	private String name;
	private StoneColor player;
	private StoneColor opposite;
	
	/**
	 * @param name is the Name of the Computer
	 */
	public ComputerPlayer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


	@Override
	public void setColor(StoneColor color) {
		this.player = color;
		if(color == StoneColor.BLACK) opposite = StoneColor.WHITE; else opposite = StoneColor.BLACK;
	}

	@Override
	public StoneColor getColor() {
		return player;
	}
	
	@Override
	public StoneColor getOppositeColor() {
		if (getColor() == StoneColor.BLACK)
			return StoneColor.WHITE;
		else return StoneColor.BLACK;
	}


	public Move layStone(Board board, int move, int numberOfStones) {
		//panel.setRobotOnTurn(true);
		System.out.println("I'm thinking ... \n");

		// Computer determines his best possible move
		MinimaxResult result = Minmax.minmaxDecide(board, player, opposite,
				1, move, numberOfStones);
		return result.bestMove;

	}

	public Move moveStone(Board board, int move, int numberOfStones) {
		return layStone(board, move, numberOfStones);
	}

}
