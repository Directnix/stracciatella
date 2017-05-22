import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Nick van Endhoven, 2119719 on 5/22/2017.
 */
public abstract class GameStream {
    DataInputStream in;
    DataOutputStream out;
}
