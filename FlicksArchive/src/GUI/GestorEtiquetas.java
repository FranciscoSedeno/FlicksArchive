package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import flicksArchive.Etiqueta;
import flicksArchive.Lista;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class GestorEtiquetas extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestorEtiquetas frame = new GestorEtiquetas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestorEtiquetas() 
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 438, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneLasEtiquetas = new JLabel("Seleccione la etiqueta que desee eliminar:");
		lblSeleccioneLasEtiquetas.setBounds(16, 13, 296, 21);
		contentPane.add(lblSeleccioneLasEtiquetas);
		
		comboBox = new JComboBox();
		comboBox.setBounds(19, 47, 243, 22);
		contentPane.add(comboBox);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(290, 47, 97, 25);
		contentPane.add(btnEliminar);
	}
	
	public void eliminaEtiqueta(Lista lista)
	{
		lista.getFiltro().borrarEtiquetaUsuario((Etiqueta) comboBox.getSelectedItem());
	}
	
	@SuppressWarnings("unchecked")
	public void setListaEtiquetas(List<Etiqueta> etiquetas)
	{
		comboBox.setModel(new DefaultComboBoxModel<>(etiquetas.toArray()));
		contentPane.updateUI();
	}
	
	public void controlador(ControladorEtiquetas contEtiquetas)
	{
		btnEliminar.addActionListener(contEtiquetas);
		btnEliminar.setActionCommand("ELIMINAR ETIQUETA");
	}

}
