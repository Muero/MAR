package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panel5 extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public Panel5(){
		this.setLayout(null);
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(230,230,230));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		drawField(g);
	}

	private void drawField(Graphics g){
		float a=this.getWidth()/8.0f;
		float b=this.getHeight()/9.0f;
		System.out.println(a);
		System.out.println(b);
		g.setColor(Color.gray);
		int s=20;
		g.fillRect((int) (1*a)-(s/2),(int) (1*b)-(s/2),s,s);
		g.fillRect((int) (4*a)-(s/2),(int) (1*b)-(s/2),s,s);
		g.fillRect((int) (7*a)-(s/2),(int) (1*b)-(s/2),s,s);
		g.fillRect((int) (1*a)-(s/2),(int) (4*b)-(s/2),s,s);
		g.fillRect((int) (7*a)-(s/2),(int) (4*b)-(s/2),s,s);
		g.fillRect((int) (1*a)-(s/2),(int) (7*b)-(s/2),s,s);
		g.fillRect((int) (4*a)-(s/2),(int) (7*b)-(s/2),s,s);
		g.fillRect((int) (7*a)-(s/2),(int) (7*b)-(s/2),s,s);

		g.fillRect((int) (2*a)-(s/2),(int) (2*b)-(s/2),s,s);
		g.fillRect((int) (4*a)-(s/2),(int) (2*b)-(s/2),s,s);
		g.fillRect((int) (6*a)-(s/2),(int) (2*b)-(s/2),s,s);
		g.fillRect((int) (2*a)-(s/2),(int) (4*b)-(s/2),s,s);
		g.fillRect((int) (6*a)-(s/2),(int) (4*b)-(s/2),s,s);
		g.fillRect((int) (2*a)-(s/2),(int) (6*b)-(s/2),s,s);
		g.fillRect((int) (4*a)-(s/2),(int) (6*b)-(s/2),s,s);
		g.fillRect((int) (6*a)-(s/2),(int) (6*b)-(s/2),s,s);

		g.fillRect((int) (3*a)-(s/2),(int) (3*b)-(s/2),s,s);
		g.fillRect((int) (4*a)-(s/2),(int) (3*b)-(s/2),s,s);
		g.fillRect((int) (5*a)-(s/2),(int) (3*b)-(s/2),s,s);
		g.fillRect((int) (3*a)-(s/2),(int) (4*b)-(s/2),s,s);
		g.fillRect((int) (5*a)-(s/2),(int) (4*b)-(s/2),s,s);
		g.fillRect((int) (3*a)-(s/2),(int) (5*b)-(s/2),s,s);
		g.fillRect((int) (4*a)-(s/2),(int) (5*b)-(s/2),s,s);
		g.fillRect((int) (5*a)-(s/2),(int) (5*b)-(s/2),s,s);
		
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
		float c=a/10;

		g.drawString("Wahrscheinlichkeit",((int) ((1*a))-s/2)+((int) (c*12)),(int) (8*b)+s-3);
		g.setColor(Color.black);
		g.fillRect((int) ((1*a))-s/2,(int) (8*b),(int) (c*10),s);
		for(int i=0;i<10;i++){
			g.setColor(Output.probabilityColor[i]);
			g.fillRect((int) ((1*a)+(i*c))-s/2,(int) (8*b),(int) (c),s);
			g.setColor(Color.black);
			g.drawRect((int) ((1*a)+(i*c))-s/2,(int) (8*b),(int) (c),s);			
		}

	}

}