package GUI;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame marco = new JFrame("Flicks Archive");
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		
		marco.setPreferredSize(new Dimension(rec.width, rec.height));
		
		
		
		VentanaPrincipal window = new VentanaPrincipal();
		
		JScrollPane sp = new JScrollPane(window, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		marco.add(sp);
		
//		JScrollBar barra = new JScrollBar();
//		marco.add(barra);
		
		marco.setResizable(true);
		marco.pack();
		marco.setVisible(true);
		
	}

}
