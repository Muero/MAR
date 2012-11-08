package muehle;

import muehle.connection.BTConnection;
import muehle.connection.Connection;

public class Main2 {
	
	public static void main(String[] args){
		
		Connection conn = new BTConnection();
		conn.openConnection();
		
	}

}
