package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import muehle.Main;

public class Panel2 extends JPanel{
	private static final long serialVersionUID = 1L;

	
	private Point alpha = new Point(0,0);
	private int sensitivity = 50;
	
	public Panel2(int x,int y,int width, int height){
		this.setBounds(x,y,width,height);
		this.setLayout(null);
		this.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				alpha.x = arg0.getX();
				alpha.y = arg0.getY();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		thread();
	}
	
	public void paintComponent(Graphics g){
		if(Main.guimode == 1){
			g.fillRect(0,0,Main.img.getWidth(),Main.img.getHeight());

			for(int i=0;i<Main.img.getWidth();i++){
				for(int j=0;j<Main.img.getHeight();j++){
					g.setColor(new Color(Main.imgcolor[i][j].getRed(),Main.imgcolor[i][j].getGreen(),Main.imgcolor[i][j].getBlue(),Panel3.getValue()));
					if(this.checkStones(Main.imgcolor[i][j].getRed(),Main.imgcolor[i][j].getGreen(),Main.imgcolor[i][j].getBlue(),i,j))
						g.setColor(new Color(Main.imgcolor[i][j].getRed(),Main.imgcolor[i][j].getGreen(),Main.imgcolor[i][j].getBlue(),255));
					g.drawLine(i, j, i, j);
					
				}
			}
			g.setColor(Color.black);
			for(int i=0;i<5;i++)
				g.drawRect(i,i,Main.img.getWidth()-2*i,Main.img.getHeight()-2*i);			
		}else{
			
		}
		
	}

	private void thread(){
		new Thread(){
			public void run(){
				while(true){
					Main.frame.repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public boolean checkStones(int r,int g,int b,int i,int j){
		if(!(alpha.x == 0 && alpha.y == 0)){
			int ra = Main.imgcolor[alpha.x][alpha.y].getRed();
			int ga = Main.imgcolor[alpha.x][alpha.y].getGreen();
			int ba = Main.imgcolor[alpha.x][alpha.y].getBlue();
			if(Math.abs(r-ra)<sensitivity&&Math.abs(g-ga)<sensitivity&&Math.abs(b-ba)<sensitivity)
				return true;
			else 
				return false;
		}else
			return false;
	}
}
