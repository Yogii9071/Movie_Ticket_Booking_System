import java.awt.*;
import java.io.IOException;
import java.io.File;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.util.Scanner;
import java.sql.*;

class MovieDescription extends JFrame {
    Scanner sc ;
    JLabel l0,l1,l2,l3,l4;
    JPanel p0,p1,p2,p3,p4;
    JTextField t0,t1,t2;
    JButton b1;
    JComboBox<String> visualizationDropdown,visualizationDropdown2;
    JTextPane textPane;
    ImageIcon imageIcon;
    Container con;

    MovieDescription(String title,String uname) {
        super(title);
        String nm = uname;
        sc = new Scanner(System.in);
        setSize(1100, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        con = getContentPane();

        // Load and resize image
        try {
            Image img = ImageIO.read(new File("Kalki298.jpg"));
            Image scaledImage = img.getScaledInstance(250, 300, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        con.setLayout(null);

        // Create JTextPane for description//
        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setOpaque(true);
        textPane.setContentType("text/html"); // Set content type to HTML for formatting

        // Add movie description text//
        String descriptionText = "<html><body><h1 style=\"font-family: Cooper Black, sans-serif; font-size: 40px;\">Description</h1>" +
            "<p style=\"font-family: 'Constantia', sans-serif; font-size: 15px;\">Kalki 2898 AD is a 2024 Indian Telugu-language epic science fiction film directed by Nag Ashwin " +
            "and produced by Vyjayanthi Movies. The film stars Amitabh Bachchan, Kamal Haasan, Prabhas, Deepika Padukone, and Disha Patani. " +
            "Inspired by Hindu scriptures, it is the first installment in a planned Kalki Cinematic Universe. Set in a post-apocalyptic " +
            "world in the year 2898 AD, the film follows a select group on a mission to save Kalki, the unborn child of SUM-80, a lab subject.</p>" +
            "</body></html>";
        textPane.setText(descriptionText);
        textPane.setBounds(280, 5, 800, 300);
        con.add(textPane);

        // Add JLabel with image
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(5, 5, 250, 300);
        con.add(imageLabel);

        //show-name//
        p0 = new JPanel();
        l0 = new JLabel("Show ");
        t0 = new JTextField(10);
        t0.setText("KALKI 2898 AD");
        t0.setEditable(false);
        p0.add(l0);
        p0.add(t0);
        p0.setBounds(10, 320, 180, 30);
        p0.setOpaque(true);
        con.add(p0);

        //name//
        p1 = new JPanel();
        l1 = new JLabel("Name ");
        t1 = new JTextField(10);
        t1.setText(nm);
        t1.setEditable(false);
        p1.add(l1);
        p1.add(t1);
        p1.setBounds(200, 320, 180, 30);
        p1.setOpaque(true);
        con.add(p1);
        
        //Visualization//
        p2 = new JPanel();
        l2 = new JLabel("Visualization: ");
        String[] visualizationOptions = { "2D", "3D" };
        visualizationDropdown = new JComboBox<>(visualizationOptions);
        p2.add(l2);
        p2.add(visualizationDropdown);
        p2.setBounds(390, 320, 180, 30);
        con.add(p2);

        //Theatre//
        p3 = new JPanel();
        l3 = new JLabel("Theatre ");
        String[] visualizationOptions2 = { "PVR", "IMAX", "INOX" };
        visualizationDropdown2 = new JComboBox<>(visualizationOptions2);
        p3.add(l3);
        p3.add(visualizationDropdown2);
        p3.setBounds(580, 320, 180, 30);
        con.add(p3);

        //Fare//
        p4 = new JPanel();
        l4 = new JLabel("Fare ");
        t2 = new JTextField(10);
        t2.setEditable(false);
        visualizationDropdown.addActionListener(e -> {
            if (visualizationDropdown.getSelectedItem().equals("2D")) {
                t2.setText("180");
            } else {
                t2.setText("250");
            }
        });
        
        if (visualizationDropdown.getSelectedItem().equals("2D")) {
            t2.setText("180");
        } else {
            t2.setText("250");
        }
        p4.add(l4);
        p4.add(t2);
        p4.setBounds(770, 320, 170, 30);
        p4.setOpaque(true);
        con.add(p4);

        b1 = new JButton("Confirm Booking");
        b1.setBounds(950, 320, 130, 30);
        con.add(b1);

        b1.addActionListener(e ->{
            if(e.getSource()==b1){
                String name = t1.getText();
                String movie_name =t0.getText();
                String visuals = (String) visualizationDropdown.getSelectedItem();
                String theatre = (String) visualizationDropdown2.getSelectedItem();
                String fare = t2.getText();
                String status = "Confirmed";
                int flag =0;
                do{
                String amount="";
                try{
                System.out.print("Pay amount to confirm booking "+fare+": ");
                int num = sc.nextInt();
                amount = String.valueOf(num);
                }catch(NumberFormatException ef){
                    ef.printStackTrace();
                }
                if(amount.matches(t2.getText())){
                    JOptionPane.showMessageDialog(this, "Ticket Booked Sucessfully");
            
                    String url = "jdbc:mysql://localhost:3306/booking";
                    String userName = "root";
                    String password = "907140";
                    try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection connection = DriverManager.getConnection(url, userName, password);

                String query = "INSERT INTO bookings VALUES(NULL,?, ?, ?, ?, ?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, movie_name);
                preparedStatement.setString(3, visuals);
                preparedStatement.setString(4, theatre);
                preparedStatement.setString(5, fare);
                preparedStatement.setString(6, status);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Data Entered", "Message", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Data Not Entered", "Message", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
            } catch (ClassNotFoundException | SQLException a) {
                a.printStackTrace();
            }
                flag =1;
                this.dispose();
                }else{
                    JOptionPane.showMessageDialog(this, "Entered amount is lesser or higher.Please enter payable amount only.");
                }
            } while(flag != 1);
                
            }
        });
        
    }
}

