import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class MoviesOnShow extends JFrame {
    private Image backgroundImage;
    private ImageIcon foregroundImage1;
    JButton b1;
    JLabel l1,l2;
    JPanel imagePanel, p1,p2;
    Container con;

    MoviesOnShow(String S,String uname) {
        super(S);
        String name = uname;
        try {
            backgroundImage = ImageIO.read(new File("YourShow.png"));
            Image img = ImageIO.read(new File("Kalki298.jpg"));
            Image scaledImage = img.getScaledInstance(200, 250, Image.SCALE_SMOOTH); // Resize the image
            foregroundImage1 = new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        setContentPane(imagePanel);

        con = getContentPane();
        b1 = new JButton("Book");
        p1 = new JPanel();
        l1 = new JLabel(foregroundImage1);
        p1.setOpaque(false); // Make the panel transparent
        p1.setBounds(100, 100, 200, 250); // Set the position and size of the panel

        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);

        p1.add(l1);
        p1.add(Box.createVerticalStrut(10)); // Add some space between the image and the button
        p1.add(b1);
        imagePanel.add(p1); // Add p1 to imagePanel

        setLayout(new BorderLayout());
        con.add(p1, BorderLayout.EAST);

        p2 = new JPanel();
        l2 = new JLabel("<html>Welcome to YourShow<br>" + name + "</html>");
        l2.setFont(new Font("Cooper Black", Font.BOLD, 50));
        p2.add(l2);
        p2.setBounds(0,0,200,50);
        p2.setOpaque(false);
        con.add(p2,BorderLayout.WEST);



        b1.addActionListener(e ->{
            if(e.getSource()==b1){
                MovieDescription desc = new MovieDescription("Movie Description",name);
                // this.dispose();
                desc.setVisible(true);
                desc.con.setBackground(new Color(255, 0, 0));
                desc.setResizable(false);
                desc.setLocationRelativeTo(b1);
            }

        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

