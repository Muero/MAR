package muehle.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import muehle.Main;
import muehle.gui.camera.Camera;

public class Panel2 extends JPanel{
	private static final long serialVersionUID = 1L;
		
	private int a=0;
	private int b=0;
	private boolean mouseClicked = false;
	private Point mousePosition = new Point(-10,-10);
	private Point starterPosition = new Point(-10,-10);
	private Point[] positions = new Point[24];
	private int index = 0;
	
	public Panel2(){
		for(int i=0;i<24;i++)
			positions[i] = new Point(-10,-10);
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				mouseClicked = true;
				mousePosition = new Point(arg0.getX(),arg0.getY());
			}
		});
	}
	
	public int getPositionIndex(){
		return index;
	}
	
	public boolean mouseClicked(){
		return mouseClicked;
	}
	public void setMouseClick(boolean click){
		mouseClicked = click;
	}
	public Point getMousePosition(){
		return mousePosition;
	}
	public Point getStarterPosition(){
		return starterPosition;
	}
	public void setStarterPosition(Point start){
		this.starterPosition = new Point(start);
	}
	public void setFieldPosition(Point field){
		if(index<24){
			positions[index] = new Point(field);
			this.index++;
		}
	}
	public Point getFieldPosition(int a){
		return positions[a];
	}
	public void clearLastFieldPosition(){
		if(index>0){
			positions[index-1] = new Point(-10,-10);
			System.out.println(positions[index-1]);
			System.out.println(index);
			this.index--;
		}
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i=0;i<Camera.imageColor.length;i++){
			for(int j=0;j<Camera.imageColor[0].length;j++){
				if(!alphacheck(Camera.imageColor[i][j]))
					g.setColor(new Color(Camera.imageColor[i][j].getRed(),Camera.imageColor[i][j].getGreen(),Camera.imageColor[i][j].getBlue(),Main.frame.panel3.getValue(0)));
				else
					g.setColor(Camera.imageColor[i][j]);
				g.drawLine(i-a, j-b, i-a, j-b);
			}
		}
		g.setColor(Color.blue);
		g.fillRect(getStarterPosition().x-5,getStarterPosition().y-5,10,10);
		
		for(int i=0;i<24;i++){
			g.setColor(Color.red);
			g.fillRect(getFieldPosition(i).x-5,getFieldPosition(i).y-5,10,10);
		}
		
	}

	private boolean alphacheck(Color c){
		if(Main.frame.panel3.isStarterSet()){
			Color alphapixel = Camera.imageColor[starterPosition.x][starterPosition.y];
			int r = Math.abs(c.getRed()-alphapixel.getRed());
			int g = Math.abs(c.getGreen()-alphapixel.getGreen());
			int b = Math.abs(c.getBlue()-alphapixel.getBlue());
				
			int a = Main.frame.panel3.getValue(1);
		
			return r<a && g<a && b<a;
		}else{
			return false;
		}

	}
	
}
