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
			this.nombre = nombre.toUpperCase();
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL + "/" + DB_SCHEMA,USER,PASS);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void inicializarDatos(Map<Integer,Elemento> lista,Set<String> plataformas,Filtro filtro) throws SQLException {
		Statement st= conn.createStatement();
		
		ResultSet rs= st.executeQuery("SELECT NombreEtiqueta FROM Etiqueta WHERE NombreUsuario LIKE '"+nombre+"' ;");
		while(rs.next()) {
			filtro.pedirEtiqueta(rs.getString(1));
		}
		
		rs= st.executeQuery("SELECT Titulo,FechaPublicacion,FechaRetirada,Descripcion,URL_Imagen,c.ID, Estado, Favorito, Nota,Nombre_Plataforma, EP1, EP2, EP3, ED1, ED2, ED3, TOTAL, PROGRESO FROM Usuario u JOIN Catalogo c on (c.ID=u.ID) WHERE u.NombreUsuario LIKE '"+nombre+"' ;");
		
		while(rs.next()) {
			Elemento e = new Elemento(rs.getString(1),rs.getDate(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(10),rs.getInt(17),rs.getInt(7),rs.getBoolean(8),rs.getInt(9), rs.getInt(18));
			lista.put(rs.getInt(6),e);
			
			for(int i = 11;i<17 ;i++) {
				String aux= rs.getString(i);
				if(aux!=null) {
					if(i<=13) {
						//PERSONALIZADAS
						e.anadirEtiqueta(filtro, aux);
						
					}else {
						//DEFECTO 
						e.anadirEtiquetaDefecto(filtro, aux);
					}
				}
			}
			
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
	public Elemento buscarElemento(int id, Filtro f) throws SQLException {
		Statement st = conn.createStatement();
		Elemento elem=null;
		ResultSet rs = st.executeQuery("SELECT Titulo,FechaPublicacion,FechaRetirada,Descripcion, URL_Imagen,ID, Nombre_Plataforma,ED1, ED2, ED3, TOTAL FROM Catalogo WHERE  ID="+id+";");
		if(rs.next()) {
			elem=new Elemento(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getInt(6),rs.getString(7),rs.getInt(11));
		}
		for(int i=8;i<11;i++) {
			String aux= rs.getString(i);
			if(aux!=null) {
				elem.anadirEtiquetaDefecto(f,aux);
			}
		}
		
		st.close();
		return elem;
	}
	public void actualizar(Collection<Elemento> l, Collection<Etiqueta> etiquetas) throws SQLException {
		Statement st= conn.createStatement();

		st.executeUpdate("DELETE  FROM Etiqueta WHERE NombreUsuario LIKE '"+nombre+"' ;");
		st.executeUpdate("DELETE  FROM Usuario WHERE NombreUsuario LIKE '"+nombre+"' ;");
		
		for(Elemento e:l) {
			st.execute("INSERT INTO Usuario (`NombreUsuario`, `ID`, `Estado`, `Favorito`, `Nota`, `Progreso`) VALUES ( '"+nombre+"',"+e.valores()+" );");
			int n = e.getContEtiqUsu();
			Etiqueta[] et = e.etiquetasUsuario();
			if(n>=1) {
				st.execute("UPDATE `Usuario` SET `EP1` = '"+et[0]+"' WHERE ID ="+e.getId()+" AND UPPER(NombreUsuario) LIKE '"+nombre+"' ;");
			}
			if(n>=2) {
				st.execute("UPDATE `Usuario` SET `EP2` = '"+et[1]+"' WHERE ID ="+e.getId()+" AND UPPER(NombreUsuario) LIKE '"+nombre+"' ;");
			}
			if(n>=3) {
				st.execute("UPDATE `Usuario` SET `EP3` = '"+et[2]+"' WHERE ID ="+e.getId()+" AND UPPER(NombreUsuario) LIKE '"+nombre+"' ;");
			}
		}
		
		for (Etiqueta etiqueta : etiquetas) 
		{
			//System.out.println("Etiqueta: " +  etiqueta.getNombre());
			st.execute("INSERT INTO Etiqueta VALUES ( '"+etiqueta.getNombre()+"','"+nombre+"');");
		}
		
		st.close();
	}
	
	public void finalizarConexion() throws SQLException{
		conn.close();
	}
	
}

