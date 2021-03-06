package muehle;

import static muehle.model.Board.StoneColor.BLACK;
import static muehle.model.Board.StoneColor.NONE;
import static muehle.model.Board.StoneColor.WHITE;
import muehle.connection.Connection;
import muehle.gui.Panel5;
import muehle.model.Board;
import muehle.model.Board.StoneColor;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;

public class Main {

	public static void main(String[] args) {
		Linker.createObjects(); // Startup Gui and Board
		Linker.finish(); // Show Panel0 and get Information about Robot, Webcam
							// and Difficulty
		Linker.setupGamePlay(); // Initialize Players, Connections and Modus
								// (Webcam/No Webcam etc)
		Linker.startupGui(); // Starts Gamemenu or, if webcam is used,
								// webcam-Startup
		Linker.play(); // Gameplay
		Linker.closeGame(); // Close NXT-Connections, Webcamconnection and Game
	}

	/**
	 * 
	 * Here is the best train determined and placed on the board
	 * 
	 * @param board
	 *            is the current board
	 * @param numberOfStones
	 *            is the number of how many stones you play
	 * @param player1
	 *            is the first NineMenMorrisPlayer
	 * @param player2
	 *            is the second NineMenMorrisPlayer
	 * @param conn1
	 *            is the first Connection
	 * @param conn2
	 *            is the second Connection
	 */
	public static void play(Board board, int numberOfStones,
			NineMenMorrisPlayer player1, NineMenMorrisPlayer player2,
			Connection conn1, Connection conn2) {

		System.out.println("*************************** \n"
				+ "Welcome to the game Nine Men Morris !! \n \n");

		NineMenMorrisPlayer currentPlayer = player1;
		NineMenMorrisPlayer oppositePlayer = player2;

		Connection currentConnection = conn1;
		Connection oppositeConnection = conn2;

		StoneColor currentPlayerColor = WHITE;
		StoneColor oppositePlayerColor = BLACK;

		player1.setColor(currentPlayerColor);
		player2.setColor(oppositePlayerColor);

		System.out.println(board);

		int move = 0;

		while (true) {
			Move bestMove = null;

			if (move < 2 * numberOfStones) {
				// In the Situation: Lay the Stones
				bestMove = currentPlayer.layStone(board, move, numberOfStones);
			} else if (board.getNumberOfStones(BLACK) >= 3
					&& board.getNumberOfStones(WHITE) >= 3
					&& !board
							.getStuck(currentPlayerColor, move, numberOfStones)) {
				// In the Situation: Move/Jump the Stones
				bestMove = currentPlayer.moveStone(board, move, numberOfStones);
			} else {
				// One player lost
				System.out.println("*********************************** \n"
						+ " It have been placed all the stones \n");
				if (board.getNumberOfStones(currentPlayerColor) < 3)
					System.out.println(oppositePlayer.getName() + " wins!");
				if (board.getNumberOfStones(oppositePlayerColor) < 3)
					System.out.println(currentPlayer.getName() + " wins!");
				System.out.println("***********************************");
				return;
			}

			if (bestMove.from != null) { // only in the Situation: Move and Jump
											// the Stones
				System.out.println(bestMove.from);
				board.setColor(bestMove.from, NONE);
			}
			board.setColor(bestMove.to, currentPlayerColor); // Is always !null
			if (bestMove.take != null)// If the player has a mill
				board.setColor(bestMove.take, NONE);

			// board is updated
			System.out.println(board);

			// ROBOTER IS MOVING
			if (bestMove.from != null) {
				currentConnection.moveStone(bestMove.from, bestMove.to);
			} else {
				currentConnection.setStone(bestMove.to);
			}
			if (bestMove.take != null)
				currentConnection.takeStone(bestMove.take);

			System.out.println("From " + bestMove.from);
			System.out.println("  To " + bestMove.to);
			System.out.println(" TakeStone: " + bestMove.take);

			System.out.println("");
			System.out.println(board);
			// the players will be replaced
			NineMenMorrisPlayer p = currentPlayer;
			currentPlayer = oppositePlayer;
			oppositePlayer = p;
			// the Connections will be replaced
			Connection c = currentConnection;
			currentConnection = oppositeConnection;
			oppositeConnection = c;
			// the Color will be replaced
			StoneColor color = currentPlayerColor;
			currentPlayerColor = oppositePlayerColor;
			oppositePlayerColor = color;

			if (move < 2 * numberOfStones
					|| board.getNumberOfStones(oppositePlayerColor) >= 3
					&& board.getNumberOfStones(currentPlayerColor) >= 3)
				Panel5.setBackgroundProbabilityColor(board, currentPlayerColor,
						move, numberOfStones);

			move++;
		}
	}
}
