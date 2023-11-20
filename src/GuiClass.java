import javax.swing.*;
import java.awt.*;

public class GuiClass extends JFrame {

    String userName;


    private JFrame startFrame = new JFrame("Quizkampen - " + userName);
    private JPanel startPanel = new JPanel(new BorderLayout());
    private JPanel startButtonPanel = new JPanel(new GridLayout(1, 1));
    private JButton newGameButton = new JButton("Starta nytt spel");


    private JFrame gameMenuFrame = new JFrame("Spelmeny - " + userName);
    private JPanel gameMenuPanel = new JPanel(new BorderLayout());
    private JPanel gameMenuButtonPanel = new JPanel(new GridLayout(1, 2));
    private JButton randomPlayerButton = new JButton("Slumpa spelare");
    private JButton playAgainstAFriendButton = new JButton("Spela mot en vän");


    private JFrame categoriesFrame = new JFrame("Kategorier - " + userName);
    private JPanel categoriesPanel = new JPanel(new BorderLayout());
    private JPanel categoriesButtonPanel = new JPanel(new GridLayout(3, 1));
    private JLabel categoriesLabel = new JLabel("Kategorier", SwingConstants.CENTER);
    private JButton categoryButton1 = new JButton("Kategori 1");
    private JButton categoryButton2 = new JButton("Kategori 2");
    private JButton categoryButton3 = new JButton("Kategori 3");


    private void getUserName() {
        userName = JOptionPane.showInputDialog(null, "Ange ditt användarnamn: ");
        if (userName == null || userName.trim().isEmpty()) {
            userName = "Okänd Användare";
        }
    }

    public void getGameMenu() {
        gameMenuFrame.add(gameMenuPanel);
        gameMenuPanel.add(gameMenuButtonPanel, BorderLayout.SOUTH);
        gameMenuButtonPanel.add(randomPlayerButton);
        gameMenuButtonPanel.add(playAgainstAFriendButton);

        gameMenuFrame.setLocationRelativeTo(null);
        gameMenuFrame.setSize(300, 500);
        gameMenuFrame.setVisible(true);
        gameMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void getStartWindow(){
        startFrame.add(startPanel);
        startPanel.add(startButtonPanel, BorderLayout.SOUTH);
        startButtonPanel.add(newGameButton);

        startFrame.setLocationRelativeTo(null);
        startFrame.setSize(300, 500);
        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void getCategories(){
        categoriesFrame.add(categoriesPanel);
        categoriesPanel.add(categoriesLabel, BorderLayout.NORTH);
        categoriesPanel.add(categoriesButtonPanel, BorderLayout.SOUTH);

        categoriesButtonPanel.setLayout(new BoxLayout(categoriesButtonPanel, BoxLayout.Y_AXIS));

        categoriesButtonPanel.add(categoryButton1);
        categoryButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        categoriesButtonPanel.add(categoryButton2);
        categoryButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        categoriesButtonPanel.add(categoryButton3);
        categoryButton3.setAlignmentX(Component.CENTER_ALIGNMENT);

        categoriesFrame.setSize(300, 500);
        categoriesFrame.setLocationRelativeTo(null);
        categoriesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        categoriesFrame.setVisible(true);
    }

    public GuiClass(){
        getUserName();
        getStartWindow();
        getGameMenu();
        getCategories();
    }
}
