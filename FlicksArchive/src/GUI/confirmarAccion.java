package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import flicksArchive.Lista;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class confirmarAccion extends JFrame {

	private JPanel contentPane;
	private int ID;
	private String titulo;
	private String accion;
	
	/**
	 * Create the frame.
	 */
	public confirmarAccion(int ID, String titulo, String accion, Lista lista, JPanel panel) 
	{
		super(accion);
		this.ID = ID;
		this.titulo = titulo;
		this.accion = accion;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel texto = new JLabel("¿Estás seguro de que quieres " + accion.toLowerCase() + " " + titulo + "?");
		texto.setHorizontalAlignment(SwingConstants.CENTER);
		texto.setBounds(-2, 39, 524, 106);
		contentPane.add(texto);
		
		JButton btnSi = new JButton("Si");
		btnSi.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(accion.equalsIgnoreCase("añadir"))
				{
					try 
					{
						lista.añadirElemento(lista.extraerElementoBD(ID));
					} catch (SQLException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else
				{
					lista.eliminarElemento(ID);
					
					VentanaPrincipal.refrescar(panel, lista);
				}
				dispose();
			}
		});
		btnSi.setBounds(110, 158, 97, 25);
		contentPane.add(btnSi);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
		btnNo.setBounds(299, 158, 97, 25);
		contentPane.add(btnNo);
		this.setResizable(false);
	}
}
