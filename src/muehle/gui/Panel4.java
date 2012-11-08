package muehle.gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import muehle.Linker;
import muehle.model.Board;
import muehle.model.Position;

public class Panel4 extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;

	public JButton[] button = new JButton[24];
	
	private Board board;
	
	private Color resourceColor[] = {Color.red,new Color(0,100,0)};
	private String resourceText[] = {"Analysiere Bild...","Bild analysiert!  -  Warte auf Eingabe"};
	private int bildMode = 1;
	
	public Panel4(Board board){
		this.board = board;
		this.setLayout(null);
		generateButtons();
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(230,230,230));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		drawField(g);
	}
	public void thread(){
		for(Position p : Position.getAllPositions()){
			if(Linker.usewebcam)
				button[Frame.getGuiPosition(p)].setEnabled(false);
			if(board.getColor(p) == muehle.model.Board.StoneColor.BLACK)
				button[Frame.getGuiPosition(p)].setBackground(Linker.opponentColor);
			else if(board.getColor(p) == muehle.model.Board.StoneColor.WHITE)
				button[Frame.getGuiPosition(p)].setBackground(Linker.humanColor);			
			else
				button[Frame.getGuiPosition(p)].setBackground(null);
				
		}
		
	}

	private void generateButtons(){
		for(int i=0;i<24;i++){
			button[i] = new JButton();
			button[i].setBackground(Color.gray);
			this.add(button[i]);
		}
			for(Position p : Position.getAllPositions()){
				button[Frame.getGuiPosition(p)].setName(""+p.getId());
				button[Frame.getGuiPosition(p)].addMouseListener(this);
			}
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
		
		if(Linker.usewebcam){
			g.setColor(resourceColor[bildMode]);
			g.drawString(resourceText[bildMode],(int) (0*a)-(s/2)+s+5,(int) (8*b)-9);			
		}
		
		g.setColor(Color.black);
		g.drawLine(0,(int) (8*b)-7,this.getWidth(),(int) (8*b)-7);
		g.setColor(Linker.humanColor);
		g.fillRect((int) (1*a)-(s/2),(int) (8*b),s,s);
		g.setColor(Linker.opponentColor);
		g.fillRect((int) (4*a)-(s/2),(int) (8*b),s,s);
		g.setColor(Color.black);
	}
	
	public Position getLayedButton(){
		
		for(Position p:Position.getAllPositions()){
			if(Linker.pressedButton == p.getId())
				return p;
		}
		return null;
	}

	public void mouseClicked(MouseEvent arg0) {
		Linker.pressedButton = Integer.parseInt(arg0.getComponent().getName());
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

}