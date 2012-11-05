package muehle.gui.camera;

import muehle.Linker;
import muehle.connection.Connection;
import muehle.gui.Frame;
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
		

	}

	@Override
	public void setColor(eColor color) {
		this.color = color;
		if(color == eColor.BLACK) opposite = eColor.WHITE; else opposite = eColor.BLACK;
	}
	
	@Override
	public Position layStonePosition() {
		int[] old = new int[24];
		for(Position p:Position.getAllPositions()){
			if(Linker.board.getColor(p)==eColor.BLACK)
				old[Frame.getGuiPosition(p)] = 1;
			else if(Linker.board.getColor(p)==eColor.WHITE)
				old[Frame.getGuiPosition(p)] = 2;
			else
				old[Frame.getGuiPosition(p)] = 0;
		}
		
		System.out.println("Lay your stone \n");

		boolean retry = true;
		while (retry) {
			System.out.println("WebCamInput.waitForButton");
			conn.waitForButton();
			
			int[] neu = new int[24];
			
			int stein = -1;
			neu = ImageGrabber.createRawData(Linker.frame,Linker.alphaSize,Linker.alphaValue,Linker.fieldPositions);
			for(int i=0;i<24;i++){
				if(neu[i] != old[i])
					stein = i;
			}
			if(!(stein==-1)){
				retry=false;
			}else{
				System.out.println("NOOOOOOOOT ERKANNT");
			}
			System.out.println(Linker.pressedButton);
			Linker.webcamCluster = neu;
			Linker.pressedButton = stein;
		}
		System.out.println(Linker.pressedButton);
		for(Position p:Position.getAllPositions()){
			if(Linker.pressedButton == Frame.getGuiPosition(p)){
				return p;
			}
		}
		return null;
	}

	@Override
	public Position fromStonePosition() {
		int[] old = new int[24];
		for(Position p:Position.getAllPositions()){
			if(Linker.board.getColor(p)==eColor.BLACK)
				old[Frame.getGuiPosition(p)] = 1;
			else if(Linker.board.getColor(p)==eColor.WHITE)
				old[Frame.getGuiPosition(p)] = 2;
			else
				old[Frame.getGuiPosition(p)] = 0;
		}
		
		System.out.println("Lay your stone \n");

		boolean retry = true;
		while (retry) {
			System.out.println("conn.waitForButton");
			conn.waitForButton();
			
			int[] neu = new int[24];
			
			int stein = -1;
			neu = ImageGrabber.createRawData(Linker.frame,Linker.alphaSize,Linker.alphaValue,Linker.fieldPositions);
			for(int i=0;i<24;i++){
				if(neu[i] != old[i])
					stein = i;
			}
			if(!(stein==-1)){
				retry=false;
			}else{
				System.out.println("NOOOOOOOOT ERKANNT");
			}
			System.out.println(Linker.pressedButton);
			Linker.webcamCluster = neu;
			Linker.pressedButton = stein;
		}
		System.out.println(Linker.pressedButton);
		for(Position p:Position.getAllPositions()){
			if(Linker.pressedButton == Frame.getGuiPosition(p)){
				return p;
			}
		}
		return null;
}

	@Override
	public Position toStonePosition(Position inputPositionFrom) {
		int[] old = new int[24];
		for(Position p:Position.getAllPositions()){
			if(Linker.board.getColor(p)==eColor.BLACK)
				old[Frame.getGuiPosition(p)] = 1;
			else if(Linker.board.getColor(p)==eColor.WHITE)
				old[Frame.getGuiPosition(p)] = 2;
			else
				old[Frame.getGuiPosition(p)] = 0;
		}
		
		System.out.println("Lay your stone \n");

		boolean retry = true;
		while (retry) {
			System.out.println("conn.waitForButton");
			conn.waitForButton();
			
			int[] neu = new int[24];
			
			int stein = -1;
			neu = ImageGrabber.createRawData(Linker.frame,Linker.alphaSize,Linker.alphaValue,Linker.fieldPositions);
			for(int i=0;i<24;i++){
				if(neu[i] != old[i])
					stein = i;
			}
			if(!(stein==-1)){
				retry=false;
			}else{
				System.out.println("NOOOOOOOOT ERKANNT");
			}
			System.out.println(Linker.pressedButton);
			Linker.webcamCluster = neu;
			Linker.pressedButton = stein;
		}
		System.out.println(Linker.pressedButton);
		for(Position p:Position.getAllPositions()){
			if(Linker.pressedButton == Frame.getGuiPosition(p)){
				return p;
			}
		}
		return null;
	}

	@Override
	public Position takeStonePosition() {
		int[] old = new int[24];
		for(Position p:Position.getAllPositions()){
			if(Linker.board.getColor(p)==eColor.BLACK)
				old[Frame.getGuiPosition(p)] = 1;
			else if(Linker.board.getColor(p)==eColor.WHITE)
				old[Frame.getGuiPosition(p)] = 2;
			else
				old[Frame.getGuiPosition(p)] = 0;
		}
		
		System.out.println("Lay your stone \n");

		boolean retry = true;
		while (retry) {
			System.out.println("conn.waitForButton");
			conn.waitForButton();
			
			int[] neu = new int[24];
			
			int stein = -1;
			neu = ImageGrabber.createRawData(Linker.frame,Linker.alphaSize,Linker.alphaValue,Linker.fieldPositions);
			for(int i=0;i<24;i++){
				if(neu[i] != old[i])
					stein = i;
			}
			if(!(stein==-1)){
				retry=false;
			}else{
				System.out.println("NOOOOOOOOT ERKANNT");
			}
			System.out.println(Linker.pressedButton);
			Linker.webcamCluster = neu;
			Linker.pressedButton = stein;
		}
		System.out.println(Linker.pressedButton);
		for(Position p:Position.getAllPositions()){
			if(Linker.pressedButton == Frame.getGuiPosition(p)){
				return p;
			}
		}
		return null;
	}
}
