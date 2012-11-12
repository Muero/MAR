package muehle.gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import muehle.Linker;
import muehle.gui.camera.ImageGrabber;

public class Panel3 extends JPanel{
	private static final long serialVersionUID = 1L;

	public JButton button1 = new JButton("Starter");
	public JButton button2 = new JButton("Spiel starten");
	public JButton button3 = new JButton("Punkt hinzufügen [0|24]");
	public JButton button4 = new JButton("Letzten Punkt entfernen");
	public JButton button5 = new JButton("Automatisch setzen");
	public JButton button6 = new JButton("Update Bild");
	public JSlider sl1 = new JSlider();
	public JSlider sl2 = new JSlider();
	public JLabel l1 = new JLabel("Starter setzen");
	public JLabel l2 = new JLabel("Helligkeit");
	public JLabel l3 = new JLabel("Alpha-Korrektur");
	
	private boolean starterSet = false;
	private boolean fieldSet = false;
	private boolean isButton1 = false;	//0
	private boolean isButton2 = false;	//1
	private boolean isRb1 = true;		//2
	private boolean isRb2 = true;		//3
	private boolean isRb3 = true;		//4
	private boolean isButton3 = false;
	private boolean isButton4 = false;
	
	
	public Panel3(){
		this.setLayout(null);
		addListenersToObjects();
		addComponentsToPane();
	}		

	//Adds all Buttons and Sliders to the Component
	private void addComponentsToPane(){
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
				
								
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		button1.setBackground(Color.orange);
	this.add(button1,c);

		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		button1.setBackground(Color.green);
	this.add(button6,c);
		
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		sl1.setMinimum(0);
		sl1.setMaximum(255);
		sl1.setValue(255);
	this.add(sl1,c);
	
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		sl2.setMinimum(0);
		sl2.setMaximum(100);
		sl2.setValue(20);
	this.add(sl2,c);
		
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,0,0,10);
	this.add(l1,c);

		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,80,0,0);
	this.add(l2,c);

		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 4;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,50,0,0);
	this.add(l3,c);

		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		button3.setBackground(Color.orange);
		c.insets = new Insets(0,0,0,0);
	this.add(button3,c);
		
		
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		button4.setBackground(Color.green);
		c.insets = new Insets(0,0,0,0);
	this.add(button4,c);

		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		button5.setBackground(Color.green);
		button5.setEnabled(false);
		c.insets = new Insets(0,0,0,20);	
	this.add(button5,c);
	
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,0,0,0);
		button2.setBackground(Color.orange);
	this.add(button2,c);

	}
	/**
	 * Adds to every Component its specific MouseListener
	 */
	private void addListenersToObjects(){
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				isButton1 = true;
			}
		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (starterSet) {
					for (int i = 0; i < 24; i++) {
						Linker.fieldPositions[i] = new Point(0, 0);
						Linker.fieldPositions[i] = Linker.frame.panel2
								.getFieldPosition(i);
					}
					Linker.alphaValue = Linker.frame.panel3.getValue(1);
					Linker.done = true;
					Linker.waitForGui = false;
				}
			}
		});
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				isButton3 = true;
			}
		});
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				isButton4 = true;
			}
		});
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Linker.allowRepaint = false;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ImageGrabber.takePicture();
				ImageGrabber.importPicture(Linker.frame);
				ImageGrabber.readColor(Linker.frame);
				Linker.allowRepaint = true;
			}
		});
	}

	/*
	 * Returns true if Starter is set
	 */
	public boolean isStarterSet(){
		return starterSet;
	}
	/**
	 * If the button[number] is pressed, this method returns true
	 * @param number
	 * @return
	 */
	public boolean isPressed(int number){
		switch(number){
		case 0:
			return isButton1;
		case 1:
			return isButton2;
		case 2:
			return isRb1;
		case 3:
			return isRb2;
		case 4:
			return isRb3;
		default: 
			return false;
		}
	}
	/**
	 * Gets the value of one of the slider, called by number
	 * @param number
	 * @return
	 */
	public int getValue(int number){
		switch(number){
		case 0:
			return sl1.getValue();
		case 1:
			return sl2.getValue();
		default:
			return 0;
		}
	}
	/**
	 * Called by frame. Updates ButtonColors
	 */
	@SuppressWarnings("deprecation")
	public void thread(){
					if(isButton1){
						Linker.frame.setCursor(Cursor.CROSSHAIR_CURSOR);
						button1.setBackground(Color.orange);
						if(Linker.frame.panel2.mouseClicked()){
							button1.setBackground(Color.green);
							Linker.frame.setCursor(Cursor.DEFAULT_CURSOR);
							Linker.frame.panel2.setStarterPosition(Linker.frame.panel2.getMousePosition());
							isButton1 = false;
							starterSet = true;
							Linker.frame.panel2.setMouseClick(false);
						}
					}
					if(isButton3){
						Linker.frame.setCursor(Cursor.CROSSHAIR_CURSOR);
						button3.setBackground(Color.red);
						if(Linker.frame.panel2.mouseClicked()){
							button3.setBackground(Color.orange);
							Linker.frame.setCursor(Cursor.DEFAULT_CURSOR);
							Linker.frame.panel2.setFieldPosition(Linker.frame.panel2.getMousePosition());
							isButton3 = false;
							Linker.frame.panel2.setMouseClick(false);
						}
					}
					try{
						Linker.frame.panel3.button3.setText("Punkt hinzufügen ["+Linker.frame.panel2.getPositionIndex()+"|24]");						
					}catch(Exception e){}
					if(isButton4){
						button4.setBackground(Color.orange);
						Linker.frame.panel2.clearLastFieldPosition();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}				
						button4.setBackground(Color.green);
						isButton4 = false;
					}
					
					if(fieldSet&&starterSet)
						button2.setBackground(Color.green);
					else
						button2.setBackground(Color.orange);
					
					if(Linker.frame.panel2.getPositionIndex()==24){
						button3.setBackground(Color.green);
						fieldSet = true;
					}else{
						button3.setBackground(Color.orange);
						fieldSet = false;
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}				
	}
	
}
