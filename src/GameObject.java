import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Nick van Endhoven, 2119719 on 5/22/2017.
 */
public abstract class GameObject {

    Point2D location;
    Point2D prevLocation;
    GameStream stream;

    GameObject(Point2D location, GameStream stream){
        this.location = location;
        this.prevLocation = null;
        this.stream = stream;
    }

    abstract void update();
    abstract void draw(Graphics2D g2d);
}