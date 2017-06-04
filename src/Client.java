import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Nick van Endhoven en Lois Gussenhoven on 5/22/2017.
 */
public class Client extends GameStream {

    int port = 8000;
    Socket socket;

    Client(String ip) throws Exception{
        super();
        try {
            socket = new Socket(ip, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
