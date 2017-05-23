import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.IOException;

/**
 * Created by Nick van Endhoven, 2119719 on 5/22/2017.
 */
public class Player extends GameObject {

    Player(Point2D location, GameStream stream) {
        super(location, stream);
        new Thread(new PlayerHandler()).start();
    }

    @Override
    void update() {
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setPaint(Color.blue);
        g2d.fill(new Ellipse2D.Double(location.getX() - 30, location.getY() - 30, 60, 60));
    }

    synchronized String getStringLocation() {
        return String.valueOf(location.getX()) + "--" + String.valueOf(location.getY());
    }

    class PlayerHandler implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
//                    System.out.println(location.getX() + "-" + prevLocation.getX());
                   // if(prevLocation == null || prevLocation.getX() != location.getX() || location.getY() != prevLocation.getY()) {
                        stream.out.writeUTF("PLAYER_LOCATION!!" + getStringLocation());
                        stream.out.flush();
                        Thread.sleep(10);
                    //}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
