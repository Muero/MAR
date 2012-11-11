

package muehle.players.computer;

import muehle.model.Board;
import muehle.model.Board.StoneColor;

public class Evaluation {

	/**
	 * Rated current situation on the field.
	 * 
	 * @param board
	 *            the situation which is evaluated
	 * @return the evaluation in the form of an integer
	 */
	public static int evaluation(Board board, StoneColor player, StoneColor opposite, int depth) {

		int bewertung = (int) Math.round(Math.random() * 40);
		bewertung = 30;
		//Number of the Mills
		int playerMills = board.getNumberOfMills(player);
		int oppositeMills = board.getNumberOfMills(opposite);
		//Number of the Stones
		int numberOfPlayerStones = board.getNumberOfStones(player);
		 
		int numberOfOppositeStones = board.getNumberOfStones(opposite);
		//Number of the open Mills
		int openMillsPlayer = board.getNumberOfOpenMills(player);
		int openMillsOpposite = board.getNumberOfOpenMills(opposite);
		
		
		if (board.getStuck(player)) {
			bewertung = bewertung - 1000;
		} else if (board.getStuck(opposite)) {
			bewertung = bewertung + 1000;
		}
		bewertung = bewertung + 20 * openMillsPlayer;
		bewertung = bewertung - 20 * openMillsOpposite;

		if (playerMills > 0) {
			bewertung = bewertung + 200 * (playerMills - oppositeMills); //TODO wenn springt sollte er seine mühlen zu machen
			//System.out.println("player mill");
		} else if (playerMills < 0) {
			bewertung = bewertung + 200 * (playerMills - oppositeMills);
		}
//		if(numberOfPlayerStones<3)
//			bewertung=bewertung-1000;
//		if(numberOfOppositeStones<3)
//			bewertung=bewertung+1000;
		
		if(depth%2!=0) return -bewertung;
		else return bewertung;
		
		
	}

}