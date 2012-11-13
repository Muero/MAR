package muehle.players.human;

import muehle.model.Position;
import muehle.model.Board.StoneColor;

/**
 * Describes, how will the computer recognize the laid stone from the Human Player
 *Is implements in the WebcamInput or the ButtonInput
 */
public interface HumanPositionInput {
	Position layStonePosition();
	Position fromStonePosition();
	Position toStonePosition(Position inputPositionFrom);
	Position takeStonePosition();
	
	public void setColor(StoneColor color);
}
