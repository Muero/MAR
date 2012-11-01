package tests;

import muehle.Linker;
import muehle.gui.camera.Camera;

public class CameraTest {

	public static void main(String[] args){
		Linker.createObjects();
		Linker.usealgorithm = false;
		Linker.robotMode = 1;
		Linker.usewebcam = true;		
		Linker.setupGamePlay();
		Linker.startupGui();
		
		while(true){
			Camera.createPlayerFieldClusterFromWebcamImage(Linker.frame,Linker.fieldPositions,Linker.alphaValue,Linker.alphaSize);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
