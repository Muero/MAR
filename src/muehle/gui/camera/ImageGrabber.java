package muehle.gui.camera;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.MediaTracker;

import javax.imageio.ImageIO;
import javax.media.*;
import javax.media.protocol.*;
import javax.media.util.BufferToImage;
import javax.swing.*;
import java.awt.*; 
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.media.control.FormatControl;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;

import muehle.Linker;

public class ImageGrabber {
	
	private static final String file = "datei";
	private static final String fileEnding = "png";
	private Player p;
	private static Image image = null;
	private static BufferedImage imageBuffer = null;
	private static Color[][] imageColor;
	
	public static void startImaging(){
		System.err.println("Imaging started!");
		ImageGrabber t = new ImageGrabber();
		t.getCam();
	}	
	public static int[] createRawData(JFrame a,int alphaSize,int alphaValue,Point[] fieldpositions){
		
		Linker.allowRepaint = false;
		
		savePicture();
		importPicture(a);
		readColor(a);
		
		Linker.allowRepaint = true;
		
		return rawDataAlgorithm(alphaSize,alphaValue,fieldpositions);
	}
	public static BufferedImage getBufferedImage(){
		return imageBuffer;
	}
	public static Color[][] getImageColor(){
		return imageColor;
	}
	public static void readColor(JFrame a) {
		System.out.println("Creating ImageColors...");
		imageColor = new Color[imageBuffer.getWidth()][imageBuffer.getHeight()];
		for (int i = 0; i < imageColor.length; i++) {
			for (int j = 0; j < imageColor[0].length; j++) {
				imageColor[i][j] = new Color(imageBuffer.getRGB(i, j));
			}
		}
		System.out.println("ImageColors created!");
	}
	public static void importPicture(JFrame a){
		try {
			image = ImageIO.read(new File(file + "." + fileEnding));
			imageBuffer = (BufferedImage) image;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			MediaTracker mt = new MediaTracker(a);
			mt.addImage(image, 0);
			mt.addImage(imageBuffer, 0);
			try {
				mt.waitForID(0);
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.err.println("Bildressource konnte nicht gefunden werden");
			return;
		}
		System.out.println("Bild importiert");
	}
	public static void takePicture(){
		Linker.takePicture = true;
		while(Linker.takePicture){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static int[] rawDataAlgorithm(int alphaSize,int alphaValue,Point[] fieldpositions){
		int[] fieldMap = new int[24];
		for (int i = 0; i < 24; i++) {

			int sumR = 0;
			int sumG = 0;
			int sumB = 0;
			for (int j = -alphaSize/2; j < alphaSize/2; j++) {
				for (int k = -alphaSize/2; k < alphaSize/2; k++) {
					try {
						sumR += imageColor[fieldpositions[i].x][fieldpositions[i].y]
								.getRed();
						sumG += imageColor[fieldpositions[i].x][fieldpositions[i].y]
								.getGreen();
						sumB += imageColor[fieldpositions[i].x][fieldpositions[i].y]
								.getBlue();
					} catch (Exception e) {
					}
				}
			}
			int R = sumR / (alphaSize * alphaSize);
			int G = sumG / (alphaSize * alphaSize);
			int B = sumB / (alphaSize * alphaSize);
			
			if(R>alphaValue && G<255-alphaValue && B<255-alphaValue)
				fieldMap[i] = 1;		//1 = Rot
			else if(R<255-alphaValue && G>100+alphaValue && B>255-alphaValue)
				fieldMap[i] = 2;		//2 = Blau
			else
				fieldMap[i] = 0;		//0 = Schwarz
			System.err.println(alphaValue);
			System.err.println(i+": "+fieldMap[i]+"  -  "+R+"|"+G+"|"+B);
			
		}
		return fieldMap;
	}
	private void getCam(){
		try{
			 
			/* Grab the default web cam*/
			MediaLocator ml = new MediaLocator("vfw://0");

			/* Create my data source */
			DataSource ds = Manager.createDataSource(ml);
			
	 		requestFormatResolution(ds);
	 		
			/* Create & start my player */
			p = Manager.createRealizedPlayer(ds);
			
			p.start();
			Thread.sleep(1000);

			/* code for creating a JFrame and adding the visual component to it */
			JFrame jfrm=new JFrame("Testing Webcam");
					jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					if(p.getVisualComponent()!=null)jfrm.getContentPane().add(p.getVisualComponent());
					if(p.getControlPanelComponent()!=null)jfrm.getContentPane().add
							(p.getControlPanelComponent(),BorderLayout.SOUTH);
					jfrm.pack();
					jfrm.setLocationRelativeTo(null);
					jfrm.setVisible(true);
					jfrm.setAlwaysOnTop(true);
					jfrm.setBounds(0,Linker.guiSize.height+30,640,480);

					
			}catch(Exception e){
				e.printStackTrace();		 
			}
		new Thread(){
			public void run(){

				while(true){
			try {
				Thread.sleep(1000);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if(Linker.takePicture){
				System.err.print(".");
				FrameGrabbingControl fgc = (FrameGrabbingControl) p.getControl("javax.media.control.FrameGrabbingControl");
				Buffer b = fgc.grabFrame();
		        try {
		        	System.err.print("1-");
		            BufferToImage bbtoi = new BufferToImage((VideoFormat) b.getFormat());
		        	System.err.print("2-");
		            Image img = bbtoi.createImage(b);
		        	System.err.print("3-");
		            ImageIO.write((RenderedImage) img, fileEnding, new File(file+"."+fileEnding));
		        	System.err.print("4-");
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        Linker.takePicture = false;
				}
				}
			}
		}.start();
		
	}   		
	private boolean requestFormatResolution(DataSource ds) {
 
		if (ds instanceof CaptureDevice) {
			FormatControl[] fcs = ((CaptureDevice) ds).getFormatControls();
			for (FormatControl fc : fcs) {
				Format[] formats = ((FormatControl) fc).getSupportedFormats();
				for (Format format : formats) {
					if ((format instanceof VideoFormat) &&
						(((VideoFormat)format).getSize().getHeight() <= 240) &&
						(((VideoFormat)format).getSize().getWidth()  <= 320)) {
							((FormatControl) fc).setFormat(format);
							return true;
					}
				}
			}
		}
		return false;
	}
	private static void savePicture(){
		Linker.takePicture = true;
		System.out.println("Saving");
		while(Linker.takePicture){
			System.out.print(".");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
