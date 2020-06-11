package flicksArchive;

import java.sql.Date;
import java.util.StringJoiner;

public class Elemento implements Comparable<Elemento>{
	private int id;
	private String titulo;
	private String plataforma;
	private Date fechaPublicacion;
	private Date fechaRetirada;
	private String descripcion;
	private boolean favorito;
	public static enum estadoVisualizacion{PENDIENTE, VIENDO, FINALIZADO};
	private estadoVisualizacion estado;
	private int notaUsuario;
	private String URL_Imagen;
	private int numCapitulos;
	private int progreso = 0;
	private boolean pelicula=false;
	
	private int contEtiqD = 0;
	private Etiqueta[] etiquetasPre = new Etiqueta[3];
	private int contEtiqUsu = 0;
	private Etiqueta[] etiquetasUsuario= new Etiqueta[3];
	
	
	
	public Elemento(String titulo, Date fechaPublicacion, Date fechaRetirada, String descripcion, String img, int id, String plataforma, int num) {
		super();
		this.titulo = titulo;
		this.plataforma = plataforma;
		this.fechaPublicacion = fechaPublicacion;
		this.fechaRetirada = fechaRetirada;
		this.descripcion = descripcion;
		this.favorito = false;
		this.estado = estadoVisualizacion.PENDIENTE;
		this.notaUsuario = 0;
		URL_Imagen = img;
		this.id = id;
		numCapitulos = num;
	}
	public Elemento(String titulo, Date fechaPublicacion, Date fechaRetirada, String descripcion, String img, int id,String plataforma, int num, int  estado,boolean favorito,int nota, int actual) {
		this(titulo, fechaPublicacion, fechaRetirada, descripcion, img, id,plataforma, num);
		this.favorito = favorito;
		this.estado = estadoVisualizacion.values()[estado];
		this.notaUsuario = nota;
		progreso = actual;

	}
	
	//Devuelve los valores necesarios para almacenar el estado de un usuario con el elemento en la BD.
	public String valores() {
		StringJoiner sj=new StringJoiner(",");
		sj.add(""+id);
		sj.add(""+estado.ordinal());
		if (favorito){
			sj.add("1");
		} else {
			sj.add("0");
		}
		
		sj.add(""+notaUsuario);
		sj.add("" + progreso);
		return sj.toString();
	}
	
	//Recibe el nombre de una etiqueta personalizada y la añade al elemento, si no estaba ya y si tiene menos de 3 etiquetas ya asociadas.
	public void anadirEtiqueta(Filtro f, String n) {
		if(this.contieneEtiqueta(n)) {
			throw new IllegalArgumentException("ERROR: Has intentado añadir la etiqueta"+n.toUpperCase()+"pero ya estaba añadida.");
		}
		if(contEtiqUsu < 3) {
			Etiqueta e = f.pedirEtiqueta(n.toUpperCase());
			e.inc();
			etiquetasUsuario[contEtiqUsu] = e;
			contEtiqUsu++;
		}else {
			throw new IllegalArgumentException("ERROR: intentado añadir más etiquetas de las posibles");
		}
	}
	
	//Resetea etiquetas
	public void resetearEtiquetas()
	{
		contEtiqUsu = 0;
		for (int i = 0; i < etiquetasUsuario.length; i++) 
		{
			if(etiquetasUsuario[i] != null)
			{
				etiquetasUsuario[i].dec();
				etiquetasUsuario[i] = null;
			}
		}
	}
	
	/*
	Solo se usa al importar la información de la base de datos, el usuario no tiene acceso a ella.
	Recibe el nombre de una etiqueta por defecto y la añade al elemento, si no estaba ya y si tiene menos de 3 etiquetas ya asociadas
	*/
	public void anadirEtiquetaDefecto(Filtro f, String n) throws IllegalArgumentException{
		if(this.contieneEtiqueta(n)) {
			throw new IllegalArgumentException("Has intentado añadir la etiqueta"+n.toUpperCase()+"pero ya estaba añadida.");
		}
		if(contEtiqD < 3) {
			Etiqueta e = f.pedirEtiquetaDef(n);
			if(e==null) {
				throw new IllegalArgumentException("ETIQUETA POR DEFECTO NO RECOGIDA");
			}
			e.inc();
			etiquetasPre[contEtiqD] = e;
			contEtiqD++;
		}else {
			throw new IllegalArgumentException("INTENTO DE AÑADIR MÁS ETIQUETAS DE LAS POSIBLES");
		}
	}
	
	//Devuelve true si el elemento contiene la etiqueta et.
	public boolean contieneEtiqueta(String et) {
		int c=0;
		boolean encontrado =false;
		while(c<contEtiqUsu && !encontrado) {
			encontrado=(etiquetasUsuario[c].getNombre().equals(et.toUpperCase()));
			c++;
		}
		c=0;
		while(c<contEtiqD && !encontrado) {
			encontrado=(etiquetasPre[c].getNombre().equals(et.toUpperCase()));
			c++;
		}
		return encontrado;
	}
	
	//Getters & Setters 
	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public Date getFechaRetirada() {
		return fechaRetirada;
	}
	public void setFechaRetirada(Date fechaRetirada) {
		this.fechaRetirada = fechaRetirada;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isFavorito() {
		return favorito;
	}
	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}
	public estadoVisualizacion getEstado() {
		return estado;
	}
	public void setEstado(estadoVisualizacion estado) {
		this.estado = estado;
	}
	public int getNotaUsuario() {
		return notaUsuario;
	}
	public void setNotaUsuario(int notaUsuario) {
		this.notaUsuario = notaUsuario;
	}

	public String getURL_Imagen() {
		return URL_Imagen;
	}

	public void setURL_Imagen(String uRL_Imagen) {
		URL_Imagen = uRL_Imagen;
	}
	public int getContEtiqUsu() {
		return contEtiqUsu;
	}
	public Etiqueta[] etiquetasUsuario(){
		return etiquetasUsuario;
	}
	public Etiqueta[] etiquetasPredeterminadas(){
		return etiquetasPre;
	}
	public int getNumCapitulos() {
		return numCapitulos;
	}
	public int getProgreso() {
		return progreso;
	}
	public void setProgreso(int progreso) {
		this.progreso = progreso;
	}
	public boolean isPelicula() {
		return pelicula;
	}
	public void setPelicula(boolean pelicula) {
		this.pelicula = pelicula;
	}
	/*
	 * Funciones propias de objetos.
	 */
	
	public String toString() {
		
		StringJoiner sj=new StringJoiner(",");
		sj.add("ELEMENTO: id: "+id);
        sj.add("plataforma: "+plataforma);
        sj.add("titulo: "+titulo);
        sj.add("publicacion: "+fechaPublicacion.toString());
        sj.add("retirada: "+fechaRetirada.toString());
        sj.add("descripcion: "+descripcion);
		
		sj.add("estado: "+estado.toString());
		if (favorito){
			sj.add("favorito: SÍ");
		} else {
			sj.add("favorito: NO");
		}
		sj.add("nota: "+notaUsuario);
		return sj.toString();
		
	}
	@Override
	public int compareTo(Elemento o) {
		return (titulo.toUpperCase()).compareTo(o.titulo.toUpperCase());
	}
}
