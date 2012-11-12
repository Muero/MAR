package muehle.connection;

import muehle.model.Position;

public interface Connection {
	/**
	 * Opens the connection
	 */
	void openConnection();
	/**
	 * Closes the Connection
	 */
	void closeConnection();
	/**
	 * Sets a Stone to a Position
	 * @param to is the Position where the Stone must be placed
	 */
	void setStone(Position to);
	/**
	 * Moves a Stone from a Position to another
	 * @param from is the position where the stone must be removed
	 * @param to is the Position where the Stone must be placed
	 */
	void moveStone(Position from, Position to);
	/**
	 * If the Player has a mill, he can take away a stone
	 * @param from is the position where the stone must be removed
	 */
	void takeStone(Position from);
	/**
	 * 
	 */
	void waitForButton();
	/**Returns the name of the Connection as a String
	 * @return the name of the Connection
	 */
	String getName();
}
