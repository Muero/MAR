package muehle.players.computer;

import muehle.Board;
import muehle.Board.eColor;
import muehle.BoardPanel;
import muehle.Connection;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;
import muehle.players.computer.Minmax.MinimaxResult;

import static muehle.Board.eColor.BLACK;
import static muehle.Board.eColor.WHITE;
import static muehle.Board.eColor.NONE;

public class NormalPlayer implements NineMenMorrisPlayer {
	final int deepth = 4;

	@Override
	public String getName() {
		return "Normal Player";
	}

	public Move layStone(Board board, eColor player, eColor opposite,
			BoardPanel panel, Connection conn) {
		panel.setRobotOnTurn(true);
		System.out.println("I'm thinking ... \n");

		// Computer determines his best possible move
		MinimaxResult result = Minmax.minmaxDecide(board, opposite, player, deepth,
				0); // TODO 0
		// numberofstones
		return result.bestMove;

	}

	public Move moveStone(Board board, eColor player, eColor opposite,
			BoardPanel panel, Connection conn) {
		panel.setRobotOnTurn(true);
		System.out.println("I'm thinking ... \n");

		// Computer determines his best possible move
		MinimaxResult result = Minmax.minmaxDecide(board, opposite, player, deepth,
				0); // TODO 0
		// numberofstones

		// numberOfMoves++;
		// Computer move his stone

		return result.bestMove;
	}

}
