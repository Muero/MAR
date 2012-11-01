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

//		int numberOfWhiteStones = board.getNumberOfStones(WHITE);
//		int numberOfBlackStones = board.getNumberOfStones(BLACK);

		int openMillsPlayer = board.getNumberOfOpenMills(player);
		int openMillsOpposite = board.getNumberOfOpenMills(opposite);

		if (board.getStuck(player)) {
			bewertung = bewertung - 1000;
		} else if (board.getStuck(opposite)) {
			bewertung = bewertung + 1000;
		}
		bewertung = bewertung + 200 * openMillsPlayer;
		bewertung = bewertung - 200 * openMillsOpposite;

		if (playerMills > oppositeMills) {
			bewertung = bewertung + 100 * (playerMills - oppositeMills);
		} else if (playerMills < oppositeMills) {
			bewertung = bewertung + 200 * (playerMills - oppositeMills);
		}

		return bewertung;
	}

}