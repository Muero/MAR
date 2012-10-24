package nxt2;

import lejos.nxt.MotorPort;
import lejos.nxt.addon.RCXMotor;

public class Calc_Source {

	public static void moveDown() throws InterruptedException{
		RCXMotor m1 = new RCXMotor(MotorPort.B);
		m1.setPower(100);
		m1.backward();
		Thread.sleep(400);
		m1.stop();
		Thread.sleep(700);
	}
	public static void moveUp() throws InterruptedException{
		RCXMotor m1 = new RCXMotor(MotorPort.B);
		m1.setPower(100);
		m1.forward();
		Thread.sleep(600);
		m1.setPower(100);
	}
	public static void getFigure() throws InterruptedException{
		RCXMotor m1 = new RCXMotor(MotorPort.A);
		m1.setPower(100);
		m1.forward();
		Thread.sleep(200);
	}
	public static void setFigure() throws InterruptedException{
		RCXMotor m1 = new RCXMotor(MotorPort.A);
		m1.setPower(100);
		m1.backward();
		Thread.sleep(200);
	}
}
