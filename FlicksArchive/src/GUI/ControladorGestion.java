package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import flicksArchive.Elemento;
import flicksArchive.Lista;

public class ControladorGestion implements ActionListener {
	private VentanaGestion gestion;
	private Lista lista;
	private VentanaPrincipal ventana;
	public ControladorGestion(VentanaGestion g, Lista l, VentanaPrincipal v) {
		gestion = g;
		lista = l;
		ventana = v;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String mensaje = e.getActionCommand();
		String[] comando = mensaje.split("[ ]");
		Elemento elem = lista.conseguirElemento(Integer.parseInt(comando[1]));
		try {
			if (comando[0].equals("GUARDAR")) {
				
				gestion.actualizarelemento();
				
			} else if (comando[0].equals("ELIMINAR")) {
				lista.eliminarElemento(elem.getId());
				
				confirmarAccion ventanaAccion = new confirmarAccion(elem.getId(), ventana,elem.getTitulo(), "Eliminar", lista);
				ControladorAccion contAcc = new ControladorAccion(lista, ventana, ventanaAccion);
				ventanaAccion.controlador(contAcc);
				
				ventanaAccion.setVisible(true);
        		
        		gestion.dispose();
				
			}
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(gestion, e.getActionCommand());
		}
		
	}

}
