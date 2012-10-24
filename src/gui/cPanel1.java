package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import muehle.Main;
import muehle.model.*;
import muehle.model.Board;
import muehle.model.Position;
import muehle.model.Board.eColor;

public class cPanel1 extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	public JButton[] button = new JButton[24];

	
	public cPanel1(){
		this.setLayout(null);
		this.repaint();
		generateButtons();
		thread();
	}
	private void thread(){
		new Thread(){
			public void run(){
				while(true){
					for(Position p:Position.getAllPositions()){
						if(Board.getColor(p)==Board.eColor.NONE){
							button[Position.getGuiPosition(p)].setBackground(Color.gray);
							button[Position.getGuiPosition(p)].setEnabled(true);
						}
						if(Board.getColor(p)==eColor.WHITE){
							button[Position.getGuiPosition(p)].setBackground(Output.humanColor);
							button[Position.getGuiPosition(p)].setEnabled(false);
						}
						if(Board.getColor(p)==eColor.BLACK){
							button[Position.getGuiPosition(p)].setBackground(Output.robotColor);	
							button[Position.getGuiPosition(p)].setEnabled(false);
						}
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e){}
				}
			}
		}.start();
	}
	private void generateButtons(){
		for(int i=0;i<24;i++){
			button[i] = new JButton();
			button[i].addMouseListener(this);
			this.add(button[i]);			
		}
		button[0].setName("70");
		button[1].setName("73");
		button[2].setName("76");
		button[3].setName("61");
		button[4].setName("63");
		button[5].setName("65");
		button[6].setName("52");
		button[7].setName("53");
		button[8].setName("54");
		button[9].setName("40");
		button[10].setName("41");
		button[11].setName("42");
		button[12].setName("44");
		button[13].setName("45");
		button[14].setName("46");
		button[15].setName("32");
		button[16].setName("33");
		button[17].setName("34");
		button[18].setName("21");
		button[19].setName("23");
		button[20].setName("25");
		button[21].setName("10");
		button[22].setName("13");
		button[23].setName("16");

	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(230,230,230));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		drawField(g);
	}

	private void drawField(Graphics g){
	float a=this.getWidth()/8.0f;
	float b=this.getHeight()/9.0f;
	g.setColor(Color.gray);
	int s=20;
	button[0].setBounds((int) (1*a)-(s/2),(int) (1*b)-(s/2),s,s);
	button[1].setBounds((int) (4*a)-(s/2),(int) (1*b)-(s/2),s,s);
	button[2].setBounds((int) (7*a)-(s/2),(int) (1*b)-(s/2),s,s);
	button[9].setBounds((int) (1*a)-(s/2),(int) (4*b)-(s/2),s,s);
	button[14].setBounds((int) (7*a)-(s/2),(int) (4*b)-(s/2),s,s);
	button[21].setBounds((int) (1*a)-(s/2),(int) (7*b)-(s/2),s,s);
	button[22].setBounds((int) (4*a)-(s/2),(int) (7*b)-(s/2),s,s);
	button[23].setBounds((int) (7*a)-(s/2),(int) (7*b)-(s/2),s,s);

	button[3].setBounds((int) (2*a)-(s/2),(int) (2*b)-(s/2),s,s);
	button[4].setBounds((int) (4*a)-(s/2),(int) (2*b)-(s/2),s,s);
	button[5].setBounds((int) (6*a)-(s/2),(int) (2*b)-(s/2),s,s);
	button[10].setBounds((int) (2*a)-(s/2),(int) (4*b)-(s/2),s,s);
	button[13].setBounds((int) (6*a)-(s/2),(int) (4*b)-(s/2),s,s);
	button[18].setBounds((int) (2*a)-(s/2),(int) (6*b)-(s/2),s,s);
	button[19].setBounds((int) (4*a)-(s/2),(int) (6*b)-(s/2),s,s);
	button[20].setBounds((int) (6*a)-(s/2),(int) (6*b)-(s/2),s,s);

	button[6].setBounds((int) (3*a)-(s/2),(int) (3*b)-(s/2),s,s);
	button[7].setBounds((int) (4*a)-(s/2),(int) (3*b)-(s/2),s,s);
	button[8].setBounds((int) (5*a)-(s/2),(int) (3*b)-(s/2),s,s);
	button[11].setBounds((int) (3*a)-(s/2),(int) (4*b)-(s/2),s,s);
	button[12].setBounds((int) (5*a)-(s/2),(int) (4*b)-(s/2),s,s);
	button[15].setBounds((int) (3*a)-(s/2),(int) (5*b)-(s/2),s,s);
	button[16].setBounds((int) (4*a)-(s/2),(int) (5*b)-(s/2),s,s);
	button[17].setBounds((int) (5*a)-(s/2),(int) (5*b)-(s/2),s,s);
	
	g.drawLine((int) (1*a),(int) (1*b),(int) (1*a),(int) (7*b));
	g.drawLine((int) (2*a),(int) (2*b),(int) (2*a),(int) (6*b));
	g.drawLine((int) (3*a),(int) (3*b),(int) (3*a),(int) (5*b));
	g.drawLine((int) (4*a),(int) (1*b),(int) (4*a),(int) (3*b));
	g.drawLine((int) (4*a),(int) (5*b),(int) (4*a),(int) (7*b));
	g.drawLine((int) (5*a),(int) (3*b),(int) (5*a),(int) (5*b));
	g.drawLine((int) (6*a),(int) (2*b),(int) (6*a),(int) (6*b));
	g.drawLine((int) (7*a),(int) (1*b),(int) (7*a),(int) (7*b));

	g.drawLine((int) (1*a),(int) (1*b),(int) (7*a),(int) (1*b));
	g.drawLine((int) (2*a),(int) (2*b),(int) (6*a),(int) (2*b));
	g.drawLine((int) (3*a),(int) (3*b),(int) (5*a),(int) (3*b));
	g.drawLine((int) (1*a),(int) (4*b),(int) (3*a),(int) (4*b));
	g.drawLine((int) (5*a),(int) (4*b),(int) (7*a),(int) (4*b));
	g.drawLine((int) (3*a),(int) (5*b),(int) (5*a),(int) (5*b));
	g.drawLine((int) (2*a),(int) (6*b),(int) (6*a),(int) (6*b));
	g.drawLine((int) (1*a),(int) (7*b),(int) (7*a),(int) (7*b));
	
	g.setFont(new Font("Arial",Font.BOLD,15));
	g.drawString("Roboter",(int) (1*a)-(s/2)+s+5,(int) (8*b)+s-3);
	g.drawString("Mensch",(int) (4*a)-(s/2)+s+5,(int) (8*b)+s-3);
	g.setColor(Output.robotColor);
	g.fillRect((int) (1*a)-(s/2),(int) (8*b),s,s);
	g.setColor(Output.humanColor);
	g.fillRect((int) (4*a)-(s/2),(int) (8*b),s,s);
	
}

	
	public void mouseClicked(MouseEvent e) {
	System.out.println(e.getComponent().getName());
	Output.pressedButton = Integer.parseInt(e.getComponent().getName());	
}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	public Position getClickedButton(){
		for(Position p:Position.getAllPositions()){
			if(Output.pressedButton == p.getId())
				return p;
		}
		return null;
	}
}