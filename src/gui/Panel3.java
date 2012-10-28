package gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

import muehle.Main;

public class Panel3 extends JPanel{
	private static final long serialVersionUID = 1L;

	public JButton button1 = new JButton("Starter");
	public JButton button2 = new JButton("Spiel starten");
	public JButton button3 = new JButton("Punkt hinzufügen [0|24]");
	public JButton button4 = new JButton("Letzten Punkt entfernen");
	public JButton button5 = new JButton("Automatisch setzen");
	public JRadioButton rb1 = new JRadioButton("mit Roboter?");
	public JRadioButton rb2 = new JRadioButton("mit Webcam?");
	public JRadioButton rb3 = new JRadioButton("mit MinMax?");
	public JSlider sl1 = new JSlider();
	public JSlider sl2 = new JSlider();
	public JLabel l1 = new JLabel("Starter setzen");
	public JLabel l2 = new JLabel("Helligkeit");
	public JLabel l3 = new JLabel("Alpha-Korrektur");
	String[] string1 = {"Easy","Medium","Hard","Insane"};
	public JComboBox cb1 = new JComboBox(string1);
	
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
		thread();
	}		
	
	private void addComponentsToPane(){
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
				
		
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,0,0,10);
	this.add(cb1,c);

		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
	this.add(rb1,c);
						
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		button1.setBackground(Color.orange);
	this.add(button1,c);

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
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
	this.add(rb2,c);

		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
	this.add(rb3,c);
	
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
	private void addListenersToObjects(){
		button1.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				isButton1 = true;
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}			
		});
		button2.addMouseListener(new MouseListener(){
		public void mouseClicked(MouseEvent arg0) {
//TODO
//			if(starterSet&&fieldSet)
			if(starterSet)
				isButton2 = true;
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}			
	});
		button3.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				isButton3 = true;
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		button4.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				isButton4 = true;
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		rb1.addMouseListener(new MouseListener(){
		public void mouseClicked(MouseEvent arg0) {
			isRb1 = rb1.isSelected();
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}			
	});
		rb2.addMouseListener(new MouseListener(){
		public void mouseClicked(MouseEvent arg0) {
			isRb2 = rb2.isSelected();
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}			
	});
		rb3.addMouseListener(new MouseListener(){
		public void mouseClicked(MouseEvent arg0) {
			isRb3 = rb3.isSelected();
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}			
	});
}
	
	public boolean isStarterSet(){
		return starterSet;
	}
	
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
	public int getValue(int number){
		switch(number){
		case 0:
			return sl1.getValue();
		case 1:
			return sl2.getValue();
		case 2:
			return cb1.getSelectedIndex();
		default:
			return 0;
		}
	}
	
	private void thread(){
		new Thread(){
			@SuppressWarnings("deprecation")
			public void run(){
				try {
					Thread.sleep(1000); // wait until everything is initialized
										// FIXME needs to be fixed
				} catch (InterruptedException e1) {
					// ignore
				} 
				
				while(true){
					System.out.println("I am still running...");
					if(isButton1){
						Main.frame.setCursor(Cursor.CROSSHAIR_CURSOR);
						button1.setBackground(Color.orange);
						if(Main.frame.panel2.mouseClicked()){
							button1.setBackground(Color.green);
							Main.frame.setCursor(Cursor.DEFAULT_CURSOR);
							Main.frame.panel2.setStarterPosition(Main.frame.panel2.getMousePosition());
							isButton1 = false;
							starterSet = true;
							Main.frame.panel2.setMouseClick(false);
						}
					}
					if(isButton3){
						Main.frame.setCursor(Cursor.CROSSHAIR_CURSOR);
						button3.setBackground(Color.red);
						if(Main.frame.panel2.mouseClicked()){
							button3.setBackground(Color.orange);
							Main.frame.setCursor(Cursor.DEFAULT_CURSOR);
							Main.frame.panel2.setFieldPosition(Main.frame.panel2.getMousePosition());
							isButton3 = false;
							Main.frame.panel2.setMouseClick(false);
						}
					}
					Main.frame.panel3.button3.setText("Punkt hinzufügen ["+Main.frame.panel2.getPositionIndex()+"|24]");
					if(isButton4){
						button4.setBackground(Color.orange);
						Main.frame.panel2.clearLastFieldPosition();
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
					
					if(Main.frame.panel2.getPositionIndex()==24){
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
		}.start();
	}
	
}
