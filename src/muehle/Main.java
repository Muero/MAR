package muehle;

import static muehle.model.Board.eColor.BLACK;
import static muehle.model.Board.eColor.NONE;
import static muehle.model.Board.eColor.WHITE;
import gui.ComputerFrame;
import gui.Frame;
import gui.Output;

import java.awt.Dimension;
import java.util.Random;

import javax.swing.JFrame;

import muehle.connection.BTConnection;
import muehle.connection.Connection;
import muehle.connection.EmptyConnection;
import muehle.model.Board;
import muehle.model.Board.eColor;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;
import muehle.players.computer.NormalPlayer;
import muehle.players.human.HumanPlayer;

public class Main {

    private static final int numberOfStones = 9; //With how many stones u will play

	
	public static final int depth = 6; // playing ability
	public static int guimode = 1;

	public static Dimension size = new Dimension(542,378);


	public static Frame frame; // FIXME waeh
	public static ComputerFrame cframe;
	
	
	
	public static void main(String[] args){
		Board board = new Board();
		BoardPanel panel = new BoardPanel();
		frame = new Frame(board, BLACK,1, numberOfStones);
		cframe = new ComputerFrame(board);
		
		gui.Input.startGui(frame);
		gui.Output.create();
		gui.Input.startIngameGui(frame);


		
		System.out.println("*************************** \n"
				+ "Welcome to the game Nine Men Morris !! \n \n");

		NineMenMorrisPlayer player1 = new HumanPlayer("Patrick");
		Connection conn1 = new EmptyConnection();	//without robot
		conn1.openConnection();
		
		NineMenMorrisPlayer player2 = new NormalPlayer("Computer");
		Connection conn2;
		if(Output.userobot)
			conn2 = new BTConnection();		//with robot
		else
			conn2 = new EmptyConnection();	//without robot
		conn2.openConnection();

		JFrame f = new JFrame("Nüünistei");
		f.add(panel);
		f.pack();
		f.setVisible(true);

		if (new Random().nextBoolean()){
			Connection c = conn1; conn1 = conn2; conn2 = c;
			NineMenMorrisPlayer p = player1; player1 = player2; player2 = p;
		}

		play(board, panel, numberOfStones, player1, player2, conn1, conn2); // first phase: placing the stones
		
		conn1.closeConnection();
		conn2.closeConnection();
	}
	
	public static void play(Board board, BoardPanel panel, 	int numberOfStones, 
			NineMenMorrisPlayer player1, NineMenMorrisPlayer player2,
			Connection conn1, Connection conn2) {

		
		NineMenMorrisPlayer currentPlayer = player1;
		NineMenMorrisPlayer oppositePlayer = player2;
		
		Connection currentConnection = conn1;
		Connection oppositeConnection = conn2;
		
		eColor currentPlayerColor = WHITE;
		eColor oppositePlayerColor = BLACK;

		System.out.println(board);

		int move = 0;
		
		while (true) {
			Move bestMove = null;

			if (move < 2 * numberOfStones) {
				bestMove = currentPlayer.layStone(board, move, numberOfStones,
						currentPlayerColor, oppositePlayerColor, panel);
			} else if (board.getNumberOfStones(BLACK) >= 3
					&& board.getNumberOfStones(WHITE) >= 3
					&& !board.getStuck(currentPlayerColor)) {

				bestMove = currentPlayer.moveStone(board, move, numberOfStones,
						currentPlayerColor, oppositePlayerColor, panel);
			} else {
				System.out.println("*********************************** \n"
						+ " It have been placed all the stones \n");
				if(board.getNumberOfStones(currentPlayerColor)<3)
				System.out.println(oppositePlayer.getName()+" wins!"); // TODO
				if(board.getNumberOfStones(oppositePlayerColor)<3)
					System.out.println(currentPlayer.getName()+" wins!");
				System.out.println("***********************************");
				panel.repaint();
				return;
			}

			if (bestMove.from != null)
				System.out.println(bestMove.from);
			if (bestMove.from != null)
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
			if (bestMove.from != null)
				currentConnection.takeStone(bestMove.from);
			currentConnection.setStone(bestMove.to);
			if (bestMove.take != null)
				currentConnection.takeStone(bestMove.take);

			panel.setRobotOnTurn(false);

			System.out.println("From " + bestMove.from);
			System.out.println("  To " + bestMove.to);
			if (bestMove.take != null) {
				System.out.println(" TakeStone: " + bestMove.take);
				currentConnection.takeStone(bestMove.take);
			}
			panel.repaint();
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
