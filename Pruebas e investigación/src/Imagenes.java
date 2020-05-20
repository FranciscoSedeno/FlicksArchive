import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Imagenes extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Imagenes() {
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(849, 400));
		setLayout(null);
		ImageIcon img = new ImageIcon("C:\\Users\\Usuario\\Desktop\\Asignaturas\\Segundo\\Ingenieria del Software\\Tests\\Blank.png");
		Image img2 = img.getImage().getScaledInstance(200, 300, 1);
		ImageIcon img3 = new ImageIcon(img2);
		
		JLabel Panel_1 = new JLabel("");
		Panel_1.setForeground(new Color(0, 255, 51));
		Panel_1.setBackground(new Color(102, 51, 0));
		Panel_1.setMinimumSize(new Dimension(200, 300));
		Panel_1.setMaximumSize(new Dimension(200, 300));
		Panel_1.setPreferredSize(new Dimension(200, 300));
		Panel_1.setBounds(10, 24, 200, 300);
		Panel_1.setIcon(img3);
		add(Panel_1);
		
		JLabel Panel_2 = new JLabel("");
		Panel_2.setPreferredSize(new Dimension(200, 300));
		Panel_2.setMinimumSize(new Dimension(200, 300));
		Panel_2.setMaximumSize(new Dimension(200, 300));
		Panel_2.setBounds(220, 24, 200, 300);
		Panel_2.setIcon(img3);
		add(Panel_2);
		
		JLabel Panel_3 = new JLabel("");
		Panel_3.setPreferredSize(new Dimension(200, 300));
		Panel_3.setMinimumSize(new Dimension(200, 300));
		Panel_3.setMaximumSize(new Dimension(200, 300));
		Panel_3.setBounds(430, 24, 200, 300);
		Panel_3.setIcon(img3);
		add(Panel_3);
		
		JLabel Panel_4 = new JLabel("");
		Panel_4.setPreferredSize(new Dimension(200, 300));
		Panel_4.setMinimumSize(new Dimension(200, 300));
		Panel_4.setMaximumSize(new Dimension(200, 300));
		Panel_4.setBounds(640, 24, 200, 300);
		Panel_4.setIcon(img3);
		add(Panel_4);
		
		Choice choice = new Choice();
		choice.setMinimumSize(new Dimension(123, 23));
		choice.setPreferredSize(new Dimension(123, 23));
		choice.setBounds(351, 347, 70, 30); 
		choice.add("Sala 1");
		choice.add("Sala 2");
		choice.add("Sala 3");
		choice.add("Sala 4");
		add(choice);
		
		JButton Boton = new JButton("Seleccionar imagen");
		Boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel cartel = null;
				String data = choice.getItem(choice.getSelectedIndex());
				switch (data) {
				case "Sala 1":
					cartel = Panel_1;
					break;
				case "Sala 2":
					cartel = Panel_2;
					break;
				case "Sala 3":
					cartel = Panel_3;
					break;
				case "Sala 4":
					cartel = Panel_4;
					break;
				}
				ImagenesMain.cambio(cartel);
			}
		});
		Boton.setBounds(10, 335, 159, 45);
		add(Boton);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setMinimumSize(new Dimension(123, 23));
		btnLimpiar.setMaximumSize(new Dimension(123, 23));
		btnLimpiar.setPreferredSize(new Dimension(123, 23));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel cartel = null;
				String data = choice.getItem(choice.getSelectedIndex());
				switch (data) {
				case "Sala 1":
					cartel = Panel_1;
					break;
				case "Sala 2":
					cartel = Panel_2;
					break;
				case "Sala 3":
					cartel = Panel_3;
					break;
				case "Sala 4":
					cartel = Panel_4;
					break;
				}
				ImagenesMain.limpiar(cartel);
			}
		});
		btnLimpiar.setBounds(179, 335, 159, 45);
		add(btnLimpiar);
		
		JButton Copy = new JButton("A\u00F1adir car\u00E1tula");
		Copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImagenesMain.copiar();
			}
		});
		Copy.setMinimumSize(new Dimension(123, 23));
		Copy.setBounds(681, 335, 159, 45);
		add(Copy);
		

	}
}
