import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Nick van Endhoven en Lois Gussenhoven on 5/16/2017.
 */
public class PlayMenu extends JPanel {

    JTextField tfIp;
    MenuButton btnJoin;
    PlayMenu self = this;
    JButton btnMake;

    PlayMenu() {
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel lbTitle = new JLabel("Airhockey - Stracciatella");
        lbTitle.setBounds(480 - 320, -50, 640, 400);

        try {
            BufferedImage biTitle = ImageIO.read(getClass().getResource("title.png"));
            lbTitle.setText("");
            lbTitle.setIcon(new ImageIcon(biTitle));
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(lbTitle);

        btnMake = new MenuButton("Spel maken");
        btnMake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(self, "Wat is jouw naam?");

                GameStream gs = new Server();
                new GameFrame(gs, name, GameFrame.TYPE_SERVER);
                BeginMenu.frame.dispose();
            }
        });
        btnMake.setBounds(480 - 320, 350, 640, 50);

        JButton btnConnect = new MenuButton("Verbinden via IP");
        btnConnect.setBounds(480 - 320, 450, 640, 50);
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnConnect.setEnabled(false);
                tfIp = new JTextField("localhost");
                tfIp.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        tfIp.setText("");
                    }
                });
                tfIp.setBounds(480 - 320, 500, 540, 40);
                add(tfIp);

                btnJoin = new MenuButton("GO");
                btnJoin.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String name = JOptionPane.showInputDialog(self, "Wat is jouw naam?");

                            new GameFrame(new Client(tfIp.getText()), name, GameFrame.TYPE_CLIENT);
                            BeginMenu.frame.dispose();
                        }catch (Exception ex){
                            tfIp.setText("Can't connect to server");
                        }
                    }
                });
                btnJoin.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
                btnJoin.setBounds(480 - 320 + 540, 500, 100, 40);
                add(btnJoin);

                revalidate();
                repaint();
            }
        });

        JButton btnBack = new MenuButton("Terug");
        btnBack.setBounds(960- 360, 650, 200, 50);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) BeginMenu.cards.getLayout();
                cardLayout.show(BeginMenu.cards, "BeginCard");
            }
        });

        add(btnBack);
        add(btnMake);
        add(btnConnect);

    }

}
