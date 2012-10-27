package muehle.players;

import gui.Panel4;
import muehle.model.Board;
import muehle.model.Board.eColor;
import gui.cPanel1;

public interface NineMenMorrisPlayer {
	/**
	 * Can only be called if situation is non-stuck for player player.
	 * @param board
	 * @param player
	 * @param panel
	 * @param conn
	 * @return
	 */
	Move layStone(Board board, int move, int numberOfStones, eColor player,eColor opposite, Panel4 panel, cPanel1 cpanel1);

	Move moveStone(Board board, int move, int numberOfStones, eColor player, eColor opposite,Panel4 panel, cPanel1 cpanel1);

	String getName();
}

