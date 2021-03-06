import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.*;

/**
 * Created by Nick van Endhoven en Lois Gussenhoven on 5/22/2017.
 */
public class GameFrame extends JFrame {

    final int MARGIN = 5;
    final int WIDTH = 320 - (MARGIN * 2);

    static int P_WIDTH = 960;
    static int P_HEIGHT = 800;

    static int TYPE_CLIENT = 0;
    static int TYPE_SERVER = 1;
    int type;

    Queue<String> messages = new LinkedList<>();

    GameStream gameStream;

    TextArea taChat;
    JTextField tfChat;

    String opponentUserName;

    Game game;

    GameFrame(GameStream gameStream, String username, int type) {
        this.gameStream = gameStream;
        this.type = type;
        try {
            gameStream.out.writeUTF("USER!!" + String.valueOf(username));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(P_WIDTH, P_HEIGHT);
        setLocationRelativeTo(null);

        JPanel contentpane = new JPanel(null);

        JPanel pnNameWrap = new JPanel();
        pnNameWrap.setBounds(MARGIN, MARGIN, WIDTH, 40);
        pnNameWrap.setBackground(Color.white);

        opponentUserName = getOpponentUserName();

        JLabel lblName = new JLabel(opponentUserName);
        lblName.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        pnNameWrap.add(lblName);

        contentpane.add(pnNameWrap);

        taChat = new TextArea();
        taChat.setEditable(false);
        taChat.setBounds(MARGIN, 45 + MARGIN, WIDTH, 800 - 140);

        contentpane.add(taChat);

        tfChat = new JTextField();
        tfChat.setFocusable(true);
        tfChat.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!tfChat.getText().isEmpty()) {
                        putOutString(username + ": " + tfChat.getText());
                        messages.add(String.valueOf(tfChat.getText()));
                        tfChat.setText("");
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_F1){
                    if(type == GameFrame.TYPE_SERVER && !game.gameEnd) {
                        game.pause = !game.pause;
                    }
                }
            }
        });
        tfChat.setBounds(MARGIN, 50 + MARGIN + (800 - 140), WIDTH, 40);

        contentpane.add(tfChat);

        game = new Game(gameStream, type, username, opponentUserName);
        game.setBounds(320 + MARGIN, MARGIN, 630 - (MARGIN * 2), 760 - (MARGIN * 2));
        P_WIDTH = game.getWidth();
        P_HEIGHT = game.getHeight();
        contentpane.add(game);

        setContentPane(contentpane);
        setResizable(false);
        setVisible(true);

        new Thread(new Read()).start();
        new Thread(new Write()).start();

    }

    void putOutString(String s) {
        taChat.append(s + "\n");
    }

    String getOpponentUserName() {
        try {
            String input = String.valueOf(gameStream.in.readUTF());
            if (input.split("!!")[0].equals("USER"))
                return input.split("!!")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    class Read implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    String input = String.valueOf(gameStream.in.readUTF());


                    String[] values = input.split("!!");
                    if (values[0].equals("STREAM")) {
                        game.opponent.location = new Point2D.Double(
                                Double.parseDouble(values[1]),
                                Double.parseDouble(values[2])
                        );

                        if(type == GameFrame.TYPE_CLIENT){
                            game.puk.location = new Point2D.Double(
                                    Double.parseDouble(values[3]),
                                    Double.parseDouble(values[4])
                            );
                            game.opponent.score = Integer.parseInt(values[5].split("-")[0].trim());
                            game.player.score = Integer.parseInt(values[5].split("-")[1].trim());
                            game.pause = Boolean.parseBoolean(values[6]);
                        }

                        if (values.length > 7) {
                            putOutString(opponentUserName + ": " + values[7]);
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Write implements Runnable {

        @Override
        public void run() {

            new Timer(5, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String write = "STREAM!!";

                        write += String.valueOf(game.player.location.getX()) + "!!";
                        write += String.valueOf(game.player.location.getY()) + "!!";

                        write += String.valueOf(game.puk.location.getX()) + "!!";
                        write += String.valueOf(game.puk.location.getY()) + "!!";
                        write += String.valueOf(game.player.score + " - " + game.opponent.score) + "!!";
                        write += game.pause;

                        if (!messages.isEmpty()) {
                            String message = messages.poll();
                            write += "!!" + message;
                        }

                        gameStream.out.writeUTF(write);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
