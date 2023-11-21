import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumMap;

public class GuiClass extends JFrame {

    ArrayList <Category> randomCats = showCategoryOptions();
    String userName;
    ArrayList<Category> categories = new ArrayList<>();
    Path pathToCategory_kroppknopp = FileSystems.getDefault().getPath("src", "kropp&knopp.txt");
    Path pathToCategory_djurnatur = FileSystems.getDefault().getPath("src", "djur&natur.txt");
    Category category_kroppknopp = new Category("Kropp & knopp");
    Category category_djurnatur = new Category("Djur & natur");
    Category category_film = new Category("Film");
    Category category_sport = new Category("Sport");
    Category chosenCategory;
    QuestionWithAnswers currentQuestionWithAnswers;

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
    /*private JButton category_kroppKnopp = new JButton("Kropp och Knopp");
    private JButton category_djurNatur = new JButton("Djur och Natur");
    private JButton category_film = new JButton("Film");
    private JButton category_sport = new JButton("Sport");*/


    private JFrame quizFrame = new JFrame("Quiz - " + userName);
    private JPanel quizPanel = new JPanel();
    JPanel questionAndResultPanel = new JPanel();
    private int questionNr;
    private String correctAnswer = "Terminator";
    private JLabel questionNumber = new JLabel("Fråga " + questionNr);
    private JLabel question;
    private JLabel result = new JLabel();
    private JPanel answerPanel = new JPanel();
    private JButton answer1;
    private JButton answer2;
    private JButton answer3;
    private JButton answer4;


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

    public void chooseCategory(){
        categoriesFrame.add(categoriesPanel);
        categoriesPanel.add(categoriesLabel, BorderLayout.NORTH);
        categoriesPanel.add(categoriesButtonPanel, BorderLayout.SOUTH);

        categoriesButtonPanel.setLayout(new BoxLayout(categoriesButtonPanel, BoxLayout.Y_AXIS));

        categoriesButtonPanel.add(randomCats.get(0).getCategoryButton());
        randomCats.get(0).getCategoryButton().setAlignmentX(Component.CENTER_ALIGNMENT);
        categoriesButtonPanel.add(randomCats.get(1).getCategoryButton());
        randomCats.get(1).getCategoryButton().setAlignmentX(Component.CENTER_ALIGNMENT);
        categoriesButtonPanel.add(randomCats.get(2).getCategoryButton());
        randomCats.get(2).getCategoryButton().setAlignmentX(Component.CENTER_ALIGNMENT);
        ActionListener buttonClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                chosenCategory = (Category) clickedButton.getClientProperty("chosenCategory");
            }
        };
        categoriesFrame.setSize(300, 500);
        categoriesFrame.setLocationRelativeTo(null);
        categoriesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        categoriesFrame.setVisible(true);

    }

    public void getQuizWindow(){
        int random = (int) (Math.random() * chosenCategory.allQuestions.size());
        currentQuestionWithAnswers = chosenCategory.allQuestions.get(random);
        question = new JLabel(currentQuestionWithAnswers.getQuestion());
        answer1 = currentQuestionWithAnswers.getCorrectAnswer();
        answer2 = currentQuestionWithAnswers.getIncorrectAnswers().get(0);
        answer3 = currentQuestionWithAnswers.getIncorrectAnswers().get(1);
        answer4 = currentQuestionWithAnswers.getIncorrectAnswers().get(2);

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
            if (clickedButton == currentQuestionWithAnswers.getCorrectAnswer()) {
                clickedButton.setBackground(Color.GREEN);
                result.setText("Du svarade rätt!");

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
    public ArrayList<Category> showCategoryOptions () {
        int randomInt1 = (int) (Math.random() * categories.size());
        int randomInt2 = (int) (Math.random() * categories.size());
        while (randomInt1 == randomInt2) {
            randomInt2 = (int) (Math.random() * categories.size());
        }
        int randomInt3 = (int) (Math.random() * categories.size());
        while (randomInt3 == randomInt1 || randomInt3 == randomInt2) {
            randomInt3 = (int) (Math.random() * categories.size());
        }
        Category cat1 = categories.get(randomInt1);
        Category cat2 = categories.get(randomInt2);
        Category cat3 = categories.get(randomInt3);
        ArrayList<Category> cats = new ArrayList<>();
        cats.add(cat1);cats.add(cat2);cats.add(cat3);
        return cats;
    }
    public GuiClass(){
       /* getUserName();
        getStartWindow();
        getGameMenu();
        getCategories();*/
        getQuizWindow();
    }
}
