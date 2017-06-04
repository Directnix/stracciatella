import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Created by Nick van Endhoven en Lois Gussenhoven on 5/29/2017.
 */
public class Puk extends GameObject {

    double currentSpeed = 3;
    double speedX = currentSpeed;
    double speedY = currentSpeed;
    double size = 55;

    Player player;
    Opponent opponent;

    int type;

    Puk(Point2D location, Player player, Opponent opponent, int type, Game game) {
        super(location, game);
        this.player = player;
        this.opponent = opponent;
        this.type = type;
    }

    @Override
    void update() {
        if(type == GameFrame.TYPE_SERVER && !game.pause) {
            location = new Point2D.Double(location.getX() + speedX, location.getY() + speedY);

            if (location.getX() - (size / 2) < 0) {
                speedX = -speedX;
                location = new Point2D.Double((size / 2), location.getY());
            } else if (location.getX() + (size / 2) > GameFrame.P_WIDTH) {
                speedX = -speedX;
                location = new Point2D.Double(GameFrame.P_WIDTH - (size / 2), location.getY());
            }

            if (location.getY() - (size / 2) < 0 || location.getY() + (size / 2) > GameFrame.P_HEIGHT) {
                if(location.getY() - (size / 2) < 0){
                    player.score++;
                }else{
                    opponent.score++;
                }

                location = new Point2D.Double(310, 374);
                currentSpeed = 3;
                speedX = currentSpeed;
                speedY = currentSpeed;
            }

            if (location.distance(player.location) < (size / 2) + (player.size / 2)) {

                if (location.getX() >= player.location.getX())
                    speedX = Math.random() * currentSpeed;
                if (location.getX() < player.location.getX())
                    speedX = Math.random() * -currentSpeed;

                if(speedY < 0)
                    speedY = currentSpeed;
                else
                    speedY = -currentSpeed;

            }

            if (location.distance(opponent.location) < (size / 2) + (opponent.size / 2)) {
                if (location.getX() >= opponent.location.getX())
                    speedX = Math.random() * currentSpeed;
                if (location.getX() < opponent.location.getX())
                    speedX = Math.random() * -currentSpeed;

                if(speedY < 0)
                    speedY = currentSpeed;
                else
                    speedY = -currentSpeed;
            }

            currentSpeed += 0.001;
        }
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setPaint(new Color(90,90,90));
        g2d.fill(new Ellipse2D.Double(location.getX() - (size/2), location.getY() - (size/2), size, size));
    }
}
