package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import flicksArchive.Elemento;
import flicksArchive.Lista;

public class ControladorBotones implements ActionListener {
	private Lista lista;
	private VentanaPrincipal ventana;
	
	public ControladorBotones (Lista l, VentanaPrincipal v) {
		lista = l;
		ventana = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String[] comando = e.getActionCommand().split("[ ]");
		Elemento elem; 
		try {
			if (comando[0].equals("LISTA")) {
				elem = lista.conseguirElemento(Integer.parseInt(comando[1]));
				VentanaGestion gestion = new VentanaGestion("Gestionar elemento", elem, lista, ventana);
				ControladorGestion contGestion = new ControladorGestion(gestion,lista, ventana);
				gestion.controlador(contGestion);
				gestion.setVisible(true);
			} else if (comando[0].equals("BUSCA")) {
				elem = lista.extraerElementoBD(Integer.parseInt(comando[1]));
				confirmarAccion confAc = new confirmarAccion(elem.getId(), ventana, elem.getTitulo(), "Añadir", lista);
				
				ControladorAccion contAc = new ControladorAccion(lista, ventana,confAc);
				confAc.controlador(contAc);
				confAc.setVisible(true);
			}
			
			
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(ventana, exc.getMessage());
		}
		
		
	}
}
