import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Nick van Endhoven, 2119719 on 5/22/2017.
 */
public class Game extends JPanel implements ActionListener {

    GameStream stream;
    ArrayList<GameObject> objects = new ArrayList<>();

    Player player;
    Opponent opponent;
    Puk puk;

    String score = "0 - 0";

    int y, oppY, type;

    Game(GameStream stream, int type) {
        this.type = type;
        if(type == GameFrame.TYPE_SERVER){
            oppY = 87;
            y = 662;
        } else {
            oppY = 662;
            y = 87;
        }

        setBackground(Color.white);
        this.stream = stream;

        player = new Player(new Point2D.Double(310, y));
        objects.add(player);

        opponent = new Opponent(new Point2D.Double(310, oppY));
        objects.add(opponent);

        puk = new Puk(new Point2D.Double(310,375),player,opponent, type);
        objects.add(puk);

        setFocusable(true);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                player.location = new Point2D.Double(e.getX(), y);

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
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

        if(type == GameFrame.TYPE_SERVER)
            g2d.drawString(player.score + " - " + opponent.score, 100,100);
        else
            g2d.drawString(score,100,100);

        for (GameObject o : objects)
            o.draw(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (GameObject o : objects)
            o.update();
        repaint();
    }
}
