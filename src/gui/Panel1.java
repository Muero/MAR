package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import muehle.Main;

public class Panel1 extends JPanel{
	private static final long serialVersionUID = 1L;

	Color c = new Color(0,0,0);
	
	public Panel1(int x,int y,int width,int height){
		this.setBounds(x,y,width,height);
		this.setLayout(null);
		
	}
	
	public void paintComponent(Graphics g){
		if(Main.guimode == 1){
			g.drawImage(Main.img,0,0,Main.img.getWidth(this),Main.img.getHeight(this),this);
			g.setColor(c);
		}else{
			g.setColor(Color.lightGray);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.black);

			g.fillRect(10, 10, this.getWidth()-20,10);
			g.fillRect(10,this.getHeight()-20,this.getWidth()-20,10);
			g.fillRect(10,10,10,this.getHeight()-20);
			g.fillRect(this.getWidth()-20,10,10,this.getHeight()-20);
			
			g.fillRect(100,100,this.getWidth()-200,10);
			g.fillRect(100,this.getHeight()-110,this.getWidth()-200,10);
			g.fillRect(100, 100, 10, this.getHeight()-200);
			g.fillRect(this.getWidth()-110,100,10,this.getHeight()-200);
			
			g.fillRect(200, 200, this.getWidth()-400, 10);
			g.fillRect(200,this.getHeight()-210,this.getWidth()-400,10);
//			g.fillRect()
			
		}
		g.setColor(Color.black);
		for(int i=0;i<5;i++)
			g.drawRect(i,i,this.getWidth()-2*i,this.getHeight()-2*i);

	}
}
