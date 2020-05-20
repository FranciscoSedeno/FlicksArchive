import javax.swing.*;
import java.sql.*;

public class ServerMain {
    

    public static String conectar(JTextField txt){
        String res = "Resultado: ";
        String texto = txt.getText();
        if (tryParse(txt)) {
            try {
                // create our mysql database connection
                String host = "localhost";
                String port = "3306";
                String db = "tests";
                String user = "Apps";
                String pass = "Aplicaciones";
                String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user="
                        + user + "&password=" + pass + "&useSSL=false";
                Connection con = DriverManager.getConnection(url);

                DriverManager.getConnection(url);


                // our SQL SELECT query.
                // if you only need a few columns, specify them by name instead of using "*"
                String query = "SELECT Nombre, Edad, Estado FROM personas WHERE ID = " + texto;

                // create the java statement
                Statement st = con.createStatement();

                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery(query);

                // iterate through the java resultset
                while (rs.next()) {
                    String firstName = rs.getString("Nombre");
                    Integer age = rs.getInt("Edad");
                    Boolean alive = rs.getBoolean("Estado");

                   res +=  firstName + " " + Integer.toString(age) + " " + Boolean.toString(alive);
                }
                st.close();
            } catch (Exception x) {
                System.err.println("Got an exception! ");
                System.err.println(x.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Solo números por favor.");
        }
        return res;
    }
    private static boolean tryParse(JTextField textField1) {
        try {
            Double.parseDouble(textField1.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args)
{
    ServerTest server = new ServerTest();
    JFrame frame = new JFrame("Server");
    JPanel content = new JPanel();
    content.add(new ServerTest().ret(server));
    frame.getContentPane().add(content);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(true);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    }
}



