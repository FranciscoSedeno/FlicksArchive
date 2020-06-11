package GUI;

import javax.swing.ImageIcon;

public class Principal {
	
	public static void main(String[] args) {
		
		VentanaInicio inicio = new VentanaInicio();
		ControladorInicio contInicio = new ControladorInicio(inicio);
		ImageIcon img = new ImageIcon(Principal.class.getResource("/img/FAicon.png"));
		inicio.setIconImage(img.getImage());
		inicio.controlador(contInicio);
		inicio.setVisible(true);
		
		
		
	}

}
