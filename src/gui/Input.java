package gui;

import muehle.Main;
import camera.Camera;

public class Input {
	public static void startGui(Frame frame){
		if(Output.usewebcam){
			Camera.generatePlayer();
			Camera.importPictureFromPlayer(frame);
		}else{
			Camera.importPictureFromPC(frame);
		}
		Camera.readColor(frame);
		if(Output.usewebcam)		
			Camera.closePlayer();
		frame.setGuiMode(1);
		frame.waitForStart();
	}
	
	public static void startIngameGui(Frame frame){
		if(Output.usewebcam){
			Main.frame.setGuiMode(2);
		}else{
			Main.frame.setGuiMode(3);
		}
	}
	
}
