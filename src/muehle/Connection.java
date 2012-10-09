package muehle;

public interface Connection {
	void openConnection();
	void closeConnection();
	void setStone(Position to);
	void moveStone(Position from, Position to); //TODO braucht man nicht
	void takeStone(Position from);
}
