package flicksArchive;

import java.sql.SQLException;
import java.util.*;

import flicksArchive.Notificacion.motivoNotificacion;

public class Lista {
	
	private String nombreUsuario;
	private Map<Integer,Elemento> listaElementos;
	private Set<String> listaPlataformas;
	private Conexion conexion;
	private Comparator<Elemento> ordenacion= Comparator.naturalOrder();
	private Filtro filtro=new Filtro();
	private List<Notificacion> listaNotificaciones;
	
	public Lista(String nombre) throws SQLException {
		nombreUsuario = nombre;
		conexion = new Conexion(nombre);
		listaElementos= new HashMap<Integer, Elemento>();
		listaPlataformas=new TreeSet<String>();
		listaNotificaciones=new ArrayList<Notificacion>();
		conexion.inicializarDatos(listaElementos,listaPlataformas,filtro,listaNotificaciones);
		
		
	}
	
	//Solo para testing
	public Lista(String nombre, Conexion conexion2,Filtro filtro2) throws SQLException {
		nombreUsuario = nombre;
		conexion = conexion2;
		filtro=filtro2;
		listaElementos= new HashMap<Integer, Elemento>();
		listaPlataformas=new TreeSet<String>();
	
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
		List<Elemento> l=filtro.filtrado(listaElementos.values());
		Collections.sort(l, ordenacion);
		return l;
	}
	
	public List<Etiqueta> etiquetas()
	{
		List<Etiqueta> etiq = filtro.etiquetas();
		etiq.add(0, new Etiqueta("-"));
		
		return etiq;
	}
	public String logNotificaciones() {
		StringJoiner publicaciones = new StringJoiner("\n");
		StringJoiner descatalogadas = new StringJoiner("\n");
		int p=0,d=0;
		publicaciones.add("<html><h1 style=\"background-color:powderblue;\">PUBLICACIONES</h1></html>");
		descatalogadas.add("<html><h1 style=\"background-color:tomato;\">RETIRADAS</h1></html>");
		
		for (Notificacion notificacion : listaNotificaciones) {
			if(notificacion.getMotivo().equals(motivoNotificacion.PUBLICACION)) {
				publicaciones.add(notificacion.toString());
				p++;
			}else {
				descatalogadas.add(notificacion.toString());
				d++;
			}
			
		}
		if(d==0) {
			descatalogadas.add("Ningun elemento de tu lista.");
		}
		if(p==0) {
			publicaciones.add("Ningun elemento de tu lista.");
		}
		publicaciones.add("");
		publicaciones.add(descatalogadas.toString());
		return publicaciones.toString();
		
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
	
	public void ordenaA_Z() {
		ordenacion= Comparator.naturalOrder();
	}
	public void ordenaZ_A() {
		ordenacion=Comparator.reverseOrder();
	}
	public void ordenaNota() {
		ordenacion= new ComparatorNotas();
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
