package muehle;

import muehle.connection.BTConnection;
import muehle.connection.Connection;

/*

TODO Sound einfügen
TODO Button einfügen, um Anzahl Steine zu wählen
TODO Wahrscheinlichkeit als Help-Button

*/

public class Main2{
	
	public static void main(String[] args){
		Connection conn = new BTConnection();
		conn.openConnection();
	}
	
}