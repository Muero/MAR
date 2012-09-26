package muehle;

import gui.Frame;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import camera.Camera;
import muehle.Board.eColor;
import muehle.bt.BTConnection;
//test
public class Main {

	public static final int depth = 2; // playing ability
	public static boolean usewebcam = false;
	public static boolean userobot = false;
	public static boolean usealgorithm = false;
	public static int difficulty = 0;
	public static int guimode = 1;
	public static Dimension size = new Dimension(542,378);

	public static Frame frame = new Frame();

	
	
	
	
	public static void main(String[] args) {
		Connection conn;
		//---------------------------------------------------------------------
		
	
		if(usewebcam){
			Camera.generatePlayer();
			Camera.importPictureFromPlayer(frame);
		}else{
			Camera.importPictureFromPC(frame);
		}
		Camera.readColor(frame);
		if(usewebcam)		
			Camera.closePlayer();


		frame.setGuiMode(1);
		frame.waitForStart();
		frame.setGuiMode(2);
		
		
		gui.Output.create();
		
		
		
		userobot = frame.panel3.isPressed(2);
		usewebcam = frame.panel3.isPressed(3);
		usealgorithm = frame.panel3.isPressed(4);
		
		if(userobot)
			conn = new BTConnection();		//with robot
		else
			conn = new EmptyConnection();	//without robot
		conn.openConnection();

		
					
				
		//---------------------------------------------------------------------

		Board board = new Board();
		BoardPanel panel = new BoardPanel();

//		JFrame mainWindow = initializeGui(panel, conn);
//		mainWindow.pack();
//		mainWindow.setVisible(true);

		Play.lay(board, panel, depth, conn); // first phase: placing the stones
		
		System.out.println("*********************************** \n" +
				" It have been placed all the stones \n");
		
		Play.move(board, panel, depth, conn); // second phase: moving & junmping the stones

		if (board.getNumberOfStones(eColor.BLACK) < 3)
			System.out.println("You win!");
		if (board.getNumberOfStones(eColor.WHITE) < 3)
			System.out.println("You lost!");
		System.out.println("***********************************");
		panel.repaint();
		System.out.println(board);
		conn.closeConnection();
	}

	private static JFrame initializeGui(JPanel panel, final Connection conn) {
		JFrame frame = new JFrame("Muehle");
		frame.setAlwaysOnTop(true);
		frame.add(panel);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				conn.closeConnection();
				System.exit(0);
			}
		});
		
		
		
		
		
		return frame;
	}

}
