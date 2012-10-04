package muehle;

import static muehle.Board.eColor.*;
import muehle.Board.eColor;

/**
 * determine the best turn for the computer
 * 
 * @return the best value of the evaluation
 */
public class Minmax {

	public static int deepthWhiteMill = 0; // not used yet
	public static int deepthBlackMill = 0; // not used yet

	public static int minmaxDecide(Board board, eColor computer, eColor player,
			int depth, int numberOfMoves) {
		int value = 0;
		if (!board.getStuck(player, computer)) {
			if (numberOfMoves < Main.numberOfStones * 2) {
				value = Minmax.minmaxLay(board, computer, player, depth,
						numberOfMoves);
			} else if (board.getNumberOfStones(computer) > 3) {
				value = Minmax.minmaxMove(board, computer, player, depth,
						numberOfMoves);
			} else {
				value = Minmax.minmaxJumping(board, computer, player, depth,
						numberOfMoves);
			}
		} else {
			value = Evaluation.evaluation(board);
		}

		return value;

	}

	public static int minmaxLay(Board board, eColor computer, eColor player,
			int depth, int numberOfMoves) {

		if (depth > 0) {

			int result;

			Position nextMove = null;
			Position nextTake = null;

			if (computer == BLACK) {
				result = Integer.MIN_VALUE;
			} else {
				result = Integer.MAX_VALUE;
			}

			for (Position turn : Position.getAllPositions()) {
				if (board.getColor(turn) == NONE) { // On every possible
													// position, a brick is
													// laid.
					board.setColor(turn, computer);
					if (board.isMill(turn, computer)) { // If with this position
														// "turn" is a mill
						for (Position takeAway : Position.getAllPositions()) { // any
																				// opposing
																				// stone
																				// is
																				// taken
																				// away
							if (board.getColor(takeAway) == player
									&& !board.isMill(takeAway, player)) {
								board.setColor(takeAway, NONE);

								int value = minmaxDecide(board, player,
										computer, // other player has its turn
										(depth - 1), numberOfMoves + 1);

								if (computer == BLACK) {
									if (result < value) {
										result = value;
										nextMove = turn;
										nextTake = takeAway;
									}
								}

								if (computer == WHITE) {
									if (result > value) {
										result = value;
										nextMove = turn;
										nextTake = takeAway;
									}
								}

								board.setColor(takeAway, player);
							}
						}
					} else {
						// no new mill with this move

						// other player has its turn
						int value = minmaxDecide(board, player, computer,
								(depth - 1), numberOfMoves + 1);

						// Depending on what color is in the series, the minimum
						// or the maximum is stored.
						if (computer == BLACK) {
							if (result < value) {
								result = value;
								nextMove = turn;
								nextTake = null;
							}
						}

						if (computer == WHITE) {
							if (result > value) {
								result = value;
								nextMove = turn;
								nextTake = null;
							}

						}

					}

					board.setColor(turn, NONE);

				}
			}

			// The best turn is stored
			Play.nextTurnFrom = null;
			Play.nextTurnTo = nextMove;
			Play.nextTake = nextTake;

			return result;
		} else {
			// If the depth is reached, the current field rated
			return Evaluation.evaluation(board);

		}

	}

	public static int minmaxMove(Board board, eColor computer, eColor player,
			int depth, int numberOfMoves) {
		if (depth > 0) {

			int result;

			Position nextMoveFrom = null;
			Position nextMoveTo = null;
			Position nextTake = null;

			if (computer == BLACK) {
				result = Integer.MIN_VALUE;
			} else {
				result = Integer.MAX_VALUE;
			}

			for (Position turnFrom : Position.getAllPositions()) {
				if (board.getColor(turnFrom) == computer) {
					board.setColor(turnFrom, NONE); // Every brick with the
													// Color is removed
					for (Position turnTo : Position.getNeighboursOf(turnFrom)) {
						if (board.getColor(turnTo) == NONE) {
							board.setColor(turnTo, computer); // on each nearby
																// position is a
																// stone set

							if (board.isMill(turnTo, computer)) { // If with
																	// this
																	// position
																	// "turnTo"
																	// is a mill
								for (Position takeAway : Position
										.getAllPositions()) {
									if (board.getColor(takeAway) == player
											&& !board.isMill(takeAway, player)) {
										board.setColor(takeAway, NONE); // any
																		// opposing
																		// stone
																		// is
																		// taken
																		// away

										int value = minmaxDecide(
												board,
												player, // other player has its
														// turn
												computer, (depth - 1),
												numberOfMoves + 1);

										if (computer == BLACK) {
											if (result < value) {
												result = value;
												nextMoveFrom = turnFrom;
												nextMoveTo = turnTo;
												nextTake = takeAway;
											}
										}

										if (computer == WHITE) {
											if (result > value) {
												result = value;
												nextMoveFrom = turnFrom;
												nextMoveTo = turnTo;
												nextTake = takeAway;
											}
										}

										board.setColor(takeAway, player);
									}
								}
							} else {
								// no new mill with this move

								// other player has its turn
								int value = minmaxDecide(board, player,
										computer, (depth - 1),
										numberOfMoves + 1);

								// Depending on what color is in the series, the
								// minimum or the maximum is stored.
								if (computer == BLACK) {
									if (result < value) {
										result = value;
										nextMoveFrom = turnFrom;
										nextMoveTo = turnTo;
										nextTake = null;
									}
								}

								if (computer == WHITE) {
									if (result > value) {
										result = value;
										nextMoveFrom = turnFrom;
										nextMoveTo = turnTo;
										nextTake = null;
									}

								}

							}
							board.setColor(turnTo, NONE);

						}
					}
					board.setColor(turnFrom, computer);
				}
			}
			// The best turn is stored
			Play.nextTurnFrom = nextMoveFrom;
			Play.nextTurnTo = nextMoveTo;
			Play.nextTake = nextTake;
			return result;
		} else {
			// If the depth is reached, the current field rated
			return Evaluation.evaluation(board);

		}

	}

	public static int minmaxJumping(Board board, eColor computer,
			eColor player, int depth, int numberOfMoves) {
		if (depth > 0) {

			int result;

			Position nextMoveFrom = null;
			Position nextMoveTo = null;
			Position nextTake = null;

			if (computer == BLACK) {
				result = Integer.MIN_VALUE;
			} else {
				result = Integer.MAX_VALUE;
			}

			for (Position turnFrom : Position.getAllPositions()) {
				if (board.getColor(turnFrom) == computer) { // Every brick with
															// the Color is
															// removed
					board.setColor(turnFrom, NONE);
					for (Position turnTo : Position.getAllPositions()) {
						if (turnTo != turnFrom
								&& board.getColor(turnTo) == NONE) {
							board.setColor(turnTo, computer); // On every
																// possible
																// position, a
																// brick is
																// laid.

							if (board.isMill(turnTo, computer)) { // If with
																	// this
																	// position
																	// "turnTo"
																	// is a mill
								for (Position takeAway : Position
										.getAllPositions()) {
									if (board.getColor(takeAway) == player
											&& !board.isMill(takeAway, player)) {
										board.setColor(takeAway, NONE); // any
																		// opposing
																		// stone
																		// is
																		// taken
																		// away

										int value = minmaxDecide(
												board, // other player has its
														// turn
												player, computer, (depth - 1),
												numberOfMoves);

										if (computer == BLACK) {
											if (result < value) {
												result = value;
												nextMoveFrom = turnFrom;
												nextMoveTo = turnTo;
												nextTake = takeAway;
											}
										}

										if (computer == WHITE) {
											if (result > value) {
												result = value;
												nextMoveFrom = turnFrom;
												nextMoveTo = turnTo;
												nextTake = takeAway;
											}
										}

										board.setColor(takeAway, player);
									}
								}

							} else {
								// no new mill with this move

								// other player has its turn
								int value = minmaxDecide(board, player,
										computer, (depth - 1),
										numberOfMoves + 1);

								// Depending on what color is in the series, the
								// minimum or the maximum is stored.
								if (computer == BLACK) {
									if (result < value) {
										result = value;
										nextMoveFrom = turnFrom;
										nextMoveTo = turnTo;
										nextTake = null;
									}
								}

								if (computer == WHITE) {
									if (result > value) {
										result = value;
										nextMoveFrom = turnFrom;
										nextMoveTo = turnTo;
										nextTake = null;
									}

								}

							}
							board.setColor(turnTo, NONE);

						}
					}
					board.setColor(turnFrom, computer);

				}
			}
			// The best turn is stored
			Play.nextTurnFrom = nextMoveFrom;
			Play.nextTurnTo = nextMoveTo;
			Play.nextTake = nextTake;
			return result;
		} else {
			// If the depth is reached, the current field rated
			return Evaluation.evaluation(board);

		}

	}
}
