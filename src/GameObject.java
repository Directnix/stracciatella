import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Nick van Endhoven, 2119719 on 5/22/2017.
 */
public abstract class GameObject {

    Point2D location;
    Game game;

    GameObject(Point2D location, Game game){
        this.location = location;
        this.game = game;
    }

    abstract void update();
    abstract void draw(Graphics2D g2d);
}
