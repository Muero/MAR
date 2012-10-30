package muehle.gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import muehle.gui.camera.Camera;


public class Panel1 extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public Panel1(){
		this.setLayout(null);
	}
	
	public void paintComponent(Graphics g){
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(Camera.imageBuffer,0,0,this.getWidth(),this.getHeight(),this);
	}

}
