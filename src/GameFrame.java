import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by Nick van Endhoven, 2119719 on 5/22/2017.
 */
public class GameFrame extends JFrame {

    final int MARGIN = 5;
    final int WIDTH = 320 - (MARGIN * 2);

    GameStream gameStream;

    TextArea taChat;
    JTextField tfChat;

    String opponentUserName;

    GameFrame(GameStream gameStream, String username) {
        this.gameStream = gameStream;
        try {
            gameStream.out.writeUTF("USER!!" + String.valueOf(username));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(960, 800);
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
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    putOutString(username + ": " + tfChat.getText());
                    try {
                        gameStream.out.writeUTF("CHAT!!" + String.valueOf(tfChat.getText()));
                        tfChat.setText("");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });
        tfChat.setBounds(MARGIN, 50 + MARGIN + (800 - 140), WIDTH, 40);

        contentpane.add(tfChat);

        setContentPane(contentpane);
        setResizable(false);
        setVisible(true);

        new Thread(new MessageHandler()).start();

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

    class MessageHandler implements Runnable {

        @Override
        public void run() {
            while (true) {

                try {
                    String input = String.valueOf(gameStream.in.readUTF());
                    if (input.split("!!")[0].equals("CHAT"))
                        putOutString(opponentUserName + ": " + input.split("!!")[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
