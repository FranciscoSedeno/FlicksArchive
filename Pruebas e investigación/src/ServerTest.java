import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/*
 * Created by JFormDesigner on Tue Mar 03 17:58:25 CET 2020
 */



/**
 * @author Juan Marcos
 */
public class ServerTest {
    public ServerTest() {
        initComponents();
    }


    private void enterPressed(ActionEvent e) {

        label4.setText(ServerMain.conectar(textField2));

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Juan Marcos
        PanelPrimc = new JPanel();
        textField2 = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        label1 = new JLabel();

        //======== PanelPrimc ========
        {
            PanelPrimc.setName("PanelPrinc");
            PanelPrimc.setPreferredSize(new Dimension(600, 170));
            PanelPrimc.setMinimumSize(new Dimension(600, 170));
            PanelPrimc.setMaximumSize(new Dimension(600, 170));
            PanelPrimc.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
            0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
            . BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
            red) ,PanelPrimc. getBorder( )) ); PanelPrimc. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
            beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

            //---- textField2 ----
            textField2.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            textField2.addActionListener(e -> enterPressed(e));

            //---- label3 ----
            label3.setText("Introduzca el c\u00f3digo a buscar:");

            //---- label4 ----
            label4.setText("Resultado:");
            label4.setHorizontalTextPosition(SwingConstants.CENTER);
            label4.setHorizontalAlignment(SwingConstants.LEFT);

            GroupLayout PanelPrimcLayout = new GroupLayout(PanelPrimc);
            PanelPrimc.setLayout(PanelPrimcLayout);
            PanelPrimcLayout.setHorizontalGroup(
                PanelPrimcLayout.createParallelGroup()
                    .addGroup(PanelPrimcLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(PanelPrimcLayout.createParallelGroup()
                            .addGroup(PanelPrimcLayout.createSequentialGroup()
                                .addComponent(label3)
                                .addGap(6, 6, 6)
                                .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelPrimcLayout.createSequentialGroup()
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            PanelPrimcLayout.setVerticalGroup(
                PanelPrimcLayout.createParallelGroup()
                    .addGroup(PanelPrimcLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(PanelPrimcLayout.createParallelGroup()
                            .addGroup(PanelPrimcLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(label3))
                            .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(PanelPrimcLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(label4))
                        .addContainerGap(97, Short.MAX_VALUE))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }



    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Juan Marcos
    private JPanel PanelPrimc;
    private JTextField textField2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JPanel ret(ServerTest s) {
        return s.PanelPrimc;
    }
}
