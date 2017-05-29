import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.IOException;

/**
 * Created by Nick van Endhoven, 2119719 on 5/22/2017.
 */
public class Player extends GameObject {

    double size = 70;

    Player(Point2D location) {
        super(location);
    }

    @Override
    void update() {
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setPaint(Color.black);
        g2d.fill(new Ellipse2D.Double(location.getX() - (size/2), location.getY() - (size/2), size, size));
    }


}
