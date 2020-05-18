package flicksArchive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBaseDatos {
	public Statement iniciar(){
		try {
			final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
			final String DB_URL = "jdbc:mysql://database-iis.cobadwnzalab.eu-central-1.rds.amazonaws.com";
			final String DB_SCHEMA = "flicksdb";
			Class.forName(JDBC_DRIVER);
			System.out.println("Class Loaded....");
			
			//  Database credentials
			final String USER = "flickr";
			final String PASS = "distanciafocal";
			
			Connection conn = DriverManager.getConnection(DB_URL + "/" + DB_SCHEMA,USER,PASS);
			System.out.println("Connected to the database....");
			Statement st = conn.createStatement();
			
			return st;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return null;
	}
}
