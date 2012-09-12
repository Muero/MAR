package camera;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
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

import muehle.Main;

public class Camera {

	private static Player player = null;
	public static Image image = null;

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
	public static void importPicture(String name,String type){
		while (image == null) {
			System.out.println("<-> get Picture from WebCam...");
			FrameGrabbingControl fgc = (FrameGrabbingControl) player
					.getControl("javax.media.control.FrameGrabbingControl");
			Buffer buf = fgc.grabFrame();

			BufferToImage bi = new BufferToImage((VideoFormat) buf.getFormat());
			image = bi.createImage(buf);
		}	
		savePicture(name,type);
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

	public static void readColor(){
		try {
			Main.img = ImageIO.read(new File(Main.file+"."+Main.fileEnding));
			Main.imgcolor = new Color[Main.img.getWidth()][Main.img.getHeight()];
			for(int i=0;i<Main.img.getWidth();i++){
				for(int j=0;j<Main.img.getHeight();j++){
					Main.imgcolor[i][j] = new Color(Main.img.getRGB(i, j));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
