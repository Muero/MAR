package muehle.players.computer;

import static muehle.model.Board.eColor.NONE;

import java.util.HashMap;
import java.util.Map;

import muehle.model.Board;
import muehle.model.Board.eColor;
import muehle.model.Position;
import muehle.players.Move;

/**
 * determine the best turn for the computer
 * 
 * @return the best value of the evaluation
 */
public class Minmax {

	static class MinimaxResult {
		Move bestMove;
		int rank;

		public int getRank() {
			return rank;
		}

		public MinimaxResult(Move bestMove, int rank) {
			this.bestMove = bestMove;
			this.rank = rank;
		}
	}

	public static int deepthWhiteMill = 0; // not used yet
	public static int deepthBlackMill = 0; // not used yet
	public static MinimaxResult minmaxDecide(Board board, eColor player,
			eColor opposite, int depth, int move, int numberOfStones) {
		MinimaxResult value = null;

		if (!board.getStuck(opposite) && board.freePosition(opposite)) {
			if (move < numberOfStones * 2) {
				value = Minmax.minmaxLay(board, player, opposite, depth, move,
						numberOfStones);
			} else if (board.getNumberOfStones(player) > 3) {
				//System.out.println("minimax bewegen");
				value = Minmax.minmaxMove(board, player, opposite, depth, move,
						numberOfStones);
			} else {
				//System.out.println("minimax springen");
				value = Minmax.minmaxJumping(board, player, opposite, depth,
						move, numberOfStones);
			}
		} else {
			value = new MinimaxResult(null, Evaluation.evaluation(board));
		}

		return value;

	}

	// true == oppposite(computer) ist dran
	// false == player
	public static MinimaxResult minmaxLay(Board board, eColor player,
			eColor opposite, int depth, int move, int numberOfStones) {
		if (depth > 0) {
			int result;

			Position nextMove = null;
			Position nextTake = null;

			result = Integer.MIN_VALUE;

			for (Position turn : Position.getAllPositions()) {
				if (board.getColor(turn) == NONE) { // On every possible
													// position, a brick is
													// laid.
					board.setColor(turn, player);
					if (board.isMill(turn, player)) { // If with this position
														// "turn" is a mill
						for (Position takeAway : Position.getAllPositions()) { // any
																				// opposing
																				// stone
																				// is
																				// taken
																				// away
							if (board.getColor(takeAway) == opposite
									&& !board.isMill(takeAway, opposite)) {
								board.setColor(takeAway, NONE);

								MinimaxResult value = minmaxDecide(board,
										opposite, player, // other player has
															// its turn
										(depth - 1), move + 1, numberOfStones);

								if (result < value.rank) {
									result = value.rank;
									nextMove = turn;
									nextTake = takeAway;

								}

								board.setColor(takeAway, opposite);
							}
						}
					} else {
						// no new mill with this move

						// other player has its turn
						MinimaxResult value = minmaxDecide(board, opposite,
								player, (depth - 1), move + 1, numberOfStones);

						// Depending on what color is in the series, the minimum
						// or the maximum is stored.
						if (result < value.rank) {
							result = value.rank;
							nextMove = turn;
							nextTake = null;

						}

					}

					board.setColor(turn, NONE);

				}
			}

			return new MinimaxResult(new Move(null, nextMove, nextTake),
					(-1 * result));
		} else {
			// If the depth is reached, the current field rated
			return new MinimaxResult(null, Evaluation.evaluation(board));

		}

	}

	public static MinimaxResult minmaxMove(Board board, eColor player,
			eColor opposite, int depth, int move, int numberOfStones) {
		if (depth > 0) {

			int result;
			//System.out.println("hier");
			Position nextMoveFrom = null;
			Position nextMoveTo = null;
			Position nextTake = null;

			// if (turnDistinction)
			result = Integer.MIN_VALUE;
			// else
			// result = Integer.MAX_VALUE;

			for (Position turnFrom : Position.getAllPositions()) {
				if (board.getColor(turnFrom) == player) {
					board.setColor(turnFrom, NONE); // Every brick with the
													// Color is removed
					for (Position turnTo : Position.getNeighboursOf(turnFrom)) {
						if (board.getColor(turnTo) == NONE) {
							board.setColor(turnTo, player); // on each nearby
															// position is a
															// stone set

							if (board.isMill(turnTo, player)) { // If with
																// this
																// position
																// "turnTo"
																// is a mill
								for (Position takeAway : Position
										.getAllPositions()) {
									if (board.getColor(takeAway) == opposite
											&& !board
													.isMill(takeAway, opposite)) {
										board.setColor(takeAway, NONE); // any
																		// opposing
																		// stone
																		// is
																		// taken
																		// away

										MinimaxResult value = minmaxDecide(
												board,
												opposite, // other player has
															// its
															// turn
												player, (depth - 1), move + 1,
												numberOfStones);

										if (result < value.rank) {
											result = value.rank;
											nextMoveFrom = turnFrom;
											nextMoveTo = turnTo;
											nextTake = takeAway;

										}

										board.setColor(takeAway, opposite);
									}
								}
							} else {
								// no new mill with this move

								// other player has its turn
								MinimaxResult value = minmaxDecide(board,
										opposite, player, (depth - 1),
										move + 1, numberOfStones);

								// Depending on what color is in the series, the
								// minimum or the maximum is stored.
								if (result < value.rank) {
									result = value.rank;
									nextMoveFrom = turnFrom;
									nextMoveTo = turnTo;
									nextTake = null;
								}

							}
							board.setColor(turnTo, NONE);

						}
					}
					board.setColor(turnFrom, player);
				}
			}
			// The best turn is stored
			//System.out.println("bestMove gesetzt");
			return new MinimaxResult(new Move(nextMoveFrom, nextMoveTo,
					nextTake), (-1 * result));
		} else {
			// If the depth is reached, the current field rated
			return new MinimaxResult(null, Evaluation.evaluation(board));

		}

	}

	public static MinimaxResult minmaxJumping(Board board, eColor player,
			eColor opposite, int depth, int move, int numberOfStones) {
		if (depth > 0) {

			int result;

			Position nextMoveFrom = null;
			Position nextMoveTo = null;
			Position nextTake = null;

			// if (turnDistinction)
			result = Integer.MIN_VALUE;
			// else
			// result = Integer.MAX_VALUE;

			for (Position turnFrom : Position.getAllPositions()) {
				if (board.getColor(turnFrom) == player) { // Every brick with
															// the Color is
															// removed
					board.setColor(turnFrom, NONE);
					for (Position turnTo : Position.getAllPositions()) {
						if (turnTo != turnFrom
								&& board.getColor(turnTo) == NONE) {
							board.setColor(turnTo, player); // On every
															// possible
															// position, a
															// brick is
															// laid.

							if (board.isMill(turnTo, player)) { // If with
																// this
																// position
																// "turnTo"
																// is a mill
								for (Position takeAway : Position
										.getAllPositions()) {
									if (board.getColor(takeAway) == opposite
											&& !board
													.isMill(takeAway, opposite)) {
										board.setColor(takeAway, NONE); // any
																		// opposing
																		// stone
																		// is
																		// taken
																		// away

										MinimaxResult value = minmaxDecide(
												board, // other player has its
														// turn
												opposite, player, (depth - 1),
												move + 1, numberOfStones);

										if (result < value.rank) {
											result = value.rank;
											nextMoveFrom = turnFrom;
											nextMoveTo = turnTo;
											nextTake = takeAway;
										}

										board.setColor(takeAway, opposite);
									}
								}

							} else {
								// no new mill with this move

								// other player has its turn
								MinimaxResult value = minmaxDecide(board,
										opposite, player, (depth - 1),
										move + 1, numberOfStones);

								// Depending on what color is in the series, the
								// minimum or the maximum is stored.
								if (result < value.rank) {
									result = value.rank;
									nextMoveFrom = turnFrom;
									nextMoveTo = turnTo;
									nextTake = null;
								}

							}
							board.setColor(turnTo, NONE);

						}
					}
					board.setColor(turnFrom, player);

				}
			}
			return new MinimaxResult(new Move(nextMoveFrom, nextMoveTo,
					nextTake), (-1 * result));
		} else {
			// If the depth is reached, the current field rated
			return new MinimaxResult(null, Evaluation.evaluation(board));

		}

	}
	
	public static Map<Position, Integer> getProbability(Board board, eColor player, int move, int numberOfStones) {
		Map<Position, Integer> probabilities = new HashMap<Position, Integer>();
		int depth = 4;
		for (Position p : Position.getAllPositions()) {
			if (board.getColor(p) == eColor.NONE) { //TODO gilt nur bei legen und springen
				MinimaxResult res = Minmax.minmaxDecide(board, player, player==eColor.BLACK?eColor.WHITE:eColor.BLACK, depth, move,
						numberOfStones);
				probabilities.put(p,res.getRank());
			}else{
				probabilities.put(p, 0);
			}
		}
		System.err.println(probabilities);
		return probabilities;
	}

}
