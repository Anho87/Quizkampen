package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiClass extends JFrame {

    String userName;

    protected JFrame startFrame = new JFrame("Quizkampen - " + userName);
    protected JPanel startPanel = new JPanel(new BorderLayout());
    protected JPanel startButtonPanel = new JPanel(new GridLayout(1, 1));
    protected JButton newGameButton = new JButton("Starta nytt spel");


    protected JFrame gameMenuFrame = new JFrame("Spelmeny - " + userName);
    protected JPanel gameMenuPanel = new JPanel(new BorderLayout());
    protected JPanel gameMenuButtonPanel = new JPanel(new GridLayout(1, 2));
    protected JButton randomPlayerButton = new JButton("Slumpa spelare");
    protected JButton playAgainstAFriendButton = new JButton("Spela mot en vän");


    protected JFrame categoriesFrame = new JFrame("Kategorier - " + userName);
    protected JPanel categoriesPanel = new JPanel(new BorderLayout());
    protected JPanel categoriesButtonPanel = new JPanel(new GridLayout(3, 1));
    protected JLabel categoriesLabel = new JLabel("Kategorier", SwingConstants.CENTER);
    protected JButton categoryButton1 = new JButton("Kategori 1");
    protected JButton categoryButton2 = new JButton("Kategori 2");
    protected JButton categoryButton3 = new JButton("Kategori 3");


    protected JFrame quizFrame = new JFrame("Quiz - " + userName);
    protected JPanel quizPanel = new JPanel();
    JPanel questionAndResultPanel = new JPanel();
    protected int questionNr;
    protected JLabel questionNumber = new JLabel("Fråga " + questionNr);
    protected JLabel question = new JLabel();
    protected JLabel result = new JLabel();
    protected JPanel answerPanel = new JPanel();
    protected JButton answer1 = new JButton();
    protected JButton answer2 = new JButton();
    protected JButton answer3 = new JButton();
    protected JButton answer4 = new JButton();


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
        quizFrame.setTitle("Quizkampen - " + userName);
        categoriesFrame.setTitle("Kategorier - " + userName);
    }

    public void getGameMenu() {
        gameMenuFrame.add(gameMenuPanel);
        gameMenuPanel.add(gameMenuButtonPanel, BorderLayout.SOUTH);
        gameMenuButtonPanel.add(randomPlayerButton);
        gameMenuButtonPanel.add(playAgainstAFriendButton);

        gameMenuFrame.setSize(300, 500);
        gameMenuFrame.setLocationRelativeTo(null);
        gameMenuFrame.setVisible(true);
        gameMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void getStartWindow() {
        startFrame.add(startPanel);
        startPanel.add(startButtonPanel, BorderLayout.SOUTH);
        startButtonPanel.add(newGameButton);
        GuiClass g = this;
        //newGameButton.addActionListener(new GameActionListener(newGameButton, g, startFrame));

        startFrame.setSize(300, 500);
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

   /* public void getCategories() {
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
    }*/

    public void getQuizWindow() {
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

        //checkAnswer();

        quizFrame.setSize(300, 500);
        quizFrame.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu chatMenu = new JMenu("Chat");
        JMenu changeColorMenu = new JMenu("Change color");
        JMenu quitMenu = new JMenu("Quit");

        JMenuItem redItem = new JMenuItem("Red");
        JMenuItem greenItem = new JMenuItem("Green");
        JMenuItem blueItem = new JMenuItem("Blue");

        changeColorMenu.add(redItem);
        changeColorMenu.add(greenItem);
        changeColorMenu.add(blueItem);

        redItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quizPanel.setBackground(Color.RED);
            }
        });

        greenItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quizPanel.setBackground(Color.GREEN);
            }
        });

        blueItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quizPanel.setBackground(Color.BLUE);
            }
        });

        menuBar.add(chatMenu);
        menuBar.add(changeColorMenu);
        menuBar.add(quitMenu);

        quizFrame.setJMenuBar(menuBar);

        quizFrame.setVisible(true);
        quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  /*  private void checkAnswer() {
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
    }*/

    public GuiClass() {
        getUserName();
        getStartWindow();
       // getQuizWindow();
        // getGameMenu();
        //getCategories();
    }

    public void setQuestion(String question) {
        this.question.setText(question);
    }

    public void setAnswer1(String answer) {
        this.answer1.setText(answer);
    }

    public void setAnswer2(String answer) {
        this.answer2.setText(answer);
    }

    public void setAnswer3(String answer) {
        this.answer3.setText(answer);
    }

    public void setAnswer4(String answer) {
        this.answer4.setText(answer);
    }

    public static void main(String[] args) {
        GuiClass g = new GuiClass();
        // g.getUserName();
        //g.getStartWindow();
        // g.getQuizWindow();
    }
}
