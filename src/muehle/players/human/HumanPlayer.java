package muehle.players.human;

import static muehle.model.Board.eColor.NONE;
import gui.Output;
import gui.Panel4;
import gui.cPanel1;
import muehle.Main;
import muehle.connection.Connection;
import muehle.model.Board;
import muehle.model.Board.eColor;
import muehle.model.Position;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;
import camera.Camera;

public class HumanPlayer implements NineMenMorrisPlayer {

	public static Position inputPosition, takeAway;
	public static Position inputPositionFrom, inputPositionTo;

	private String name;
	private Connection conn;

	public HumanPlayer(String name, Connection conn) {
		this.name = name;
		this.conn = conn;
	}

	public String getName() {
		return name;
	}

	@Override
	public Move layStone(Board board, int move, int numberOfStones,
			eColor player, eColor opposite, Panel4 panel, cPanel1 cpanel) {
		// panel.clickedButton = null;
		// panel.refreshButtonColor(board);

		// The player is laying his stone
		System.out.println("Lay your stone \n");

		if (!Output.usewebcam) {
			Output.pressedButton = -1;
			do {
				inputPosition = cpanel.getClickedButton();

				if (inputPosition != null) {
					if (board.getColor(inputPosition) == NONE) {
						board.setColor(inputPosition, player);
					} else {
						inputPosition = null;
						// panel.clickedButton = null;
						System.out.println("position occupied");
					}
				}
				sleep(1);
			} while (inputPosition == null);
			Output.pressedButton = -1;
			// board is updated
			// panel.refreshButtonColor(board);
			// panel.repaint();
		} else {

			inputPosition = Position.p96; // Muss leider initialisiert werden
			boolean retry = true;
			while (retry) {
				conn.waitForButton();

				boolean[] vorher = Output.cluster;
				boolean[] nachher = new boolean[24];
				for (int k = 0; k < 24; k++) {
					nachher[k] = true;
				}
				nachher = Camera.createPlayerFieldClusterFromWebcamImage(
						Main.frame, Camera.imageBuffer, Output.fieldPositions,
						Output.alphaValue, Output.alphaSize);
				int stone = -1;
				for (int j = 0; j < 24; j++) {
					if (vorher[j] != nachher[j]) {
						stone = j;
						break;
					}
				}
				if (stone != -1) {
					for (Position p : Position.getAllPositions()) {
						System.err.println(p);
						if (cPanel1.getButtonId(p) == stone) {
							inputPosition = p;
							System.out.println("inputPosition = "
									+ inputPosition.getId());
							if (board.getColor(inputPosition) == NONE) {
								board.setColor(inputPosition, player);
								retry = false;
							} else {
								inputPosition = Position.p96;
							}
							break;
						}
					}
				}
				Output.cluster = nachher;
			}
		}

		if (board.isMill(inputPosition, player)) { // if the player has a
													// mill

			// panel.refreshButtonColor(board);
			System.out.println("You have mill, which stone take you away?");

			// panel.clickedButton = null;

			if (!Output.usewebcam) {
				do {
					takeAway = cpanel.getClickedButton(); // player can take
															// away a stone
					if (takeAway != null) {
						if (board.getColor(takeAway) == opposite
								&& !board.isMill(takeAway, opposite)) {
							board.setColor(takeAway, NONE);
						} else {
							takeAway = null;
							// panel.clickedButton = null;
							System.out.println("It's not an opponent's stone");
						}
					}
					sleep(1);
				} while (takeAway == null);
			} else {

				conn.waitForButton();
				inputPosition = Position.p96; // Muss leider initialisiert
												// werden
				boolean[] vorher = Output.cluster;
				boolean[] nachher = Camera
						.createPlayerFieldClusterFromWebcamImage(Main.frame,
								Camera.imageBuffer, Output.fieldPositions,
								Output.alphaValue, Output.alphaSize);
				int stone = -1;
				for (int j = 0; j < 24; j++) {
					if (vorher[j] != nachher[j]) {
						stone = j;
						break;
					}
				}
				if (stone != -1) {
					System.err.println(stone);
					for (Position p : Position.getAllPositions()) {
						if (cPanel1.getButtonId(p) == stone) {
							inputPosition = p;
							break;
						}
					}
				}
				Output.cluster = nachher;

			}

			// board is updated
			// panel.refreshButtonColor(board);
			// panel.repaint();
		}
		System.out.println(board);
		return new Move(null, inputPosition, takeAway);

	}

	@Override
	public Move moveStone(Board board, int move, int numberOfStones,
			eColor player, eColor opposite, Panel4 panel, cPanel1 cpanel) {
		// panel.refreshButtonColor(board);
		// panel.repaint();
		do {
			System.out.println("Your next move? \n" + "From where?");

			// panel.clickedButton = null;
			Output.pressedButton = -1;
			do { // The player said which stone he wants to move
				inputPositionFrom = panel.getClickedButton();
				if (inputPositionFrom != null) {
					if (board.getColor(inputPositionFrom) != player) {
						inputPositionFrom = null;
						// panel.clickedButton = null;
						Output.pressedButton = -1;
						System.out.println("Position not " + player);
					}
				}
				sleep(1);
			} while (inputPositionFrom == null);

			// panel.refreshButtonColor(board);
			// panel.repaint();
			System.out.println("Whereto ?");

			// The player says whereto he wants to move his stone
			// panel.clickedButton = null;
			Output.pressedButton = -1;
			do {
				inputPositionTo = panel.getClickedButton();
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
		// panel.refreshButtonColor(board);
		// panel.repaint();

		if (board.isMill(inputPositionTo, player)) { // if the player has a
														// mill
			// panel.refreshButtonColor(board);
			// panel.repaint();
			System.out.println("You have mill, which stone take you away?");
			// panel.clickedButton = null;
			Output.pressedButton = -1;
			do { // player can take away a stone
				takeAway = panel.getClickedButton();
				if (takeAway != null) {
					if (board.getColor(takeAway) == opposite
							&& !(board.isMill(takeAway, opposite))) {
						board.setColor(takeAway, NONE);
					} else {
						System.out.println("It's not an opponent's stone");
						takeAway = null;
						// panel.clickedButton = null;
						Output.pressedButton = -1;
					}
				}
				sleep(1);
			} while (takeAway == null);
			// panel.refreshButtonColor(board);
			// panel.repaint();
		}

		System.out.println(board);
		return new Move(inputPositionFrom, inputPositionTo, takeAway);
	}

	public static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
