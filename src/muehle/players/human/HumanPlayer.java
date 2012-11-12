package muehle.players.human;

import static muehle.model.Board.StoneColor.NONE;
import muehle.connection.Connection;
import muehle.model.Board;
import muehle.model.Board.StoneColor;
import muehle.model.Position;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;

public class HumanPlayer implements NineMenMorrisPlayer {
	private String name;
	private Connection conn;
	private HumanPositionInput input;

	private StoneColor color;

	public HumanPlayer(String name, Connection conn, HumanPositionInput input) {
		this.name = name;
		this.conn = conn;
		this.input = input;
	}

	public String getName() {
		return name;
	}

	@Override
	public void setColor(StoneColor color) {
		this.color = color;
		input.setColor(color);
	}

	@Override
	public StoneColor getColor() {
		return color;
	}
	
	@Override
	public StoneColor getOppositeColor() {
		if (getColor() == StoneColor.BLACK)
			return StoneColor.WHITE;
		else return StoneColor.BLACK;
	}
	@Override
	public Move layStone(Board board, int move, int numberOfStones) {

		// The player is laying his stone
		Position inputPosition = input.layStonePosition();
		board.setColor(inputPosition, color);

		Position takeAway = null;
		if (board.isMill(inputPosition, color) ) { // if the player has a mill
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

		Position takeAway = null;
		if (board.isMill(inputPositionTo, color)) { // if the player has a mill
			// TODO: Regelabklärung: Was wenn Gegner nur geschlossene Muehlen?
			takeAway = input.takeStonePosition();
		}
			
		System.out.println(board);
		return new Move(inputPositionFrom, inputPositionTo, takeAway);
	}


}
