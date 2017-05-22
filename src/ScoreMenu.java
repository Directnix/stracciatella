import Score.ScoreLog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nick van Endhoven, 2119719 on 5/16/2017.
 */
public class ScoreMenu extends JPanel {

    JPanel plScore = new JPanel(null);

    ScoreMenu(){
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

        JScrollPane spScore = new JScrollPane(plScore);
        spScore.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        spScore.setBounds(480-320,300,640,300);

        JButton btnBack = new MenuButton("Terug");
        btnBack.setBounds(960- 360, 650, 200, 50);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) BeginMenu.cards.getLayout();
                cardLayout.show(BeginMenu.cards, "BeginCard");
            }
        });

        add(btnBack);
        add(spScore);
        updateLog();
    }

    public void updateLog(){
        ArrayList<ScoreLog> logs = ScoreManager.getInstance().logs;

        plScore.removeAll();
        plScore.setLayout(new GridLayout(logs.size(),1));

        for(int i = 0; i < logs.size(); i++) {
            boolean c = false;
            if(i % 2 == 0)
                c = true;

            plScore.add(new ScorePane(logs.get(i),c));
        }

        revalidate();
        repaint();
    }

    class ScorePane extends JPanel{
        ScorePane(ScoreLog scoreLog, boolean c){
            setLayout(null);

            //TODO: date format
            //TODO: fix height

            JLabel lblScore = new JLabel(scoreLog.getOpponentUserName());
            JLabel lblInfo = new JLabel(scoreLog.getPlayerScore() + " - " + scoreLog.getOpponentScore());

            lblScore.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            lblScore.setBounds(10, 5, 500, 30);

            lblInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            lblInfo.setBounds(520, 5, 500, 30);

            if(c) {
                lblInfo.setForeground(new Color(95, 127, 243));
                lblScore.setForeground(new Color(95, 127, 243));
                setBackground(Color.white);
            } else {
                lblInfo.setForeground(Color.white);
                lblScore.setForeground(Color.white);
                setBackground(new Color(95, 127, 243));
            }

            add(lblScore);
            add(lblInfo);
        }
    }
}
