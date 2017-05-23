import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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

    Game(GameStream stream){
        setBackground(Color.white);
        this.stream = stream;

        player = new Player(new Point2D.Double(100,100), stream);
        objects.add(player);

        opponent = new Opponent(new Point2D.Double(100,100), stream);
        objects.add(opponent);

        setFocusable(true);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                player.prevLocation = player.location;
                player.location = new Point2D.Double(e.getX(),e.getY());

            }
        });

        new Timer(15, this).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for(GameObject o : objects)
            o.draw(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(GameObject o : objects)
            o.update();
        repaint();
    }
}
