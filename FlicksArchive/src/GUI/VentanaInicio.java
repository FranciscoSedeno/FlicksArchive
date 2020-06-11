package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPasswordField;
import java.awt.event.ActionEvent;

public class VentanaInicio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsuario;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	private JButton btnIniciar;
	private JButton btnRegistrar;
	private JLabel lblError;
	private JLabel lblFlicksArchive;
	private JPasswordField pfContr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
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
	public VentanaInicio() {
		super("Inicio de Sesión");
		
		ImageIcon img = new ImageIcon(Principal.class.getResource("/img/FAicon.png"));
		setIconImage(img.getImage());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(69, 142, 75, 14);
		contentPane.add(lblUsuario);
		
		lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(69, 182, 75, 14);
		contentPane.add(lblContrasea);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(154, 139, 201, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(45);
		
		btnIniciar = new JButton("Iniciar Sesi\u00F3n");
	
		btnIniciar.setBounds(69, 262, 121, 23);
		contentPane.add(btnIniciar);
		
		btnRegistrar = new JButton("Reg\u00EDstrate");
		btnRegistrar.setBounds(234, 262, 121, 23);
		contentPane.add(btnRegistrar);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setVerticalAlignment(SwingConstants.TOP);
		lblError.setBounds(69, 210, 286, 41);
		contentPane.add(lblError);
		
		lblFlicksArchive = new JLabel("");
		lblFlicksArchive.setBounds(79, 11, 286, 100);
		
		File file = new File (VentanaInicio.class.getResource("/img/FAbanner.png").getPath());
		try {
			BufferedImage bfImage = ImageIO.read(file);
			Image dimg = bfImage.getScaledInstance(lblFlicksArchive.getWidth(), lblFlicksArchive.getHeight(), bfImage.SCALE_SMOOTH);
			ImageIcon icono = new ImageIcon(dimg);
			lblFlicksArchive.setIcon(icono);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(lblFlicksArchive);
		
		pfContr = new JPasswordField();
		pfContr.setBounds(154, 179, 201, 20);
		contentPane.add(pfContr);
	}
	
	public void controlador (ActionListener ctr) {
		tfUsuario.setActionCommand("INICIAR");
		pfContr.setActionCommand("INICIAR");
		btnIniciar.setActionCommand("INICIAR");
		btnRegistrar.setActionCommand("REGISTRAR");
		tfUsuario.addActionListener(ctr);
		pfContr.addActionListener(ctr);
		btnIniciar.addActionListener(ctr);
		btnRegistrar.addActionListener(ctr);
		
	}

	public String getusu() {
		// TODO Auto-generated method stub
		return tfUsuario.getText();
	}

	public String getcont() {
		// TODO Auto-generated method stub
		return new String(pfContr.getPassword());
	}
	public void setError(String msg) {
		lblError.setText(msg);
	}
}
