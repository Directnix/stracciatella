import Score.ScoreLog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Nick van Endhoven, 2119719 on 5/15/2017.
 */
public class BeginMenu extends JPanel{

    static JPanel cards = new JPanel(new CardLayout());

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

        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, "BeginCard");

        ScoreManager.getInstance().addLog(new ScoreLog(new Date(),"LunaLois", 4, 10));
        ScoreManager.getInstance().addLog(new ScoreLog(new Date(),"Lolnen10", 10, 3));
        ScoreManager.getInstance().addLog(new ScoreLog(new Date(),"Rickeitje", 10, 6));
        ScoreManager.getInstance().addLog(new ScoreLog(new Date(),"ZaRooler", 7, 10));
        ScoreManager.getInstance().addLog(new ScoreLog(new Date(),"LunaLois", 6, 10));
        ScoreManager.getInstance().addLog(new ScoreLog(new Date(),"Lolnen10", 5, 10));
        ScoreManager.getInstance().addLog(new ScoreLog(new Date(),"Rickeitje", 10, 6));
        ScoreManager.getInstance().addLog(new ScoreLog(new Date(),"ZaRooler", 10, 9));
        ScoreManager.getInstance().addLog(new ScoreLog(new Date(),"LunaLois", 6, 10));
        ScoreManager.getInstance().addLog(new ScoreLog(new Date(),"Lolnen10", 5, 10));
        ScoreManager.getInstance().addLog(new ScoreLog(new Date(),"Rickeitje", 10, 6));
        ScoreManager.getInstance().addLog(new ScoreLog(new Date(),"ZaRooler", 10, 9));

        cards.add(new BeginMenu(), "BeginCard");
        cards.add(new PlayMenu(), "PlayCard");
        cards.add(new ScoreMenu(), "ScoreCard");

        JFrame frame = new JFrame("Stracciatella");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(960,800);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(cards);
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

        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, "PlayCard");
            }
        });

        JButton btnScore = new MenuButton("Score bekijken");
        btnScore.setBounds(480-320,450,640,50);

        btnScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, "ScoreCard");
            }
        });


        add(btnPlay);
        add(btnScore);
    }
}