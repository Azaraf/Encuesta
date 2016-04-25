package org.jcapiz.inspira.database;

import java.sql.Connection;
import java.sql.SQLException;

public class Servicios { // Ya sé que debería llamarse Servicio y que debería ser abstracta incluso, implementando cada servicio extendiendola.

	private DatabaseConnection db;
	
	public Servicios(String host, String database, String usr, String psswd){
		db = new DatabaseConnection(host,database,usr,psswd);
	}
	
	public boolean validarBoleta(String boleta){
		boolean result = false;
		try{
			Connection con = db.getConnection();
			boolean rs = con.createStatement().execute("");
		}catch(SQLException e){
			
		}
		return result;
	}
}
