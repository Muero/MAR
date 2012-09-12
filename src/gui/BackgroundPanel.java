package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public BackgroundPanel(int x,int y,int width,int height,int defaultheight){
		this.setBounds(x,y,width,height);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	}

	
}
