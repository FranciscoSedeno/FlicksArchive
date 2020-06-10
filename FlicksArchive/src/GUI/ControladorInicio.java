package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
			//TODO Iniciar sesion correctamente
//			if (!usuario.existe || usuario.contraseña != contraseña) {
				inicio.setError("<html>El usuario no existe o la combinación no es correcta</html>");
//			} else { 
//				conexion.iniciasesion(usuario);
//			}
		} else if (comando.equals("CONFIRMAR")){
			registro.setError("");
			String usuario=registro.getusu();
			String contraseña = registro.getcont();
			String confirmacion = registro.getconfirmacion();
			if (usuario.length()<4 || usuario.length()>20) {
				registro.setError("<html>El usuario tiene que contener entre 4 y 20 caracteres</html>");
			} else if (!contraseña.equals(confirmacion)) {
				registro.setError("Las contraseñas no coinciden");
			//} else if (usuario.enbasedatos) { TODO Si el usuario está en la base de datos-> No crear e informar
			} else {
				//TODO conexion.iniciasesion(usuario);
			}
			
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
