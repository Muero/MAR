package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class cPanel1 extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public cPanel1(){
		this.setLayout(null);
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.orange);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}