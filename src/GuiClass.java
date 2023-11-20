import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


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


    private JFrame quizFrame = new JFrame("Quiz - " + userName);
    private JPanel quizPanel = new JPanel();
    JPanel questionAndResultPanel = new JPanel();
    private int questionNr;
    private String correctAnswer = "Terminator";
    private JLabel questionNumber = new JLabel("Fråga " + questionNr);
    private JLabel question = new JLabel("I vilken film yttras orden 'I'll be back'?");
    private JLabel result = new JLabel();
    private JPanel answerPanel = new JPanel();
    private JButton answer1 = new JButton("Terminator");
    private JButton answer2 = new JButton("När Harry mötte Sally");
    private JButton answer3 = new JButton("Hitta Nemo");
    private JButton answer4 = new JButton("Gladiator");


    private void getUserName() {
        userName = JOptionPane.showInputDialog(null, "Ange ditt användarnamn: ");
        if (userName == null || userName.trim().isEmpty()) {
            userName = "Okänd Användare";
        }
        updateFrameTitles();
    }
    private void updateFrameTitles() {
        startFrame.setTitle("Quizkampen - " + userName);
        gameMenuFrame.setTitle("Spelmeny - " + userName);
        categoriesFrame.setTitle("Kategorier - " + userName);
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

    public void getQuizWindow(){
        quizFrame.add(quizPanel);
        quizPanel.setLayout(new BorderLayout());
        quizPanel.add(questionNumber, BorderLayout.NORTH);
        quizPanel.add(questionAndResultPanel, BorderLayout.CENTER);

        questionAndResultPanel.setLayout(new BorderLayout());
        questionAndResultPanel.add(question, BorderLayout.NORTH);
        questionAndResultPanel.add(result, BorderLayout.CENTER);

        question.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        question.setHorizontalAlignment(JLabel.CENTER);
        result.setHorizontalAlignment(JLabel.CENTER);
        result.setText("");

        quizPanel.add(answerPanel, BorderLayout.SOUTH);

        answerPanel.setLayout(new GridLayout(2, 2, 10, 10));
        answerPanel.add(answer1);
        answerPanel.add(answer2);
        answerPanel.add(answer3);
        answerPanel.add(answer4);

        checkAnswer();

        quizFrame.setLocationRelativeTo(null);
        quizFrame.setSize(300, 500);
        quizFrame.setVisible(true);
        quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void checkAnswer() {
        ActionListener answerListener = e -> {
            JButton clickedButton = (JButton) e.getSource();
            if (clickedButton.getText().equals(correctAnswer)) {
                clickedButton.setBackground(Color.GREEN);
                result.setText("Du svarde rätt!");

            } else {
                clickedButton.setBackground(Color.RED);
                result.setText("Du svarade fel.");
            }

            answer1.setEnabled(false);
            answer2.setEnabled(false);
            answer3.setEnabled(false);
            answer4.setEnabled(false);
        };

        answer1.addActionListener(answerListener);
        answer2.addActionListener(answerListener);
        answer3.addActionListener(answerListener);
        answer4.addActionListener(answerListener);
    }

    public GuiClass(){
       /* getUserName();
        getStartWindow();
        getGameMenu();
        getCategories();*/
        getQuizWindow();
    }
}
