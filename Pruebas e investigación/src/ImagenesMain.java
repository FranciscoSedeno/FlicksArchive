
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

public class ImagenesMain {

	public static void main(String[] args) {
		
		
		
			
		JFrame frame = new JFrame();
		JPanel panel = new Imagenes();
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

	}
	
	@SuppressWarnings("unused")
	private static void Out(File[] files) {
		for(int i = 0; i<files.length; i++) {
			String file = files[i].getAbsolutePath();
			System.out.println(file);
		}
		
	}
	
	
	public static void cambio(JLabel cartel) {
		
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		   } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {}
			
				JFileChooser chooser = new JFileChooser("C:\\Users\\Usuario\\Desktop\\Asignaturas\\Segundo\\Ingenieria del Software\\Tests\\Files\\");
			    chooser.setMultiSelectionEnabled(false);
			    int status = chooser.showOpenDialog(null);
			    if (status == JFileChooser.APPROVE_OPTION) {
			    	
			    	File file = chooser.getSelectedFile();
			    	
					ImageIcon img = new ImageIcon(file.getAbsolutePath());
					Image img2 = img.getImage().getScaledInstance(200, 300, 1);
					ImageIcon img3 = new ImageIcon(img2);
					
					cartel.setIcon(img3);
			}
	}
	
public static void limpiar(JLabel cartel) {	
				    	
	ImageIcon img = new ImageIcon("C:\\Users\\Usuario\\Desktop\\Asignaturas\\Segundo\\Ingenieria del Software\\Tests\\Blank.png");
	Image img2 = img.getImage().getScaledInstance(200, 300, 1);
	ImageIcon img3 = new ImageIcon(img2);				
	cartel.setIcon(img3);
}
	
	public static void copiar() {
		
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	    }
		
	    JFileChooser chooser = new JFileChooser();
	    chooser.setMultiSelectionEnabled(true);
	    int status = chooser.showOpenDialog(null);
	    if (status == JFileChooser.APPROVE_OPTION) {
	    	
	    	File[] files = chooser.getSelectedFiles();
	    	
	    	for(int i = 0; i< files.length; i++) {
	    		try {
	    			Path directorio = Paths.get("C:\\Users\\Usuario\\Desktop\\Asignaturas\\Segundo\\Ingenieria del Software\\Tests\\Files\\"+ files[i].getName());
	    			Files.copy(Paths.get(files[i].getAbsolutePath()), directorio);
	    		} catch (IOException e) {
	    			JOptionPane.showMessageDialog(null, "Uno o mas archivos ya estaban en la carpeta de destino");
	    		}
	    	}
	    }
	}
}


