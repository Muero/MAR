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
	public static int evaluation(Board board, StoneColor player,
			StoneColor opposite, int depth, int move, int numberOfStones) {

		int bewertung = (int) Math.round(Math.random() * 40);
		bewertung = 30;
		// Number of the Mills
		int playerMills = board.getNumberOfMills(player);
		int oppositeMills = board.getNumberOfMills(opposite);
		// Number of the Stones
		int numberOfPlayerStones = board.getNumberOfStones(player);
		int numberOfOppositeStones = board.getNumberOfStones(opposite);
		// Number of the open Mills
		int openMillsPlayerMove = board.getNumberOfOpenMillsMove(player);
		int openMillsOppositeMove = board.getNumberOfOpenMillsMove(opposite);
		int openMillsPlayerLay = board.getNumberOfOpenMillsLay(player);
		int openMillsOppositeLay = board.getNumberOfOpenMillsLay(opposite);

		if (board.getStuck(player, move, numberOfStones)) {
			bewertung = bewertung - 1000;
		} else if (board.getStuck(opposite, move, numberOfStones)) {
			bewertung = bewertung + 1000;
		} else {

			if (move < numberOfStones * 2) {
				// In the Situation: Lay the stones
				if (playerMills > 0)
					bewertung = bewertung + 200 * (playerMills - oppositeMills);// In
																				// the
																				// Lay
																				// phase,
																				// the
																				// computer
				else if (oppositeMills > 0) // should better "destroy" other
											// mills,
					bewertung = bewertung + 300 * (playerMills - oppositeMills);// than
																				// create
																				// own
																				// mills
				bewertung = bewertung + 20 * openMillsPlayerLay;
				bewertung = bewertung - 20 * openMillsOppositeLay;
			} else if (numberOfPlayerStones > 3 || numberOfOppositeStones > 3) {
				// In the Situation: Move the Stones
				if (playerMills > 0)
					bewertung = bewertung + 400 * (playerMills - oppositeMills);
				else if (oppositeMills > 0)
					bewertung = bewertung + 200 * (playerMills - oppositeMills);
				bewertung = bewertung + 20 * openMillsPlayerMove;
				bewertung = bewertung - 20 * openMillsOppositeMove;
			} else if (numberOfPlayerStones == 3 || numberOfOppositeStones == 3) {
				// In the Situation: One player can jump
				if (playerMills > 0)
					bewertung = bewertung + 400 * (playerMills - oppositeMills);
				else if (oppositeMills > 0)
					bewertung = bewertung + 200 * (playerMills - oppositeMills);
				bewertung = bewertung + 20 * openMillsPlayerLay;
				bewertung = bewertung - 20 * openMillsOppositeLay;
			} else if ((numberOfPlayerStones < 3 || numberOfOppositeStones < 3)) {
				if (numberOfPlayerStones < 3)
					bewertung = bewertung - 1000;
				if (numberOfOppositeStones < 3)
					bewertung = bewertung + 1000;
			}
		}

		if (depth % 2 != 0)
			return -bewertung;
		else
			return bewertung;

	}

}