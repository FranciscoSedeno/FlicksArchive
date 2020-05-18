package GUI;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

public class VentanaPrincipal extends JPanel {

	/**
	 * Create the panel.
	 */
	
	
	public VentanaPrincipal() {
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setPreferredSize(new Dimension(rec.width-100, rec.height-100));

	}

}
