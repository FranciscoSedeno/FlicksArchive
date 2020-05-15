package flicksArchive;

import java.util.Date;

public class Elemento {
	private String titulo;
	private Date fechaPublicacion;
	private Date fechaRetirada;
	private String descripcion;
	private boolean favorito;
	private enum estadoVisualizacion{PENDIENTE, VIENDO, FINALIZADO};
	private estadoVisualizacion estado;
	private int notaUsuario;
	
	
	
	public Elemento(String titulo, Date fechaPublicacion, Date fechaRetirada, String descripcion) {
		super();
		this.titulo = titulo;
		this.fechaPublicacion = fechaPublicacion;
		this.fechaRetirada = fechaRetirada;
		this.descripcion = descripcion;
		this.favorito = false;
		this.estado = estadoVisualizacion.PENDIENTE;
		this.notaUsuario = -1;
		
	}
	
	
	
	//Getters & Setters(por si acaso)
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

}
