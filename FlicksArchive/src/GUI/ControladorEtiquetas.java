package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import flicksArchive.Filtro;
import flicksArchive.Lista;

public class ControladorEtiquetas implements ActionListener{
	
	private GestorEtiquetas gestorEtiquetas;
	private Lista lista;
	private VentanaPrincipal ventana;
	
	public ControladorEtiquetas(GestorEtiquetas gestorEtiquetas, Lista lis, VentanaPrincipal ventana) 
	{
		this.gestorEtiquetas = gestorEtiquetas;
		lista = lis;
		this.ventana = ventana;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		String comando = e.getActionCommand();
		if(comando.equals("ELIMINAR ETIQUETA"))
		{
			gestorEtiquetas.eliminaEtiqueta(lista);
			gestorEtiquetas.dispose();
			ventana.setModelComboBox(lista);
		}
		
	}

}
