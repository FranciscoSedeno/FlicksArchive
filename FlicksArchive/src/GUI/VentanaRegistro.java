package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class VentanaRegistro extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JLabel lblCon;
	private JLabel lblConfCon;
	private JPasswordField pfCont;
	private JPasswordField pfConfirmacion;
	private JButton btnRegistrarse;
	private JLabel lblUsuario;
	private JLabel lblError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
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
	public VentanaRegistro() {
		super("Registro de Usuario");
		
		ImageIcon img = new ImageIcon(Principal.class.getResource("/img/FAicon.png"));
		setIconImage(img.getImage());
		
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(30, 49, 356, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		lblUsuario = new JLabel("Usuario (debe contener entre 4 y 20 caracteres ):",SwingConstants.LEFT);
		lblUsuario.setVerticalAlignment(SwingConstants.TOP);
		lblUsuario.setBounds(30, 15, 356, 23);
		contentPane.add(lblUsuario);
		
		lblCon = new JLabel("Introduzca la contraseña que desea (entre 4 y 20 caracteres):",SwingConstants.LEFT);
		lblCon.setBounds(30, 80, 356, 28);
		contentPane.add(lblCon);
		
		lblConfCon = new JLabel("Vuelva a introducir la contrase\u00F1a",SwingConstants.LEFT);
		lblConfCon.setBounds(30, 150, 270, 14);
		contentPane.add(lblConfCon);
		
		pfCont = new JPasswordField();
		pfCont.setBounds(30, 119, 356, 20);
		contentPane.add(pfCont);
		
		pfConfirmacion = new JPasswordField();
		pfConfirmacion.setBounds(30, 175, 356, 20);
		contentPane.add(pfConfirmacion);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(30, 206, 113, 23);
		contentPane.add(btnRegistrarse);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(153, 198, 233, 38);
		contentPane.add(lblError);
		
	}
	public void controlador (ControladorInicio ctr) {
		tfUsuario.setActionCommand("CONFIRMAR");
		pfConfirmacion.setActionCommand("CONFIRMAR");
		pfCont.setActionCommand("CONFIRMAR");
		btnRegistrarse.setActionCommand("CONFIRMAR");
		tfUsuario.addActionListener(ctr);
		pfConfirmacion.addActionListener(ctr);
		pfCont.addActionListener(ctr);
		btnRegistrarse.addActionListener(ctr);
		addWindowListener(ctr);
	}

	public String getusu() {
		// TODO Auto-generated method stub
		return tfUsuario.getText();
	}

	public String getcont() {
		// TODO Auto-generated method stub
		return new String(pfCont.getPassword());
	}

	public String getconfirmacion() {
		// TODO Auto-generated method stub
		return new String(pfConfirmacion.getPassword());
	}
	
	public void setError(String error) {
		lblError.setText(error);
	}
	
	
}
