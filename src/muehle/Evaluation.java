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

		int newBlackMills = board.getNumberOfMills(BLACK) - Play.oldBlackMills;
		int newWhiteMills = board.getNumberOfMills(WHITE) - Play.oldWhiteMills;

		int numberOfWhiteStones = board.getNumberOfStones(WHITE); // not used
																	// yet
		int numberOfBlackStones = board.getNumberOfStones(BLACK); // not used
																	// yet

		if (board.getStuck(BLACK, WHITE)) {
			bewertung = bewertung + 1000;
		}else if (board.getStuck(WHITE, BLACK)) {
			bewertung = bewertung - 1000;
		}
		

		if (newBlackMills > newWhiteMills) {
			bewertung = bewertung + 100 * (newBlackMills - newWhiteMills);
		} else if (newBlackMills < newWhiteMills) {
			bewertung = bewertung + 200 * (newBlackMills - newWhiteMills);
		}

		return bewertung;
	}

}