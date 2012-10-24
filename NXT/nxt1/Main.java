package nxt1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;


public class Main {

	/**
	 *			70		  73         76
	 * 	
	 * 				61	  63 	65
	 * 					525354
	 * 			40	41	42  44  45   46
	 * 					323334
	 * 				21    23    25
	 * 
	 * 			10		  13		 16
	 *           0   1   2 3 4   5    6
	 */
	
	
	public static int ablage = 0;
	public static int muehleablage = 0;
	public static BTConnection btc = Bluetooth.waitForConnection();	    
	public static DataInputStream dis = btc.openDataInputStream();
	public static DataOutputStream dos = btc.openDataOutputStream();
	public static TouchSensor s3 = new TouchSensor(SensorPort.S3);
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
	         LCD.drawString("Waiting for PC...",0,0);
	         LCD.refresh();
	                
	         
	         
      
	         LCD.clear();
	         LCD.drawString("|-CONNECTED-|",1,0);
	         LCD.refresh();

	 		while(true){
	            int m = dis.readInt();
	            int n = dis.readInt();
	 			
	            LCD.drawString("             ",2,3);
	            LCD.drawString("             ",2,4);
	            LCD.drawInt(m,2,3);
	            LCD.drawInt(n,2,4);
	            LCD.refresh();
	            if((m == -100 && n == -100)){
	            	dis.close();
	            	Thread.sleep(100);
	            	btc.close();
	            	LCD.clear();
	            	break;
	            }else if(m == 500){//READY FOR TOUCH
	            	while(!s3.isPressed()){}            	
	            	dos.writeInt(0);
	            	dos.flush();
	            }else if(m>=290){//Stein in Ablage geben
	            	m = m-300;
	            	LCD.drawInt(m,0,0);
	            	if(muehleablage<7)
	            		m = m+muehleablage;
	            	else{
	            		m = m+muehleablage-7;
	            	}
	            	Thread.sleep(100);
	            	Calc_Source.moveA(m<0?false:true,Math.abs(m));//WIDTH
	            	Thread.sleep(100);
	            	Calc_Source.moveB(n<0?false:true,Math.abs(n));//HEIGHT
	            	Thread.sleep(100);
	            	dos.writeInt(0);
	            	dos.flush();
	            	muehleablage++;
	            	
	            }else if(m>=190){//Roboter aus Ablage fahren
	            	m = m-200;
	            	LCD.drawInt(m,0,0);
	            	if(ablage<7)
	            		m = m-ablage;
	            	else{
	            		m = m-ablage+7;
	            	}
	            	Thread.sleep(100);
	            	Calc_Source.moveA(m<0?false:true,Math.abs(m));//WIDTH
	            	Thread.sleep(100);
	            	Calc_Source.moveB(n<0?false:true,Math.abs(n),true);//HEIGHT
	            	Thread.sleep(100);
	            	dos.writeInt(0);
	            	dos.flush();	            	
	            	ablage++;
	            	
	            }else if(m>=90){//Roboter in Ablage fahren
	            	m = m-100;
	            	LCD.drawInt(m,0,0);
	            	if(ablage<7)
	            		m = m+ablage;
	            	else{
	            		m = m+ablage-7;
	            	}
	            	Thread.sleep(100);
	            	Calc_Source.moveA(m<0?false:true,Math.abs(m));//WIDTH
	            	Thread.sleep(100);
	            	Calc_Source.moveB(n<0?false:true,Math.abs(n),true);//HEIGHT
	            	Thread.sleep(100);
	            	dos.writeInt(0);
	            	dos.flush();
	            	
	            }else{//Normal
	            	Thread.sleep(100);
	            	Calc_Source.moveA(m<0?false:true,Math.abs(m));//WIDTH
	            	Thread.sleep(100);
	            	LCD.clear();
	            	LCD.drawString("moveB",0,0);
	            	LCD.drawInt(Math.abs(n),0,1);
	            	Calc_Source.moveB(n<0?false:true,Math.abs(n));//HEIGHT
	            	Thread.sleep(100);
	            	dos.writeInt(0);
	            	dos.flush();
	            }
	   		}
	   }
}
