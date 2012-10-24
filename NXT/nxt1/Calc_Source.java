package nxt1;

import lejos.nxt.LCD;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.RCXMotor;
import lejos.nxt.addon.RCXRotationSensor;

public class Calc_Source {
	
	public static final int rotationA = 11;
	public static final int rotationB = 64;	
	
	public static RCXMotor m1 = new RCXMotor(MotorPort.A);
	public static RCXMotor m2 = new RCXMotor(MotorPort.B);
	public static RCXRotationSensor r1 = new RCXRotationSensor(SensorPort.S1);
	public static RCXRotationSensor r2 = new RCXRotationSensor(SensorPort.S2);
	
	public static void moveA(boolean direction,int numberofloops) throws InterruptedException{//OBEN
		r1.resetTachoCount();
		Thread.sleep(100);
		if(numberofloops > 0){
			m1.setPower(80);
			if(direction)
				m1.forward();
			else
				m1.backward();
		
			for(int i = 0;i<numberofloops;i++){
				r1.resetTachoCount();
				while(Math.abs(r1.getRawTachoCount())<rotationA){
					LCD.drawString(r1.getRawTachoCount()+"",0,2);
				}
			}
			m1.stop();
		}
	}
	public static void moveB(boolean direction,int numberofloops) throws InterruptedException{
		r2.resetTachoCount();
		if(numberofloops > 0){
			m2.setPower(100);
			if(direction)
				m2.forward() ;
			else
				m2.backward();
		
			for(int i=0;i<numberofloops;i++){
				r2.resetTachoCount();
				while(Math.abs(r2.getRawTachoCount())<rotationB){
					LCD.drawString(r2.getRawTachoCount()+"",0,3);
				}
			}
			m2.stop();
		}
	}
	public static void moveB(boolean direction,int numberofloops,boolean special) throws InterruptedException{
		r2.resetTachoCount();
		if(numberofloops > 0){
			m2.setPower(100);
			if(direction)
				m2.forward() ;
			else
				m2.backward();
		
			for(int i=0;i<numberofloops;i++){
				r2.resetTachoCount();
				while(Math.abs(r2.getRawTachoCount())<rotationB){
					LCD.drawString(r2.getRawTachoCount()+"",0,3);
				}
			}
			if(Main.ablage > 6){
				r2.resetTachoCount();
				while(Math.abs(r2.getRawTachoCount())<((rotationB/2)-10)){
					LCD.drawString(r2.getRawTachoCount()+"",0,3);
				}
			}
			m2.stop();
		}
	}

	public static void moveAB(boolean directionA,boolean directionB,int numberofloopsA,int numberofloopsB) throws InterruptedException{
		RCXMotor m1 = new RCXMotor(MotorPort.A);
		RCXMotor m2 = new RCXMotor(MotorPort.B);
		RCXRotationSensor r1 = new RCXRotationSensor(SensorPort.S1);
		RCXRotationSensor r2 = new RCXRotationSensor(SensorPort.S2);
		
		if((numberofloopsA != 0) && (numberofloopsB != 0)){

			r1.resetTachoCount();
			r2.resetTachoCount();

			m1.setPower(80);
			if(directionA)
				m1.forward() ;
			else
				m1.backward();
			
			m2.setPower(100);
			if(directionB)
				m2.forward() ;
			else
				m2.backward();
		
			int loop1=0;
			int loop2=0;

			boolean a = true;
			while(a){

				if(Math.abs(r1.getRawTachoCount()) >= rotationA)
					loop1++;
				if(Math.abs(r2.getRawTachoCount()) >= rotationB)
					loop2++;
				
				if(loop1 == numberofloopsA)
					m1.stop();
				if(loop2 == numberofloopsB)
					m2.stop();
				
				a = (m1.isMoving() && m2.isMoving());
				LCD.drawString(a?"TRUE":"FALSE",0,0);
			}
			LCD.drawString("END OF WHILE",0,0);
		}else{
			if(numberofloopsB == 0)
				moveA(directionA,numberofloopsA);
			else
				moveB(directionB,numberofloopsB);
		}
		
	}
}
