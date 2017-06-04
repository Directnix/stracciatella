import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Nick van Endhoven en Lois Gussenhoven on 5/22/2017.
 */
public class Server extends GameStream {

    int port = 8000;
    ServerSocket server;

    Server() {
        super();
        try {
            server = new ServerSocket(port);
            Socket socket = server.accept();

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
