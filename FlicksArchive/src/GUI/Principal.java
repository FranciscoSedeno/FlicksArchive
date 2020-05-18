package GUI;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame marco = new JFrame("Flicks Archive");
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		
		marco.setPreferredSize(new Dimension(rec.width, rec.height));
		
		
		
		VentanaPrincipal window = new VentanaPrincipal();
		
		marco.add(window);
		
		marco.setResizable(true);
		marco.pack();
		marco.setVisible(true);
		
	}

}
