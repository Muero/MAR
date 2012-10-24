package camera;

import gui.Output;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
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
	public static String file = "field";
	public static String fileEnding = "jpg";

	public static MediaTracker mt;
	public static MediaLocator ml;

	public static void generatePlayer() {
		ml = new MediaLocator("vfw://0");
		try {
			player = Manager.createRealizedPlayer(ml);
		} catch (Exception e) {
		}
		System.out.println("<-> Create player...");
		player.start();
		System.out.println("<-> Player created!");
	}

	public static void closePlayer() {
		player.close();
	}

	public static void importPictureFromPlayer(JFrame a) {
		generatePlayer();
		System.out.println("<-> get Picture from WebCam...");
		image = null;
		imageBuffer = null;
		while ((image == null) || (imageBuffer == null)) {
			player.start();
			FrameGrabbingControl fgc = (FrameGrabbingControl) player
					.getControl("javax.media.control.FrameGrabbingControl");
			Buffer buf = fgc.grabFrame();
			BufferToImage bi = new BufferToImage((VideoFormat) buf.getFormat());
			image = bi.createImage(buf);
			imageBuffer = (BufferedImage) image;
			try {
				mt = new MediaTracker(a);
				mt.addImage(image, 0);
				mt.addImage(imageBuffer, 0);
				try {
					mt.waitForAll();
				} catch (Exception e) {
					System.out.println(e);
				}
			} catch (Exception e) {
				System.err
						.println("Bildressource konnte nicht gefunden werden");
				return;
			}
		}
		System.out.println("<-> Image:        Width: " + image.getWidth(a)
				+ "  Height: " + image.getHeight(a));
		System.out.println("<-> ImageBuffer:  Width: " + imageBuffer.getWidth()
				+ "  Height: " + imageBuffer.getHeight());
		savePicture(file, fileEnding);
	}

	public static void importPictureFromPC(JFrame a) {
		try {
			image = ImageIO.read(new File(file + "." + fileEnding));
			imageBuffer = (BufferedImage) image;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			mt = new MediaTracker(a);
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
	}

	public static Image getImage() {
		return image;
	}

	public static void savePicture(String name, String type) {
		try {
			ImageIO.write(imageBuffer, type, new File(name + "." + type));
			System.out.println("Image Saved!");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(0);
		}
	}

	private static BufferedImage toBufferedImage(Image i) {
		BufferedImage bi = new BufferedImage(i.getWidth(null),
				i.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.drawImage(i, 0, 0, null);
		return bi;
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

	public static boolean[] createPlayerFieldClusterFromWebcamImage(JFrame a,
			BufferedImage img, Point[] fieldpositions, int alphaValue,
			int alphaSize) {
		boolean[] fieldMap = new boolean[24];
		importPictureFromPlayer(a);
		readColor(a);
		int sumRs = 0;
		int sumGs = 0;
		int sumBs = 0;
		for (int j = -alphaSize / 2; j < alphaSize / 2; j++) {
			for (int k = -alphaSize / 2; k < alphaSize / 2; k++) {
				try {
					sumRs += imageColor[Output.starterPosition.x + j][Output.starterPosition.y
							+ k].getRed();
					sumGs += imageColor[Output.starterPosition.x + j][Output.starterPosition.y
							+ k].getGreen();
					sumBs += imageColor[Output.starterPosition.x + j][Output.starterPosition.y
							+ k].getBlue();
				} catch (Exception e) {
				}
			}
		}
		int Rs = sumRs / (alphaSize * alphaSize);
		int Gs = sumGs / (alphaSize * alphaSize);
		int Bs = sumBs / (alphaSize * alphaSize);
		int alpha = Output.alphaValue;

		for (int i = 0; i < 24; i++) {

			int sumR = 0;
			int sumG = 0;
			int sumB = 0;
			for (int j = 0; j < alphaSize; j++) {
				for (int k = 0; k < alphaSize; k++) {
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
			if (R < Rs + alpha && G < Gs + alpha && B < Bs + alpha)
				fieldMap[i] = true;
			else
				fieldMap[i] = false;
			if (R == 0 && G == 0 && B == 0)
				fieldMap[i] = false;
		}
		return fieldMap;
	}

}
