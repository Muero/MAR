package muehle;

import gui.Frame;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import camera.Camera;

import java.awt.Color;
import muehle.Board.eColor;
//test
public class Main {

	public static final int depth = 2; // playing ability
	public static final boolean usewebcam = true;
	public static final boolean userobot = false;
	public static String file = "field";
	public static String fileEnding = "jpg";
	public static BufferedImage img;
	public static Color[][] imgcolor;
	public static int guimode = 0;
	public static Dimension size = new Dimension(1600,800);

	public static Frame frame = new Frame(size);

	
	
	
	
	public static void main(String[] args) {
		Connection conn;
		//conn = new BTConnection();		//with robot
		conn = new EmptyConnection();		//without robot
		conn.openConnection();
		//---------------------------------------------------------------------
		
				if(usewebcam)

					Camera.generatePlayer();
				if(usewebcam)
					Camera.importPicture(file,fileEnding);
				Camera.readColor();

					frame.setGuiMode(1);
					if(usewebcam)
						
						Camera.closePlayer();
							
					
				
		//---------------------------------------------------------------------

		Board board = new Board();
		BoardPanel panel = new BoardPanel();

		JFrame mainWindow = initializeGui(panel, conn);
		mainWindow.pack();
		mainWindow.setVisible(true);

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
