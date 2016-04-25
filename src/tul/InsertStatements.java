package tul;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.jcapiz.inspira.database.DatabaseConnection;

public class InsertStatements {

	DatabaseConnection db;
	
	public InsertStatements(){
		db = new DatabaseConnection("localhost","Demanda_Unidades_Aprendizaje","jirachi","sharPedo319");
	}
	
	public void insertaAlumnos(){
		File f = new File("chu");
		try{
			BufferedReader entrada = new BufferedReader(new FileReader(f));
			String rawString;
			String[] columns;
			String[] nombres;
			while((rawString = entrada.readLine()) != null){
				columns = rawString.split(",");
				nombres = columns[3].split(" ");
				try{
					CallableStatement cstmnt = db.getConnection().prepareCall("{call insertaAlumno(?,?,?,?,?)}");
					cstmnt.setString(1, columns[0]);
					cstmnt.setString(2, columns[1]);
					cstmnt.setString(3, columns[2]);
					cstmnt.setString(4, columns[4]);
					cstmnt.registerOutParameter(5, java.sql.Types.VARCHAR);
					cstmnt.executeUpdate();
					for(String str : nombres){
						cstmnt = db.getConnection().prepareCall("{call insertaNombreAlumno(?,?,?)}");
						cstmnt.setString(1, columns[0]);
						cstmnt.setString(2, str);
						cstmnt.registerOutParameter(3, Types.VARCHAR);
						cstmnt.executeUpdate();
					}
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			entrada.close();
		}catch(IOException e){
			File fi = new File("Te digo on toy.txt");
			try{
				DataOutputStream salida = new DataOutputStream(new FileOutputStream(fi));
				salida.writeChars("TULA MESTA");
				salida.close();
			}catch(IOException ex){
				ex.printStackTrace();
			}
			System.out.println("PNG***************************");
			e.printStackTrace();
		}
	}
	
	public void insertaAcademias(){
		File f = new File("tul.csv");
		try{
			BufferedReader entrada = new BufferedReader(new FileReader(f));
			String rawString;
			while((rawString = entrada.readLine()) != null){
				try{
					CallableStatement cstmnt = db.getConnection().prepareCall("{call insertaAcademia(?,?)}");
					cstmnt.setString(1, rawString);
					cstmnt.registerOutParameter(2, java.sql.Types.VARCHAR);
					cstmnt.executeUpdate();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			entrada.close();
		}catch(IOException e){
			System.out.println("PNG***************************");
			e.printStackTrace();
		}
	}
	
	public void insertaMaterias(){
		File f = new File("materias.csv");
		try{
			BufferedReader entrada = new BufferedReader(new FileReader(f));
			String rawString;
			while((rawString = entrada.readLine()) != null){
				try{
					CallableStatement cstmnt = db.getConnection().prepareCall("{call insertaUnidad_Aprendizaje(?,?)}");
					cstmnt.setString(1, rawString);
					cstmnt.registerOutParameter(2, java.sql.Types.VARCHAR);
					cstmnt.executeUpdate();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			entrada.close();
		}catch(IOException e){
			System.out.println("PNG***************************");
			e.printStackTrace();
		}
	}
}