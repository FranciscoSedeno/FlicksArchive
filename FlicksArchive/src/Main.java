
import java.sql.SQLException;

import flicksArchive.Elemento;
import flicksArchive.Lista;


public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		
		Lista prueba=new Lista("Fran");
		System.out.println(prueba.toString());
		prueba.getConexion().finalizarConexion();
	}

}
