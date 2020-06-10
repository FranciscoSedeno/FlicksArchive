package GUI;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import flicksArchive.Elemento;
import flicksArchive.Elemento.estadoVisualizacion;
import flicksArchive.Etiqueta;
import flicksArchive.Filtro;
import flicksArchive.Lista;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.GridBagConstraints;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class VentanaGestion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnGuardar;
	private JButton btnEliminar;
	private JRadioButton rdbtnFavorito;
	@SuppressWarnings("rawtypes")
	private JComboBox CBnotas, CBestado;
	private JComboBox<Integer> cbProgreso;
	private JTextField txtGenUsu1;
	private JTextField textGenUsu2;
	private JTextField textGenUsu3;
	private JLabel lbGenero1, lbGenero2, lbGenero3;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VentanaGestion(String titulo,Elemento elem,VentanaPrincipal ventana) 
	{
		super(titulo);
		setPreferredSize(new Dimension(800, 600));
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(560, 240, 684, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel_1.add(panel, gbc_panel);
		panel.setLayout(null);
        
        JLabel lblNombre = new JLabel(elem.getTitulo());
        lblNombre.setBounds(290, 19, 232, 14);
        panel.add(lblNombre);
        
        JLabel lblEstado = new JLabel("Estado: ");
        lblEstado.setBounds(290, 45, 46, 14);
        panel.add(lblEstado);
        
        JLabel lblNota = new JLabel("Nota:");
        lblNota.setBounds(290, 81, 46, 14);
        panel.add(lblNota);
        
        Integer[] notas = new Integer[11];
        for (int i = 0; i <= 10; i++ ) {
        	notas[i] = i;
        }
        
        JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
        lblDescripcin.setBounds(290, 118, 89, 14);
        panel.add(lblDescripcin);
        
        JLabel desc = new JLabel(elem.getDescripcion());
        desc.setVerticalAlignment(SwingConstants.TOP);
        desc.setHorizontalAlignment(SwingConstants.LEFT);
        desc.setBounds(290, 143, 343, 23);
        panel.add(desc);
        
        JLabel imagen = new JLabel();
        imagen.setBounds(20, 11, 200, 300);
        panel.add(imagen);
        imagen.setForeground(new Color(0, 255, 51));
        imagen.setBackground(new Color(102, 51, 0));
        imagen.setMinimumSize(new Dimension(200, 300));
        imagen.setMaximumSize(new Dimension(200, 300));
        imagen.setPreferredSize(new Dimension(200, 300));
        try {
			imagen.setIcon(new ImageIcon(new URL(elem.getURL_Imagen())));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        rdbtnFavorito = new JRadioButton("Favorito");        
        rdbtnFavorito.setBounds(20, 341, 109, 23);
        panel.add(rdbtnFavorito);
        rdbtnFavorito.setSelected(elem.isFavorito());
        
        CBnotas = new JComboBox();
        CBnotas.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        CBnotas.setBounds(358, 78, 73, 20);
        panel.add(CBnotas);
        CBnotas.setSelectedIndex(elem.getNotaUsuario());
        
        CBestado = new JComboBox();
        CBestado.setModel(new DefaultComboBoxModel(estadoVisualizacion.values()));
        CBestado.setBounds(358, 42, 99, 20);
        panel.add(CBestado);
        CBestado.setSelectedIndex(elem.getEstado().ordinal());
        
        btnGuardar = new JButton("Guardar");

        btnGuardar.setBounds(194, 341, 89, 23);
        panel.add(btnGuardar);
        
        btnEliminar = new JButton("Eliminar");

        btnEliminar.setBounds(412, 340, 89, 23);
        panel.add(btnEliminar);
        
        JLabel lblGnero = new JLabel("G\u00E9nero");
        lblGnero.setBounds(290, 179, 56, 16);
        panel.add(lblGnero);
        
        Etiqueta[] etiquetasPre = elem.etiquetasPredeterminadas();
        Etiqueta[] etiquetasUsu = elem.etiquetasUsuario();
        
        lbGenero1 = new JLabel((etiquetasPre[0]!=null)?etiquetasPre[0].getNombre():"");
        lbGenero1.setBounds(290, 208, 89, 16);
        panel.add(lbGenero1);
        
        lbGenero2 = new JLabel((etiquetasPre[1]!=null)?etiquetasPre[1].getNombre():"");
        lbGenero2.setBounds(412, 208, 89, 16);
        panel.add(lbGenero2);
        
        lbGenero3 = new JLabel((etiquetasPre[2]!=null)?etiquetasPre[2].getNombre():"");
        lbGenero3.setBounds(544, 208, 89, 16);
        panel.add(lbGenero3);
        
        txtGenUsu1 = new JTextField();
        txtGenUsu1.setText((etiquetasUsu[0] != null)?etiquetasUsu[0].getNombre():"");
        txtGenUsu1.setBounds(290, 273, 89, 22);
        panel.add(txtGenUsu1);
        txtGenUsu1.setColumns(10);
        
        textGenUsu2 = new JTextField();
        textGenUsu2.setText((etiquetasUsu[1] != null)?etiquetasUsu[1].getNombre():"");
        textGenUsu2.setColumns(10);
        textGenUsu2.setBounds(412, 274, 89, 22);
        panel.add(textGenUsu2);
        
        textGenUsu3 = new JTextField();
        textGenUsu3.setText((etiquetasUsu[2] != null)?etiquetasUsu[2].getNombre():"");
        textGenUsu3.setColumns(10);
        textGenUsu3.setBounds(544, 274, 89, 22);
        panel.add(textGenUsu3);
        
        JLabel lbGeneroPerso = new JLabel("Etiquetas personalizadas");
        lbGeneroPerso.setBounds(290, 237, 192, 16);
        panel.add(lbGeneroPerso);
        
        JLabel lblN = new JLabel("Progreso");
        lblN.setBounds(491, 46, 56, 16);
        panel.add(lblN);
        
        String numCapitulos[] = new String[elem.getNumCapitulos() + 1];
        for (int i = 0; i < numCapitulos.length; i++) 
        {
        	numCapitulos[i] = "" + i;
		}
        
        cbProgreso = new JComboBox();
        cbProgreso.setModel(new DefaultComboBoxModel(numCapitulos));
        cbProgreso.setSelectedIndex(elem.getProgreso());
        cbProgreso.setBounds(555, 45, 78, 22);
        cbProgreso.setEditable(true);
        panel.add(cbProgreso);
	}
	
	
	public void controlador (ActionListener ctr, Elemento elem) {
		btnEliminar.setActionCommand("ELIMINAR " + elem.getId());
		btnEliminar.addActionListener(ctr);
		btnGuardar.setActionCommand("GUARDAR " + elem.getId() + " " + " " + " ");
		btnGuardar.addActionListener(ctr);
	}
	
	public void actualizarelemento(Filtro filtro, Elemento elem) throws NumberFormatException
	{
		String aux;
		elem.setFavorito(rdbtnFavorito.isSelected());
		if (Integer.parseInt((String) cbProgreso.getSelectedItem()) < 0) {
			elem.setProgreso(0);
			cbProgreso.getModel().getSize();
		} else if (Integer.parseInt((String) cbProgreso.getSelectedItem()) >= cbProgreso.getModel().getSize()) {
			elem.setProgreso(cbProgreso.getModel().getSize()-1);
		} else {
			elem.setProgreso(cbProgreso.getSelectedIndex());
		}
		
		elem.resetearEtiquetas();
		aux = txtGenUsu1.getText();
		if(!aux.equals(""))
			elem.anadirEtiqueta(filtro, aux);
		aux = textGenUsu2.getText();
		if(!aux.equals(""))
			elem.anadirEtiqueta(filtro, aux);
		aux = textGenUsu3.getText();
		if(!aux.equals(""))
			elem.anadirEtiqueta(filtro, aux);
		elem.setEstado((estadoVisualizacion)CBestado.getSelectedItem());
		elem.setNotaUsuario(CBnotas.getSelectedIndex());
	}
}
