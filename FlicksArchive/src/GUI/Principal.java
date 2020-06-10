package GUI;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import flicksArchive.Lista;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Principal {
	
	
	public void crearGUI () {
		try 
		{
			JFrame marco = new JFrame("Flicks Archive");
			Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
			Lista prueba;

			prueba = new Lista("Fran");
			
			
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error accediendo a la Base de datos");
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		VentanaInicio inicio = new VentanaInicio();
		ControladorInicio contInicio = new ControladorInicio(inicio);
		ImageIcon img = new ImageIcon(Principal.class.getResource("/img/FAicon.png"));
		inicio.setIconImage(img.getImage());
		inicio.controlador(contInicio);
		inicio.setVisible(true);
		
		
		
	}

}
