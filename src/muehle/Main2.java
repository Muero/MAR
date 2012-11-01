package muehle;

import static muehle.model.Board.eColor.BLACK;
import static muehle.model.Board.eColor.NONE;
import static muehle.model.Board.eColor.WHITE;

import java.awt.Dimension;
import java.util.Random;
import java.util.Scanner;

import muehle.connection.Connection;
import muehle.connection.EmptyConnection;
import muehle.gui.Frame;
import muehle.gui.Panel4;
import muehle.model.Board;
import muehle.model.Board.eColor;
import muehle.model.Position;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;
import muehle.players.computer.ComputerPlayer;
import muehle.players.human.HumanPlayer;
import muehle.players.human.HumanPositionInput;

public class Main2 {

	private static final int numberOfStones = 9; // With how many stones u will
													// plays
	public static int guimode = 1;

	public static Dimension size = new Dimension(542, 378);

	public static Frame frame; // FIXME waeh


	public static void main(String[] args) {
		Board board = new Board();
		Linker.difficulty = 5;

		System.out.println("*************************** \n"
				+ "Welcome to the game Nine Men Morris !! \n \n");

		Connection conn1 = new EmptyConnection(); // without robot
		HumanPositionInput input = null;
		
		input = new HumanPositionInput() {
			Scanner s = new Scanner(System.in);
			
			@Override
			public Position toStonePosition(Position inputPositionFrom) {
				Position p = null;
				do {
					System.out.println(">> input to stone position");
					p = Position.getPosition(s.nextInt());
				} while(p == null);
				return p;
			}
			
			@Override
			public Position takeStonePosition() {
				Position p = null;
				do {
					System.out.println(">> input take stone position");
					p = Position.getPosition(s.nextInt());
				} while(p == null);
				return p;
			}
			
			@Override
			public void setColor(eColor color) {
			}
			
			@Override
			public Position layStonePosition() {
				Position p = null;
				do {
					System.out.println(">> input lay stone position");
					p = Position.getPosition(s.nextInt());
				} while(p == null);
				return p;
			}
			
			@Override
			public Position fromStonePosition() {
				Position p = null;
				do {
					System.out.println(">> input from stone position");
					p = Position.getPosition(s.nextInt());
				} while(p == null);
				return p;
			}
		};
		
		
		NineMenMorrisPlayer player1 = new HumanPlayer("Patrick", conn1, input);

		conn1.openConnection();

		NineMenMorrisPlayer player2 = new ComputerPlayer("Computer");
		Connection conn2;
		conn2 = new EmptyConnection(); // without robot
		conn2.openConnection();

		if (new Random().nextBoolean()) {
			Connection c = conn1;
			conn1 = conn2;
			conn2 = c;
			NineMenMorrisPlayer p = player1;
			player1 = player2;
			player2 = p;
		}

		Main2.play(board, null, numberOfStones, player1,
				player2, conn1, conn2); // TODO check whether parameter panel4 and cpanel1 are still necessary

		conn1.closeConnection();
		conn2.closeConnection();
	}

	public static void play(Board board, Panel4 panel4,
			int numberOfStones, NineMenMorrisPlayer player1,
			NineMenMorrisPlayer player2, Connection conn1, Connection conn2) {

		NineMenMorrisPlayer currentPlayer = player1;
		NineMenMorrisPlayer oppositePlayer = player2;

		Connection currentConnection = conn1;
		Connection oppositeConnection = conn2;

		eColor currentPlayerColor = WHITE;
		eColor oppositePlayerColor = BLACK;
		
		player1.setColor(currentPlayerColor);
		player2.setColor(oppositePlayerColor);

		System.out.println(board);

		int move = 0;

		while (true) {
			Move bestMove = null;

			if (move < 2 * numberOfStones) {
				bestMove = currentPlayer
						.layStone(board, move, numberOfStones);
			} else if (board.getNumberOfStones(BLACK) >= 3
					&& board.getNumberOfStones(WHITE) >= 3
					&& !board.getStuck(currentPlayerColor)) {

				bestMove = currentPlayer
						.moveStone(board, move, numberOfStones);
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

			System.out.println(board);

			// ROBOTER IS MOVING
			if (bestMove.from != null)
				currentConnection.takeStone(bestMove.from);
			currentConnection.setStone(bestMove.to);
			if (bestMove.take != null)
				currentConnection.takeStone(bestMove.take);

			System.out.println("From " + bestMove.from);
			System.out.println("  To " + bestMove.to);
			if (bestMove.take != null) {
				System.out.println(" TakeStone: " + bestMove.take);
				currentConnection.takeStone(bestMove.take);
			}
			
			System.out.println("");
			System.out.println(board);

			NineMenMorrisPlayer p = currentPlayer;
			currentPlayer = oppositePlayer;
			oppositePlayer = p;

			Connection c = currentConnection;
			currentConnection = oppositeConnection;
			oppositeConnection = c;

			eColor color = currentPlayerColor;
			currentPlayerColor = oppositePlayerColor;
			oppositePlayerColor = color;

			move++;
		}
	}
}
