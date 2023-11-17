import javax.swing.*;
import java.awt.*;

public class GuiKlass extends JFrame {

    String name;
    JLabel namePromt = new JLabel("Ange ditt användarnamn");
    JTextArea userName = new JTextArea();
    JButton enterNameButton = new JButton("Klart");
    JButton startButton = new JButton("Starta nytt spel");
    JPanel panelName = new JPanel();




    JPanel panelGameMenu = new JPanel();
    JButton randomPlayerButton = new JButton("Slumpad spelare");
    JButton playAgainstAFriendButton = new JButton("Spela mot en vän");


    public GuiKlass(){

       getNameWindow();
       // getGameMenu();


    }

    public void getNameWindow(){
        this.add(panelName);
        panelName.setLayout(new GridLayout(3,1));

        panelName.add(namePromt);
        panelName.add(userName);
        panelName.add(enterNameButton);
        enterNameButton.addActionListener(new GameActionListener());

        setVisible(true);
        setSize(200, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void getGameMenu(){
        this.add(panelGameMenu);
        panelGameMenu.setLayout(new GridLayout(1,2));
        add(randomPlayerButton);
        randomPlayerButton.setPreferredSize(new Dimension(150, 100));
        add(playAgainstAFriendButton);
        playAgainstAFriendButton.setPreferredSize(new Dimension(150, 100));

        setVisible(true);

        setSize(100, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void getCategories(){


    }
}
