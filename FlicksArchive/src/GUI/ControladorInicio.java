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
			String contrase�a = inicio.getcont();
			//TODO Iniciar sesion correctamente
//			if (!usuario.existe || usuario.contrase�a != contrase�a) {
				inicio.setError("<html>El usuario no existe o la combinaci�n no es correcta</html>");
//			} else { 
//				conexion.iniciasesion(usuario);
//			}
		} else if (comando.equals("CONFIRMAR")){
			registro.setError("");
			String usuario=registro.getusu();
			String contrase�a = registro.getcont();
			String confirmacion = registro.getconfirmacion();
			if (usuario.length()<4 || usuario.length()>20) {
				registro.setError("<html>El usuario tiene que contener entre 4 y 20 caracteres</html>");
			} else if (!contrase�a.equals(confirmacion)) {
				registro.setError("Las contrase�as no coinciden");
			//} else if (usuario.enbasedatos) { TODO Si el usuario est� en la base de datos-> No crear e informar
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
