package gui;

import java.awt.Point;

import muehle.Main;

public class Output {
	
	public static Point[] fieldPositions = new Point[24];
	public static int alphaValue = 0;
	public static Point starterPosition = new Point(0,0);
	
	public static int difficulty = 0;
	
	public static boolean userobot = false;
	public static boolean usewebcam = false;
	public static boolean usealgorithm = false;
	
	
	public static void create(){
		for(int i=0;i<24;i++){
			fieldPositions[i] = new Point(Main.frame.panel2.getFieldPosition(i));
		}
		alphaValue = Main.frame.panel3.getValue(1);
		starterPosition = new Point(Main.frame.panel2.getStarterPosition());

		difficulty = Main.frame.panel3.getValue(2);
		
		userobot = Main.frame.panel3.isPressed(2);
		usewebcam = Main.frame.panel3.isPressed(3);
		usealgorithm = Main.frame.panel3.isPressed(4);
	}
	

}
