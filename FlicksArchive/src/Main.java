
import java.sql.SQLException;
import java.util.List;

import flicksArchive.Elemento;
import flicksArchive.Lista;
import flicksArchive.Tupla;


public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		
		Lista prueba=new Lista("Guillermo");
		List<Tupla> listaTuplas = prueba.buscarElementoNuevo("B");
//		System.out.println(prueba.toString());
		for (Tupla tupla : listaTuplas) 
		{
			System.out.println(tupla.getTitulo() + " " + tupla.getUrl_img());
		}
		prueba.getConexion().finalizarConexion();
	}

}
