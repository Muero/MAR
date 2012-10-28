package muehle.players.human;

import muehle.model.Position;
import muehle.model.Board.eColor;

public interface HumanPositionInput {
	Position layStonePosition();
	Position fromStonePosition();
	Position toStonePosition(Position inputPositionFrom);
	Position takeStonePosition();
	
	public void setColor(eColor color);
}
