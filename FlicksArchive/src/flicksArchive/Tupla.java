package flicksArchive;

public class Tupla {
	private String titulo;
	private int id;
	private String url_img;
	
	public Tupla(String titulo, int id,String imagen) {
		this.titulo = titulo;
		this.id = id;
		url_img= imagen;
	}

	public String getUrl_img() {
		return url_img;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getId() {
		return id;
	}
	
	
}
