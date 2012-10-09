package muehle;

import static muehle.Board.eColor.BLACK;
import static muehle.Board.eColor.WHITE;

public class Evaluation {

	/**
	 * Rated current situation on the field.
	 * 
	 * @param board
	 *            the situation which is evaluated
	 * @return the evaluation in the form of an integer
	 */
	public static int evaluation(Board board) {

		int bewertung = (int) Math.round(Math.random() * 40);

		int BlackMills = board.getNumberOfMills(BLACK);
		int WhiteMills = board.getNumberOfMills(WHITE);

//		int numberOfWhiteStones = board.getNumberOfStones(WHITE);
//		int numberOfBlackStones = board.getNumberOfStones(BLACK);

		int openMillsBlack = board.getNumberOfOpenMills(BLACK);
		int openMillsWhite = board.getNumberOfOpenMills(WHITE);

		if (board.getStuck(BLACK)) {
			bewertung = bewertung - 1000;
		} else if (board.getStuck(WHITE)) {
			bewertung = bewertung + 1000;
		}
		bewertung = bewertung + 200 * openMillsBlack;
		bewertung = bewertung - 200 * openMillsWhite;

		if (BlackMills > WhiteMills) {
			bewertung = bewertung + 100 * (BlackMills - WhiteMills);
		} else if (BlackMills < WhiteMills) {
			bewertung = bewertung + 200 * (BlackMills - WhiteMills);
		}

		return bewertung;
	}

}