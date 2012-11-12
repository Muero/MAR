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
	public static Connection conn1;														//Connections can be BTConnection or EmptyConnection, if NXT's are used or not
	public static Connection conn2;														//Same for this Connection
	public static NineMenMorrisPlayer player1;											//Default is Human
	public static NineMenMorrisPlayer player2;											//Default is Robot
	public static HumanPositionInput input1 = null;										//Can be ButtonInput or WebcamInput. Is defined in setupGamePlay()
	public static HumanPositionInput input2 = null;
	
	//Final Fields
	public static final int numberOfStones = 5;											//How many Stones to lay
	public static final Color opponentColor = new Color(255,0,0);						//Color of the Human Player
	public static final Color humanColor = new Color(0,0,255);							//Color of the OpponentPlayer
	public static final Dimension guiSize = new Dimension(542, 378);					//Size of the Gui Window
	public static final String[] difficultyNames = {"Easy","Normal","Hard","Insane"};	//Used in Panel0
	public static final String[] modes = 												//DropupMenu in the StartupGui
		{"Man","Machine","Computer"};
	public static final Color[] probabilityColor = 										//Sets up Colors for different Probabilities
	   {new Color(255,0,0),new Color(225,25,0),
		new Color(200,50,0),new Color(175,75,0),
		new Color(150,100,0),new Color(125,125,0),
		new Color(100,150,0),new Color(75,175,0),
		new Color(50,200,0),new Color(0,255,0),
		new Color(255,255,255)};

	//Will be generated in Startup-Gui
	public static String name1 = "Name 1";
	public static String name2 = "Name 2";
	public static int mode1 = 0;
	public static int mode2 = 0;
	public static boolean usewebcam1 = false;											//Webcam or no Webcam
	public static boolean usewebcam2 = false;
	public static boolean usealgorithm1 = false;											//RandomPlayer or ComputerPlayer
	public static boolean usealgorithm2 = false;
	public static int difficulty1 = 2;													//Difficulty of ComputerPlayer.
	public static int difficulty2 = 2;
	public static int difficulty = 2;
	public static boolean done = false;													//Determines if Startup-Gui is finished or not
	
	//Will be generated in StartGameGui
	public static int alphaValue = 0;													//Value to determine the Field Colors
	public static int alphaSize = 20;													//Size of the PixelCluster the Algorithm uses to determine the color median
	public static Point[] fieldPositions = new Point[24];								//Positions on the picture where the colored Pixels are
	public static Point starterPosition = new Point(0,0);								//related Position to Black
	
	//Dynamic
	public static Map<Position, Integer> probability = new HashMap<Position, Integer>();//Probabilities of all Positions on the Mühlefield
	public static int[] webcamCluster = new int[24];									//Is used from muehle.gui.camera.WebCamInput
	public static boolean waitForGui = true;											//Waits for a specific GuiMode. The GuiMode will be set in frame.setGuiMode(int mode)
	public static int pressedButton = -1;												//If ButtonInput is used, this method will not be -1 if a button is pressed
	public static boolean allowRepaint = true;											//During the camera updates the picture it should not be pained because it generates Nullpointer Exceptions
	public static boolean takePicture = false;											//True if camera takes a picture
	
	
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
		
		//Connection 1
		if(mode1 == 0){
			conn1 = new EmptyConnection();
		}else if(mode1 == 1){
			if(usewebcam1)
				conn1 = new BTConnection();
			else
				conn1 = new EmptyConnection();
		}else{
			conn1 = new EmptyConnection();
		}
		conn1.openConnection();

		//Connection 2
		if(mode2 == 0){
			conn2 = new EmptyConnection();
		}else if(mode2 == 1){
			if(usewebcam2)
				conn2 = new BTConnection();
			else
				conn2 = new EmptyConnection();
		}else{
			conn2 = new EmptyConnection();
		}
		conn2.openConnection();
		
		//If Webcam is in use
		if(usewebcam1) {
			input1 = new WebCamInput(board,conn1);
			
			System.out.println(conn1.getName());
			
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
			input1 = new ButtonInput(board, frame.panel4);
		}
		if(usewebcam2) {
			input2 = new WebCamInput(board,conn2);
			
			System.out.println(conn2.getName());
			
			
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
			input2 = new ButtonInput(board, frame.panel4);
		}

		if(mode1 == 0){
			player1 = new HumanPlayer(name1,conn1,input1);			
		}else{
			if(usealgorithm1)
				player1 = new ComputerPlayer(name1);
			else
				player1 = new RandomPlayer();
		}		
		if(mode2 == 0){
			player2 = new HumanPlayer(name2,conn2,input2);			
		}else{
			if(usealgorithm2)
				player2 = new ComputerPlayer(name2);
			else
				player2 = new RandomPlayer();
		}
		
		
		if(usealgorithm1)
			difficulty = difficulty1;
		else
			difficulty = difficulty2;
		
		
		//Choose who starts
		if (new Random().nextBoolean()) {
			Connection c = conn1;
			conn1 = conn2;
			conn2 = c;
			NineMenMorrisPlayer p = player1;
			player1 = player2;
			player2 = p;
			String s = name1;
			name1 = name2;
			name2 = s;
		}
		
		
	}
	
	public static void startupGui(){
		if(usewebcam1){
			frame.setGuiMode(2);
			frame.waitFor();
			frame.setGuiMode(3);
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
		System.exit(0);
	}
}
