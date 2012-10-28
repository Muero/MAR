package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import muehle.model.Board;
import muehle.model.Board.eColor;
import muehle.model.Position;
import muehle.players.computer.Minmax;

public class Panel5 extends JPanel {
	private static final long serialVersionUID = 1L;

	public JButton[] button = new JButton[24];

	private Board board;

	public Panel5(Board board, eColor player, int move, int numberOfStones) {

		this.board = board;
		this.setLayout(null);
		this.repaint();
		generateButtons();
		thread(player, move, numberOfStones);
	}

	private Color getProbabilityColor(Integer i) {
		if (i <= 100)
			return new Color(255, 0, 0);
		if (i <= 200)
			return new Color(255, 25, 0);
		if (i <= 300)
			return new Color(275, 75, 0);
		if (i <= 400)
			return new Color(150, 100, 0);
		if (i <= 500)
			return new Color(125, 125, 0);
		if (i <= 600)
			return new Color(100, 150, 0);
		if (i <= 700)
			return new Color(75, 175, 0);
		if (i <= 800)
			return new Color(50, 200, 0);
		if (i <= 900)
			return new Color(0, 255, 0);
		if (i <= 1000)
			return new Color(255, 255, 255);
		return new Color(0, 0, 0);
	}

	private void thread(final eColor player, final int move,
			final int numberOfStones) {

		new Thread() {
			public void run() {
				while (true) {
					for (Position p : Position.getAllPositions()) {
						cPanel1.getButton(p).setEnabled(false);
						cPanel1.getButton(p).setBackground(
								getProbabilityColor(Minmax.getProbability(
										board, player, move, numberOfStones)
										.get(p)));
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
			}
		}.start();
	}

	private void generateButtons() {
		for (int i = 0; i < 24; i++) {
			button[i] = new JButton();
			this.add(button[i]);
		}
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(230, 230, 230));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		drawField(g);
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
			// g.setColor(BestMove.probabilityColor[i]);
			g.fillRect((int) ((1 * a) + (i * c)) - s / 2, (int) (8 * b),
					(int) (c), s);
			g.setColor(Color.black);
			g.drawRect((int) ((1 * a) + (i * c)) - s / 2, (int) (8 * b),
					(int) (c), s);
		}

	}

}
