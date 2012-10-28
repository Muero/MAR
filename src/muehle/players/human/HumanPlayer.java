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

	private Position inputPosition, takeAway;
	private Position inputPositionFrom, inputPositionTo;

	private String name;
	private eColor color;
	private Connection conn;
	private HumanPositionInput input;

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
	public Move layStone(Board board, int move, int numberOfStones,
			eColor player, eColor opposite, Panel4 panel, cPanel1 cpanel) {

		// The player is laying his stone
		inputPosition = input.layStonePosition();
		board.setColor(inputPosition, player);

		if (board.isMill(inputPosition, player)) { // if the player has a mill
			// TODO: Regelabklärung: Was wenn Gegner nur geschlossene Muehlen?
			takeAway = input.takeStonePosition();
		}
		return new Move(null, inputPosition, takeAway);
	}

	@Override
	public Move moveStone(Board board, int move, int numberOfStones,
			eColor player, eColor opposite, Panel4 panel, cPanel1 cpanel) {
		// panel.refreshButtonColor(board);
		// panel.repaint();
		do {
			inputPositionFrom = input.fromStonePosition();
			inputPositionTo = input.toStonePosition(inputPositionFrom);
		} while (inputPositionTo == null);

		// Stones are moved
		board.setColor(inputPositionFrom, NONE);
		board.setColor(inputPositionTo, player);

		// board is updated
		System.out.println(board);
		// panel.refreshButtonColor(board);
		// panel.repaint();

		if (board.isMill(inputPositionTo, player)) { // if the player has a mill
			// TODO: Regelabklärung: Was wenn Gegner nur geschlossene Muehlen?
			takeAway = input.takeStonePosition();
		}
			
		System.out.println(board);
		return new Move(inputPositionFrom, inputPositionTo, takeAway);
	}


}
