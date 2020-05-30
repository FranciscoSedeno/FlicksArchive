package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import flicksArchive.Lista;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

public class confirmarAccion extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSi;
	private JButton btnNo;
	private String accion;
	private int ID;

	public confirmarAccion(int ID, VentanaPrincipal ventanaprincipal, String titulo, String accion) 
	{
		super(accion);
		
		this.ID = ID;
		this.accion = accion;
		setBounds(100, 100, 526, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel texto = new JLabel("¿Estás seguro de que quieres " + accion.toLowerCase() + " " + titulo + "?");
		texto.setHorizontalAlignment(SwingConstants.CENTER);
		texto.setBounds(-2, 39, 524, 106);
		contentPane.add(texto);
		
		btnSi = new JButton("Si");
		
		btnSi.setBounds(110, 158, 97, 25);
		contentPane.add(btnSi);
		
		btnNo = new JButton("No");
		btnNo.setBounds(299, 158, 97, 25);
		contentPane.add(btnNo);
		this.setResizable(false);
	}
	
	public void controlador (ActionListener ctr) {
		btnSi.setActionCommand(accion + " SI " + ID);
		btnSi.addActionListener(ctr);
		btnNo.setActionCommand(accion + " NO " + ID);
		btnNo.addActionListener(ctr);

	}
}
