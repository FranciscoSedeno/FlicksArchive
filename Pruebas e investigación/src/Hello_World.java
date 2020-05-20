import javax.swing.*;

public class Hello_World {
	public static void guitest() {
		//Create the window
		JFrame frame = new JFrame("Test1.java");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label = new JLabel("Hello World");
		frame.getContentPane().add(label);

		//Set up the window
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				guitest();
			}
		});
	}
}
