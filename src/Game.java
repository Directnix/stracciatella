import Score.ScoreLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Nick van Endhovenen en Lois Gussenhoven on 5/22/2017.
 */
public class Game extends JPanel implements ActionListener {

    GameStream stream;
    ArrayList<GameObject> objects = new ArrayList<>();

    Player player;
    Opponent opponent;
    Puk puk;

    final int endscore = 10;

    int y, oppY, type;
    boolean pause = true;

    String username;
    String opponentUsername;

    boolean gameEnd = false;

    Game(GameStream stream, int type, String username, String opponentUsername) {
        this.type = type;
        this.username = username;
        this.opponentUsername = opponentUsername;

        if (type == GameFrame.TYPE_SERVER) {
            oppY = 87;
            y = 662;
        } else {
            oppY = 662;
            y = 87;
        }

        setBackground(Color.white);
        this.stream = stream;

        player = new Player(new Point2D.Double(310, y), this);
        objects.add(player);

        opponent = new Opponent(new Point2D.Double(310, oppY), this);
        objects.add(opponent);

        puk = new Puk(new Point2D.Double(310, 375), player, opponent, type, this);
        objects.add(puk);

        setFocusable(true);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (!pause)
                    player.location = new Point2D.Double(e.getX(), y);

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                if (!pause)
                    player.location = new Point2D.Double(e.getX(), y);

            }
        });

        new Timer(15, this).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.red);
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

        g2d.setStroke(new BasicStroke(10));

        g2d.drawLine(5, 0, 5, getHeight());
        g2d.drawLine(getWidth() - 5, 0, getWidth() - 5, getHeight());

        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.white);
        g2d.fill(new Ellipse2D.Double(getWidth() / 2 - 60, getHeight() / 2 - 60, 120, 120));
        g2d.setPaint(Color.red);
        g2d.draw(new Ellipse2D.Double(getWidth() / 2 - 60, getHeight() / 2 - 60, 120, 120));

        g2d.setFont(new Font("Courir New", Font.BOLD, 12));

        g2d.drawString(player.score + " - " + opponent.score, getWidth() - 100, 20);
        for (GameObject o : objects)

            o.draw(g2d);

        if (gameEnd) {
            Font f = new Font("Courir New", Font.BOLD, 24);
            g2d.setFont(f);

            FontMetrics metrics = g.getFontMetrics(f);

            String text;
            if (player.score >= endscore)
                text = "Gewonnen! Goed gedaan";
            else
                text = opponentUsername + " heeft gewonnen, loser..";

            int x =(getWidth() - metrics.stringWidth(text)) / 2;
            g2d.drawString(text, x, getHeight()/3);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (GameObject o : objects)
            o.update();

        if ((player.score >= endscore || opponent.score >= endscore) && !gameEnd) {
            gameEnd = true;

            ScoreManager.getInstance().addLog(new ScoreLog(new Date(), username, opponentUsername, player.score, opponent.score));
            ScoreManager.getInstance().save();

            pause = true;

            CardLayout cardLayout = (CardLayout) BeginMenu.cards.getLayout();
            cardLayout.show(BeginMenu.cards, "BeginCard");
        }

        repaint();
    }
}
