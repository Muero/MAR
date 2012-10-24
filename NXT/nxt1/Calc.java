package nxt1;

public class Calc {
	
	
	public static int actualPos = 0;
	
	public static void move(int a,int b) throws InterruptedException{	//von zu
		int numofloops1 = 0;
		int numofloops2 = 0;
		
		numofloops1 = (b%10) - (a%10);
		numofloops2 = b/10 - a/10;
		Calc_Source.moveA(b>0?true:false,numofloops1);
		Calc_Source.moveB(b>0?true:false,numofloops2);
		;
	}
/*	public static void move(int a){			//zu
		;
	}
	public static void move(){				//zurück
		;
	}
*/	
	public static void getBluetooth(){
		;
	}
	
	
	
	
	
}
