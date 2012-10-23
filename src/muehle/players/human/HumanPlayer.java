package muehle.players.human;

import static muehle.model.Board.eColor.NONE;
import muehle.BoardPanel;
import muehle.model.Board;
import muehle.model.Board.eColor;
import muehle.model.Position;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;

public class HumanPlayer implements NineMenMorrisPlayer {

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
	public Move layStone(Board board, int move, int numberOfStones, eColor player,eColor opposite, BoardPanel panel) {
		panel.clickedButton = null;
		panel.refreshButtonColor(board);

		// The player is laying his stone
		System.out.println("Lay your stone \n");
		do {
			inputPosition = panel.clickedButton;

			if (inputPosition != null) {
				if (board.getColor(inputPosition) == NONE) {
					board.setColor(inputPosition, player);
				} else {
					inputPosition = null;
					panel.clickedButton = null;
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

			panel.clickedButton = null;
			do {
				takeAway = panel.clickedButton; // player can take away a stone
				if (takeAway != null) {
					if (board.getColor(takeAway) == opposite
							&& !board.isMill(takeAway, opposite)) {
						board.setColor(takeAway, NONE);
					} else {
						takeAway = null;
						panel.clickedButton = null;
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
	public Move moveStone(Board board, int move, int numberOfStones, eColor player,eColor opposite, BoardPanel panel) {
		panel.refreshButtonColor(board);
		panel.repaint();
		do {
			System.out.println("Your next move? \n" + "From where?");

			panel.clickedButton = null;
			do { // The player said which stone he wants to move
				inputPositionFrom = panel.clickedButton;
				if (inputPositionFrom != null) {
					if (board.getColor(inputPositionFrom) != player) {
						inputPositionFrom = null;
						panel.clickedButton = null;
						System.out.println("Position not "+player);
					}
				}
				sleep(1);
			} while (inputPositionFrom == null);

			panel.refreshButtonColor(board);
			panel.repaint();
			System.out.println("Whereto ?");

			// The player says whereto he wants to move his stone
			panel.clickedButton = null;
			do {
				inputPositionTo = panel.clickedButton;
				if (inputPositionTo != null) {
					if (board.getColor(inputPositionTo) == NONE &&
					// Position must be neighbours if the player has more
					// than three stones
							(Position.isNeighbour(inputPositionTo,
									inputPositionFrom)
									&& board.getNumberOfStones(player) > 3
							// Positions mustn't be neighbours if the player
							// has three stones --> he can jump
							|| board.getNumberOfStones(player) == 3)) {
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
		board.setColor(inputPositionTo, player);

		// board is updated
		System.out.println(board);
		panel.refreshButtonColor(board);
		panel.repaint();

		if (board.isMill(inputPositionTo, player)) { // if the player has a
													// mill
			panel.refreshButtonColor(board);
			panel.repaint();
			System.out.println("You have mill, which stone take you away?");
			panel.clickedButton = null;
			do { // player can take away a stone
				takeAway = panel.clickedButton;
				if (takeAway != null) {
					if (board.getColor(takeAway) == opposite
							&& !(board.isMill(takeAway, opposite))) {
						board.setColor(takeAway, NONE);
					} else {
						System.out.println("It's not an opponent's stone");
						takeAway = null;
						panel.clickedButton = null;
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
