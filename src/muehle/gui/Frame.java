package muehle.gui;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

import muehle.Linker;
import muehle.model.Board;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;
		
	public Panel0 panel0;	//StartupPanel
	public Panel1 panel1;	//StartGameGuiPanel left
	public Panel2 panel2;	//StartGameGuiPanel right
	public Panel3 panel3;	//StartGameGuiPanel bottom
	public Panel4 panel4;	//GameGuiPanel left
	public Panel5 panel5;	//GameGuiPanel right
	
	/**
	 * Constructor of the Gui
	 * @param board
	 */
	public Frame(Board board){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel0 = new Panel0();
		panel1 = new Panel1();
		panel2 = new Panel2();
		panel3 = new Panel3();
		panel4 = new Panel4(board);
		panel5 = new Panel5(board);
		addComponentsToPane(this.getContentPane());		
		setGuiMode(0);
		th.start();
	}
	
	/**
	 * Defines and runs a Thread that updates the
	 * Gui every 100ms. Can be interrupted with stop()
	 */
	private Thread th = new Thread(){
		public void run(){
			while(true){
				Linker.frame.repaint();
				Linker.frame.panel0.thread();
				try{
					Thread.sleep(50);
				}catch(Exception e){}
				
			}
		}		
	};

	/**
	 * Adds all panels to the frame
	 * @param pane
	 */
	private void addComponentsToPane(Container pane){
		pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
				
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 2;
			c.gridheight = 2;
			panel0.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pane.add(panel0,c);
			
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
			
		
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.ipadx = 200;
			c.ipady = 200;
			panel4.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pane.add(panel4,c);

			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 1;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.ipadx = 200;
			c.ipady = 200;
			panel5.setBorder(new BevelBorder(BevelBorder.LOWERED));
			pane.add(panel5,c);
	
		this.pack();

	}

	/**
	 *Sets the specific Gui Mode:
	 *0 = Make frame invisible
	 *1 = StartupGui
	 *2 = StartGameGui
	 *3 = Start Webcam-HumanGui
	 *4 = Start Computer-HumanGui 
	 */
	public void setGuiMode(int newmode){
		switch(newmode){
		case 0:
			this.setVisible(false);
			break;
		case 1:	//StartupGui
			this.setVisible(true);
			this.setSize(200,250);
			this.panel0.setVisible(true);
			this.panel1.setVisible(false);
			this.panel2.setVisible(false);
			this.panel3.setVisible(false);
			this.panel4.setVisible(false);
			this.panel5.setVisible(false);
			break;
		case 2:	//StartGameGui
			this.setVisible(true);
			panel0.setVisible(false);
			panel1.setVisible(true);
			panel2.setVisible(true);
			panel3.setVisible(true);
			panel4.setVisible(false);
			panel5.setVisible(false);
			break;
		case 3:	//Webcam-Human Gui
			this.setVisible(true);
			panel4.setVisible(true);
			panel5.setVisible(true);
			break;
		case 4:	//Computer-Human Gui
			this.setVisible(true);
			panel4.setVisible(true);
			panel5.setVisible(true);
			break;
		}
	}

	/**
	 * Waits for the Finish-Button of the current GuiMode
	 */
	public void waitFor(){
			while(Linker.waitForGui){
				try{
					Thread.sleep(100);
				}catch(Exception e){}
			}
			Linker.waitForGui = true;
	}

	/**
	 * Stops the current Thread
	 */
	public void stop(){
		th.interrupt();
	}

}