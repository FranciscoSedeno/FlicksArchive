package flicksArchive;

import java.sql.*;
import java.util.*;

public class Conexion {
	 final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
     final String DB_URL = "jdbc:mysql://database-iis.cobadwnzalab.eu-central-1.rds.amazonaws.com";
     final String DB_SCHEMA = "flicksdb";
     private Connection conn;
     final String USER = "flickr";
     final String PASS = "distanciafocal";
     private String nombre;
     
     
	public Conexion(String nombre) {
		
		try {
			this.nombre = nombre;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL + "/" + DB_SCHEMA,USER,PASS);
			
			Statement st= conn.createStatement();
			ResultSet rs= st.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'flicksdb' AND  TABLE_NAME = '"+nombre+"';");
			if(!rs.next()) {
				st.executeUpdate("CREATE TABLE "+nombre+" (ID int(11), Estado int(1), Favorito Bit(1), Nota int(2),PRIMARY KEY (ID));");
			}
			
			st.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void inicializarDatos(Map<Integer,Elemento> lista,Set<String> plataformas) throws SQLException {
		Statement st= conn.createStatement();
		ResultSet rs= st.executeQuery("SELECT Titulo,FechaPublicacion,FechaRetirada,Descripcion,URL_Imagen,c.ID, Estado, Favorito, Nota,Nombre_Plataforma FROM "+nombre+" u JOIN Catalogo c on (c.ID=u.ID) ;");
		while(rs.next()) {
			lista.put(rs.getInt(6),new Elemento(rs.getString(1),rs.getDate(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(10),rs.getInt(7),rs.getBoolean(8),rs.getInt(9)));
			plataformas.add(rs.getString(10).toUpperCase());
		}
		st.close();
		
	}
	public List<Tupla> buscarPorTitulo(String titulo) throws SQLException{
		List<Tupla> sol = new LinkedList<>();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT ID, Titulo, URL_Imagen FROM Catalogo WHERE UPPER(Titulo) LIKE '%" + titulo.toUpperCase() + "%';");
		while(rs.next()) {
			sol.add(new Tupla(rs.getString(2),rs.getInt(1),rs.getString(3)));
		}
		st.close();
		return sol;
	}
	public Elemento buscarElemento(int id) throws SQLException {
		Statement st = conn.createStatement();
		Elemento elem=null;
		ResultSet rs = st.executeQuery("SELECT Titulo,FechaPublicacion,FechaRetirada,Descripcion,ID, Nombre_Plataforma FROM Catalogo WHERE  ID="+id+";");
		if(rs.next()) {
			elem=new Elemento(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getInt(6),rs.getString(7));
		}
		st.close();
		return elem;
	}
	public void actualizar(List<Elemento> l) throws SQLException {
		Statement st= conn.createStatement();
		
		st.executeUpdate("DELETE  FROM "+nombre+" ;");
		
		
		for(Elemento e:l) {
			st.execute("INSERT INTO "+nombre+" VALUES ("+e.valores()+" );");
		}
		st.close();
	}
	
	public void finalizarConexion() throws SQLException{
		conn.close();
	}
	
}

