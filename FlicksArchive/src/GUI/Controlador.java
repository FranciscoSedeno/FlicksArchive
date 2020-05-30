package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import flicksArchive.Lista;

public class Controlador implements ActionListener, ChangeListener {
	
	private Lista lista;
	private VentanaPrincipal ventana;
	
	
	public Controlador (Lista l, VentanaPrincipal v) {
		lista = l;
		ventana = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		try {
			if (comando.equals("ACEPTAR")) {
				ventana.mostrarbusqueda(lista);
			}
			
		} catch (Exception exc){
			JOptionPane.showMessageDialog(ventana, exc.getMessage());
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		try {
			if(ventana.tabbedPane.getSelectedIndex() == 0)
			{
				ventana.refrescar(lista);
				ventana.buscador.setText("");
		    	ventana.panelBusqueda.removeAll();
			}
			
		} catch (Exception exc){
			JOptionPane.showMessageDialog(ventana, exc.getMessage());
		}
		
	}

}
