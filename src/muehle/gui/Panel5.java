package muehle.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import muehle.Linker;
import muehle.model.Board;
import muehle.model.Board.StoneColor;
import muehle.model.Position;
import muehle.players.computer.Minmax;

public class Panel5 extends JPanel {
	private static final long serialVersionUID = 1L;

	public static JButton[] button = new JButton[24];
	public static JButton showHelp = new JButton("Show Help");
	public boolean help = false;
	public Board board;

	public Panel5(Board board) {

		this.board = board;
		this.setLayout(null);
		generateButtons();
	}

	/**
	 * @param i
	 * @return
	 */
	private static Color getProbabilityColor(int i) {
		if(i==0){
			return null;
		}
		if (i <= -120)
			return new Color(255, 0, 0);
		if (i <= -80)
			return new Color(240, 25, 0);
		if (i <= -50)
			return new Color(210, 75, 0);
		if (i <= -30)
			return new Color(150, 100, 0);
		if (i <= 0)
			return new Color(125, 125, 0);
		if (i <= 70)
			return new Color(100, 150, 0);
		if (i <= 100)
			return new Color(75, 175, 0);
		if (i <= 200)
			return new Color(50, 200, 0);
		if (i <= 300)
			return new Color(0, 255, 0);
		if (i <= 1000)
			return new Color(255, 255, 255);
		return new Color(0, 0, 0);
	}

	public static void setBackgroundProbabilityColor(Board board, final StoneColor player,final int move, final int numberOfStones) {

		Map<Position, Integer> prob = new HashMap<Position, Integer>();
		prob = Minmax.getProbability(board, player,move, numberOfStones);
		
		for (Position p : Position.getAllPositions()) {
			button[Frame.getGuiPosition(p)].setEnabled(false);
			button[Frame.getGuiPosition(p)].setBackground(getProbabilityColor(prob.get(p)));
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
	}

	private void generateButtons() {
		for (int i = 0; i < 24; i++) {
			button[i] = new JButton();
			button[i].setEnabled(false);
			this.add(button[i]);
		}
		showHelp.setBackground(Color.orange);
		showHelp.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				help = !help;
				Linker.frame.panel5.paintComponent(getGraphics());
			}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseExited(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
			}
			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
		this.add(showHelp);
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(230, 230, 230));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(help){
			for(int i=0;i<24;i++)
				button[i].setVisible(true);
			showHelp.setText("hide Help");
			showHelp.setBounds(this.getWidth()-100,this.getHeight()-20,100,20);
			drawField(g);
		}else{
			showHelp.setBounds(this.getWidth()-100,this.getHeight()-20,100,20);
			showHelp.setText("show Help");
			showHelp.setVisible(true);
			for(int i=0;i<24;i++)
				button[i].setVisible(false);
		}
		
	}

	private void drawField(Graphics g) {
		float a = this.getWidth() / 8.0f;
		float b = this.getHeight() / 9.0f;
		g.setColor(Color.gray);
		int s = 20;
		button[0].setBounds((int) (1 * a) - (s / 2), (int) (1 * b) - (s / 2),
				s, s);
		button[1].setBounds((int) (4 * a) - (s / 2), (int) (1 * b) - (s / 2),
				s, s);
		button[2].setBounds((int) (7 * a) - (s / 2), (int) (1 * b) - (s / 2),
				s, s);
		button[9].setBounds((int) (1 * a) - (s / 2), (int) (4 * b) - (s / 2),
				s, s);
		button[14].setBounds((int) (7 * a) - (s / 2), (int) (4 * b) - (s / 2),
				s, s);
		button[21].setBounds((int) (1 * a) - (s / 2), (int) (7 * b) - (s / 2),
				s, s);
		button[22].setBounds((int) (4 * a) - (s / 2), (int) (7 * b) - (s / 2),
				s, s);
		button[23].setBounds((int) (7 * a) - (s / 2), (int) (7 * b) - (s / 2),
				s, s);
		button[3].setBounds((int) (2 * a) - (s / 2), (int) (2 * b) - (s / 2),
				s, s);
		button[4].setBounds((int) (4 * a) - (s / 2), (int) (2 * b) - (s / 2),
				s, s);
		button[5].setBounds((int) (6 * a) - (s / 2), (int) (2 * b) - (s / 2),
				s, s);
		button[10].setBounds((int) (2 * a) - (s / 2), (int) (4 * b) - (s / 2),
				s, s);
		button[13].setBounds((int) (6 * a) - (s / 2), (int) (4 * b) - (s / 2),
				s, s);
		button[18].setBounds((int) (2 * a) - (s / 2), (int) (6 * b) - (s / 2),
				s, s);
		button[19].setBounds((int) (4 * a) - (s / 2), (int) (6 * b) - (s / 2),
				s, s);
		button[20].setBounds((int) (6 * a) - (s / 2), (int) (6 * b) - (s / 2),
				s, s);

		button[6].setBounds((int) (3 * a) - (s / 2), (int) (3 * b) - (s / 2),
				s, s);
		button[7].setBounds((int) (4 * a) - (s / 2), (int) (3 * b) - (s / 2),
				s, s);
		button[8].setBounds((int) (5 * a) - (s / 2), (int) (3 * b) - (s / 2),
				s, s);
		button[11].setBounds((int) (3 * a) - (s / 2), (int) (4 * b) - (s / 2),
				s, s);
		button[12].setBounds((int) (5 * a) - (s / 2), (int) (4 * b) - (s / 2),
				s, s);
		button[15].setBounds((int) (3 * a) - (s / 2), (int) (5 * b) - (s / 2),
				s, s);
		button[16].setBounds((int) (4 * a) - (s / 2), (int) (5 * b) - (s / 2),
				s, s);
		button[17].setBounds((int) (5 * a) - (s / 2), (int) (5 * b) - (s / 2),
				s, s);

		g.drawLine((int) (1 * a), (int) (1 * b), (int) (1 * a), (int) (7 * b));
		g.drawLine((int) (2 * a), (int) (2 * b), (int) (2 * a), (int) (6 * b));
		g.drawLine((int) (3 * a), (int) (3 * b), (int) (3 * a), (int) (5 * b));
		g.drawLine((int) (4 * a), (int) (1 * b), (int) (4 * a), (int) (3 * b));
		g.drawLine((int) (4 * a), (int) (5 * b), (int) (4 * a), (int) (7 * b));
		g.drawLine((int) (5 * a), (int) (3 * b), (int) (5 * a), (int) (5 * b));
		g.drawLine((int) (6 * a), (int) (2 * b), (int) (6 * a), (int) (6 * b));
		g.drawLine((int) (7 * a), (int) (1 * b), (int) (7 * a), (int) (7 * b));

		g.drawLine((int) (1 * a), (int) (1 * b), (int) (7 * a), (int) (1 * b));
		g.drawLine((int) (2 * a), (int) (2 * b), (int) (6 * a), (int) (2 * b));
		g.drawLine((int) (3 * a), (int) (3 * b), (int) (5 * a), (int) (3 * b));
		g.drawLine((int) (1 * a), (int) (4 * b), (int) (3 * a), (int) (4 * b));
		g.drawLine((int) (5 * a), (int) (4 * b), (int) (7 * a), (int) (4 * b));
		g.drawLine((int) (3 * a), (int) (5 * b), (int) (5 * a), (int) (5 * b));
		g.drawLine((int) (2 * a), (int) (6 * b), (int) (6 * a), (int) (6 * b));
		g.drawLine((int) (1 * a), (int) (7 * b), (int) (7 * a), (int) (7 * b));

		g.setFont(new Font("Arial", Font.BOLD, 15));
		float c = a / 10;

		g.drawString("Wahrscheinlichkeit", ((int) ((1 * a)) - s / 2)
				+ ((int) (c * 12)), (int) (8 * b) + s - 3);
		g.setColor(Color.black);
		g.fillRect((int) ((1 * a)) - s / 2, (int) (8 * b), (int) (c * 10), s);
		for (int i = 0; i < 10; i++) {
			g.setColor(Linker.probabilityColor[i]);
			g.fillRect((int) ((1 * a) + (i * c)) - s / 2, (int) (8 * b),
					(int) (c), s);
			g.setColor(Color.black);
			g.drawRect((int) ((1 * a) + (i * c)) - s / 2, (int) (8 * b),
					(int) (c), s);
		}

	}

	public void thread(){
	}
}
