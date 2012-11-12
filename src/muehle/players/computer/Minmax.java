package muehle.players.computer;

import static muehle.model.Board.StoneColor.NONE;

import java.util.HashMap;
import java.util.Map;

import muehle.Linker;
import muehle.model.Board;
import muehle.model.Board.StoneColor;
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

	public static MinimaxResult minmaxDecide(Board board, StoneColor player,
			StoneColor opposite, int depth, int move, int numberOfStones) {
		MinimaxResult value = null;

		if (move < numberOfStones * 2 || !board.getStuck(player)) { // TODO in lay phase nicht richtig
			if (move < numberOfStones * 2) {
				//In the Situation: Lay the stones
				value = Minmax.minmaxLay(board, player, opposite, depth, move,
						numberOfStones);
			} else if (board.getNumberOfStones(player) > 3) {
				//In the Situation: Move the Stones
				value = Minmax.minmaxMove(board, player, opposite, depth, move,
						numberOfStones);
			} else if (board.getNumberOfStones(player) == 3){
				//In the Situation: Jump with the Stones
				value = Minmax.minmaxJumping(board, player, opposite, depth,
						move, numberOfStones);
			} else{
				//player has less than 3 Stones / he lost
				value = new MinimaxResult(null, Evaluation.evaluation(board, player, opposite, depth, move, numberOfStones));
			}
		} else {
			//player get stuck / lost
			value = new MinimaxResult(null, Evaluation.evaluation(board, player, opposite, depth, move, numberOfStones));
		}

		return value;

	}

	public static MinimaxResult minmaxLay(Board board, StoneColor player,
			StoneColor opposite, int depth, int move, int numberOfStones) {
		if (depth<Linker.difficulty){
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
										(depth + 1), move + 1, numberOfStones);

								if(depth==1)
									System.err.println(turn + " " + takeAway + " " + value.rank);
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
								player, (depth + 1), move + 1, numberOfStones);

						// Depending on what color is in the series, the minimum
						// or the maximum is stored.
						if(depth==1)
						System.out.println(turn + " " + value.rank);
						if (result < value.rank) {
							result = value.rank;
							nextMove = turn;
							nextTake = null;

						}

					}

					board.setColor(turn, NONE);

				}
			}

			return new MinimaxResult(new Move(null, nextMove, nextTake), -result);
		} else {
			// If the depth is reached, the current field rated
			return new MinimaxResult(null, -Evaluation.evaluation(board, player, opposite, depth, move, numberOfStones));

		}

	}

	public static MinimaxResult minmaxMove(Board board, StoneColor player,
			StoneColor opposite, int depth, int move, int numberOfStones) {
		if (depth<Linker.difficulty) {

			int result;
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
												player, (depth + 1), move + 1,
												numberOfStones);
										if(depth==1)
											System.err.println(turnTo + " " + value.rank);
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
										opposite, player, (depth + 1),
										move + 1, numberOfStones);

								// Depending on what color is in the series, the
								// minimum or the maximum is stored.
								if(depth==1)
									System.out.println(turnTo + " " + value.rank);
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

			return new MinimaxResult(new Move(nextMoveFrom, nextMoveTo,
					nextTake), (-1 * result));
		} else {
			// If the depth is reached, the current field rated
			return new MinimaxResult(null, -Evaluation.evaluation(board, player, opposite, depth, move, numberOfStones));

		}

	}

	public static MinimaxResult minmaxJumping(Board board, StoneColor player,
			StoneColor opposite, int depth, int move, int numberOfStones) {
		if (depth<Linker.difficulty) {

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
												opposite, player, (depth + 1),
												move + 1, numberOfStones);
										if(depth==1)
											System.err.println(turnTo + " " + value.rank);
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
										opposite, player, (depth + 1),
										move + 1, numberOfStones);

								// Depending on what color is in the series, the
								// minimum or the maximum is stored.
								if(depth==1)
									System.out.println(turnTo + " " + value.rank);
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
					nextTake), -result);
		} else {
			// If the depth is reached, the current field rated
			return new MinimaxResult(null, -Evaluation.evaluation(board, player, opposite, depth, move, numberOfStones));

		}

	}

	public static Map<Position, Integer> getProbability(Board board, StoneColor player, int move, int numberOfStones) {
		Map<Position, Integer> probabilities = new HashMap<Position, Integer>();
		int depth = 4;
		for (Position p : Position.getAllPositions()) {
			if (board.getColor(p) == StoneColor.NONE) { //TODO gilt nur bei legen und springen
				board.setColor(p, player);
				MinimaxResult res = Minmax.minmaxDecide(board, player==StoneColor.BLACK?StoneColor.WHITE:StoneColor.BLACK, player, depth-1, move+1,
						numberOfStones);
				probabilities.put(p,-res.getRank());
				board.setColor(p, StoneColor.NONE);
			}else{
				probabilities.put(p, 0);
			}
		}
		System.err.println(probabilities);
		return probabilities;
	}

}