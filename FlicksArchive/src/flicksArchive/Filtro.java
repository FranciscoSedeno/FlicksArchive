package flicksArchive;

import java.util.*;

import flicksArchive.Elemento.estadoVisualizacion;

public class Filtro {
	
	private List<Etiqueta> etiquetasPredeterminadas;
	private List<Etiqueta> etiquetasUsuario;
	private List<Etiqueta> etiquetasFiltradas;
	
	private boolean favoritoActivo=false;
	private estadoVisualizacion estadoBuscado = null;
	private String fragmentoTitulo =null;
	
	public Filtro() {
		etiquetasPredeterminadas = Etiqueta.etiquetasPorDefecto();
		etiquetasUsuario = new LinkedList<>();
		etiquetasFiltradas = new LinkedList<>();
		
		
	}
	
	/*
	 * Metodos para la creacion y obtencion de etiquetas
	 */
	public Etiqueta pedirEtiqueta(String n) {
		Etiqueta sol, aux;
		sol = null;
		Iterator <Etiqueta> i = etiquetasPredeterminadas.iterator();
		while(i.hasNext() && sol == null) {
			aux = i.next();
			if(aux.getNombre().equalsIgnoreCase(n)) {
				sol = aux;
			}
		}
		
		Iterator<Etiqueta> it = etiquetasUsuario.iterator();
		
		while(it.hasNext() && sol==null) {
			aux= it.next();
			if(aux.getNombre().equalsIgnoreCase(n)) {
				sol = aux;
			}
			
		}
		
		if(sol == null) {
			sol = new Etiqueta(n);
			etiquetasUsuario.add(sol);
		}
		
		return sol;
		
	}
	public Etiqueta pedirEtiquetaDef(String n) {
		Etiqueta sol, aux;
		sol = null;
		Iterator <Etiqueta> i = etiquetasPredeterminadas.iterator();
		while(i.hasNext() && sol == null) {
			aux = i.next();
			if(aux.getNombre().equalsIgnoreCase(n)) {
				sol = aux;
			}
		}
		
		
		return sol;
		
	}
	public Etiqueta buscaEtiqueta(String n) {
		Etiqueta  sol,aux;
		sol=null;
		Iterator<Etiqueta> it = etiquetasUsuario.iterator();
		
		while(it.hasNext() && sol==null) {
			aux= it.next();
			if(aux.getNombre().equalsIgnoreCase(n)) {
				sol = aux;
			}
			
		}
		if(sol==null) {
			sol=pedirEtiquetaDef(n);
		}
		return sol;
	}
	public void anadirEtiquetaUsuario(Etiqueta e) {
		etiquetasUsuario.add(e);
	}
	
	public void anadirEtiquetaFiltrada(Etiqueta e) {
		etiquetasFiltradas.add(e);
	}
	
	public void borrarEtiquetaUsuario(Etiqueta e) {
		etiquetasUsuario.remove(e);
	}
	
	public void borrarEtiquetaFiltrada(Etiqueta e) {
		etiquetasFiltradas.remove(e);
	}
	
	public List<Etiqueta> etiquetasSinUso()
	{
		List<Etiqueta> listaResultado = new LinkedList<>();
		for (Etiqueta etiqueta : etiquetasUsuario) 
		{
			if(etiqueta.getContador() == 0)
				listaResultado.add(etiqueta);
		}
		return listaResultado;
	}
	
	/*
	 * Metodos para configurar el filtro
	 */
	
	//Obtencion de las dos listas de etiquetas juntas
	public List<Etiqueta> etiquetas()
	{
		List<Etiqueta> resultado = new ArrayList<Etiqueta>(etiquetasPredeterminadas);
		resultado.addAll(etiquetasUsuario);
		
		return resultado;
	}
	
	public List<Etiqueta> getEtiquetasUsuario() 
	{
		return etiquetasUsuario;
	}
	
	//Selecciona si se esta buscando o no solo los elementos favoritos.
	public void setFavoritoActivo(boolean favoritoActivo) {
		this.favoritoActivo = favoritoActivo;
	}
	
	//Selecciona el estado que se está buscando, si no se quiere buscar ninguno se pasa null.
	public void setEstadoBuscado(estadoVisualizacion estadoBuscado) {
		this.estadoBuscado = estadoBuscado;
	}
	
	public void setFragmentoTitulo(String fragmentoTitulo) {
		if(fragmentoTitulo != null)
			this.fragmentoTitulo = fragmentoTitulo.toUpperCase();
		else
			this.fragmentoTitulo = null;
	}
	
	//Borra las etiquetas aplicadas y añade aquellas etiquetas de filtro cuyo nombre coincida con algun string de etiquetas
	public void setEtiquetas(List<String> etiquetas) {
		
		Iterator<String> it = etiquetas.iterator();
		List<String> errores=new LinkedList<String>();
		Etiqueta aux;
		String act;
		etiquetasFiltradas.clear();
		
		while(it.hasNext()) {
			act=it.next();
			aux= buscaEtiqueta(act);
			if(aux!=null) {
				etiquetasFiltradas.add(aux);
			}else {
				errores.add(act);
			}
		}
		if(!errores.isEmpty()) {
			throw new IllegalArgumentException("Las siguientes etiquetas no están registradas: "+errores.toArray().toString());
		}
	}
	
	/*
	 * Metodos para filtrar una coleccion de elementos.
	 */
	
	public List<Elemento> filtrado(Collection<Elemento> listaElementos){
		
		
		Etiqueta aux;
		boolean noElementos = false;
		Iterator<Etiqueta> itEtFiltro = etiquetasFiltradas.iterator();
		List<Elemento> sol = new LinkedList<Elemento>(listaElementos);			
		
		
		noElementos = !(etiquetasFiltradas.size() < 3);
		while(itEtFiltro.hasNext() && !noElementos) {
			aux = itEtFiltro.next();
			if(aux.getContador() == 0) {
				noElementos = true;
			}
		}
	
		if(!noElementos) {
			if(favoritoActivo) {
				sol=filtroFavorito(sol);
			}
			
			if(estadoBuscado!=null) {
				sol=filtroEstado(sol, estadoBuscado);
			}
			etiquetasFiltradas.sort(Comparator.naturalOrder());
			itEtFiltro = etiquetasFiltradas.iterator();
			while(itEtFiltro.hasNext() && !sol.isEmpty()) {
				aux=itEtFiltro.next();
				sol = filtroIndividual(sol, aux);
			}
			if(fragmentoTitulo!=null) {
				sol = filtroTitulo(sol);
			}
		}else {
			sol=new ArrayList<Elemento>();
		}
		return sol;
	}
	

	private List<Elemento> filtroIndividual (Collection<Elemento> lista, Etiqueta e){
		Iterator<Elemento> it = lista.iterator();
		Elemento elem;
		List<Elemento> sol = new LinkedList<>();
	
		
		while(it.hasNext()) {
			elem=it.next();
			
			if(elem.contieneEtiqueta(e.getNombre())) {
				sol.add(elem);
			}

		
		}
		return sol;
	}
	private List<Elemento> filtroFavorito (Collection<Elemento> lista){
		Iterator<Elemento> it = lista.iterator();
		Elemento elem;
		List<Elemento> sol = new LinkedList<>();
	
		
		while(it.hasNext()) {
			elem=it.next();
			
			if(elem.isFavorito()) {
				sol.add(elem);
			}

		
		}
		return sol;
	}
	private List<Elemento> filtroEstado (Collection<Elemento> lista,estadoVisualizacion estado){
		Iterator<Elemento> it = lista.iterator();
		Elemento elem;
		List<Elemento> sol = new LinkedList<>();
	
		
		while(it.hasNext()) {
			elem=it.next();
			
			if(elem.getEstado().equals(estado)) {
				sol.add(elem);
			}

		
		}
		return sol;
	}
	private List<Elemento> filtroTitulo (Collection<Elemento> lista){
		Iterator<Elemento> it = lista.iterator();
		Elemento elem;
		List<Elemento> sol = new LinkedList<>();
	
		
		while(it.hasNext()) {
			elem=it.next();
			if(elem.getTitulo().toUpperCase().contains(fragmentoTitulo)) {
				sol.add(elem);
			}

		
		}
		return sol;
	}
}
