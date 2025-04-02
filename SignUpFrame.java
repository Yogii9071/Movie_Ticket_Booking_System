import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class SignUpFrame extends JFrame implements ActionListener {
    JLabel l1, l2;
    JTextField t1;
    JPasswordField t2;
    JButton b1;

    Connection conn = null;
    Statement stmt = null;
    String url = "jdbc:mysql://localhost:3306/booking";
    String userName = "root";
    String password = "907140";

    SignUpFrame(String s) {
        super(s);

        l1 = new JLabel("Username ");
        l2 = new JLabel("Set Password ");

        t1 = new JTextField(10);
        t2 = new JPasswordField(10);

        b1 = new JButton("SignUp");

        b1.addActionListener(this);

        setLayout(new FlowLayout());
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(b1);

        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String username = t1.getText();
            String password = new String(t2.getPassword());
            try {
                conn = DriverManager.getConnection(url, userName, this.password);
                stmt = conn.createStatement();
                String query = "INSERT INTO cred (uname, password) VALUES ('" + username + "', '" + password + "')";
                stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "Sign-Up successful!, Click OK to redirect on login page");
                this.dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

