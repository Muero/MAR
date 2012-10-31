package muehle.gui.camera;

import muehle.Linker;
import muehle.connection.Connection;
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

		System.out.println("Lay your stone \n");

		boolean retry = true;
		while (retry) {
			conn.waitForButton();
			
			boolean[] old = new boolean[24];
			boolean[] neu = new boolean[24];
			
			int stein = -1;
			old = Linker.webcamCluster;
			neu = Camera.createPlayerFieldClusterFromWebcamImage(Linker.frame,Camera.imageBuffer,Linker.fieldPositions,Linker.alphaValue,Linker.alphaSize);
			for(int i=0;i<24;i++){
				if(neu[i] != old[i])
					stein = i;
			}
			if(!(stein==-1)){
				retry=false;
			}else{
				System.out.println("NOOOOOOOOT ERKANNT");
			}
				
			Linker.webcamCluster = neu;
			Linker.pressedButton = stein;		
		

		}
		for(Position p:Position.getAllPositions()){
			if(Linker.pressedButton == p.getId())
				return p;
		}
		return null;
	}

	@Override
	public Position fromStonePosition() {
		System.out.println("Your next move? \n" + "From where?");
		System.out.println("Lay your stone \n");

		boolean retry = true;
		while (retry) {
			conn.waitForButton();
			
			boolean[] old = new boolean[24];
			boolean[] neu = new boolean[24];
			
			int stein = -1;
			old = Linker.webcamCluster;
			neu = Camera.createPlayerFieldClusterFromWebcamImage(Linker.frame,Camera.imageBuffer,Linker.fieldPositions,Linker.alphaValue,Linker.alphaSize);
			for(int i=0;i<24;i++){
				if(neu[i] != old[i])
					stein = i;
			}
			if(!(stein==-1)){
				retry=false;
			}else{
				System.out.println("NOOOOOOOOT ERKANNT");
			}
				
			Linker.webcamCluster = neu;
			Linker.pressedButton = stein;		
		

		}
		for(Position p:Position.getAllPositions()){
			if(Linker.pressedButton == p.getId())
				return p;
		}
		return null;
}

	@Override
	public Position toStonePosition(Position inputPositionFrom) {
		System.out.println("Whereto ?");
		boolean retry = true;
		while (retry) {
			conn.waitForButton();
			
			boolean[] old = new boolean[24];
			boolean[] neu = new boolean[24];
			
			int stein = -1;
			old = Linker.webcamCluster;
			neu = Camera.createPlayerFieldClusterFromWebcamImage(Linker.frame,Camera.imageBuffer,Linker.fieldPositions,Linker.alphaValue,Linker.alphaSize);
			for(int i=0;i<24;i++){
				if(neu[i] != old[i])
					stein = i;
			}
			if(!(stein==-1)){
				retry=false;
			}else{
				System.out.println("NOOOOOOOOT ERKANNT");
			}
				
			Linker.webcamCluster = neu;
			Linker.pressedButton = stein;		
		

		}
		for(Position p:Position.getAllPositions()){
			if(Linker.pressedButton == p.getId())
				return p;
		}
		return null;
	}

	@Override
	public Position takeStonePosition() {
		
		boolean retry = true;
		while (retry) {
			conn.waitForButton();
			
			boolean[] old = new boolean[24];
			boolean[] neu = new boolean[24];
			
			int stein = -1;
			old = Linker.webcamCluster;
			neu = Camera.createPlayerFieldClusterFromWebcamImage(Linker.frame,Camera.imageBuffer,Linker.fieldPositions,Linker.alphaValue,Linker.alphaSize);
			for(int i=0;i<24;i++){
				if(neu[i] != old[i])
					stein = i;
			}
			if(!(stein==-1)){
				retry=false;
			}else{
				System.out.println("NOOOOOOOOT ERKANNT");
			}
				
			Linker.webcamCluster = neu;
			Linker.pressedButton = stein;		
		

		}
		for(Position p:Position.getAllPositions()){
			if(Linker.pressedButton == p.getId())
				return p;
		}
		return null;

	}

}
