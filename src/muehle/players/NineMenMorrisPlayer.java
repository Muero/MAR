package muehle.players;

import muehle.BoardPanel;
import muehle.model.Board;
import muehle.model.Board.eColor;

public interface NineMenMorrisPlayer {
	/**
	 * Can only be called if situation is non-stuck for player player.
	 * @param board
	 * @param player
	 * @param panel
	 * @param conn
	 * @return
	 */
	Move layStone(Board board, int move, int numberOfStones, eColor player,eColor opposite, BoardPanel panel);

	Move moveStone(Board board, int move, int numberOfStones, eColor player, eColor opposite, BoardPanel panel);

	String getName();
}

