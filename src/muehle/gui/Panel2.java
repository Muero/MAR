package muehle.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import muehle.Linker;
import muehle.gui.camera.ImageGrabber;

public class Panel2 extends JPanel {
	private static final long serialVersionUID = 1L;

	private int a = 0;
	private int b = 0;
	private boolean mouseClicked = false;
	private Point mousePosition = new Point(-10, -10);
	private Point starterPosition = new Point(-10, -10);
	private Point[] positions = new Point[24];
	private int index = 0;

	public Panel2() {
		for (int i = 0; i < 24; i++)
			positions[i] = new Point(-10, -10);
		this.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				mouseClicked = true;
				mousePosition = new Point(arg0.getX(), arg0.getY());
			}
		});
	}

	/**
	 * Returns the Index of the last set Position
	 * 
	 * @return index
	 */
	
	public void resetIndex(){
		this.index = 0;
	}
	
	public int getPositionIndex() {
		return index;
	}

	/**
	 * Checks if Mouse clicked on a button
	 * 
	 * @return
	 */
	public boolean mouseClicked() {
		return mouseClicked;
	}

	/**
	 * MouseClick can be done automatically
	 * 
	 * @param click
	 */
	public void setMouseClick(boolean click) {
		mouseClicked = click;
	}

	/**
	 * Returns MousePosition on the Panel
	 */
	public Point getMousePosition() {
		return mousePosition;
	}

	/**
	 * Returns StarterPosition to determine color values
	 * 
	 * @return
	 */
	public Point getStarterPosition() {
		return starterPosition;
	}

	/**
	 * Sets the starterPosition
	 * 
	 * @param start
	 */
	public void setStarterPosition(Point start) {
		this.starterPosition = new Point(start);
	}

	/**
	 * Sets the fieldPosition
	 * 
	 * @param field
	 */
	public void setFieldPosition(Point field) {
		if (index < 24) {
			positions[index] = new Point(field);
			this.index++;
		}
	}

	/**
	 * Returns the actual fieldPosition from index a
	 * 
	 * @param a
	 * @return positions
	 */
	public Point getFieldPosition(int a) {
		return positions[a];
	}

	/**
	 * Clears the last Field Position and subtracts index by 1
	 */
	public void clearLastFieldPosition() {
		if (index > 0) {
			positions[index - 1] = new Point(-10, -10);
			System.out.println(positions[index - 1]);
			System.out.println(index);
			this.index--;
		}
	}

	/**
	 * Is called from frame. Updates Picture
	 */
	public void thread() {
		if (Linker.allowRepaint) {
			this.setIgnoreRepaint(false);
			this.repaint();
		} else {
			this.setIgnoreRepaint(true);
		}
	}

	/**
	 * Paints the Picture in Form of a Pixel-Array to easily change specific
	 * Pixels
	 */
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (int i = 0; i < ImageGrabber.getImageColor().length; i++) {
			for (int j = 0; j < ImageGrabber.getImageColor()[0].length; j++) {
				if (!alphacheck(ImageGrabber.getImageColor()[i][j]))
					g.setColor(new Color(ImageGrabber.getImageColor()[i][j]
							.getRed(), ImageGrabber.getImageColor()[i][j]
							.getGreen(), ImageGrabber.getImageColor()[i][j]
							.getBlue(), Linker.frame.panel3.getValue(0)));
				else
					g.setColor(ImageGrabber.getImageColor()[i][j]);
				g.drawLine(i - a, j - b, i - a, j - b);
			}
		}
		g.setColor(Color.blue);
		g.fillRect(getStarterPosition().x - 5, getStarterPosition().y - 5, 10,
				10);

		for (int i = 0; i < 24; i++) {
			g.setColor(Color.red);
			g.fillRect(getFieldPosition(i).x - 5, getFieldPosition(i).y - 5,
					10, 10);
		}

	}

	/**
	 * Checks if a Pixel is in the range of Starterposition's Color and the
	 * alphavalue. Returns true if this happens
	 * 
	 * @param c
	 * @return boolean
	 */
	private boolean alphacheck(Color c) {
		if (Linker.frame.panel3.isStarterSet()) {
			Color alphapixel = ImageGrabber.getImageColor()[starterPosition.x][starterPosition.y];
			int r = Math.abs(c.getRed() - alphapixel.getRed());
			int g = Math.abs(c.getGreen() - alphapixel.getGreen());
			int b = Math.abs(c.getBlue() - alphapixel.getBlue());

			int a = Linker.frame.panel3.getValue(1);

			return r < a && g < a && b < a;
		} else {
			return false;
		}

	}

}
