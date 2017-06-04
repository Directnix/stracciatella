import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Created by Nick van Endhoven en Lois Gussenhoven on 5/22/2017.
 */
public class Opponent extends GameObject {

    double size = 70;
    int score = 0;

    Opponent(Point2D location, Game game) {
        super(location, game);
    }

    @Override
    void update() {
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setPaint(Color.red);
        g2d.fill(new Ellipse2D.Double(location.getX() - (size/2), location.getY() - (size/2), size, size));
    }
}