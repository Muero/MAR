package muehle.gui.camera;

import static muehle.model.Board.eColor.NONE;
import muehle.Main;
import muehle.connection.Connection;
import muehle.gui.Output;
import muehle.gui.cPanel1;
import muehle.model.Board;
import muehle.model.Board.eColor;
import muehle.model.Position;
import muehle.players.human.HumanPositionInput;

public class WebCamInput implements HumanPositionInput {
	private Board board;
	private Connection conn;
	private eColor color;
	private eColor opposite;

	public WebCamInput(Board board, Connection conn) {
		this.board = board;
		this.conn = conn;
		
		Output.cluster = Camera.createPlayerFieldClusterFromWebcamImage(
				Main.frame, Camera.imageBuffer, Output.fieldPositions,
				Output.alphaValue, Output.alphaSize);

	}

	@Override
	public void setColor(eColor color) {
		this.color = color;
		if(color == eColor.BLACK) opposite = eColor.WHITE; else opposite = eColor.BLACK;
	}
	
	@Override
	public Position layStonePosition() {
		System.out.println("Lay your stone \n");
		
		Position inputPosition = null;
		
		boolean retry = true;
		while (retry) {
			conn.waitForButton();

			boolean[] vorher = Output.cluster;
			boolean[] nachher = new boolean[24];
			for (int k = 0; k < 24; k++) {
				nachher[k] = true;
			}
			nachher = Camera.createPlayerFieldClusterFromWebcamImage(
					Main.frame, Camera.imageBuffer, Output.fieldPositions,
					Output.alphaValue, Output.alphaSize);
			int stone = -1;
			for (int j = 0; j < 24; j++) {
				if (vorher[j] != nachher[j]) {
					stone = j;
					break;
				}
			}
			if (stone != -1) {
				for (Position p : Position.getAllPositions()) {
					System.err.println(p);
					if (cPanel1.getButtonId(p) == stone) {
						inputPosition = p;
						System.out.println("inputPosition = "
								+ inputPosition.getId());
						if (board.getColor(inputPosition) == NONE) {
							retry = false;
						}
						break;
					}
				}
			}
			Output.cluster = nachher;
		}
		return inputPosition;
	}

	@Override
	public Position fromStonePosition() {
		System.out.println("Your next move? \n" + "From where?");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position toStonePosition(Position inputPositionFrom) {
		System.out.println("Whereto ?");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position takeStonePosition() {
		System.out.println("You have mill, which stone take you away?");
		
		Position takePosition = null;
		conn.waitForButton();
		takePosition = null; // null entspricht p96
		boolean[] vorher = Output.cluster;
		boolean[] nachher = Camera
				.createPlayerFieldClusterFromWebcamImage(Main.frame,
						Camera.imageBuffer, Output.fieldPositions,
						Output.alphaValue, Output.alphaSize);
		int stone = -1;
		for (int j = 0; j < 24; j++) {
			if (vorher[j] != nachher[j]) {
				stone = j;
				break;
			}
		}
		if (stone != -1) {
			System.err.println(stone);
			for (Position p : Position.getAllPositions()) {
				if (cPanel1.getButtonId(p) == stone) {
					takePosition = p;
					break;
				}
			}
		}
		// FIXME was wenn stone hier == -1 ist, also falls kein Unterschied zwischen vorher und nachher?
		// 
		Output.cluster = nachher;
		return takePosition;
	}



}
