package muehle.players;

import muehle.Board;
import muehle.Board.eColor;
import muehle.BoardPanel;
import muehle.Connection;

public interface NineMenMorrisPlayer {
	/**
	 * Can only be called if situation is non-stuck for player player.
	 * @param board
	 * @param player
	 * @param panel
	 * @param conn
	 * @return
	 */
	Move layStone(Board board, eColor player,eColor opposite, BoardPanel panel,
			Connection conn);

	Move moveStone(Board board, eColor player, eColor opposite, BoardPanel panel,
			Connection conn);

	String getName();
}

