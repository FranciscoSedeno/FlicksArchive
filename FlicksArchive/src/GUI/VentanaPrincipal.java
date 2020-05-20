package GUI;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import flicksArchive.Elemento;
import flicksArchive.Lista;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.FlowLayout;


public class VentanaPrincipal extends JPanel {
	public JTextField buscador;
	private Lista botones;
	private List<JButton> botonList = new ArrayList<>(); //Array de los botones creados
	int indice = 0;
			
	/**
	 * Create the panel.
	 */
	
	
	public VentanaPrincipal(Lista prueba) {
		botones = prueba;
		setBackground(SystemColor.textHighlight);
		setName("FlicksArchive");
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setPreferredSize(new Dimension(rec.width-100, rec.height-100));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 1792, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 918, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.activeCaptionBorder);
		tabbedPane.setBorder(null);
		tabbedPane.setPreferredSize(new Dimension(1920, 1020));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 1;
		add(tabbedPane, gbc_tabbedPane);
		
		JPanel Lista = new JPanel();
		tabbedPane.addTab("Lista", null, Lista, null);
		GridBagLayout gbl_Lista = new GridBagLayout();
		gbl_Lista.columnWidths = new int[]{1766, 0};
		gbl_Lista.rowHeights = new int[]{790, 0};
		gbl_Lista.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_Lista.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		Lista.setLayout(gbl_Lista);
		
		JPanel contentPane = new JPanel();
		GridBagConstraints gbc_contentPane = new GridBagConstraints();
		gbc_contentPane.fill = GridBagConstraints.BOTH;
		gbc_contentPane.gridx = 0;
		gbc_contentPane.gridy = 0;
		Lista.add(contentPane, gbc_contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{1766, 0};
		gbl_contentPane.rowHeights = new int[]{842, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		WrapLayout wl_panel = new WrapLayout(FlowLayout.CENTER, 5, 5);
		wl_panel.setAlignment(FlowLayout.LEFT);
		panel.setLayout(wl_panel);
		JPanel Añadir = new JPanel();
		tabbedPane.addTab("A\u00F1adir", new ImageIcon(VentanaPrincipal.class.getResource("/img/anadir.png")), Añadir, null);
		Añadir.setLayout(null);
		
		for(Elemento elemento : botones.getListaActual())
		{
			JButton Boton = new JButton("");
			Boton.setForeground(new Color(0, 255, 51));
			Boton.setBackground(new Color(102, 51, 0));
			Boton.setMinimumSize(new Dimension(200, 300));
			Boton.setMaximumSize(new Dimension(200, 300));
			Boton.setPreferredSize(new Dimension(200, 300));
			Boton.setBounds(10, 24, 200, 300);
			botonList.add(Boton);
			try 
			{
				Boton.setIcon(new ImageIcon(new URL(elemento.getURL_Imagen())));
				Boton.addActionListener(new ActionListener() 
				{
					
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						// TODO Auto-generated method stub
						
					}
				});
				panel.add(Boton);
				indice++;
				panel.updateUI();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		buscador = new JTextField();
		buscador.setBounds(10, 32, 432, 23);
		buscador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buscador.setText("");
			}
		});
		Añadir.add(buscador);
		buscador.setColumns(10);
		
		JLabel busca = new JLabel("Buscador:");
		busca.setBounds(10, 11, 65, 14);
		Añadir.add(busca);
		
		JButton aceptar = new JButton("Buscar");
		aceptar.setBounds(447, 32, 89, 23);
		aceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//JOptionPane.showMessageDialog(aceptar, "Resultado de la busqueda");
				buscador.setText("");
			}
		});
		Añadir.add(aceptar);


	}
}
