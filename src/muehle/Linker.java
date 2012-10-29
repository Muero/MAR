package muehle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import muehle.gui.Frame;
import muehle.gui.camera.Camera;
import muehle.model.Board;
import muehle.model.Board.eColor;
import muehle.model.Position;

public class Linker {
	
	//Objects - Warning! Have to be defined!
	public static Frame frame;															//Generates Constructor of muehle.gui.Frame
	public static Board board;															//Generates Constructor of muehle.model.Board
	
	//Final Fields
	public static final int numberOfStones = 9;											//How many Stones to lay
	public static final Color opponentColor = new Color(255,255,255);					//Color of the virtual Opponent
	public static final Color humanColor = new Color(0,0,0);							//Color of the Human Player
	public static final Dimension guiSize = new Dimension(542, 378);					//Size of the Gui Window
	public static final String[] difficultyNames = {"Easy","Normal","Hard","Insane"};	//Used in Panel0
	
	//Will be generated in Startup-Gui
	public static boolean userobot = false;												//Only Laptop or with Robot
	public static boolean usewebcam = false;											//Webcam or no Webcam
	public static boolean usealgorithm = false;											//RandomPlayer or ComputerPlayer
	public static int difficulty = 0;													//Difficulty of ComputerPlayer.

	//Will be generated in StartGameGui
	public static int alphaValue = 0;													//Value to determine the Field Colors
	public static Point[] fieldPositions = new Point[24];								//Positions on the picture where the colored Pixels are
	public static Point starterPosition = new Point(0,0);								//related Position to Black
	
	//Dynamic
	public static Map<Position,eColor> webcamCluster = new HashMap<Position,eColor>();	//Is used from muehle.gui.camera.Camera
	public static boolean waitForGui = true;											//Waits for a specific GuiMode. The GuiMode will be set in frame.setGuiMode(int mode)
		
	public static void createObjects(){
		board = new Board();
		frame = new Frame(board);
	}
	public static void finish(){
		frame.setGuiMode(1);
		frame.waitFor();
	}
	public static void startupGui(){
		if(usewebcam){
			Camera.generatePlayer();
			Camera.importPictureFromPlayer(frame);
		}else{
			Camera.importPictureFromPC(frame);
		}
		Camera.readColor(frame);
		if(usewebcam)		
			Camera.closePlayer();
		frame.setGuiMode(2);
		frame.waitFor();
	}
}
