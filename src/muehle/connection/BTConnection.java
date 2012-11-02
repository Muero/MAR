
package muehle.connection;

import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.pc.comm.NXTConnector;
import muehle.model.Position;


public class BTConnection implements Connection {
	/**
	 *			70		  73         76
	 * 	
	 * 				61	  63 	65
	 * 					525354
	 * 			40	41	42  44  45   46
	 * 					323334
	 * 				21    23    25
	 * 
	 * 			10		  13		 16
	 * 
	 *          0   1   2  3  4  5    6
	 *          7   8   9
	 */
	//	Board.NXTBoard[0][0][1]
	
	public static int NXTAblage = 1; //X-Koordinate der Spielsteine

	public static Dimension getDistance(int a, int b) {
		return new Dimension(b % 10 - a % 10, (b / 10 - a / 10));
	}

	public static NXTConnector conn = new NXTConnector();
	public static NXTConnector conn2 = new NXTConnector();

	public static DataOutputStream dos;
	public static DataInputStream dis;
	public static DataOutputStream dos2;
	public static DataInputStream dis2;

	@Override
	public void openConnection() {
		conn = start(conn, "M1");
		dos = new DataOutputStream(conn.getOutputStream());
		dis = new DataInputStream(conn.getInputStream());
		conn2 = start(conn2, "M2");
		dos2 = new DataOutputStream(conn2.getOutputStream());
		dis2 = new DataInputStream(conn2.getInputStream());
		System.out.println("NXT's ready!!!");

		send(dos2,dis2,1);	//Pumpen beginnen
	}

	@Override
	public void closeConnection() {
		// shutown NXT
		send(dos,dis,-100,-100);
		send(dos2,dis2,-100);

		stop(conn, dis, dos);
		stop(conn2, dis2, dos2);
	}

	public void setStone(Position to) {
		send(dos2,dis2,4);	//auf
		Dimension a = getDistance(Position.p96.getId(),0);
		send(dos,dis,a.width+100,a.height);	//100, da er in Ablage geht
		send(dos2, dis2, 3);	//ab
		send(dos2, dis2, 5);	//schliessen
		send(dos2, dis2, 4);	//auf
		a = getDistance(0,to.getId());
		send(dos, dis,a.width+200,a.height);	//200, da er aus Ablage fährt
		send(dos2, dis2, 3);	//ab
		send(dos2, dis2, 6);	//öffnen
		send(dos2, dis2, 4);	//auf
		a = getDistance(to.getId(),Position.p96.getId());
		send(dos, dis, a.width, a.height);
	}
	
	public void waitForButton(){
		send(dos,dis,500,0);//Send starting
	}

	public void moveStone(Position from, Position to) {
		Dimension a;
		a = getDistance(Position.p96.getId(), from.getId());
		send(dos, dis, a.width, a.height);
		send(dos2, dis2, 3);	//ab
		send(dos2, dis2, 5);	//schliessen
		send(dos2, dis2, 4);	//auf
		a = getDistance(from.getId(), to.getId());
		System.out.println(a.getHeight());
		send(dos, dis, a.width, a.height);
		send(dos2, dis2, 3);	//ab
		send(dos2, dis2, 6);	//öffnen
		send(dos2, dis2, 4);	//auf
		a = getDistance(to.getId(),Position.p96.getId());
		send(dos, dis, a.width, a.height);
	}

	public void takeStone(Position from) {
		Dimension a;
		a = getDistance(Position.p96.getId(),from.getId());
		send(dos, dis,a.width,a.height);
		send(dos2,dis2,3);
		send(dos2,dis2,5);
		send(dos2,dis2,4);
		a = getDistance(from.getId(),0);
		send(dos, dis, a.width+300,a.height);
		send(dos2,dis2,3);
		send(dos2,dis2,6);
		send(dos2,dis2,4);
	}
	
	/**Connection is made with the NXT. If you couldn't connect with the NXT, it publishes a message.
	 * @param name is the name by which it connects 
	 * @return the connection
	 */
	public static NXTConnector start(NXTConnector conn, String name) {
		boolean connected = conn.connectTo(name);
		if (!connected) {
			System.err.println("Failed to connect to any NXT");
			System.exit(1);
		}
		return conn;
	}

	//Sends two commands to the NXT in form of two integers.
	public static void send(DataOutputStream dos, DataInputStream dis, int a,
			int b) {
		try {
			System.out.println("Sending " + a + " && " + b);
			dos.writeInt(a);
			dos.flush();
			dos.writeInt(b);
			dos.flush();
		} catch (IOException ioe) {
			System.out.println("IO Exception writing bytes:");
			System.out.println(ioe.getMessage());
		}
		if (a != -100)
			receive(dis);
	}

	//Sends one command to the NXT in form of an integer
	public static void send(DataOutputStream dos, DataInputStream dis, int a) {
		try {
			System.out.println("Sending " + a);
			dos.writeInt(a);
			dos.flush();
		} catch (IOException ioe) {
			System.out.println("IO Exception writing bytes:");
			System.out.println(ioe.getMessage());
		}
		if (a != -100)
			receive(dis);
	}

	//stops the Connection and orders the NXTs to stop.
	public static void stop(NXTConnector conn, DataInputStream dis,
			DataOutputStream dos) {
		try {
			dis.close();
			dos.close();
			conn.close();
		} catch (IOException ioe) {
			System.out.println("IOException closing connection:");
			System.out.println(ioe.getMessage());
		}
	}

	private static int receive(DataInputStream dis) {
		try {
			System.err.println("WAIT FOR RECEIVE");
			return dis.readInt();
		} catch (IOException ioe) {
			System.out.println("IO Exception reading bytes:");
			System.out.println(ioe.getMessage());
			return -1;
		}
	}


}
