package muehle;

import muehle.connection.BTConnection;
import muehle.connection.Connection;

public class Main2 {

	/**
	 * This class is used if the Connection to the computer did not work
	 * properly, so we can repeat it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = new BTConnection();
		conn.openConnection();
	}
}
