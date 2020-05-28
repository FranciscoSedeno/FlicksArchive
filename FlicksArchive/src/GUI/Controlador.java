package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import flicksArchive.Lista;

public class Controlador implements ActionListener {
	
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
		System.out.println("HEY HEY");
		try {
			if (comando.equals("ACEPTAR")) {
				System.out.println("ME CAGO EN MI PUTA VIDA ESTOY HASTA LOS COJONES DE LA UNIVERSIDAD");
				ventana.mostrarbusqueda();
			}
			
		} catch (Exception exc){
			JOptionPane.showMessageDialog(ventana, exc.getMessage());
		}
		
	}

}
