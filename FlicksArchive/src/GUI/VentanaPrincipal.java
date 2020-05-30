package GUI;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import flicksArchive.Elemento;
import flicksArchive.Etiqueta;
import flicksArchive.Filtro;
import flicksArchive.Lista;
import flicksArchive.Tupla;
import flicksArchive.Elemento.estadoVisualizacion;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class VentanaPrincipal extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public JTextField buscador;
	private JButton aceptar;
	private List<JButton> botonesLista = new ArrayList<JButton>();
	private List<JButton> botonesBusca = new ArrayList<JButton>();
	public JPanel panelBusqueda;
	public JPanel panel;
	public JTabbedPane tabbedPane;
	private JTextField textFieldBuscar;
	@SuppressWarnings("rawtypes")
	private JComboBox cbEtiqueta1, cbEtiqueta2, cbEtiqueta3, cbEstado;
	private JButton btBuscarLista;
	
	private JRadioButton rdFavoritos;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VentanaPrincipal() {
		setBackground(SystemColor.textHighlight);
		setName("FlicksArchive");
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setPreferredSize(new Dimension(rec.width, rec.height));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 1792, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 918, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
		gbl_Lista.columnWidths = new int[]{1850, 0};
		gbl_Lista.rowHeights = new int[]{77, 862, 0};
		gbl_Lista.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_Lista.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		Lista.setLayout(gbl_Lista);
		
		JPanel panelFiltros = new JPanel();
		GridBagConstraints gbc_panelFiltros = new GridBagConstraints();
		gbc_panelFiltros.fill = GridBagConstraints.BOTH;
		gbc_panelFiltros.insets = new Insets(0, 0, 5, 0);
		gbc_panelFiltros.gridx = 0;
		gbc_panelFiltros.gridy = 0;
		Lista.add(panelFiltros, gbc_panelFiltros);
		panelFiltros.setLayout(null);
		
		JLabel labelBuscar = new JLabel("Buscador");
		labelBuscar.setBounds(0, 0, 56, 16);
		panelFiltros.add(labelBuscar);
		
		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(0, 16, 436, 22);
		panelFiltros.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		cbEstado = new JComboBox();
		
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"-", "PENDIENTE", "VIENDO", "FINALIZADO"}));
		cbEstado.setBounds(1643, 16, 185, 22);
		panelFiltros.add(cbEstado);
		
		btBuscarLista = new JButton("Buscar");
		btBuscarLista.setBounds(447, 15, 97, 25);
		panelFiltros.add(btBuscarLista);
		
		JLabel lbEstados = new JLabel("Estado");
		lbEstados.setBounds(1643, 0, 56, 16);
		panelFiltros.add(lbEstados);
		
		cbEtiqueta1 = new JComboBox();
		cbEtiqueta1.setBounds(790, 16, 146, 22);
		panelFiltros.add(cbEtiqueta1);
		
		cbEtiqueta2 = new JComboBox();
		cbEtiqueta2.setBounds(994, 16, 146, 22);
		panelFiltros.add(cbEtiqueta2);
		
		cbEtiqueta3 = new JComboBox();
		cbEtiqueta3.setBounds(1195, 16, 146, 22);
		panelFiltros.add(cbEtiqueta3);
		
		JLabel lblEtiquetas = new JLabel("Etiquetas");
		lblEtiquetas.setBounds(790, 0, 56, 16);
		panelFiltros.add(lblEtiquetas);
		
		rdFavoritos = new JRadioButton("Favoritos");
		rdFavoritos.setBounds(1447, 15, 127, 25);
		panelFiltros.add(rdFavoritos);
		
		JPanel contentPane = new JPanel();
		GridBagConstraints gbc_contentPane = new GridBagConstraints();
		gbc_contentPane.fill = GridBagConstraints.BOTH;
		gbc_contentPane.gridx = 0;
		gbc_contentPane.gridy = 1;
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
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		WrapLayout wl_panel = new WrapLayout(FlowLayout.CENTER, 5, 5);
		wl_panel.setAlignment(FlowLayout.LEFT);
		panel.setLayout(wl_panel);
		JPanel Añadir = new JPanel();
		tabbedPane.addTab("A\u00F1adir", new ImageIcon(VentanaPrincipal.class.getResource("/img/anadir.png")), Añadir, null);
		
		
		GridBagLayout gbl_Añadir = new GridBagLayout();
		gbl_Añadir.columnWidths = new int[]{1728, 0};
		gbl_Añadir.rowHeights = new int[]{811, 0};
		gbl_Añadir.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_Añadir.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		Añadir.setLayout(gbl_Añadir);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		Añadir.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1728, 0};
		gbl_panel_2.rowHeights = new int[]{811, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel panelExt = new JPanel();
		GridBagConstraints gbc_panelExt = new GridBagConstraints();
		gbc_panelExt.fill = GridBagConstraints.BOTH;
		gbc_panelExt.gridx = 0;
		gbc_panelExt.gridy = 0;
		panel_2.add(panelExt, gbc_panelExt);
		panelExt.setLayout(null);
		
		JPanel panelBuscador = new JPanel();
		panelBuscador.setBounds(0, 0, 526, 44);
		panelExt.add(panelBuscador);
		panelBuscador.setLayout(null);
		
		buscador = new JTextField();
		buscador.setBounds(0, 21, 432, 23);
		panelBuscador.add(buscador);
		
		
		JLabel busca = new JLabel("Buscador:");
		busca.setBounds(0, 0, 65, 14);
		panelBuscador.add(busca);
		
		aceptar = new JButton("Buscar");
		aceptar.setBounds(437, 21, 89, 23);
		panelBuscador.add(aceptar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBounds(2, 72, 1726, 739);
		panelExt.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1726, 0};
		gbl_panel_1.rowHeights = new int[]{739, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		panel_1.add(scrollPane_1, gbc_scrollPane_1);
		
		panelBusqueda = new JPanel();
		panelBusqueda.setBorder(null);
		WrapLayout wl_panel_2 = new WrapLayout(FlowLayout.CENTER, 5, 5);
		wl_panel_2.setAlignment(FlowLayout.LEFT);
		panelBusqueda.setLayout(wl_panel_2);
		scrollPane_1.setViewportView(panelBusqueda);
		
		buscador.setColumns(10);
	}
	
	public void estadoFiltrado(Filtro fil) {
		List<String> listaEtiquetas = new ArrayList<String>();
		int indice = cbEstado.getSelectedIndex();
		if (indice == 0) {
			fil.setEstadoBuscado(null);
		} else {
			fil.setEstadoBuscado(estadoVisualizacion.values()[indice-1]);
		}
		fil.setFavoritoActivo(rdFavoritos.isSelected());
		
		if (cbEtiqueta1.getSelectedIndex() != 0) {
			listaEtiquetas.add(((Etiqueta)cbEtiqueta1.getSelectedItem()).getNombre());
		} 
		if (cbEtiqueta2.getSelectedIndex() != 0) {
			listaEtiquetas.add(((Etiqueta)cbEtiqueta2.getSelectedItem()).getNombre());
		}
		if (cbEtiqueta3.getSelectedIndex() != 0) {
			listaEtiquetas.add(((Etiqueta)cbEtiqueta3.getSelectedItem()).getNombre());
		}
		fil.setEtiquetas(listaEtiquetas);
		
		if (textFieldBuscar.getText().equals("")) {
			fil.setFragmentoTitulo(null);
		} else {
			fil.setFragmentoTitulo(textFieldBuscar.getText());
		}
		
	}
	
	public void controlador (Controlador ctr) {
		aceptar.addActionListener(ctr);
		aceptar.setActionCommand("ACEPTAR");
		buscador.addActionListener(ctr);
		buscador.setActionCommand("ACEPTAR");
		tabbedPane.addChangeListener(ctr);	
		textFieldBuscar.addActionListener(ctr);
		btBuscarLista.addActionListener(ctr);
		textFieldBuscar.setActionCommand("FILTRAR");
		btBuscarLista.setActionCommand("FILTRAR");
		
		
	}
	
	@SuppressWarnings("unchecked")
	public void refrescar(Lista botones)
	{
		botonesLista.clear();
		panel.removeAll();
		
		ControladorBotones contBot = new ControladorBotones(botones, this);
		for(Elemento elemento : botones.getListaFiltrada())
		{
			JButton Boton = new JButton("");
			Boton.setForeground(new Color(0, 255, 51));
			Boton.setBackground(Color.BLACK);
			Boton.setMinimumSize(new Dimension(200, 300));
			Boton.setMaximumSize(new Dimension(200, 300));
			Boton.setPreferredSize(new Dimension(200, 300));
			Boton.setBounds(10, 24, 200, 300);
			try 
			{
				Boton.setIcon(new ImageIcon(new URL(elemento.getURL_Imagen())));
				Boton.setActionCommand("LISTA@/%" + String.valueOf(elemento.getId()));
				Boton.addActionListener(contBot);
				
				panel.add(Boton);
				botonesLista.add(Boton);
				panel.updateUI();
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
		}

		
		cbEtiqueta1.setModel(new DefaultComboBoxModel<>(botones.etiquetas().toArray()));
		cbEtiqueta2.setModel(new DefaultComboBoxModel<>(botones.etiquetas().toArray()));
		cbEtiqueta3.setModel(new DefaultComboBoxModel<>(botones.etiquetas().toArray()));
		panel.updateUI();

	}
	
	

	public void mostrarbusqueda(Lista botones) throws MalformedURLException, SQLException {
		botonesBusca.clear();
		panelBusqueda.removeAll();
		String buscado = buscador.getText();
		List<Tupla> listaTuplas = botones.buscarElementoNuevo(buscado);
		ControladorBotones contBot = new ControladorBotones(botones,this);
		for(Tupla tupla : listaTuplas)
		{
			if(!botones.estaElemento(tupla.getId()))
			{
				JButton Boton = new JButton("");
				Boton.setForeground(new Color(0, 255, 51));
				Boton.setBackground(Color.BLACK);
				Boton.setMinimumSize(new Dimension(200, 300));
				Boton.setMaximumSize(new Dimension(200, 300));
				Boton.setPreferredSize(new Dimension(200, 300));
				Boton.setBounds(10, 24, 200, 300);
				Boton.setIcon(new ImageIcon(new URL(tupla.getUrl_img())));
				Boton.setActionCommand("BUSCA@/%" + String.valueOf(tupla.getId()) + "@/%" + tupla.getTitulo());
				Boton.addActionListener(contBot);
				panelBusqueda.add(Boton);
				panelBusqueda.updateUI();
			}
		}	
	}
}
