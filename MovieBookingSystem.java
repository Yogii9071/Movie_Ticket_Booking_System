import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class LoginPage extends JFrame implements ActionListener{
    JPasswordField t1;
    JTextField t2,t3;
    JButton b1,b2;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs;
    String url = "jdbc:mysql://localhost:3306/booking";
    String userName = "root";
    String password = "907140";
    LoginPage(String s)
    {
    super(s);
    Container con=getContentPane();
    con.setLayout(new BorderLayout());
    t1 = new JPasswordField(10);
    t2 = new JTextField(10);
    t3 = new JTextField(10);
    b1= new JButton(" Login ");
    b2= new JButton(" SignUp");
    JPanel p1,p2,p3,p4;
    p1=new JPanel();
    p2=new JPanel();
    p3=new JPanel();
    p4=new JPanel();

    p1.add(new JLabel("User Name "));
    p1.add(t2);
    con.add(p1,BorderLayout.WEST);

    p2.add(new JLabel("Password "));
    p2.add(t1);
    con.add(p2,BorderLayout.CENTER);

    p4.add(b1);
    con.add(p4,BorderLayout.EAST);

    p3.add(new JLabel("New-User?/SignUp "));
    p3.add(b2);
    con.add(p3,BorderLayout.SOUTH);
    b1.addActionListener(this);
    b2.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            char[] a = t1.getPassword();
            String s1 = new String(a);
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, userName, password);
                stmt = conn.createStatement();
                String uname = t2.getText();
                String password = s1;

                String ss = "SELECT * FROM cred WHERE uname='" + uname + "' AND password='" + password + "'";
                rs = stmt.executeQuery(ss);
                
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful");
                    // this.dispose();
                    MoviesOnShow ms = new MoviesOnShow("Book Your Shows",uname);
                    ms.setVisible(true);
                    ms.setSize(1200,800);
                    ms.setResizable(false);
                    ms.setLocationRelativeTo(this);

                } else {
                    JOptionPane.showMessageDialog(this, "USER NOT REGISTERED OR INVALID USERNAME OR PASSWORD", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == b2) {
            SignUpFrame signUpFrame = new SignUpFrame("Your-Show SignUp Page");
            signUpFrame.setVisible(true);
            signUpFrame.setLocationRelativeTo(b2);
            signUpFrame.setResizable(false);
        }
    }

}   
class MovieBookingSystem {
    public static void main(String[] args) {
        LoginPage log = new LoginPage("Your Show Login Page");
        log.setVisible(true);
        log.setSize(500,200);
        log.setLocation(700, 300);
        log.setResizable(false);
        log.getContentPane().setBackground(new Color(160, 32, 240));
    }
}
