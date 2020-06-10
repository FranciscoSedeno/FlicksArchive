package flicksArchive;

import java.util.ArrayList;
import java.util.List;

public class Etiqueta implements Comparable<Etiqueta>{
	
	private String nombre;
	private int contador;
	
	public Etiqueta(String n) {
		nombre = n;
		contador = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	
	public void inc() {
		contador++;
	}
	public void dec() {
		contador--;
	}
	
	public String toString() {
		return nombre;
	}
	public static List<Etiqueta> etiquetasPorDefecto(){
		
		List<Etiqueta> lista = new ArrayList<Etiqueta>();
		lista.add(new Etiqueta("DRAMA"));
		lista.add(new Etiqueta("COMEDIA"));
		lista.add(new Etiqueta("ACCION"));
		lista.add(new Etiqueta("FANTASIA"));
		lista.add(new Etiqueta("ADULTOS"));
		lista.add(new Etiqueta("INFANTIL"));
		lista.add(new Etiqueta("CIENCIA-FICCION"));
		lista.add(new Etiqueta("FUTURISTA"));
		
		return lista;
		
	}
	@Override
	public int compareTo(Etiqueta o) {
		return this.contador - o.getContador();
	}
	
	
}
