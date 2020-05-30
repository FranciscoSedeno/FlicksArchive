package flicksArchive;

import java.sql.SQLException;
import java.util.*;

public class Lista {
	
	private String nombreUsuario;
	private Map<Integer,Elemento> listaElementos;
	private Set<String> listaPlataformas;
	private Conexion conexion;
	//private Ordenacion ordenacion;
	private Filtro filtro=new Filtro();
	//private List<Notificaciones> listaNotificaciones;
	
	public Lista(String nombre) throws SQLException {
		nombreUsuario = nombre;
		conexion = new Conexion(nombreUsuario);
		listaElementos= new HashMap<Integer, Elemento>();
		listaPlataformas=new TreeSet<String>();
		conexion.inicializarDatos(listaElementos,listaPlataformas,filtro);
		
		
	}
	
	
	/*
	 * Funciones para consultar a la base de datos sobre elementos del catalogo
	 */
	public List<Tupla> buscarElementoNuevo(String titulo) throws SQLException {
		return conexion.buscarPorTitulo(titulo);//Se muestra img y titulo de cada elemento de listaMostrada al usuario
	}
	
	public Elemento extraerElementoBD(int id) throws SQLException
	{
		return conexion.buscarElemento(id,filtro);
	}
	
	public void actualizar() throws SQLException
	{
		conexion.actualizar(listaElementos.values(), filtro.getEtiquetasUsuario());
	}
	
	//Devuelve la lista
	public Collection<Elemento> getListaActual()
	{
		return listaElementos.values();
	}
	//Devuelve la lista de acuerdo a los filtros
	public Collection<Elemento> getListaFiltrada(){
		return filtro.filtrado(listaElementos.values());
	}
	
	public List<Etiqueta> etiquetas()
	{
		List<Etiqueta> etiq = filtro.etiquetas();
		etiq.add(0, new Etiqueta("-"));
		
		return etiq;
	}
	/*
	 * Funciones para manipular la Lista de Elementos
	 */
	public void añadirElemento(Elemento elem) {
		listaElementos.put(elem.getId(), elem);
	}
	
	public void eliminarElemento(int id) {
		listaElementos.get(id).resetearEtiquetas();
		listaElementos.remove(id);
	}

	public Elemento conseguirElemento(int id) {
		return listaElementos.get(id);
	}
	
	public boolean estaElemento(int id) {

        return listaElementos.containsKey(id);
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
	
	public Filtro getFiltro()
	{
		return filtro;
	}
	
	public Conexion getConexion(){
		return conexion;
	}
}
