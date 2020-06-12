package flicksArchive;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Conexion {
	 private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	 private static final String DB_URL = "jdbc:mysql://database-iis.cobadwnzalab.eu-central-1.rds.amazonaws.com";
	 private static final String DB_SCHEMA = "flicksdb";
     private Connection conn;
     private static final String USER = "flickr";
     private static final String PASS = "distanciafocal";
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
	public static String encriptarMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md .digest(input.getBytes());
			BigInteger number = new BigInteger(1,messageDigest);
			String hashtext= number.toString(16);
			while(hashtext.length()<32) {
				hashtext = "0"+hashtext;
			}
			return hashtext;
		}catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			System.err.println("Error encriptando.");
		}
		return input;
	}
	public void inicializarDatos(Map<Integer,Elemento> lista,Set<String> plataformas,Filtro filtro,List<Notificacion> notificaciones) throws SQLException {
		Statement st= conn.createStatement();
		
		long millis=System.currentTimeMillis();
		Date today=new Date(millis);
		

		ResultSet rs= st.executeQuery("SELECT NombreEtiqueta FROM Etiqueta WHERE NombreUsuario LIKE '"+nombre+"' ;");
		while(rs.next()) {
			filtro.pedirEtiqueta(rs.getString(1));
		}
		
		rs= st.executeQuery("SELECT Titulo,FechaPublicacion,FechaRetirada,Descripcion,URL_Imagen,c.ID, Estado, Favorito, Nota,Nombre_Plataforma, EP1, EP2, EP3, ED1, ED2, ED3, TOTAL, PROGRESO, esPelicula FROM Usuario u JOIN Catalogo c on (c.ID=u.ID) WHERE u.NombreUsuario LIKE '"+nombre+"' ;");
		
		while(rs.next()) {
			Elemento e = new Elemento(rs.getString(1),rs.getDate(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(10),rs.getInt(17),rs.getInt(7),rs.getBoolean(8),rs.getInt(9), rs.getInt(18));
			lista.put(rs.getInt(6),e);
			
			e.setPelicula(rs.getBoolean(19));
			
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
			Lista.actualizarNotificaciones(today, e, notificaciones);
			plataformas.add(rs.getString(10).toUpperCase());
		}
		st.close();
		
	}
	public static boolean verificacion(String nombre,String contrase) {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e1) {
			System.err.println("Error buscando clase.");
		}
		boolean r=false;
		try(Connection conn = DriverManager.getConnection(DB_URL + "/" + DB_SCHEMA,USER,PASS)){
			
			Statement st = conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT NombreUsuario FROM Password WHERE NombreUsuario LIKE '"+nombre.toUpperCase()+"' AND Password LIKE '"+encriptarMD5(contrase)+"';" );
			System.out.println(encriptarMD5(contrase));
			r=rs.next();
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			
		}
		return r;
	}
	public static boolean disponible(String nombre)  {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e1) {
			System.err.println("Error buscando clase.");
		}
		boolean r=false;
		try(Connection conn = DriverManager.getConnection(DB_URL + "/" + DB_SCHEMA,USER,PASS)){
			
			Statement st = conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT NombreUsuario FROM Password WHERE NombreUsuario LIKE '"+nombre.toUpperCase()+"';" );
			r=!rs.next();
			
		} catch (SQLException e) {
			System.err.println("Error conectando con la BD.");
			
		}
		return r;
	}
	public static void registro(String nombre,String contrase) {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e1) {
			System.err.println("Error buscando clase.");
		}

		try(Connection conn = DriverManager.getConnection(DB_URL + "/" + DB_SCHEMA,USER,PASS)){
			
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO Password VALUES ('"+nombre.toUpperCase()+"','"+encriptarMD5(contrase)+"');" );
			
			
		} catch (SQLException e) {
			System.err.println("Error conectando con la BD.");
			
		}
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
		ResultSet rs = st.executeQuery("SELECT Titulo,FechaPublicacion,FechaRetirada,Descripcion, URL_Imagen,ID, Nombre_Plataforma,ED1, ED2, ED3, TOTAL,esPelicula FROM Catalogo WHERE  ID="+id+";");
		if(rs.next()) {
			elem=new Elemento(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getInt(6),rs.getString(7),rs.getInt(11));
			elem.setPelicula(rs.getBoolean(12));
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

