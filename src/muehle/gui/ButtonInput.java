package muehle.gui;

import static muehle.model.Board.StoneColor.NONE;
import muehle.Linker;
import muehle.model.Board;
import muehle.model.Board.StoneColor;
import muehle.model.Position;
import muehle.players.human.HumanPositionInput;

public class ButtonInput implements HumanPositionInput {

	private Board board;
	private StoneColor player;
	private StoneColor opposite;
	private Panel4 panel4;

	public ButtonInput(Board board, Panel4 panel4) {
		this.board = board;
		this.panel4 = panel4;
	}

	@Override
	public void setColor(StoneColor color) {
		this.player = color;
		if (color == StoneColor.BLACK)
			opposite = StoneColor.WHITE;
		else
			opposite = StoneColor.BLACK;
	}

	@Override
	public Position layStonePosition() {
		System.out.println("Lay your stone \n");
		Position inputPosition = null;
		Linker.pressedButton = -1;
		do {
			inputPosition = panel4.getLayedButton();

			if (inputPosition != null) {
				if (board.getColor(inputPosition) != NONE) {
					inputPosition = null;
					Linker.pressedButton = -1;
					System.out.println("position occupied");
				}
			}
			sleep(1);
		} while (inputPosition == null);
		Linker.pressedButton = -1;
		return inputPosition;
	}

	@Override
	public Position fromStonePosition() {
		System.out.println("Your next move? \n" + "From where?");

		Position inputPositionFrom = null;
		Linker.pressedButton = -1;
		do { // The player said which stone he wants to move
			inputPositionFrom = panel4.getLayedButton();
			if (inputPositionFrom != null) {
				if (board.getColor(inputPositionFrom) != player) {
					inputPositionFrom = null;
					Linker.pressedButton = -1;
					System.out.println("Position not " + player);
				}
			}
			sleep(1);
		} while (inputPositionFrom == null);
		return inputPositionFrom;
	}

	@Override
	public Position toStonePosition(Position inputPositionFrom) {
		System.out.println("Whereto ?");
		Position inputPositionTo = null;
		boolean moveNotPossible = false;
		// The player says whereto he wants to move his stone
		// panel.clickedButton = null;
		Linker.pressedButton = -1;
		do {
			inputPositionTo = panel4.getLayedButton();
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
					moveNotPossible = true;
					System.out.println("Move not possible");
				}
			}
			sleep(1);
		} while (inputPositionTo == null);
		if (moveNotPossible)
			return null;
		else
			return inputPositionTo;
	}

	@Override
	public Position takeStonePosition() {
		System.out.println("You have mill, which stone take you away?");

		Position takeAway = null;
		Linker.pressedButton = -1;
		do {
			takeAway = panel4.getLayedButton(); // player can take
												// away a stone
			if (takeAway != null) {
				if (board.getColor(takeAway) != opposite
						|| board.isMill(takeAway, opposite)) {
					takeAway = null;
					Linker.pressedButton = -1;
					System.out
							.println("It's not an opponent's stone or the stone is in a mill");

				}
			}
			sleep(1);
		} while (takeAway == null);
		Linker.pressedButton = -1;
		return takeAway;
	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
