package nxt2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class Main {

    public static TouchSensor s1 = new TouchSensor(SensorPort.S1);
    public static BTConnection btc = Bluetooth.waitForConnection();	           
    public static DataInputStream dis = btc.openDataInputStream();
    public static DataOutputStream dos = btc.openDataOutputStream();
    public static DataOutputStream dos2 = btc.openDataOutputStream();

	public static void main(String[] args) throws InterruptedException, IOException{
        LCD.clear();
        LCD.drawString("|-CONNECTED-|",1,0);
        LCD.refresh();
        
 		while(true){
			
           int m = dis.readInt();
           LCD.drawString("             ",2,3);
           LCD.drawInt(m,2,3);
           LCD.refresh();
           if(m == -100){
           	dis.close();
           	Thread.sleep(100);
           	btc.close();
           	LCD.clear();
           	break;
           }else{
           	//DO RUN
        	if(m == 3)
        		Calc_Source.moveDown();
        	else if(m == 4)
        		Calc_Source.moveUp();
        	else if(m == 5)
        		Calc_Source.getFigure();
        	else if(m == 6)
        		Calc_Source.setFigure();
           	dos.writeInt(0);
           	dos.flush();
           }
  		}
	}
}
