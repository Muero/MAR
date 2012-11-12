package muehle.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {

	/**
	 * represents the Color of the Stones
	 */
	public enum StoneColor {
		NONE, BLACK, WHITE // The three modes that a "Position" may have.
	}

	private Map<Position, StoneColor>board = new HashMap<Position, StoneColor>();

	/**
	 * Returns the Color on the Position p. If on the Position is no Color it
	 * returns the Color NONE.
	 * 
	 * @param p is the position where you want to find out the color
	 * @return c is the color at the position p
	 */
	public StoneColor getColor(Position p) {
		if (p == null)
			throw new IllegalArgumentException();
		StoneColor c = board.get(p);
		if (c == null)
			c = StoneColor.NONE;
		return c;
	}

	/**
	 * Set the color c on the position p. The color can be retrieved with method
	 * {@link getColor}
	 * 
	 * @param p is the position where you want to set a Color
	 * @param c is the color you want to set
	 * @throws IllegalArgumentException
	 *             if p == null
	 */
	public void setColor(Position p, StoneColor c) {
		if (p == null)
			throw new IllegalArgumentException();
		board.put(p, c);
	}

	/*
	 * draw the Board on the console
	 */
	public String toString() {
		return String.format(

				// "NONEx-------------NONEx-------------NONEx\n"+
				// "  |   NONEx-------NONEx-------NONEx   |  \n"+
				// "  |     |   NONEx-NONEx-NONEx   |     |  \n"+
				// "NONEx-NONEx-NONEx       NONEx-NONEx-NONEx\n"+
				// "  |     |   NONEx-NONEx-NONEx   |     |  \n"+
				// "  |   NONEx-------NONEx-------NONEx   |  \n"+
				// "NONEx-------------NONEx-------------NONEx\n",

				"%5s-------------%5s-------------%5s\n"
						+ "  |   %5s-------%5s-------%5s   |  \n"
						+ "  |     |   %5s-%5s-%5s   |     |  \n"
						+ "%5s-%5s-%5s       %5s-%5s-%5s\n"
						+ "  |     |   %5s-%5s-%5s   |     |  \n"
						+ "  |   %5s-------%5s-------%5s   |  \n"
						+ "%5s-------------%5s-------------%5s\n\n",

				getColor(Position.p10), getColor(Position.p13),
				getColor(Position.p16), getColor(Position.p21),
				getColor(Position.p23), getColor(Position.p25),
				getColor(Position.p32), getColor(Position.p33),
				getColor(Position.p34), getColor(Position.p40),
				getColor(Position.p41), getColor(Position.p42),
				getColor(Position.p44), getColor(Position.p45),
				getColor(Position.p46), getColor(Position.p52),
				getColor(Position.p53), getColor(Position.p54),
				getColor(Position.p61), getColor(Position.p63),
				getColor(Position.p65), getColor(Position.p70),
				getColor(Position.p73), getColor(Position.p76));
	}

	/**
	 * Returns the number of stones of a given color.
	 * 
	 * @param color is the given color
	 * @return the number of stones with the given color
	 */
	public int getNumberOfStones(StoneColor color) {
		int counter = 0;
		for (StoneColor c : board.values()) {
			if (c == color)
				counter++;
		}
		return counter;
	}

	/**
	 * Test whether a mill is on the Position pos.
	 * 
	 * @param stone is the color of the mill
	 * @param pos is one of the positions of the mill
	 * @return whether a mill is at the Position pos
	 */
	public boolean isMill(Position pos, StoneColor stone) {
		int id = pos.getId();
		Set<Position> candidates = new HashSet<Position>();
		candidates.add(pos);

		Set<Position> neighbours = Position.getNeighboursOf(pos);
		for (Position p : neighbours) {
			candidates.add(p);
			for (Position p2 : Position.getNeighboursOf(p)) {
				if (p2.getId() / 10 == id / 10 || p2.getId() % 10 == id % 10)
					candidates.add(p2);
			}
		}

		// vertical
		int v = 0;
		for (Position p : candidates) {
			if (p.getId() % 10 == id % 10 && getColor(p) == stone)
				v++;
		}

		// horizontal
		int h = 0;
		for (Position p : candidates) {
			if (p.getId() / 10 == id / 10 && getColor(p) == stone)
				h++;
		}

		return v == 3 || h == 3;

	}

	/**
	 * Counts the number of mills in the given color c
	 * 
	 * @param c is the color whose mills care counted
	 * @return number of mills in the color c
	 */
	public int getNumberOfMills(StoneColor c) {
		int counter = 0;

		// horizontal

		if (board.get(Position.p10) == c && board.get(Position.p13) == c
				&& board.get(Position.p16) == c) {
			counter++;
		}
		if (board.get(Position.p21) == c && board.get(Position.p23) == c
				&& board.get(Position.p25) == c) {
			counter++;
		}
		if (board.get(Position.p32) == c && board.get(Position.p33) == c
				&& board.get(Position.p34) == c) {
			counter++;
		}
		if (board.get(Position.p40) == c && board.get(Position.p41) == c
				&& board.get(Position.p42) == c) {
			counter++;
		}
		if (board.get(Position.p44) == c && board.get(Position.p45) == c
				&& board.get(Position.p46) == c) {
			counter++;
		}
		if (board.get(Position.p52) == c && board.get(Position.p53) == c
				&& board.get(Position.p54) == c) {
			counter++;
		}
		if (board.get(Position.p61) == c && board.get(Position.p63) == c
				&& board.get(Position.p65) == c) {
			counter++;
		}
		if (board.get(Position.p70) == c && board.get(Position.p73) == c
				&& board.get(Position.p76) == c) {
			counter++;
		}

		// vertical

		if (board.get(Position.p10) == c && board.get(Position.p40) == c
				&& board.get(Position.p70) == c) {
			counter++;
		}
		if (board.get(Position.p21) == c && board.get(Position.p41) == c
				&& board.get(Position.p61) == c) {
			counter++;
		}
		if (board.get(Position.p32) == c && board.get(Position.p42) == c
				&& board.get(Position.p52) == c) {
			counter++;
		}
		if (board.get(Position.p33) == c && board.get(Position.p23) == c
				&& board.get(Position.p13) == c) {
			counter++;
		}
		if (board.get(Position.p53) == c && board.get(Position.p63) == c
				&& board.get(Position.p73) == c) {
			counter++;
		}
		if (board.get(Position.p34) == c && board.get(Position.p44) == c
				&& board.get(Position.p54) == c) {
			counter++;
		}
		if (board.get(Position.p25) == c && board.get(Position.p45) == c
				&& board.get(Position.p65) == c) {
			counter++;
		}
		if (board.get(Position.p16) == c && board.get(Position.p46) == c
				&& board.get(Position.p76) == c) {
			counter++;
		}
		return counter;

	}
	/**
	 * Decide if there is a Position without a Color
	 * 
	 * @return if there is a Position with no Color
	 */
	public boolean freePosition(){
		for (Position p : Position.getAllPositions()){
			if (getColor(p)==StoneColor.NONE)
				return true;
		}
		
		return false;
	}

	/**
	 * Tests if you get stuck
	 * @param you is your Color
	 * @return if your Color get stuck
	 */
	public boolean getStuck(StoneColor you) {
		if (getNumberOfStones(you)==0)
			return false;		
		for (Position p : Position.getAllPositions()) {
			if (getColor(p) == you) {
				for (Position n : Position.getNeighboursOf(p)) {
					if (getColor(n) == StoneColor.NONE)
						return false;
				}

			}

		}
		return true;
	}

	/**
	 * Counts the number of Mills on the board
	 * @param stone is the Color of the mill
	 * @return the number of Mills with the color stone
	 */
	public int getNumberOfOpenMillsMove(StoneColor stone) {
		
		int z = 0;
		// biggest circle
		// left
		if (board.get(Position.p10) == stone
				&& board.get(Position.p40) == stone
				&& board.get(Position.p73) == stone
				&& board.get(Position.p70) == StoneColor.NONE)
			z++;
		if (board.get(Position.p10) == stone
				&& board.get(Position.p70) == stone
				&& board.get(Position.p41) == stone
				&& board.get(Position.p40) == StoneColor.NONE)
			z++;
		if (board.get(Position.p70) == stone
				&& board.get(Position.p40) == stone
				&& board.get(Position.p13) == stone
				&& board.get(Position.p10) == StoneColor.NONE)
			z++;
		// top
		if (board.get(Position.p70) == stone
				&& board.get(Position.p73) == stone
				&& board.get(Position.p46) == stone
				&& board.get(Position.p76) == StoneColor.NONE)
			z++;
		if (board.get(Position.p70) == stone
				&& board.get(Position.p63) == stone
				&& board.get(Position.p76) == stone
				&& board.get(Position.p73) == StoneColor.NONE)
			z++;
		if (board.get(Position.p40) == stone
				&& board.get(Position.p73) == stone
				&& board.get(Position.p76) == stone
				&& board.get(Position.p70) == StoneColor.NONE)
			z++;
		// right
		if (board.get(Position.p76) == stone
				&& board.get(Position.p46) == stone
				&& board.get(Position.p13) == stone
				&& board.get(Position.p16) == StoneColor.NONE)
			z++;
		if (board.get(Position.p76) == stone
				&& board.get(Position.p45) == stone
				&& board.get(Position.p16) == stone
				&& board.get(Position.p46) == StoneColor.NONE)
			z++;
		if (board.get(Position.p73) == stone
				&& board.get(Position.p46) == stone
				&& board.get(Position.p16) == stone
				&& board.get(Position.p76) == StoneColor.NONE)
			z++;
		// bottom
		if (board.get(Position.p10) == stone
				&& board.get(Position.p13) == stone
				&& board.get(Position.p46) == stone
				&& board.get(Position.p16) == StoneColor.NONE)
			z++;
		if (board.get(Position.p10) == stone
				&& board.get(Position.p23) == stone
				&& board.get(Position.p16) == stone
				&& board.get(Position.p13) == StoneColor.NONE)
			z++;
		if (board.get(Position.p40) == stone
				&& board.get(Position.p13) == stone
				&& board.get(Position.p16) == stone
				&& board.get(Position.p10) == StoneColor.NONE)
			z++;
		// medium circle
		// left
		if (board.get(Position.p21) == stone
				&& board.get(Position.p41) == stone
				&& board.get(Position.p63) == stone
				&& board.get(Position.p61) == StoneColor.NONE)
			z++;
		if (board.get(Position.p21) == stone
				&& board.get(Position.p42) == stone
				&& board.get(Position.p61) == stone
				&& board.get(Position.p41) == StoneColor.NONE)
			z++;
		if (board.get(Position.p21) == stone
				&& board.get(Position.p40) == stone
				&& board.get(Position.p61) == stone
				&& board.get(Position.p41) == StoneColor.NONE)
			z++;
		if (board.get(Position.p23) == stone
				&& board.get(Position.p41) == stone
				&& board.get(Position.p61) == stone
				&& board.get(Position.p21) == StoneColor.NONE)
			z++;
		// top
		if (board.get(Position.p61) == stone
				&& board.get(Position.p63) == stone
				&& board.get(Position.p45) == stone
				&& board.get(Position.p65) == StoneColor.NONE)
			z++;
		if (board.get(Position.p61) == stone
				&& board.get(Position.p53) == stone
				&& board.get(Position.p65) == stone
				&& board.get(Position.p63) == StoneColor.NONE)
			z++;
		if (board.get(Position.p61) == stone
				&& board.get(Position.p73) == stone
				&& board.get(Position.p65) == stone
				&& board.get(Position.p63) == StoneColor.NONE)
			z++;
		if (board.get(Position.p41) == stone
				&& board.get(Position.p63) == stone
				&& board.get(Position.p65) == stone
				&& board.get(Position.p61) == StoneColor.NONE)
			z++;
		// right
		if (board.get(Position.p65) == stone
				&& board.get(Position.p45) == stone
				&& board.get(Position.p23) == stone
				&& board.get(Position.p25) == StoneColor.NONE)
			z++;
		if (board.get(Position.p65) == stone
				&& board.get(Position.p44) == stone
				&& board.get(Position.p25) == stone
				&& board.get(Position.p45) == StoneColor.NONE)
			z++;
		if (board.get(Position.p65) == stone
				&& board.get(Position.p46) == stone
				&& board.get(Position.p25) == stone
				&& board.get(Position.p45) == StoneColor.NONE)
			z++;
		if (board.get(Position.p63) == stone
				&& board.get(Position.p45) == stone
				&& board.get(Position.p25) == stone
				&& board.get(Position.p65) == StoneColor.NONE)
			z++;
		// bottom
		if (board.get(Position.p21) == stone
				&& board.get(Position.p23) == stone
				&& board.get(Position.p45) == stone
				&& board.get(Position.p25) == StoneColor.NONE)
			z++;
		if (board.get(Position.p21) == stone
				&& board.get(Position.p33) == stone
				&& board.get(Position.p25) == stone
				&& board.get(Position.p23) == StoneColor.NONE)
			z++;
		if (board.get(Position.p21) == stone
				&& board.get(Position.p13) == stone
				&& board.get(Position.p25) == stone
				&& board.get(Position.p23) == StoneColor.NONE)
			z++;
		if (board.get(Position.p41) == stone
				&& board.get(Position.p23) == stone
				&& board.get(Position.p25) == stone
				&& board.get(Position.p21) == StoneColor.NONE)
			z++;
		// smallest circle
		// left
		if (board.get(Position.p32) == stone
				&& board.get(Position.p42) == stone
				&& board.get(Position.p53) == stone
				&& board.get(Position.p52) == StoneColor.NONE)
			z++;
		if (board.get(Position.p32) == stone
				&& board.get(Position.p41) == stone
				&& board.get(Position.p52) == stone
				&& board.get(Position.p42) == StoneColor.NONE)
			z++;
		if (board.get(Position.p33) == stone
				&& board.get(Position.p42) == stone
				&& board.get(Position.p52) == stone
				&& board.get(Position.p32) == StoneColor.NONE)
			z++;
		// top
		if (board.get(Position.p52) == stone
				&& board.get(Position.p53) == stone
				&& board.get(Position.p44) == stone
				&& board.get(Position.p54) == StoneColor.NONE)
			z++;
		if (board.get(Position.p52) == stone
				&& board.get(Position.p63) == stone
				&& board.get(Position.p54) == stone
				&& board.get(Position.p53) == StoneColor.NONE)
			z++;
		if (board.get(Position.p42) == stone
				&& board.get(Position.p53) == stone
				&& board.get(Position.p54) == stone
				&& board.get(Position.p52) == StoneColor.NONE)
			z++;
		// right
		if (board.get(Position.p54) == stone
				&& board.get(Position.p44) == stone
				&& board.get(Position.p33) == stone
				&& board.get(Position.p34) == StoneColor.NONE)
			z++;
		if (board.get(Position.p54) == stone
				&& board.get(Position.p45) == stone
				&& board.get(Position.p34) == stone
				&& board.get(Position.p44) == StoneColor.NONE)
			z++;
		if (board.get(Position.p53) == stone
				&& board.get(Position.p44) == stone
				&& board.get(Position.p34) == stone
				&& board.get(Position.p54) == StoneColor.NONE)
			z++;
		// bottom
		if (board.get(Position.p32) == stone
				&& board.get(Position.p33) == stone
				&& board.get(Position.p44) == stone
				&& board.get(Position.p34) == StoneColor.NONE)
			z++;
		if (board.get(Position.p32) == stone
				&& board.get(Position.p23) == stone
				&& board.get(Position.p34) == stone
				&& board.get(Position.p33) == StoneColor.NONE)
			z++;
		if (board.get(Position.p42) == stone
				&& board.get(Position.p33) == stone
				&& board.get(Position.p34) == stone
				&& board.get(Position.p32) == StoneColor.NONE)
			z++;

		// left cross
		if (board.get(Position.p40) == stone
				&& board.get(Position.p41) == stone
				&& board.get(Position.p52) == stone
				&& board.get(Position.p42) == StoneColor.NONE)
			z++;
		if (board.get(Position.p40) == stone
				&& board.get(Position.p41) == stone
				&& board.get(Position.p32) == stone
				&& board.get(Position.p42) == StoneColor.NONE)
			z++;
		if (board.get(Position.p40) == stone
				&& board.get(Position.p61) == stone
				&& board.get(Position.p42) == stone
				&& board.get(Position.p41) == StoneColor.NONE)
			z++;
		if (board.get(Position.p40) == stone
				&& board.get(Position.p21) == stone
				&& board.get(Position.p42) == stone
				&& board.get(Position.p41) == StoneColor.NONE)
			z++;
		if (board.get(Position.p10) == stone
				&& board.get(Position.p41) == stone
				&& board.get(Position.p42) == stone
				&& board.get(Position.p40) == StoneColor.NONE)
			z++;
		if (board.get(Position.p70) == stone
				&& board.get(Position.p41) == stone
				&& board.get(Position.p42) == stone
				&& board.get(Position.p40) == StoneColor.NONE)
			z++;
		// up cross
		if (board.get(Position.p73) == stone
				&& board.get(Position.p63) == stone
				&& board.get(Position.p54) == stone
				&& board.get(Position.p53) == StoneColor.NONE)
			z++;
		if (board.get(Position.p73) == stone
				&& board.get(Position.p63) == stone
				&& board.get(Position.p52) == stone
				&& board.get(Position.p53) == StoneColor.NONE)
			z++;
		if (board.get(Position.p73) == stone
				&& board.get(Position.p61) == stone
				&& board.get(Position.p53) == stone
				&& board.get(Position.p63) == StoneColor.NONE)
			z++;
		if (board.get(Position.p73) == stone
				&& board.get(Position.p65) == stone
				&& board.get(Position.p53) == stone
				&& board.get(Position.p63) == StoneColor.NONE)
			z++;
		if (board.get(Position.p63) == stone
				&& board.get(Position.p53) == stone
				&& board.get(Position.p70) == stone
				&& board.get(Position.p73) == StoneColor.NONE)
			z++;
		if (board.get(Position.p63) == stone
				&& board.get(Position.p53) == stone
				&& board.get(Position.p76) == stone
				&& board.get(Position.p73) == StoneColor.NONE)
			z++;
		
		
		// right cross
		if (board.get(Position.p46) == stone
				&& board.get(Position.p45) == stone
				&& board.get(Position.p54) == stone
				&& board.get(Position.p44) == StoneColor.NONE)
			z++;
		if (board.get(Position.p46) == stone
				&& board.get(Position.p45) == stone
				&& board.get(Position.p34) == stone
				&& board.get(Position.p44) == StoneColor.NONE)
			z++;
		if (board.get(Position.p46) == stone
				&& board.get(Position.p65) == stone
				&& board.get(Position.p44) == stone
				&& board.get(Position.p45) == StoneColor.NONE)
			z++;
		if (board.get(Position.p46) == stone
				&& board.get(Position.p25) == stone
				&& board.get(Position.p44) == stone
				&& board.get(Position.p45) == StoneColor.NONE)
			z++;
		if (board.get(Position.p76) == stone
				&& board.get(Position.p45) == stone
				&& board.get(Position.p44) == stone
				&& board.get(Position.p46) == StoneColor.NONE)
			z++;
		if (board.get(Position.p16) == stone
				&& board.get(Position.p45) == stone
				&& board.get(Position.p44) == stone
				&& board.get(Position.p46) == StoneColor.NONE)
			z++;
		// bottom cross
		if (board.get(Position.p13) == stone
				&& board.get(Position.p23) == stone
				&& board.get(Position.p32) == stone
				&& board.get(Position.p33) == StoneColor.NONE)
			z++;
		if (board.get(Position.p13) == stone
				&& board.get(Position.p23) == stone
				&& board.get(Position.p34) == stone
				&& board.get(Position.p33) == StoneColor.NONE)
			z++;
		if (board.get(Position.p13) == stone
				&& board.get(Position.p21) == stone
				&& board.get(Position.p33) == stone
				&& board.get(Position.p23) == StoneColor.NONE)
			z++;
		if (board.get(Position.p13) == stone
				&& board.get(Position.p25) == stone
				&& board.get(Position.p33) == stone
				&& board.get(Position.p23) == StoneColor.NONE)
			z++;
		if (board.get(Position.p10) == stone
				&& board.get(Position.p23) == stone
				&& board.get(Position.p33) == stone
				&& board.get(Position.p13) == StoneColor.NONE)
			z++;
		if (board.get(Position.p16) == stone
				&& board.get(Position.p23) == stone
				&& board.get(Position.p33) == stone
				&& board.get(Position.p13) == StoneColor.NONE)
			z++;
		return z;
	}
	public int getNumberOfOpenMillsLay(StoneColor stone) {
		int z = 0;
		// biggest circle
		// left
		if (board.get(Position.p10) == stone
				&& board.get(Position.p40) == stone
				&& board.get(Position.p70) == StoneColor.NONE)
			z++;
		if (board.get(Position.p10) == stone
				&& board.get(Position.p70) == stone
				&& board.get(Position.p40) == StoneColor.NONE)
			z++;
		if (board.get(Position.p70) == stone
				&& board.get(Position.p40) == stone
				&& board.get(Position.p10) == StoneColor.NONE)
			z++;
		// top
		if (board.get(Position.p70) == stone
				&& board.get(Position.p73) == stone
				&& board.get(Position.p76) == StoneColor.NONE)
			z++;
		if (board.get(Position.p70) == stone
				&& board.get(Position.p76) == stone
				&& board.get(Position.p73) == StoneColor.NONE)
			z++;
		if (board.get(Position.p73) == stone
				&& board.get(Position.p76) == stone
				&& board.get(Position.p70) == StoneColor.NONE)
			z++;
		// right
		if (board.get(Position.p76) == stone
				&& board.get(Position.p46) == stone
				&& board.get(Position.p16) == StoneColor.NONE)
			z++;
		if (board.get(Position.p76) == stone
				&& board.get(Position.p16) == stone
				&& board.get(Position.p46) == StoneColor.NONE)
			z++;
		if (board.get(Position.p46) == stone
				&& board.get(Position.p16) == stone
				&& board.get(Position.p76) == StoneColor.NONE)
			z++;
		// bottom
		if (board.get(Position.p10) == stone
				&& board.get(Position.p13) == stone
				&& board.get(Position.p16) == StoneColor.NONE)
			z++;
		if (board.get(Position.p10) == stone
				&& board.get(Position.p16) == stone
				&& board.get(Position.p13) == StoneColor.NONE)
			z++;
		if (board.get(Position.p13) == stone
				&& board.get(Position.p16) == stone
				&& board.get(Position.p10) == StoneColor.NONE)
			z++;
		// medium circle
		// left
		if (board.get(Position.p21) == stone
				&& board.get(Position.p41) == stone
				&& board.get(Position.p61) == StoneColor.NONE)
			z++;
		if (board.get(Position.p21) == stone
				&& board.get(Position.p61) == stone
				&& board.get(Position.p41) == StoneColor.NONE)
			z++;
		if (board.get(Position.p41) == stone
				&& board.get(Position.p61) == stone
				&& board.get(Position.p21) == StoneColor.NONE)
			z++;
		// top
		if (board.get(Position.p61) == stone
				&& board.get(Position.p63) == stone
				&& board.get(Position.p65) == StoneColor.NONE)
			z++;
		if (board.get(Position.p61) == stone
				&& board.get(Position.p65) == stone
				&& board.get(Position.p63) == StoneColor.NONE)
			z++;
		if (board.get(Position.p63) == stone
				&& board.get(Position.p65) == stone
				&& board.get(Position.p61) == StoneColor.NONE)
			z++;
		// right
		if (board.get(Position.p65) == stone
				&& board.get(Position.p45) == stone
				&& board.get(Position.p25) == StoneColor.NONE)
			z++;
		if (board.get(Position.p65) == stone
				&& board.get(Position.p25) == stone
				&& board.get(Position.p45) == StoneColor.NONE)
			z++;
		if (board.get(Position.p45) == stone
				&& board.get(Position.p25) == stone
				&& board.get(Position.p65) == StoneColor.NONE)
			z++;
		// bottom
		if (board.get(Position.p21) == stone
				&& board.get(Position.p23) == stone
				&& board.get(Position.p25) == StoneColor.NONE)
			z++;
		if (board.get(Position.p21) == stone
				&& board.get(Position.p25) == stone
				&& board.get(Position.p23) == StoneColor.NONE)
			z++;
		if (board.get(Position.p23) == stone
				&& board.get(Position.p25) == stone
				&& board.get(Position.p21) == StoneColor.NONE)
			z++;
		// smallest circle
		// left
		if (board.get(Position.p32) == stone
				&& board.get(Position.p42) == stone
				&& board.get(Position.p52) == StoneColor.NONE)
			z++;
		if (board.get(Position.p32) == stone
				&& board.get(Position.p52) == stone
				&& board.get(Position.p42) == StoneColor.NONE)
			z++;
		if (board.get(Position.p42) == stone
				&& board.get(Position.p52) == stone
				&& board.get(Position.p32) == StoneColor.NONE)
			z++;
		// top
		if (board.get(Position.p52) == stone
				&& board.get(Position.p53) == stone
				&& board.get(Position.p54) == StoneColor.NONE)
			z++;
		if (board.get(Position.p52) == stone
				&& board.get(Position.p54) == stone
				&& board.get(Position.p53) == StoneColor.NONE)
			z++;
		if (board.get(Position.p53) == stone
				&& board.get(Position.p54) == stone
				&& board.get(Position.p52) == StoneColor.NONE)
			z++;
		// right
		if (board.get(Position.p54) == stone
				&& board.get(Position.p44) == stone
				&& board.get(Position.p34) == StoneColor.NONE)
			z++;
		if (board.get(Position.p54) == stone
				&& board.get(Position.p34) == stone
				&& board.get(Position.p44) == StoneColor.NONE)
			z++;
		if (board.get(Position.p44) == stone
				&& board.get(Position.p34) == stone
				&& board.get(Position.p54) == StoneColor.NONE)
			z++;
		// bottom
		if (board.get(Position.p32) == stone
				&& board.get(Position.p33) == stone
				&& board.get(Position.p34) == StoneColor.NONE)
			z++;
		if (board.get(Position.p32) == stone
				&& board.get(Position.p34) == stone
				&& board.get(Position.p33) == StoneColor.NONE)
			z++;
		if (board.get(Position.p33) == stone
				&& board.get(Position.p34) == stone
				&& board.get(Position.p32) == StoneColor.NONE)
			z++;

		// left cross
		if (board.get(Position.p40) == stone
				&& board.get(Position.p41) == stone
				&& board.get(Position.p42) == StoneColor.NONE)
			z++;
		if (board.get(Position.p40) == stone
				&& board.get(Position.p42) == stone
				&& board.get(Position.p41) == StoneColor.NONE)
			z++;
		if (board.get(Position.p41) == stone
				&& board.get(Position.p42) == stone
				&& board.get(Position.p40) == StoneColor.NONE)
			z++;
		// up cross
		if (board.get(Position.p73) == stone
				&& board.get(Position.p63) == stone
				&& board.get(Position.p53) == StoneColor.NONE)
			z++;
		if (board.get(Position.p73) == stone
				&& board.get(Position.p53) == stone
				&& board.get(Position.p63) == StoneColor.NONE)
			z++;
		if (board.get(Position.p63) == stone
				&& board.get(Position.p53) == stone
				&& board.get(Position.p73) == StoneColor.NONE)
			z++;
		
		
		// right cross

		if (board.get(Position.p46) == stone
				&& board.get(Position.p45) == stone
				&& board.get(Position.p44) == StoneColor.NONE)
			z++;

		if (board.get(Position.p46) == stone
				&& board.get(Position.p44) == stone
				&& board.get(Position.p45) == StoneColor.NONE)
			z++;

		if (board.get(Position.p45) == stone
				&& board.get(Position.p44) == stone
				&& board.get(Position.p46) == StoneColor.NONE)
			z++;
		// bottom cross
	
		if (board.get(Position.p13) == stone
				&& board.get(Position.p23) == stone
				&& board.get(Position.p33) == StoneColor.NONE)
			z++;
		
		if (board.get(Position.p13) == stone
				&& board.get(Position.p33) == stone
				&& board.get(Position.p23) == StoneColor.NONE)
			z++;
		
		if (board.get(Position.p23) == stone
				&& board.get(Position.p33) == stone
				&& board.get(Position.p13) == StoneColor.NONE)
			z++;
		return z;
	}
		
		
		
	}






