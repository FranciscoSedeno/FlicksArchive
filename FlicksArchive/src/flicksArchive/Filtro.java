package flicksArchive;

import java.util.*;

public class Filtro {
	
	private List<Etiqueta> etiquetasPredeterminadas;
	private List<Etiqueta> etiquetasUsuario;
	private List<Etiqueta> etiquetasFiltradas;
	
	public Filtro() {
		etiquetasPredeterminadas = Etiqueta.etiquetasPorDefecto();
		etiquetasUsuario = new LinkedList<>();
		etiquetasFiltradas = new LinkedList<>();
		
		
	}
	
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
	
//	public List<Etiqueta> buscarEtiqueta(String n) {
//		List<Etiqueta> resultado = new LinkedList<>();
//		Etiqueta aux;
//		for(int i = 0; i < etiquetasTotales.size(); i++) {
//			aux = etiquetasTotales.get(i);
//			if(aux.getNombre().indexOf(n) != -1) {
//				resultado.add(aux);
//			}
//		}
//		return resultado;
//	}
	public Collection<Elemento> filtrado(Collection<Elemento> listaElementos){
		
		
		Etiqueta aux;
		boolean noElementos = false;
		Iterator<Etiqueta> itEtFiltro = etiquetasFiltradas.iterator();
		Collection<Elemento> sol = listaElementos;			
		
		noElementos = !(etiquetasFiltradas.size() < 7);
		while(itEtFiltro.hasNext() && !noElementos) {
			aux = itEtFiltro.next();
			if(aux.getContador() == 0) {
				noElementos = true;
			}
		}
	
		
	
		if(!noElementos) {
			etiquetasFiltradas.sort(Comparator.naturalOrder());
			itEtFiltro = etiquetasFiltradas.iterator();
			while(itEtFiltro.hasNext() && !sol.isEmpty()) {
				aux=itEtFiltro.next();
				sol = filtroIndividual(sol, aux);
			}
		}
		return sol;
	}
	

	private Collection<Elemento> filtroIndividual (Collection<Elemento> lista, Etiqueta e){
		Iterator<Elemento> it = lista.iterator();
		Elemento elem;
		Collection<Elemento> sol = new LinkedList<>();
	
		
		while(it.hasNext()) {
			elem=it.next();
			
			if(elem.contieneEtiqueta(e.getNombre())) {
				sol.add(elem);
			}

		
		}
		return sol;
	}
}
