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
import javax.swing.JTextField;

import muehle.Linker;

public class Panel0 extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;

	public JCheckBox jcb1; // Webcam
	public JCheckBox jcb2; // Auch Webcam
	public JCheckBox jcb3; // Algorithm
	public JCheckBox jcb4; // Auch Algorithm
	public JButton jb1; // NextButton
	public JComboBox<String> jc0;
	public JComboBox<String> jc1;
	public JComboBox<String> jc2;
	public JComboBox<String> jc3;
	public JTextField jtf1;
	public JTextField jtf2;

	public Panel0() {
		jtf1 = new JTextField("Name 1");
		jtf2 = new JTextField("Name 2");
		jc2 = new JComboBox<String>();
		for (String s : Linker.modes)
			jc2.addItem(s);
		jc3 = new JComboBox<String>();
		for (String s : Linker.modes)
			jc3.addItem(s);

		jcb1 = new JCheckBox("Mit Webcam?");
		jcb2 = new JCheckBox("Mit Webcam?");
		jcb3 = new JCheckBox("Mit Algorithmus?");
		jcb4 = new JCheckBox("Mit Algorithmus?");
		jb1 = new JButton("Next Step");

		jc1 = new JComboBox<String>();
		for (String s : Linker.difficultyNames)
			jc1.addItem(s);
		jc0 = new JComboBox<String>();
		for (String s : Linker.difficultyNames)
			jc0.addItem(s);

		jb1.addMouseListener(this);
		addComponentsToPane();
	}

	/**
	 * Adds all Components to the pane and makes them resizable
	 */
	private void addComponentsToPane() {
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(jc2, c);

		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(jc3, c);

		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(jtf1, c);

		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(jtf2, c);

		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(jcb1, c);

		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(jcb2, c);

		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(jcb3, c);

		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(jcb4, c);

		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(jc0, c);

		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(jc1, c);

		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(jb1, c);

	}

	/**
	 * thread will be executed from frame every 50ms. It updates the colors of
	 * the buttons
	 */
	public void thread() {
		if (jcb1.isSelected()) {
			jcb1.setBackground(Color.green);
		} else {
			jcb1.setBackground(null);
		}
		if (jcb2.isSelected()) {
			jcb2.setBackground(Color.green);
		} else {
			jcb2.setBackground(null);
		}
		if (jcb3.isSelected()) {
			jcb3.setBackground(Color.green);
		} else {
			jcb3.setBackground(null);
		}
		if (jcb4.isSelected()) {
			jcb4.setBackground(Color.green);
		} else {
			jcb4.setBackground(null);
		}

		if (jc2.getSelectedIndex() == 0) {
			jcb1.setEnabled(false);
			jcb3.setEnabled(false);
			jc0.setEnabled(false);
		} else if (jc2.getSelectedIndex() == 1) {
			jcb1.setEnabled(true);
			jcb3.setEnabled(true);
			jc0.setEnabled(true);
		} else {
			jcb1.setEnabled(false);
			jcb3.setEnabled(true);
			jc0.setEnabled(true);
		}
		if (jc3.getSelectedIndex() == 0) {
			jcb2.setEnabled(false);
			jcb4.setEnabled(false);
			jc1.setEnabled(false);
		} else if (jc3.getSelectedIndex() == 1) {
			jcb2.setEnabled(true);
			jcb4.setEnabled(true);
			jc1.setEnabled(true);
		} else {
			jcb2.setEnabled(false);
			jcb4.setEnabled(true);
			jc1.setEnabled(true);
		}

		if (jcb3.isSelected() && jc2.getSelectedIndex() != 0)
			jc0.setEnabled(true);
		else
			jc0.setEnabled(false);
		if (jcb4.isSelected() && jc3.getSelectedIndex() != 0)
			jc1.setEnabled(true);
		else
			jc1.setEnabled(false);

	}

	/**
	 * Listeners of all Buttons. Only mouseClicked is used
	 */
	public void mouseReleased(MouseEvent e) {
		jb1.setText("Please wait...");

		Linker.mode1 = jc2.getSelectedIndex();
		Linker.mode2 = jc3.getSelectedIndex();

		Linker.name1 = jtf1.getText();
		Linker.name2 = jtf2.getText();

		Linker.usewebcam1 = jcb1.isSelected();
		Linker.usewebcam2 = jcb2.isSelected();

		Linker.usealgorithm1 = jcb3.isSelected();
		Linker.usealgorithm2 = jcb4.isSelected();
		switch (jc0.getSelectedIndex()) {
		case 0:
			Linker.difficulty1 = 2; // easy
			break;
		case 1:
			Linker.difficulty1 = 4; // normal
			break;
		case 2:
			Linker.difficulty1 = 6; // hard
			break;
		case 3:
			Linker.difficulty1 = 8; // insane
			break;
		default:
			Linker.difficulty1 = 2;
			break;
		}
		switch (jc1.getSelectedIndex()) {
		case 0:
			Linker.difficulty2 = 2; // easy
			break;
		case 1:
			Linker.difficulty2 = 4; // normal
			break;
		case 2:
			Linker.difficulty2 = 6; // hard
			break;
		case 3:
			Linker.difficulty2 = 8; // insane
			break;
		default:
			Linker.difficulty2 = 2;
			break;
		}
		Linker.waitForGui = false;
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

}
