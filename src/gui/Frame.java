package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import muehle.Main;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static final int mühlesize = 400;
	public static int step = 0;

	public Panel1 panel1;
	public Panel2 panel2;
	public Panel3 panel3;
	
	public Frame(Dimension size){
		this.setLayout(null);
		this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-size.width/2,0,size.width,size.height);
		panel1 = new Panel1(10,10,this.getWidth()/2-20,this.getHeight()-200);
		panel2 = new Panel2(this.getWidth()/2,10,this.getWidth()/2-30,this.getHeight()-200);
		panel3 = new Panel3(10,this.getHeight()-175,this.getWidth()-44,100);
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.refresh();
				
	}
	
	public void refresh(){
		new Thread(){
			public void run(){
				Main.frame.repaint();
				try{
					Thread.sleep(5);
				}catch(Exception e){}
			}
		}.start();
	}

	public void setGuiMode(int newmode){
		Main.guimode = newmode;
		switch(newmode){
		case 0:
			Main.frame.setVisible(false);
			break;
		case 1:
			Main.frame.setVisible(true);
			Main.frame.panel3.setVisible(true);
			Main.frame.panel2.setVisible(true);
			Main.frame.panel1.setVisible(true);
			break;
		case 2:
			Main.frame.setVisible(true);
			Main.frame.panel3.setVisible(false);
			Main.frame.panel2.setVisible(true);
			Main.frame.panel1.setVisible(true);					
			break;
		}
		Main.frame.repaint();
	}

}
