package camera;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.Buffer;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.JFrame;

public class Camera {

	private static Player player = null;

	public static Image image = null;
	public static BufferedImage imageBuffer = null;
	public static Color[][] imageColor = null;
	public static String file = "field2";
	public static String fileEnding = "jpg";

	public static MediaTracker mt;
	
	public static void generatePlayer(){
			MediaLocator ml = new MediaLocator("vfw://0");			
		try{
			player = Manager.createRealizedPlayer(ml);			
		}catch(Exception e){
			System.err.println("<-> Could not create player! Program will run without WebCam");
		}
		System.out.println("<-> Create player...");
		player.start();
		System.out.println("<-> Player created!");
	}
	public static void closePlayer(){
		player.close();
	}
	public static void importPictureFromPlayer(JFrame a){
		System.out.println("<-> get Picture from WebCam...");
		while (image == null) {
			FrameGrabbingControl fgc = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
			Buffer buf = fgc.grabFrame();
			BufferToImage bi = new BufferToImage((VideoFormat) buf.getFormat());
			image = bi.createImage(buf);
			imageBuffer = (BufferedImage) image;
				try {
					mt = new MediaTracker(a);
					mt.addImage(image, 0);
					mt.addImage(imageBuffer,0);
					try{
						mt.waitForAll();
					}catch(Exception e){
						System.out.println(e);
					}
				}catch(Exception e){System.err.println("Bildressource konnte nicht gefunden werden");return;}
			}
		
		savePicture(file,fileEnding);
	}
	public static void importPictureFromPC(JFrame a){
		try {
			image = ImageIO.read(new File(file+"."+fileEnding));
			imageBuffer = (BufferedImage) image;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			mt = new MediaTracker(a);
			mt.addImage(image, 0);
			mt.addImage(imageBuffer, 0);
			try{
				mt.waitForID(0);
			}catch(Exception e){
				System.out.println(e);
			}
		}catch(Exception e){System.err.println("Bildressource konnte nicht gefunden werden");return;}
	}
	
	public static Image getImage(){
		return image;
	}
	
	private static void savePicture(String name,String type){
		try {
			ImageIO.write(toBufferedImage(image), type, new File(
					name+"."+type));
			System.out.println("Image Saved!");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(0);
		}
		image = null;
	}
	private static BufferedImage toBufferedImage(Image i) {
		BufferedImage bi = new BufferedImage(i.getWidth(null),
				i.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.drawImage(i, 0, 0, null);
		return bi;
	}

	public static void readColor(JFrame a){
			imageColor = new Color[imageBuffer.getWidth(a)][imageBuffer.getHeight(a)];
			System.out.println("Creating IMAGE...");
			for(int i=0;i<imageColor.length;i++){
				for(int j=0;j<imageColor[0].length;j++){
					imageColor[i][j] = new Color(imageBuffer.getRGB(i, j));
				}
			}
			System.out.println("IMAGE created!");

	}
}
