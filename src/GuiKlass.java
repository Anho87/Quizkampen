import javax.swing.*;
import java.awt.*;

public class GuiKlass extends JFrame {

    String name;
    JLabel namePromt = new JLabel("Ange ditt användarnamn");
    JTextArea userName = new JTextArea();
    JButton enterNameButton = new JButton("Klart");
    JButton startButton = new JButton("Starta nytt spel");
    JPanel panel = new JPanel();
    public GuiKlass(){

        this.add(panel);
        panel.setLayout(new GridLayout(3,1));

        panel.add(namePromt);
        panel.add(userName);
        panel.add(enterNameButton);
        enterNameButton.addActionListener(new GameActionListener(enterNameButton));


        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setVisible(true);

    }
}
