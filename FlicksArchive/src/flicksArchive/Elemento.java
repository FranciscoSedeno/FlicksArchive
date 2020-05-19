package flicksArchive;

import java.sql.Date;
import java.util.StringJoiner;

public class Elemento {
	private int id;
	private String titulo;
	private String Plataforma;
	private Date fechaPublicacion;
	private Date fechaRetirada;
	private String descripcion;
	private boolean favorito;
	public static enum estadoVisualizacion{PENDIENTE, VIENDO, FINALIZADO};
	private estadoVisualizacion estado;
	private int notaUsuario;
	private String URL_Imagen;
	
	
	
	
	public Elemento(String titulo, Date fechaPublicacion, Date fechaRetirada, String descripcion, String img, int id) {
		super();
		this.titulo = titulo;
		this.fechaPublicacion = fechaPublicacion;
		this.fechaRetirada = fechaRetirada;
		this.descripcion = descripcion;
		this.favorito = false;
		this.estado = estadoVisualizacion.PENDIENTE;
		this.notaUsuario = 10;
		URL_Imagen = img;
		this.id = id;
	}
	
	public void editarElemento() {
		//while(!botonGuardar){
			
		
		
		
		//}
	}
	
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
	
	//Getters & Setters(por si acaso)
	public String getPlataforma() {
		return Plataforma;
	}

	public void setPlataforma(String plataforma) {
		Plataforma = plataforma;
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

}
