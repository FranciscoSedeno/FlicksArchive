package flicksArchive;

import java.sql.Date;
import java.util.StringJoiner;

public class Elemento {
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
	
	
	
	
	public Elemento(String titulo, Date fechaPublicacion, Date fechaRetirada, String descripcion, String img, int id, String plataforma) {
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
	}
	public Elemento(String titulo, Date fechaPublicacion, Date fechaRetirada, String descripcion, String img, int id,String plataforma,int  estado,boolean favorito,int nota) {
		this(titulo, fechaPublicacion, fechaRetirada, descripcion, img, id,plataforma);
		this.favorito = favorito;
		this.estado = estadoVisualizacion.values()[estado];
		this.notaUsuario = nota;

	}
	public void editarElemento() {
		//while(!botonGuardar){
			
		
		
		
		//}
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
		return sj.toString();
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
}
