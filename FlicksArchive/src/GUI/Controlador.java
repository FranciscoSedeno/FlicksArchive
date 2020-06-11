package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import flicksArchive.Etiqueta;
import flicksArchive.Filtro;
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
			} else if (comando.equals("FILTRAR")) {
				Filtro filtro = lista.getFiltro();
				ventana.estadoFiltrado(filtro);
				ventana.refrescar(lista);
			} else if (comando.equals("LIMPIAR"))
			{
				ventana.setModelComboBox(lista);
				ventana.estadoFiltrado(lista.getFiltro());
				ventana.refrescar(lista);
			} else if (comando.equals("ELIMINAR ETIQUETAS"))
			{
				//TODO Rellenar aqui la ventana emergente
				GestorEtiquetas gestorEtiquetas = new GestorEtiquetas();
				ControladorEtiquetas contEtiquetas = new ControladorEtiquetas(gestorEtiquetas, lista, ventana);
				gestorEtiquetas.setListaEtiquetas(lista.getFiltro().etiquetasSinUso());
				gestorEtiquetas.controlador(contEtiquetas);
				gestorEtiquetas.setVisible(true);
			} else if (comando.equals("ORDENAR")) {
				int index = ventana.getOrden();
				
				if (index == 0) {
					
					lista.ordenaA_Z();
				} else if (index == 1) {
					lista.ordenaZ_A();
				} else if (index == 2) {
					lista.ordenaNota();
				}
				ventana.refrescar(lista);
			}
			
		}catch (Exception exc){
			JOptionPane.showMessageDialog(ventana, exc.getMessage());
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		try {
			if(ventana.getIndicetab() == 0)
			{
				ventana.refrescar(lista);
				ventana.setModelComboBox(lista);
				ventana.setTextoBuscador("");
		    	ventana.borraTodo();
			} else if (ventana.getIndicetab()==2) {
				ventana.escribeNotificaciones(lista.logNotificaciones());
				
			}
			
		} catch (Exception exc){
			JOptionPane.showMessageDialog(ventana, exc.getMessage());
		}
		
	}

}
