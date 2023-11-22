package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionListener implements ActionListener {

    private JButton newGameButton;
    private GuiClass player;
    private JFrame startFrame;

    public GameActionListener(JButton newGameButton, GuiClass player, JFrame startFrame) {
        this.newGameButton = newGameButton;
        this.player = player;
        this.startFrame = startFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) {
            player.getGameMenu();
            startFrame.dispose();
        }
    }
}
