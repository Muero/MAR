package muehle;

import static muehle.Board.eColor.BLACK;
import static muehle.Board.eColor.NONE;
import static muehle.Board.eColor.WHITE;

import java.util.Random;

import muehle.Board.eColor;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;
import muehle.players.computer.NormalPlayer;
import muehle.players.human.HumanPlayer;

public class Play {

	public static Position clickedButton = null;

	public static void play(Board board, BoardPanel panel, Connection conn,
			int numberOfMoves) {

		System.out.println("*************************** \n"
				+ "Welcome to the game Nine Men Morris !! \n \n");
		NineMenMorrisPlayer player1 = new HumanPlayer("Patrick");
		NineMenMorrisPlayer player2 = new NormalPlayer();
		NineMenMorrisPlayer currentPlayer = null;

		if (new Random().nextBoolean())
			currentPlayer = player1;
		else
			currentPlayer = player2;
		eColor currentPlayerColor = WHITE;
		eColor currentOppositeColor = BLACK;

		System.out.println(board);

		int i = 0;
		Move bestMove = null;
		if (i < numberOfMoves)
			bestMove = currentPlayer.layStone(board, currentPlayerColor,
					currentOppositeColor, panel, conn);
		else if (board.getNumberOfStones(BLACK) >= 3
				&& board.getNumberOfStones(WHITE) >= 3
				&& !board.getStuck(currentPlayerColor))
			bestMove = currentPlayer.moveStone(board, currentPlayerColor,
					currentOppositeColor, panel, conn);
		else {
			System.out.println("*********************************** \n"
					+ " It have been placed all the stones \n");
			System.out.println("You win!"); // TODO
			System.out.println("You lost!");
			System.out.println("***********************************");
			panel.repaint();
			System.out.println(board);
			return;
		}
		if (bestMove.from != null) // TODO ist dieses if nötig?
			board.setColor(bestMove.from, NONE);
		board.setColor(bestMove.to, currentPlayerColor);
		if (bestMove.take != null)
			board.setColor(bestMove.take, NONE);

		panel.refreshButtonColor(board);
		HumanPlayer.sleep(1000);

		// board is updated
		panel.repaint();
		System.out.println(board);

		// ROBOTER IS MOVING
		if (currentPlayer.getName().equals("Normal Player")) { // TODO bessere
																// lösung?
			if (bestMove.from != null) // TODO ist dieses if nötig?
				conn.takeStone(bestMove.from);
			conn.setStone(bestMove.to);
			if (bestMove.take != null)
				conn.takeStone(bestMove.take);

			panel.setRobotOnTurn(false);
		}
		panel.repaint();

		System.out.println("From " + bestMove.from);
		System.out.println("  To " + bestMove.to + "\n");
		if (bestMove.take != null) {
			System.out.println("\n TakeStone: " + bestMove.take);
			conn.takeStone(bestMove.take);
		}
		System.out.println();

		if (currentPlayer == player1)
			currentPlayer = player2;
		else
			currentPlayer = player1;
		if (currentPlayerColor == WHITE) {
			currentPlayerColor = BLACK;
			currentOppositeColor = WHITE;
		} else {
			currentPlayerColor = WHITE;
			currentOppositeColor = BLACK;
		}

	}

}