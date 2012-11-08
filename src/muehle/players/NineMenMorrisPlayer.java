package muehle.players;

import muehle.model.Board;
import muehle.model.Board.StoneColor;

public interface NineMenMorrisPlayer {
	/**
	 * Can only be called if situation is non-stuck for player player.
	 * @param board
	 * @param player
	 * @param panel
	 * @param conn
	 * @return
	 */
	Move layStone(Board board, int move, int numberOfStones);

	Move moveStone(Board board, int move, int numberOfStones);

	String getName();
	
	void setColor(StoneColor color);
	StoneColor getColor();
	StoneColor getOppositeColor();
}

