package muehle.gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import muehle.Linker;

public class Panel0 extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	public JCheckBox jcb1;	//Webcam
	public JCheckBox jcb3;	//Algorithm
	public JButton jb1;		//NextButton
	public JComboBox<String> jc1;
	public JComboBox<String> jc2;

	public Panel0(){
		jc2 = new JComboBox<String>();
		for(String s:Linker.modes)
			jc2.addItem(s);
		jcb1 = new JCheckBox("Mit Webcam?");
		jcb3 = new JCheckBox("Mit Algorithmus?");
		jb1 = new JButton("Next Step");
		jc1 = new JComboBox<String>();
		for(String s:Linker.difficultyNames)
			jc1.addItem(s);
		jb1.addMouseListener(this);
		addComponentsToPane();
	}
	
	private void addComponentsToPane(){
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
				
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.insets = new Insets(10,10,10,10);
		this.add(jcb1,c);
			
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.insets = new Insets(10,10,10,10);
		this.add(jc2,c);
		
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.insets = new Insets(10,10,10,10);
		this.add(jcb3,c);

			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 3;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.insets = new Insets(10,10,10,10);
		this.add(jc1,c);
		
		
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 4;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.insets = new Insets(10,10,10,10);
		this.add(jb1,c);
		
	}
	
	@SuppressWarnings("deprecation")
	public void thread(){
		if(jcb1.isSelected()){
			jcb1.setBackground(Color.green);
		}else{
			jcb1.setBackground(null);
		}
		if(jcb3.isSelected()){
			jcb3.setBackground(Color.green);
			jc1.enable();
		}else{
			jcb3.setBackground(null);
			jc1.disable();
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		Linker.usealgorithm = jcb3.isSelected();
		Linker.robotMode = jc2.getSelectedIndex();
		Linker.usewebcam = jcb1.isSelected();
		Linker.difficulty = jc1.getSelectedIndex();
		Linker.waitForGui = false;
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

}
