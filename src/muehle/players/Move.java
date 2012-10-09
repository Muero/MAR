package muehle.players;

import muehle.Position;

public final class Move {
	public Position from;
	public Position to;
	public Position take;

	public Move(Position from, Position to, Position take) {
		this.from = from;
		this.take = take;
		this.to = to;
	}

	public Position getFrom() {
		return from;
	}

	public Position getTo() {
		return to;
	}

	public Position getTake() {
		return take;
	}
}
