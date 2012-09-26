package gui;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

import muehle.Main;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static final int mühlesize = 400;
	public static int step = 0;
	
	public Panel1 panel1 = new Panel1();
	public Panel2 panel2 = new Panel2();
	public Panel3 panel3 = new Panel3();
		
	private int ready = 0;
	
	public Frame(){
		addComponentsToPane(this.getContentPane());
		refresh();
	}
	
	public int getReadyValue(){
		return ready;
	}
	private void addComponentsToPane(Container pane){
		pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
				
		
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.ipadx = 200;
			c.ipady = 200;
			panel1.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pane.add(panel1,c);
		
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 1;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.ipadx = 200;
			c.ipady = 200;
			panel2.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pane.add(panel2,c);
				
			c.weightx = 1;
			c.weighty = 0;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 2;
			c.gridheight = 2;
			c.ipadx = 0;
			c.ipady = 10;
			panel3.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pane.add(panel3,c);
			
		this.pack();

	}
		
	public void refresh(){
		new Thread(){
			public void run(){
				while(true){
					Main.frame.repaint();
					try{
						Thread.sleep(50);
					}catch(Exception e){}
					
				}
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
	public void waitForStart(){
		while(true){
			if(this.panel3.isPressed(1))
				break;			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
