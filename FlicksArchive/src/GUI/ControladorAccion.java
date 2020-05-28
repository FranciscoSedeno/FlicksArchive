package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import flicksArchive.Elemento;
import flicksArchive.Lista;

public class ControladorAccion implements ActionListener {
	
	private Lista lista;
	private VentanaPrincipal ventana;
	private confirmarAccion confAc;
	
	public ControladorAccion(Lista l, VentanaPrincipal v, confirmarAccion confAc) {
		// TODO Auto-generated constructor stub
		lista = l;
		ventana = v;
		this.confAc = confAc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String[] comando = e.getActionCommand().split("[ ]");
		Elemento elem = lista.conseguirElemento(Integer.parseInt(comando[2]));
		
		try {
			if (comando[0].equals("Añadir") && comando[1].equals("SI")) {
				try 
				{
					lista.añadirElemento(lista.extraerElementoBD(elem.getId()));
				} catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (comando[0].equals("Eliminar") && comando[1].equals("SI")) {
					lista.eliminarElemento(elem.getId());
					
					ventana.refrescar(lista);
			}
			confAc.dispose();
			
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(ventana, e.getActionCommand());
		}
		
	}
}
