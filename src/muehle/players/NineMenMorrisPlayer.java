package muehle.players;

import muehle.model.Board;
import muehle.model.Board.StoneColor;

public interface NineMenMorrisPlayer {
	
	
	/**Can only be called if situation is non-stuck for player player.
	 * determined the best move in the lay mode
	 * @param board is the current board
	 * @param move
	 * @param numberOfStones
	 * @return the best move with a "to" and probably a "take"
	 */
	Move layStone(Board board, int move, int numberOfStones);

	/**
	 * determined the best move in the move mode
	 * @param board
	 * @param move
	 * @param numberOfStones
	 * @return a Move with the components from, to and probably take
	 */
	Move moveStone(Board board, int move, int numberOfStones);

	/**name shall be set at the beginning
	 * 
	 * @return the name of the Player
	 */
	String getName();

	/**sets the color of the Player
	 * @param color is the player's color
	 */
	void setColor(StoneColor color);

	/**The is set by the method setColor
	 * @return the Color of the Player
	 */
	StoneColor getColor();

	/**
	 * @return the opposite color
	 */
	StoneColor getOppositeColor();
}
