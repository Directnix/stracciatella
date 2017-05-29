import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Created by Nick van Endhoven, 2119719 on 5/29/2017.
 */
public class Puk extends GameObject {

    double size = 55;

    Puk(Point2D location) {
        super(location);
    }

    @Override
    void update() {

    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setPaint(new Color(90,90,90));
        g2d.fill(new Ellipse2D.Double(location.getX() - (size/2), location.getY() - (size/2), size, size));
    }
}
