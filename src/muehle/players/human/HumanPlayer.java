package muehle.players.human;

import muehle.Board;
import muehle.Board.eColor;
import muehle.BoardPanel;
import muehle.Connection;
import muehle.Position;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;

import static muehle.Board.eColor.BLACK;
import static muehle.Board.eColor.WHITE;
import static muehle.Board.eColor.NONE;

public class HumanPlayer implements NineMenMorrisPlayer {

	public static Position clickedButton = null;
	public static Position inputPosition, takeAway;
	public static Position inputPositionFrom, inputPositionTo;


	private String name;

	public HumanPlayer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public Move layStone(Board board, eColor player,eColor opposite, BoardPanel panel,
			Connection conn) {
		clickedButton = null;
		panel.refreshButtonColor(board);

		// The player is laying his stone
		System.out.println("Lay your stone \n");
		do {
			inputPosition = clickedButton;

			if (inputPosition != null) {
				if (board.getColor(inputPosition) == NONE) {
					board.setColor(inputPosition, player);
				} else {
					inputPosition = null;
					clickedButton = null;
					System.out.println("position occupied");
				}
			}
			sleep(1);
		} while (inputPosition == null);

		// board is updated
		panel.refreshButtonColor(board);
		panel.repaint();

		if (board.isMill(inputPosition, player)) { // if the player has a
													// mill

			panel.refreshButtonColor(board);
			System.out.println("You have mill, which stone take you away?");

			clickedButton = null;
			do {
				takeAway = clickedButton; // player can take away a stone
				if (takeAway != null) {
					if (board.getColor(takeAway) == BLACK //TODO currentOppositeColor
							&& !board.isMill(takeAway, BLACK)) {
						board.setColor(takeAway, NONE);
					} else {
						takeAway = null;
						clickedButton = null;
						System.out.println("It's not an opponent's stone");
					}
				}
				sleep(1);
			} while (takeAway == null);
		}
		
		// board is updated
		panel.refreshButtonColor(board);
		panel.repaint();
		System.out.println(board);
		return new Move(null,inputPosition,takeAway);
	}

	@Override
	public Move moveStone(Board board, eColor player,eColor opposite, BoardPanel panel,
			Connection conn) {
		panel.refreshButtonColor(board);
		panel.repaint();
		do {
			System.out.println("Your next move? \n" + "From where?");

			clickedButton = null;
			do { // The player said which stone he wants to move
				inputPositionFrom = clickedButton;
				if (inputPositionFrom != null) {
					if (board.getColor(inputPositionFrom) != WHITE) {
						inputPositionFrom = null;
						clickedButton = null;
						System.out.println("Position not white");
					}
				}
				sleep(1);
			} while (inputPositionFrom == null);

			panel.refreshButtonColor(board);
			panel.repaint();
			System.out.println("Whereto ?");

			// The player says whereto he wants to move his stone
			clickedButton = null;
			do {
				inputPositionTo = clickedButton;
				if (inputPositionTo != null) {
					if (board.getColor(inputPositionTo) == NONE &&
					// Position must be neighbours if the player has more
					// than three stones
							(Position.isNeighbour(inputPositionTo,
									inputPositionFrom)
									&& board.getNumberOfStones(WHITE) > 3
							// Positions mustn't be neighbours if the player
							// has three stones --> he can jump
							|| board.getNumberOfStones(WHITE) == 3)) {
					} else {
						inputPositionFrom = null;
						System.out.println("Move not possible");
					}
				}
			} while (inputPositionTo == null);
			sleep(1);
		} while (inputPositionFrom == null || inputPositionTo == null);

		// Stones are moved
		board.setColor(inputPositionFrom, NONE);
		board.setColor(inputPositionTo, WHITE);

		// board is updated
		System.out.println(board);
		panel.refreshButtonColor(board);
		panel.repaint();

		if (board.isMill(inputPositionTo, WHITE)) { // if the player has a
													// mill
			panel.refreshButtonColor(board);
			panel.repaint();
			System.out.println("You have mill, which stone take you away?");
			clickedButton = null;
			do { // player can take away a stone
				takeAway = clickedButton;
				if (takeAway != null) {
					if (board.getColor(takeAway) == BLACK
							&& !(board.isMill(takeAway, BLACK))) {
						board.setColor(takeAway, NONE);
					} else {
						System.out.println("It's not an opponent's stone");
						takeAway = null;
						clickedButton = null;
					}
				}
				sleep(1);
			} while (takeAway == null);
			panel.refreshButtonColor(board);
			panel.repaint();
		}

		System.out.println(board);
		return null;
	}

	public static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
