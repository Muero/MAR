package muehle.gui;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

import muehle.Linker;
import muehle.model.Board;
import muehle.model.Position;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;
		
	public Panel0 panel0;	//StartupPanel
	public Panel1 panel1;	//StartGameGuiPanel left		only Webcam
	public Panel2 panel2;	//StartGameGuiPanel right		only Webcam
	public Panel3 panel3;	//StartGameGuiPanel bottom		only Webcam
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
				if(Linker.frame.panel0.isVisible())
					Linker.frame.panel0.thread();
				if(Linker.frame.panel2.isVisible())
					Linker.frame.panel2.thread();
				if(Linker.frame.panel3.isVisible())
					Linker.frame.panel3.thread();
				if(Linker.frame.panel4.isVisible())
					Linker.frame.panel4.thread();
				if(Linker.frame.panel5.isVisible())
					Linker.frame.panel5.thread();
				try{
					Thread.sleep(100);
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
	 *2 = StartGameGui (Webcam only)
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
			this.setSize(542, 378);
			panel0.setVisible(false);
			panel1.setVisible(true);
			panel2.setVisible(true);
			panel3.setVisible(true);
			panel4.setVisible(false);
			panel5.setVisible(false);
			break;
		case 3:	//Webcam-Human Gui
			this.setVisible(true);
			this.setSize(700,400);
			panel0.setVisible(false);
			panel1.setVisible(false);
			panel2.setVisible(false);
			panel3.setVisible(false);
			panel4.setVisible(true);
			panel5.setVisible(true);
			break;
		case 4:	//Computer-Human Gui
			this.setVisible(true);
			this.setSize(700,400);
			panel0.setVisible(false);
			panel1.setVisible(false);
			panel2.setVisible(false);
			panel3.setVisible(false);
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

	public static int getGuiPosition(Position p) {
		switch (p.getId()) {
		case 10:
			return 21;
		case 13:
			return 22;
		case 16:
			return 23;
		case 21:
			return 18;
		case 23:
			return 19;
		case 25:
			return 20;
		case 32:
			return 15;
		case 33:
			return 16;
		case 34:
			return 17;
		case 40:
			return 9;
		case 41:
			return 10;
		case 42:
			return 11;
		case 44:
			return 12;
		case 45:
			return 13;
		case 46:
			return 14;
		case 52:
			return 6;
		case 53:
			return 7;
		case 54:
			return 8;
		case 61:
			return 3;
		case 63:
			return 4;
		case 65:
			return 5;
		case 70:
			return 0;
		case 73:
			return 1;
		case 76:
			return 2;
		default:
			return -1;
		}
	}
}