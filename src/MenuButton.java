import javax.swing.*;
import java.awt.*;

/**
 * Created by Nick van Endhoven en Lois Gussenhoven on 5/16/2017.
 */
public class MenuButton extends JButton{
    MenuButton(String s) {
        super(s);
        Font ftButton = new Font("Comic Sans MS", Font.BOLD, 26);

        setFont(ftButton);
        setForeground(new Color(95, 127, 243));
    }
}
