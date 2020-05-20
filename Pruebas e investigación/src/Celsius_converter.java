import javax.swing.*;

public class Celsius_converter extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Celsius_converter(){
        initComponents();
    }

    private void initComponents(){
        tempTextField = new javax.swing.JTextField();
        celsiusLabel = new javax.swing.JLabel();
        convertButton = new javax.swing.JButton();
        fahrenheitLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Celsius Converter");

        celsiusLabel.setText("Celsius");
        convertButton.setText("Convertir");
        convertButton.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e) {
                convertir(e);
            }
        });

        fahrenheitLabel.setText("Fahrenheit");

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.linkSize(convertButton, tempTextField);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(tempTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(celsiusLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(convertButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(fahrenheitLabel)))
                                .addContainerGap(27, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tempTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(celsiusLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(convertButton)
                                        .addComponent(fahrenheitLabel))
                                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }
    private void convertir(java.awt.event.ActionEvent e) {
        //Parse degrees Celsius as a double and convert to Fahrenheit
        int tempFahr = (int)((Double.parseDouble(tempTextField.getText()))
                * 1.8 + 32);
        fahrenheitLabel.setText(tempFahr + " Fahrenheit");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Celsius_converter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel celsiusLabel;
    private javax.swing.JButton convertButton;
    private javax.swing.JLabel fahrenheitLabel;
    private javax.swing.JTextField tempTextField;
// End of variables declaration//GEN-END:variables
}