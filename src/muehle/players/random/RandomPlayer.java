package muehle.players.random;

//import muehle.BoardPanel;
import gui.Panel4;
import gui.cPanel1;
import muehle.model.Board;
import muehle.model.Board.eColor;
import muehle.players.Move;
import muehle.players.NineMenMorrisPlayer;

public class RandomPlayer implements NineMenMorrisPlayer {
	
	private eColor color;

	public String getName() {
		return "Random Player";
	}

	@Override
	public Move layStone(Board board, int move, int numberOfStones, eColor player,eColor opposite, Panel4 panel, cPanel1 cpanel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Move moveStone(Board board, int move, int numberOfStones, eColor player,eColor opposite, Panel4 panel,  cPanel1 cpanel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setColor(eColor color) {
		this.color = color;
	}

	@Override
	public eColor getColor() {
		return color;
	}

}
