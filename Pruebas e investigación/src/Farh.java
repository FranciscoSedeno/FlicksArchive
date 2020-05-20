import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Farh extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField1;
    private JButton convertirButton;
    private JTextPane fahrenheitTextPane;
    private JPanel PanelPrinc;

    public Container createpanel(){
        return PanelPrinc;
    }

    public Farh() {
        convertirButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                convertir(e);
            }
        });
    }

    private void convertir(java.awt.event.ActionEvent e) {
        //Parse degrees Celsius as a double and convert to Fahrenheit


        if(tryParse(textField1)) {
            int tempFahr = (int) ((Double.parseDouble(textField1.getText()))
                * 1.8 + 32);
            fahrenheitTextPane.setText(tempFahr + " Fahrenheit");
        }else{
            JOptionPane.showMessageDialog(null, "Solo números por favor.");
        }


    }
    private boolean tryParse(JTextField textField1) {
        try {
            Double.parseDouble(textField1.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
}


}
