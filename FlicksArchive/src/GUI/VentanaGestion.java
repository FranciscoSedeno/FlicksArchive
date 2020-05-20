package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import flicksArchive.Elemento;
import flicksArchive.Elemento.estadoVisualizacion;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.GridBagConstraints;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaGestion extends JFrame {

	private JPanel contentPane;
	private Elemento elem;

	public VentanaGestion(String titulo,Elemento elemento) {
		super(titulo);
		elem = elemento;
		setPreferredSize(new Dimension(800, 600));
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 684, 433);
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
        lblDescripcin.setBounds(290, 158, 89, 14);
        panel.add(lblDescripcin);
        
        JLabel desc = new JLabel(elem.getDescripcion());
        desc.setBounds(290, 202, 232, 59);
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
        
        JRadioButton rdbtnFavorito = new JRadioButton("Favorito");        
        rdbtnFavorito.setBounds(20, 341, 109, 23);
        panel.add(rdbtnFavorito);
        rdbtnFavorito.setSelected(elem.isFavorito());
        
        JComboBox CBnotas = new JComboBox();
        CBnotas.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        CBnotas.setBounds(358, 78, 73, 20);
        panel.add(CBnotas);
        CBnotas.setSelectedIndex(elem.getNotaUsuario());
        
        JComboBox CBestado = new JComboBox();
        CBestado.setModel(new DefaultComboBoxModel(estadoVisualizacion.values()));
        CBestado.setBounds(358, 42, 99, 20);
        panel.add(CBestado);
        CBestado.setSelectedIndex(elem.getEstado().ordinal());
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		elem.setFavorito(rdbtnFavorito.isSelected());
        		elem.setEstado((estadoVisualizacion)CBestado.getSelectedItem());
        		elem.setNotaUsuario(CBnotas.getSelectedIndex());
        		dispose();
        	}
        });
        btnGuardar.setBounds(515, 341, 89, 23);
        panel.add(btnGuardar);
	}
}
