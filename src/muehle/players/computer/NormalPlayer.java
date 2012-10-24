package muehle.players.computer;

import muehle.BoardPanel;
import muehle.model.Board;
import muehle.model.Board.eColor;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;
import muehle.players.computer.Minmax.MinimaxResult;

public class NormalPlayer implements NineMenMorrisPlayer {
	final int deepth = 4;
	private String name;

	
	public NormalPlayer(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public Move layStone(Board board, int move, int numberOfStones,
			eColor player, eColor opposite, BoardPanel panel) {
		panel.setRobotOnTurn(true);
		System.out.println("I'm thinking ... \n");

		// Computer determines his best possible move
		MinimaxResult result = Minmax.minmaxDecide(board, player, opposite,
				deepth, move, numberOfStones);
		return result.bestMove;

	}

	public Move moveStone(Board board, int move, int numberOfStones,
			eColor player, eColor opposite, BoardPanel panel) {
		return layStone(board, move, numberOfStones, player, opposite, panel);
	}

}
