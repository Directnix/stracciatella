import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Nick van Endhoven, 2119719 on 5/15/2017.
 */
public class BeginMenu extends JPanel{

    public static void main(String[] args){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

        }

        JFrame frame = new JFrame("Stracciatella");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(960,800);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new BeginMenu());
        frame.setResizable(false);
        frame.setVisible(true);
    }

    BeginMenu(){
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel lbTitle = new JLabel("Airhockey - Stracciatella");
        lbTitle.setBounds(480-320,-50,640,400);

        try {
            BufferedImage biTitle = ImageIO.read(getClass().getResource("title.png"));
            lbTitle.setText("");
            lbTitle.setIcon(new ImageIcon(biTitle));
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(lbTitle);

        JButton btnPlay = new MenuButton("Spelen");
        btnPlay.setBounds(480-320,350,640,50);

        JButton btnScore = new MenuButton("Score bekijken");
        btnScore.setBounds(480-320,450,640,50);


        add(btnPlay);
        add(btnScore);
    }

    class MenuButton extends JButton{
        MenuButton(String s){
            super(s);
            Font ftButton = new Font("Comic Sans MS", Font.BOLD, 26);

            setFont(ftButton);
            setForeground(new Color(95,127,243));
        }
    }
}
