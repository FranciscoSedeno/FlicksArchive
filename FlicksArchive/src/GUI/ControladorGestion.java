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
				try {
				gestion.actualizarelemento(lista.getFiltro(), elem);
				ventana.setModelComboBox(lista);
				gestion.dispose();
				} catch (NumberFormatException exc) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(gestion, "El valor introducido en el campo progreso no es correcto");
				}
			} else if (comando[0].equals("ELIMINAR")) {
				
				ConfirmarAccion ventanaAccion = new ConfirmarAccion(elem.getId(), ventana,elem.getTitulo(), "Eliminar");
				ControladorAccion contAcc = new ControladorAccion(lista, ventana, ventanaAccion, gestion);
				ventanaAccion.controlador(contAcc);
				
				ventanaAccion.setVisible(true);
				
			}
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(gestion, exc.getMessage());
			System.out.println(exc.getMessage());
		}
		
	}

}
