package flicksArchive;

import java.sql.SQLException;
import java.util.*;

public class Lista {
	
	private String nombreUsuario;
	private List<Elemento> listaElementos;
	private List<Plataforma> listaPlataformas;
	private Conexion conexion;
	//private Ordenacion ordenacion;
	//private Filtro filtro;
	//private List<Notificaciones> listaNotificaciones;
	
	public Lista(String nombre) {
		nombreUsuario = nombre;
		conexion = new Conexion(nombreUsuario);
		listaElementos = new ArrayList<>();
		listaPlataformas = new ArrayList<>();
	}
	
	public List<Tupla> buscarElementoNuevo(String titulo) throws SQLException {
		return conexion.buscarPorTitulo(titulo);//Se muestra img y titulo de cada elemento de listaMostrada al usuario
	}
	
	
	public void añadirElemento(Elemento elem) {
		if(!listaElementos.contains(elem)) {
			listaElementos.add(elem);
		}
	}
	
	public void eliminarElemento(Elemento elem) {
		listaElementos.remove(elem);
	}
	
	
	
	
	
	
	public void añadirPlataformas(Plataforma plat) {
		if(!listaPlataformas.contains(plat)) {
			listaPlataformas.add(plat);
		}
	}
	
	public void eliminarPlataforma(Plataforma plat) {
		listaPlataformas.remove(plat);
	}
	
	
	
}
