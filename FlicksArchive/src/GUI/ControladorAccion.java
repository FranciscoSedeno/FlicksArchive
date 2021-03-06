package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.UpdatableResultSet;

import flicksArchive.Elemento;
import flicksArchive.Lista;

public class ControladorAccion implements ActionListener {
	
	private Lista lista;
	private VentanaPrincipal ventana;
	private VentanaGestion venGes;
	private ConfirmarAccion confAc;
	
	public ControladorAccion(Lista l, VentanaPrincipal v, ConfirmarAccion ca, VentanaGestion vg) {
		// TODO Auto-generated constructor stub
		lista = l;
		ventana = v;
		venGes = vg;
		confAc = ca;
	}
	
	public ControladorAccion(Lista l, VentanaPrincipal v, ConfirmarAccion ca) {
		// TODO Auto-generated constructor stub
		lista = l;
		ventana = v;
		confAc = ca;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String[] comando = e.getActionCommand().split("[ ]");
		Elemento elem;
		try {
			if (comando[0].equals("Aņadir") && comando[1].equals("SI")) {
				try 
				{
					elem = lista.extraerElementoBD(Integer.parseInt(comando[2]));

					lista.aņadirElemento(lista.extraerElementoBD(elem.getId()));
				} catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (comando[0].equals("Eliminar") && comando[1].equals("SI")) {
				elem = lista.conseguirElemento(Integer.parseInt(comando[2]));
				lista.eliminarElemento(elem.getId());
				ventana.refrescar(lista);
				venGes.dispose();
				
				
				
			}
			confAc.dispose();
			
			
		} catch (Exception exc) {
			
			JOptionPane.showMessageDialog(ventana, exc.getMessage());
		}
		
	}
}
