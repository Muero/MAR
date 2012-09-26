package gui;

import java.awt.Color;
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
	
	public static Color robotColor = new Color(255,255,0);
	public static Color humanColor = new Color(0,0,0);
	public static Color[] probabilityColor = {new Color(255,0,0),new Color(225,25,0),new Color(200,50,0),new Color(175,75,0),new Color(150,100,0),new Color(125,125,0),new Color(100,150,0),new Color(75,175,0),new Color(50,200,0),new Color(0,255,0)};
	
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
