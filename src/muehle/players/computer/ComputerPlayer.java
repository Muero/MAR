package muehle.players.computer;

import muehle.Linker;
import muehle.model.Board;
import muehle.model.Board.eColor;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;
import muehle.players.computer.Minmax.MinimaxResult;

public class ComputerPlayer implements NineMenMorrisPlayer {
	private String name;
	private eColor player;
	private eColor opposite;
	
	public ComputerPlayer(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	@Override
	public void setColor(eColor color) {
		this.player = color;
		if(color == eColor.BLACK) opposite = eColor.WHITE; else opposite = eColor.BLACK;
	}

	@Override
	public eColor getColor() {
		return player;
	}

	public Move layStone(Board board, int move, int numberOfStones) {
		//panel.setRobotOnTurn(true);
		System.out.println("I'm thinking ... \n");

		// Computer determines his best possible move
		MinimaxResult result = Minmax.minmaxDecide(board, player, opposite,
				Linker.difficulty, move, numberOfStones);
		return result.bestMove;

	}

	public Move moveStone(Board board, int move, int numberOfStones) {
		return layStone(board, move, numberOfStones);
	}

}
