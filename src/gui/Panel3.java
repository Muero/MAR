package gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import muehle.Main;

public class Panel3 extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public static int pos = 100;

	JScrollBar sb = new JScrollBar();
	
	public Panel3(int x,int y,int width,int height){
			this.setBackground(Color.blue);
			this.setBounds(x,y,width,height);
			this.setLayout(null);
			sb.setBounds(0,0,this.getWidth(),this.getHeight());
			sb.setOrientation(0);
			sb.setMaximum(265);
			sb.setMinimum(0);
			sb.setValue(255);
			this.add(sb);
			thread();			
	}	
		
	public static int getValue(){
		return pos;
	}
	
	public void thread(){
		new Thread(){
			public void run(){
				while(true){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(Main.guimode == 1){
						pos = sb.getValue();
					}
				}
			}
		}.start();
	}
	
}
