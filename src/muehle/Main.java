package muehle;

import static muehle.model.Board.StoneColor.BLACK;
import static muehle.model.Board.StoneColor.NONE;
import static muehle.model.Board.StoneColor.WHITE;
import muehle.connection.BTConnection;
import muehle.connection.Connection;
import muehle.gui.Panel4;
import muehle.gui.Panel5;
import muehle.model.Board;
import muehle.model.Board.StoneColor;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;

public class Main {

	public static void main(String[] args) {
		
		Connection conn = new BTConnection();
		conn.openConnection();
		
		Linker.createObjects();		//Startup Gui and Board
		Linker.finish();			//Show Panel0 and get Information about Robot, Webcam and Difficulty
		Linker.setupGamePlay();
		Linker.startupGui();
		
		System.out.println("*************************** \n"+ "Welcome to the game Nine Men Morris !! \n \n");
		Linker.play();
				
		Linker.closeGame();

	}
	
	public static void play(Board board, Panel4 panel4,
		int numberOfStones, NineMenMorrisPlayer player1,
		NineMenMorrisPlayer player2, Connection conn1, Connection conn2) {

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
				bestMove = currentPlayer.layStone(board, move, numberOfStones);
			} else if (board.getNumberOfStones(BLACK) >= 3
					&& board.getNumberOfStones(WHITE) >= 3
					&& !board.getStuck(currentPlayerColor)) {

				bestMove = currentPlayer.moveStone(board, move, numberOfStones);
			} else {
				System.out.println("*********************************** \n"
						+ " It have been placed all the stones \n");
				if (board.getNumberOfStones(currentPlayerColor) < 3)
					System.out.println(oppositePlayer.getName() + " wins!"); // TODO
				if (board.getNumberOfStones(oppositePlayerColor) < 3)
					System.out.println(currentPlayer.getName() + " wins!");
				System.out.println("***********************************");
				return;
			}

			if (bestMove.from != null) {
				System.out.println(bestMove.from);
				board.setColor(bestMove.from, NONE);
			}
			board.setColor(bestMove.to, currentPlayerColor);
			if (bestMove.take != null)
				board.setColor(bestMove.take, NONE);

			// panel.refreshButtonColor(board);
			
			//TODO why? currentPlayer?
			//HumanPlayer.sleep(1000);

			// board is updated
			System.out.println(board);

			// ROBOTER IS MOVING
			if (bestMove.from != null){
				currentConnection.moveStone(bestMove.from,bestMove.to);
			}else{
				currentConnection.setStone(bestMove.to);
			}
			if (bestMove.take != null)
				currentConnection.takeStone(bestMove.take);

			// panel.setRobotOnTurn(false);

			System.out.println("From " + bestMove.from);
			System.out.println("  To " + bestMove.to);
			System.out.println(" TakeStone: " + bestMove.take);
			
			Panel5.setBackgroundProbabilityColor(board, currentPlayerColor, move, numberOfStones);
			System.out.println("");
			System.out.println(board);

			NineMenMorrisPlayer p = currentPlayer;
			currentPlayer = oppositePlayer;
			oppositePlayer = p;

			Connection c = currentConnection;
			currentConnection = oppositeConnection;
			oppositeConnection = c;

			StoneColor color = currentPlayerColor;
			currentPlayerColor = oppositePlayerColor;
			oppositePlayerColor = color;

			move++;
		}
	}
}
