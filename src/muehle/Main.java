package muehle;

import gui.ComputerFrame;
import gui.Frame;
import gui.Output;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import muehle.Board.eColor;
import muehle.bt.BTConnection;
//test
public class Main {

	public static final int depth = 2; // playing ability
	public static int guimode = 1;
	public static Dimension size = new Dimension(542,378);

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
}
