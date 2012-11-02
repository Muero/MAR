

package muehle.players.computer;

import muehle.model.Board;
import muehle.model.Board.eColor;

public class Evaluation {

	/**
	 * Rated current situation on the field.
	 * 
	 * @param board
	 *            the situation which is evaluated
	 * @return the evaluation in the form of an integer
	 */
	public static int evaluation(Board board, eColor player, eColor opposite) {

		int bewertung = (int) Math.round(Math.random() * 40);
		bewertung = 30;
		int playerMills = board.getNumberOfMills(player);
		int oppositeMills = board.getNumberOfMills(opposite);

		int numberOfPlayerStones = board.getNumberOfStones(player);
		int numberOfOppositeStones = board.getNumberOfStones(opposite);

		int openMillsPlayer = board.getNumberOfOpenMills(player);
		int openMillsOpposite = board.getNumberOfOpenMills(opposite);

		if (board.getStuck(player)) {
			bewertung = bewertung - 100;
		} else if (board.getStuck(opposite)) {
			bewertung = bewertung + 100;
		}
		bewertung = bewertung + 20 * openMillsPlayer;
		bewertung = bewertung - 20 * openMillsOpposite;

		if (playerMills > oppositeMills) {
			bewertung = bewertung + 10 * (playerMills - oppositeMills); //TODO wenn springt sollte er seine mühlen zu machen
		} else if (playerMills < oppositeMills) {
			bewertung = bewertung + 20 * (playerMills - oppositeMills);
		}
		if(numberOfPlayerStones<3)
			bewertung=bewertung-1000;
		if(numberOfOppositeStones<3)
			bewertung=bewertung+1000;
		
		return bewertung;
		
		
	}

}