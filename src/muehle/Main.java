package muehle;

import gui.ComputerFrame;
import gui.Frame;
import gui.Output;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
//<<<<<<< HEAD
//
//import muehle.Board.eColor;
//import camera.Camera;
////test
//public class Main {
//
//	public static final int depth = 4; // playing ability
//	public static final int numberOfStones = 6; //With how many stones u will play
//	public static final boolean usewebcam = false;
//	public static final boolean userobot = false;
//	public static String file = "field2";
//	public static String fileEnding = "jpg";
//	public static BufferedImage img;
//	public static Color[][] imgcolor;
//	public static int guimode = 0;
//	public static Dimension size = new Dimension(1600,800);
//
//	public static Frame frame = new Frame(size);
//=======
import muehle.Board.eColor;
import muehle.bt.BTConnection;
//test
public class Main {

    public static final int numberOfStones = 6; //With how many stones u will play

	
	public static final int depth = 2; // playing ability
	public static int guimode = 1;
	public static Dimension size = new Dimension(542,378);
//>>>>>>> d341048f8c73be852c0180743cce9a43a60cf19f

	public static Frame frame = new Frame();
	public static ComputerFrame cframe = new ComputerFrame();
	
	
	
	public static void main(String[] args){
		//---------------------------------------------------------------------
	
		gui.Input.startGui(frame);
		gui.Output.create();
		gui.Input.startIngameGui(frame);
		
		
		
		Connection conn;
		if(Output.userobot)
			conn = new BTConnection();		//with robot
		else
			conn = new EmptyConnection();	//without robot
		conn.openConnection();

		
					
				
		//---------------------------------------------------------------------
	
		Board board = new Board();
		BoardPanel panel = new BoardPanel();

//<<<<<<< HEAD
//		JFrame mainWindow = initializeGui(panel, conn);
//		mainWindow.pack();
//		mainWindow.setVisible(true);
//
		Play.lay(board, panel, depth, conn, numberOfStones); // first phase: placing the stones
//=======
//		Play.lay(board, panel, depth, conn); // first phase: placing the stones
//>>>>>>> d341048f8c73be852c0180743cce9a43a60cf19f
		
		System.out.println("*********************************** \n" +
				" It have been placed all the stones \n");
		
		Play.move(board, panel, depth, conn); // second phase: moving & junmping the stones

		if (board.getNumberOfStones(eColor.BLACK) < 3 || board.getStuck(eColor.WHITE, eColor.BLACK))
			System.out.println("You win!");
		if (board.getNumberOfStones(eColor.WHITE) < 3 || board.getStuck(eColor.BLACK, eColor.WHITE))
			System.out.println("You lost!");
		System.out.println("***********************************");
		panel.repaint();
		System.out.println(board);
		conn.closeConnection();
	}
}
