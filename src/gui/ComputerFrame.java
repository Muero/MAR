package gui;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

import muehle.Main;

public class ComputerFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public cPanel1 cpanel1 = new cPanel1();
	public Panel5 cpanel2 = new Panel5();
	
	public ComputerFrame(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(0,0,500,600);
		addComponentsToPane(this.getContentPane());
		refresh();
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
			cpanel1.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pane.add(cpanel1,c);
		
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 1;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.ipadx = 200;
			c.ipady = 200;
			cpanel2.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pane.add(cpanel2,c);
		this.pack();

	}

	
	private void refresh(){
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


}
