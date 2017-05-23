import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * Created by Nick van Endhoven, 2119719 on 5/22/2017.
 */
public class Opponent extends GameObject {
    Opponent(Point2D location, GameStream stream) {
        super(location, stream);

        new Thread(new OpponentHandler()).start();
    }

    @Override
    void update() {
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setPaint(Color.red);
        g2d.fill(new Ellipse2D.Double(location.getX() - 30, location.getY() - 30, 60, 60));
    }

    class OpponentHandler implements Runnable {
        @Override
        public void run() {

            while (true) {
                try {
                    String input = String.valueOf(stream.in.readUTF());

                    if (input.split("!!")[0].equals("PLAYER_LOCATION")) {
                        String loc = input.split("!!")[1];

                        System.out.println(loc);

//                        Double xLoc = Double.parseDouble(loc.split("--")[0]);
//                        Double yLoc = Double.parseDouble(loc.split("--")[1]);
//                        location = new Point2D.Double(xLoc, yLoc);
//
//                    Double xLoc = stream.in.readDouble();
//                    Double yLoc = stream.in.readDouble();
//
//                    location = new Point2D.Double(xLoc,yLoc);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}