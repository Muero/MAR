package tests;

import muehle.Linker;
import muehle.gui.camera.ImageGrabber;

public class CameraTest2 {

	public static void main(String[] args){
		
		Linker.createObjects();
		ImageGrabber.startImaging();
		
		try {
			Thread.sleep(10000);	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ImageGrabber.createRawData(Linker.frame,Linker.alphaSize,Linker.alphaValue,Linker.fieldPositions);		

	}
	
}
