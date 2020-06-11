package GUI;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JFrame;

import flicksArchive.Conexion;
import flicksArchive.Lista;

public class ControladorInicio implements ActionListener, WindowListener {
	private VentanaInicio inicio;
	private VentanaRegistro registro;
	public ControladorInicio(VentanaInicio inicio) {
		this.inicio = inicio;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		
		if (comando.equals("REGISTRAR")) {
			registro = new VentanaRegistro();
			inicio.setVisible(false);
			registro.controlador(this);
			registro.setVisible(true);
		} else if (comando.equals("INICIAR")) {
			String usuario= inicio.getusu();
			String contraseña = inicio.getcont();
			if (!Conexion.verificacion(usuario, contraseña)) {
				inicio.setError("<html>El usuario no existe o la combinación no es correcta</html>");
			} else { 
				crearGUI(usuario);
				inicio.dispose();
			}
		} else if (comando.equals("CONFIRMAR")){
			registro.setError("");
			String usuario=registro.getusu();
			String contraseña = registro.getcont();
			String confirmacion = registro.getconfirmacion();
			if (usuario.length()<4 || usuario.length()>20) {
				registro.setError("<html>El usuario tiene que contener entre 4 y 20 caracteres</html>");
			} else if (contraseña.length()<4 || contraseña.length()>20) {
				registro.setError("<html>La contraseña debe tener entre 4 y 20 caracteres</html>");
			} else if (!contraseña.equals(confirmacion)) {
				registro.setError("Las contraseñas no coinciden");
			} else if (!Conexion.disponible(usuario)) {
				registro.setError("El nombre de usuario no está disponible");
			} else {
				Conexion.registro(usuario, contraseña);
				registro.dispose();
				crearGUI(usuario);
			}
			
		}
	}
	
	public void crearGUI (String usuario) {
		try 
		{
			JFrame marco = new JFrame("Flicks Archive");
			Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
			Lista prueba;
			
			
			//Conexion conexion = new Conexion("Fran");

			prueba = new Lista(usuario);
			
			
			marco.setPreferredSize(new Dimension(rec.width, rec.height));
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 1902, 0, 0};
			gridBagLayout.rowHeights = new int[]{0, 983, 0, 0};
			gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			marco.getContentPane().setLayout(gridBagLayout);
			
			
			
			VentanaPrincipal window = new VentanaPrincipal();
			window.refrescar(prueba);
			window.setModelComboBox(prueba);
			GridBagConstraints gbc_window = new GridBagConstraints();
			gbc_window.insets = new Insets(0, 0, 5, 5);
			gbc_window.fill = GridBagConstraints.BOTH;
			gbc_window.gridx = 1;
			gbc_window.gridy = 1;
			marco.getContentPane().add(window, gbc_window);
			
			marco.setResizable(true);
			marco.pack();
			Controlador ctr = new Controlador(prueba, window);
			window.controlador(ctr);
			marco.pack();
			marco.setVisible(true);
			
			marco.addWindowListener(new WindowAdapter() 
			{
				@Override
				public void windowClosing(WindowEvent windowEvent)
				{
					try 
					{
						prueba.actualizar();
						prueba.getConexion().finalizarConexion();
						System.exit(0);
					} catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
			});
		} catch (SQLException e) {
			System.err.println("Error accediendo a la Base de datos");
			System.exit(-1);
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		inicio.setVisible(true);
	}
	
	@Override
	public void windowActivated(WindowEvent e) {		
	}
	@Override
	public void windowClosed(WindowEvent e) {
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

}
