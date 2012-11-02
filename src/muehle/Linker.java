package muehle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import muehle.connection.BTConnection;
import muehle.connection.Connection;
import muehle.connection.EmptyConnection;
import muehle.gui.ButtonInput;
import muehle.gui.Frame;
import muehle.gui.camera.ImageGrabber;
import muehle.gui.camera.WebCamInput;
import muehle.model.Board;
import muehle.model.Position;
import muehle.players.NineMenMorrisPlayer;
import muehle.players.computer.ComputerPlayer;
import muehle.players.human.HumanPlayer;
import muehle.players.human.HumanPositionInput;
import muehle.players.random.RandomPlayer;

public class Linker {
	
	//Objects - Warning! Have to be defined!
	public static Frame frame;															//Generates Constructor of muehle.gui.Frame
	public static Board board;															//Generates Constructor of muehle.model.Board
	public static Connection conn1;
	public static Connection conn2;
	public static NineMenMorrisPlayer player1;											//Default is Human
	public static NineMenMorrisPlayer player2;											//Default is Robot
	public static HumanPositionInput input = null;										//lol
	
	//Final Fields
	public static final int numberOfStones = 5;											//How many Stones to lay
	public static final Color opponentColor = new Color(255,0,0);						//Color of the Human Player
	public static final Color humanColor = new Color(0,0,255);							//Color of the OpponentPlayer
	public static final Dimension guiSize = new Dimension(542, 378);					//Size of the Gui Window
	public static final String[] difficultyNames = {"Easy","Normal","Hard","Insane"};	//Used in Panel0
	public static final String[] modes = 
		{"   Robot - Human",
		 "Computer - Human",
		 "   Robot - Robot",
		 "   Human - Human"};
	public static final Color[] probabilityColor = 										//Sets up Colors for different Probabilities
	   {new Color(255,0,0),new Color(225,25,0),
		new Color(200,50,0),new Color(175,75,0),
		new Color(150,100,0),new Color(125,125,0),
		new Color(100,150,0),new Color(75,175,0),
		new Color(50,200,0),new Color(0,255,0),
		new Color(255,255,255)};

	//Will be generated in Startup-Gui
	public static boolean usewebcam = false;											//Webcam or no Webcam
	public static boolean usealgorithm = false;											//RandomPlayer or ComputerPlayer
	public static int difficulty = 2;													//Difficulty of ComputerPlayer.
	public static int robotMode = 0;
	public static boolean done = false;
	
	//Will be generated in StartGameGui
	public static int alphaValue = 0;													//Value to determine the Field Colors
	public static int alphaSize = 20;
	public static Point[] fieldPositions = new Point[24];								//Positions on the picture where the colored Pixels are
	public static Point starterPosition = new Point(0,0);								//related Position to Black
	
	//Dynamic
	public static Map<Position, Integer> probability = new HashMap<Position, Integer>();
	public static int[] webcamCluster = new int[24];									//Is used from muehle.gui.camera.WebCamInput
	public static boolean waitForGui = true;											//Waits for a specific GuiMode. The GuiMode will be set in frame.setGuiMode(int mode)
	public static int pressedButton = -1;
	public static boolean allowRepaint = true;
	public static boolean takePicture = false;
	
	public static void createObjects(){
		board = new Board();
		frame = new Frame(board);
	}
	public static void finish(){
		frame.setGuiMode(1);
		frame.waitFor();
	}
	public static void setupGamePlay(){
		
		//SetUp Connections to NXT's
		if(robotMode == 0){			//Robot - Human
			conn1 = new BTConnection();
			conn2 = new EmptyConnection();
		}else if(robotMode == 1){	//Computer - Human
			conn1 = new EmptyConnection();
			conn2 = new EmptyConnection();
		}else if(robotMode == 2){	//Robot - Robot
			conn1 = new BTConnection();
			conn2 = new BTConnection();
		}else if(robotMode == 3){	//Human - Human
			conn1 = new EmptyConnection();
			conn2 = new EmptyConnection();
		}
		conn1.openConnection();
		conn2.openConnection();
		
		//If Webcam is in use
		if(usewebcam) {
			input = new WebCamInput(board, conn1);
			ImageGrabber.startImaging();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ImageGrabber.takePicture();
			ImageGrabber.importPicture(frame);
			ImageGrabber.readColor(frame);			
		} else {
			input = new ButtonInput(board, frame.panel4);
		}
		
		//Define Players
		player1 = new HumanPlayer("Patrick",conn1,input);
		if(usealgorithm){
			player2 = new ComputerPlayer("MüRo");
		}else{
			player2 = new RandomPlayer();
		}
		
		//Choose who starts
		if (new Random().nextBoolean()) {
			Connection c = conn1;
			conn1 = conn2;
			conn2 = c;
			NineMenMorrisPlayer p = player1;
			player1 = player2;
			player2 = p;
		}
		
		
	}
	public static void startupGui(){
		
		
		if(usewebcam){
			frame.setGuiMode(2);
			frame.waitFor();
		}else{
			frame.setGuiMode(4);
		}
	}

	public static void play(){
		Main.play(board, frame.panel4, numberOfStones, player1, player2, conn1, conn2);
	}

	public static void closeGame(){
		conn1.closeConnection();
		conn2.closeConnection();
		frame.stop();
	}
}
