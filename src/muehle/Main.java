package muehle;

import gui.ComputerFrame;
import gui.Frame;
import gui.Output;

import java.awt.Dimension;

import muehle.Board.eColor;
import muehle.bt.BTConnection;

public class Main {

    public static final int numberOfStones = 6; //With how many stones u will play

	
	public static final int depth = 2; // playing ability
	public static int guimode = 1;

	public static Dimension size = new Dimension(542,378);


	public static Frame frame = new Frame();
	public static ComputerFrame cframe = new ComputerFrame();
	
	
	
	public static void main(String[] args){
		gui.Input.startGui(frame);
		gui.Output.create();
		gui.Input.startIngameGui(frame);
		
		Connection conn;
		if(Output.userobot)
			conn = new BTConnection();		//with robot
		else
			conn = new EmptyConnection();	//without robot
		conn.openConnection();

		Board board = new Board();
		BoardPanel panel = new BoardPanel();

		System.out.println("*************************** \n"
				+ "Welcome to the game Nine Men Morris !! \n \n");
		
		Play.play(board, panel, conn, numberOfStones); // first phase: placing the stones
		
		conn.closeConnection();
	}
}
