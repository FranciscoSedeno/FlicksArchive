package GUI;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;


public class VentanaPrincipal extends JPanel {
	public JTextField buscador;

	/**
	 * Create the panel.
	 */
	
	
	public VentanaPrincipal() {
		setName("FlicksArchive");
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setPreferredSize(new Dimension(rec.width-100, rec.height-100));
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setPreferredSize(new Dimension(1920, 1020));
		tabbedPane.setBounds(10, 11, 1773, 918);
		add(tabbedPane);
		
		JPanel Lista = new JPanel();
		tabbedPane.addTab("Lista", null, Lista, null);
		Lista.setLayout(null);
		
		JButton flechader = new JButton("");
		flechader.setBounds(1717, 11, 48, 48);
		try {
			flechader.setIcon(new ImageIcon(new URL("http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/48/Actions-go-next-icon.png")));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//flechader.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/ra.png")));
		Lista.add(flechader);
		flechader.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(flechader, "Flecha Derecha");
			}
		});
		
		JButton flechaizq = new JButton("");
		flechaizq.setBounds(1659, 11, 48, 48);
		flechaizq.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(flechaizq, "Flecha Izquierda");
			}
		});
		flechaizq.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/la.png")));
		Lista.add(flechaizq);
		
		JPanel Añadir = new JPanel();
		tabbedPane.addTab("A\u00F1adir", new ImageIcon(VentanaPrincipal.class.getResource("/img/anadir.png")), Añadir, null);
		Añadir.setLayout(null);
		
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
