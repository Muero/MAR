package muehle;

import muehle.connection.BTConnection;
import muehle.connection.Connection;

/*

TODO Sound einf�gen
TODO Button einf�gen, um Anzahl Steine zu w�hlen
TODO Wahrscheinlichkeit als Help-Button

*/

public class Main2{
	
	public static void main(String[] args){
		Connection conn = new BTConnection();
		conn.openConnection();
	}
	
}