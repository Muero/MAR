package muehle.players.human;

import static muehle.model.Board.eColor.NONE;
import muehle.connection.Connection;
import muehle.model.Board;
import muehle.model.Board.eColor;
import muehle.model.Position;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;

public class HumanPlayer implements NineMenMorrisPlayer {
	private String name;
	private Connection conn;
	private HumanPositionInput input;

	private eColor color;

	public HumanPlayer(String name, Connection conn, HumanPositionInput input) {
		this.name = name;
		this.conn = conn;
		this.input = input;
	}

	public String getName() {
		return name;
	}

	@Override
	public void setColor(eColor color) {
		this.color = color;
		input.setColor(color);
	}

	@Override
	public eColor getColor() {
		return color;
	}
	
	@Override
	public eColor getOppositeColor() {
		if (getColor() == eColor.BLACK)
			return eColor.WHITE;
		else return eColor.BLACK;
	}
	@Override
	public Move layStone(Board board, int move, int numberOfStones) {

		// The player is laying his stone
		Position inputPosition = input.layStonePosition();
		board.setColor(inputPosition, color);

		Position takeAway = null;
		if (board.isMill(inputPosition, color)) { // if the player has a mill
			// TODO: Regelabklärung: Was wenn Gegner nur geschlossene Muehlen?
			takeAway = input.takeStonePosition();
		}
		return new Move(null, inputPosition, takeAway);
	}

	@Override
	public Move moveStone(Board board, int move, int numberOfStones) {
		Position inputPositionFrom, inputPositionTo;
		do {
			inputPositionFrom = input.fromStonePosition();
			inputPositionTo = input.toStonePosition(inputPositionFrom);
		} while (inputPositionTo == null);

		// Stones are moved
		board.setColor(inputPositionFrom, NONE);
		board.setColor(inputPositionTo, color);

		// board is updated
		System.out.println(board);
		// panel.refreshButtonColor(board);
		// panel.repaint();

		Position takeAway = null;
		if (board.isMill(inputPositionTo, color)) { // if the player has a mill
			// TODO: Regelabklärung: Was wenn Gegner nur geschlossene Muehlen?
			takeAway = input.takeStonePosition();
		}
			
		System.out.println(board);
		return new Move(inputPositionFrom, inputPositionTo, takeAway);
	}


}
