package flicksArchive;



public class Notificacion {
	
	public static enum motivoNotificacion {PUBLICACION,DESCATALOGADA};
	private Elemento elem;
	private motivoNotificacion motivo;
	
	/*
	 * Recibe el elemento asociado e y el motivo, 0 para PUBLICACION y 1 para DESCATALOGADA
	 */
	public Notificacion(Elemento e, int m) {
		// TODO Auto-generated constructor stub
		elem=e;
		motivo=motivoNotificacion.values()[m];
		
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(elem.isPelicula()) {
			sb.append("La pelicula ");
		}else {
			sb.append("La serie ");
		}
		sb.append(elem.getTitulo());
		sb.append(" de la plataforma ");
		sb.append(elem.getPlataforma());
		
		if(motivo.equals(motivoNotificacion.PUBLICACION)) {
			sb.append(" ya ha sido publicada");
		}else {
			sb.append(" dejara de estar disponible el día: ");
			sb.append(elem.getFechaRetirada().toString());
		}
		sb.append(".");
		return sb.toString();
	}
	public motivoNotificacion getMotivo() {
		return motivo;
	}
}
