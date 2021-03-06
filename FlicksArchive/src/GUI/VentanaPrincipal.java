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
import javax.swing.JTextPane;

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
import javax.swing.JTextArea;
import java.awt.Font;

public class VentanaPrincipal extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField buscador;
	private JButton aceptar;
	private List<JButton> botonesLista = new ArrayList<JButton>();
	private List<JButton> botonesBusca = new ArrayList<JButton>();
	private JPanel panelBusqueda;
	private JPanel panel;
	private JTabbedPane tabbedPane;
	private JTextField textFieldBuscar;
	@SuppressWarnings("rawtypes")
	private JComboBox cbEtiqueta1, cbEtiqueta2, cbEtiqueta3, cbEstado;
	private JButton btBuscarLista;
	
	private JRadioButton rdFavoritos;
	private JButton btLimpiar;
	private JButton btEliminarEtiqueta;
	private JLabel lblOrden;
	private JComboBox cbOrden;
	private JScrollPane scrollPane_2;
	private JComboBox cbTipo;
	private JLabel lblTipo;
	private JPanel tabNot;
	private JScrollPane spNot;
	private JLabel lblNotificaciones;
	private JTextPane taPublicaciones;
	private JScrollPane scrollPane_3;
	private JTextPane taRetiradas;
	private JLabel lblPublicaciones;
	private JLabel lblRetiradas;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VentanaPrincipal(String usuario) {
		setBackground(SystemColor.textHighlight);
		setName("FlicksArchive");
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setPreferredSize(new Dimension(rec.width, rec.height));
		setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(30, 30, 1855, 975);
		tabbedPane.setBackground(SystemColor.activeCaptionBorder);
		tabbedPane.setBorder(null);
		tabbedPane.setPreferredSize(new Dimension(rec.width, rec.height));
		add(tabbedPane);
		
		JPanel Lista = new JPanel();
		
		
		tabbedPane.addTab("Lista", null, Lista, null);
		GridBagLayout gbl_Lista = new GridBagLayout();
		gbl_Lista.columnWidths = new int[]{1850, 0};
		gbl_Lista.rowHeights = new int[]{117, 862, 0};
		gbl_Lista.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_Lista.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		Lista.setLayout(gbl_Lista);
		
		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 0;
		Lista.add(scrollPane_2, gbc_scrollPane_2);
		
		JPanel panelFiltros = new JPanel();
		scrollPane_2.setViewportView(panelFiltros);
		panelFiltros.setLayout(null);
		
		JLabel labelBuscar = new JLabel("Buscador");
		labelBuscar.setBounds(10, 0, 56, 16);
		panelFiltros.add(labelBuscar);
		
		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(10, 16, 284, 22);
		panelFiltros.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		cbEstado = new JComboBox();
		
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"-", "PENDIENTE", "VIENDO", "FINALIZADO"}));
		cbEstado.setBounds(366, 16, 185, 22);
		panelFiltros.add(cbEstado);
		
		btBuscarLista = new JButton("Filtrar");
		btBuscarLista.setBounds(1391, 17, 97, 25);
		panelFiltros.add(btBuscarLista);
		
		JLabel lbEstados = new JLabel("Estado");
		lbEstados.setBounds(366, 0, 56, 16);
		panelFiltros.add(lbEstados);
		
		cbEtiqueta1 = new JComboBox();
		cbEtiqueta1.setBounds(619, 16, 146, 22);
		panelFiltros.add(cbEtiqueta1);
		
		cbEtiqueta2 = new JComboBox();
		cbEtiqueta2.setBounds(823, 16, 146, 22);
		panelFiltros.add(cbEtiqueta2);
		
		cbEtiqueta3 = new JComboBox();
		cbEtiqueta3.setBounds(1024, 16, 146, 22);
		panelFiltros.add(cbEtiqueta3);
		
		JLabel lblEtiquetas = new JLabel("Etiquetas");
		lblEtiquetas.setBounds(619, 0, 56, 16);
		panelFiltros.add(lblEtiquetas);
		
		rdFavoritos = new JRadioButton("Favoritos");
		rdFavoritos.setBounds(1241, 15, 127, 25);
		panelFiltros.add(rdFavoritos);
		
		btLimpiar = new JButton("Limpiar");
		btLimpiar.setBounds(1541, 17, 97, 25);
		panelFiltros.add(btLimpiar);
		
		btEliminarEtiqueta = new JButton("Eliminar Etiqueta");
		btEliminarEtiqueta.setBounds(1692, 15, 146, 25);
		panelFiltros.add(btEliminarEtiqueta);
		
		lblOrden = new JLabel("Orden");
		lblOrden.setBounds(10, 49, 46, 14);
		panelFiltros.add(lblOrden);
		
		cbOrden = new JComboBox();
		cbOrden.setModel(new DefaultComboBoxModel(new String[] {"A-Z", "Z-A", "Nota"}));
		cbOrden.setBounds(10, 64, 118, 20);
		panelFiltros.add(cbOrden);
		
		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"-", "Serie", "Pel\u00EDcula"}));
		cbTipo.setBounds(366, 64, 185, 20);
		panelFiltros.add(cbTipo);
		
		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(366, 49, 46, 14);
		panelFiltros.add(lblTipo);
		
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
		JPanel A�adir = new JPanel();
		tabbedPane.addTab("A\u00F1adir", new ImageIcon(VentanaPrincipal.class.getResource("/img/anadir.png")), A�adir, null);
		
		
		GridBagLayout gbl_A�adir = new GridBagLayout();
		gbl_A�adir.columnWidths = new int[]{1728, 0};
		gbl_A�adir.rowHeights = new int[]{811, 0};
		gbl_A�adir.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_A�adir.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		A�adir.setLayout(gbl_A�adir);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		A�adir.add(panel_2, gbc_panel_2);
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
		
		tabNot = new JPanel();
		tabbedPane.addTab("Notificaciones", null, tabNot, null);
		tabNot.setLayout(null);
		
		spNot = new JScrollPane();
		spNot.setBorder(null);
		spNot.setBounds(10, 116, 1830, 389);
		tabNot.add(spNot);
		
		taPublicaciones = new JTextPane();
		taPublicaciones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		taPublicaciones.setEditable(false);
		spNot.setViewportView(taPublicaciones);
		
		lblNotificaciones = new JLabel("Notificaciones para " + usuario);
		lblNotificaciones.setFont(new Font("Sitka Display", Font.BOLD, 36));
		lblNotificaciones.setBounds(10, 11, 423, 39);
		tabNot.add(lblNotificaciones);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBorder(null);
		scrollPane_3.setBounds(10, 561, 1830, 389);
		tabNot.add(scrollPane_3);
		
		taRetiradas = new JTextPane();
		taRetiradas.setBorder(null);
		taRetiradas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_3.setViewportView(taRetiradas);
		taRetiradas.setEditable(false);
		
		lblPublicaciones = new JLabel("PUBLICACIONES");
		lblPublicaciones.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblPublicaciones.setBounds(10, 61, 234, 34);
		tabNot.add(lblPublicaciones);
		
		lblRetiradas = new JLabel("RETIRADAS");
		lblRetiradas.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblRetiradas.setForeground(new Color(0, 0, 0));
		lblRetiradas.setBounds(10, 516, 234, 34);
		tabNot.add(lblRetiradas);
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
		btLimpiar.addActionListener(ctr);
		btLimpiar.setActionCommand("LIMPIAR");
		btEliminarEtiqueta.addActionListener(ctr);
		btEliminarEtiqueta.setActionCommand("ELIMINAR ETIQUETAS");
		cbOrden.setActionCommand("ORDENAR");
		cbOrden.addActionListener(ctr);		
		
	}
	
	public void setTextoBuscador(String texto) {
		buscador.setText(texto);
	}
	
	public int getOrden () {
		return cbOrden.getSelectedIndex();
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
		int tipo = cbTipo.getSelectedIndex();
		if (tipo==1) {
			fil.setPelicula(false);
		} else if (tipo == 2) {
			fil.setPelicula(true);
		} else {
			fil.setPelicula(null);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void setModelComboBox(Lista botones)
	{
		cbEtiqueta1.setModel(new DefaultComboBoxModel<>(botones.etiquetas().toArray()));
		cbEtiqueta2.setModel(new DefaultComboBoxModel<>(botones.etiquetas().toArray()));
		cbEtiqueta3.setModel(new DefaultComboBoxModel<>(botones.etiquetas().toArray()));
		cbEstado.setSelectedIndex(0);
		rdFavoritos.setSelected(false);
		cbTipo.setSelectedIndex(0);
		textFieldBuscar.setText("");
		updateUI();
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

	public void borraTodo() {
		// TODO Auto-generated method stub
		panelBusqueda.removeAll();
	}

	public int getIndicetab() {
		// TODO Auto-generated method stub
		return tabbedPane.getSelectedIndex();
	}

	public void escribeNotificaciones(String[] logNotificaciones) {
		// TODO Auto-generated method stub
		taPublicaciones.setText(logNotificaciones[0]);
		taRetiradas.setText(logNotificaciones[1]);
		
	}
}
