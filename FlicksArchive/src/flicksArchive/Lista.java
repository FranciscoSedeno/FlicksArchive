package flicksArchive;

import java.sql.SQLException;
import java.util.*;

public class Lista {
	
	private String nombreUsuario;
	private Map<Integer,Elemento> listaElementos;
	private Set<String> listaPlataformas;
	private Conexion conexion;
	//private Ordenacion ordenacion;
	//private Filtro filtro;
	//private List<Notificaciones> listaNotificaciones;
	
	public Lista(String nombre) throws SQLException {
		nombreUsuario = nombre;
		conexion = new Conexion(nombreUsuario);
		listaElementos= new HashMap<Integer, Elemento>();
		listaPlataformas=new TreeSet<String>();
		conexion.inicializarDatos(listaElementos,listaPlataformas);
		
		
	}
	
	
	/*
	 * Funciones para consultar a la base de datos sobre elementos del catalogo
	 */
	public List<Tupla> buscarElementoNuevo(String titulo) throws SQLException {
		return conexion.buscarPorTitulo(titulo);//Se muestra img y titulo de cada elemento de listaMostrada al usuario
	}
	
	
	/*
	 * Funciones para manipular la Lista de Elementos
	 */
	public void añadirElemento(Elemento elem) {
		listaElementos.put(elem.getId(), elem);
	}
	
	public void eliminarElemento(int id) {
		listaElementos.remove(id);
	}

	public Elemento conseguirElemento(int id) {
		return listaElementos.get(id);
	}
	
	
	
	
	
	/*
	 * Funciones para modificar el conjunto de plataformas
	 */
	public void añadirPlataformas(String plat) {
		if(!listaPlataformas.contains(plat)) {
			listaPlataformas.add(plat);
		}
	}
	
	public void eliminarPlataforma(String plat) {
		listaPlataformas.remove(plat);
	}
	
	
	/*
	 * Funciones propias de objetos. 
	 */
	public String toString() {
		StringJoiner sj = new StringJoiner("\n");
		for(Elemento e:listaElementos.values()) {
			sj.add(e.toString());
		}
		return sj.toString();
	}
	
	public Conexion getConexion(){
		return conexion;
	}
}
