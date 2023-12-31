import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuiClass extends JFrame {

    String userName;
    String opponentUserName = "Motståndare";
    boolean hasFinished = false;

    JDialog userNameWindow = new JDialog(this, "Välkommen till Quizkampen!", true);
    JPanel userNamePanel = new JPanel();
    JLabel namePromtLabel = new JLabel("Ange användarnamn");
    JButton confirmButton = new JButton("OK");
    JTextField userNameField = new JTextField();

    JPanel categoriesPanel = new JPanel(new BorderLayout());
    JPanel categoriesButtonPanel = new JPanel(new GridLayout(3, 1));
    JLabel categoriesLabel = new JLabel("Kategorier", SwingConstants.CENTER);
    JButton categoryButton1 = new JButton("Kategori 1");
    JButton categoryButton2 = new JButton("Kategori 2");
    JButton categoryButton3 = new JButton("Kategori 3");
    ArrayList<JButton> categoryButtons = new ArrayList<>();

    JPanel quizPanel = new JPanel();
    JPanel questionAndResultPanel = new JPanel();
    private JLabel question = new JLabel("fråga");
    JLabel result = new JLabel();
    private JPanel answerPanel = new JPanel();
    JButton answer1 = new JButton("Svar 1");
    JButton answer2 = new JButton("Svar 2");
    JButton answer3 = new JButton("Svar 3");
    JButton answer4 = new JButton("Svar 4");
    private ArrayList<JButton> answerButtons = new ArrayList<>();

    JPanel waitingForPlayerPanel = new JPanel(new BorderLayout());
    JPanel waitingForPlayer1Panel = new JPanel(new BorderLayout());
    JPanel waitingForPlayer2Panel = new JPanel(new BorderLayout());
    JTextArea waitingForPlayerResultTextArea = new JTextArea();
    JTextArea waitingForPlayer1TextArea = new JTextArea();
    JTextArea waitingForPlayer2TextArea = new JTextArea();

    //Layout-variabler
    Dimension standardSize = new Dimension(500, 600);
    Dimension waitingPanelSize = new Dimension(245, 600);
    Dimension buttonSize = new Dimension(200, 100);
    Dimension namePromtSize = new Dimension(200, 50);
    Font headerFont = new Font("Arial", Font.BOLD, 24);
    Font standardFont = new Font("Arial", Font.PLAIN, 18);
    Font resultatFont = new Font("Arial", Font.PLAIN, 16);
    Font largeFont = new Font("Arial", Font.BOLD, 45);
    Color lightGreen = new Color (204, 255, 229);
    Color lightBlue = new Color(204, 255, 255);
    Color djurNaturColor = new Color(178, 255, 102);
    Color bockerOrdColor = new Color(204, 153, 255);
    Color iLabbetColor = new Color(255, 178, 102);
    Color kroppKnoppColor = new Color(255, 255, 153);
    Color filmColor = new Color(255, 153, 204);
    Color sportColor = new Color(192, 192, 192);
    Color winnerColor = new Color(128, 255, 0);
    Color loserColor = new Color(255, 102, 102);
    Color drawColor = new Color(255, 255, 153);
    LineBorder thinLineBorder = new LineBorder(Color.BLACK, 2);

    int playerScore = 0;
    int playerScoreTotal = 0;
    int opponentScore = 0;
    int opponentScoreTotal = 0;

    JLabel endingOfGameTextArea = new JLabel();
    JPanel endingOfGamePanel = new JPanel();
    JPanel buttonPanel1 = new JPanel();
    JPanel buttonPanel2 = new JPanel();
    JPanel buttonPanel3 = new JPanel();

    public String setUserName() {
        userNameWindow.setLayout(new BorderLayout());
        userNameWindow.setPreferredSize(standardSize);

        userNamePanel.setBackground(lightGreen);
        userNamePanel.setLayout(new BoxLayout(userNamePanel, BoxLayout.Y_AXIS));

        namePromtLabel.setFont(standardFont);
        namePromtLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userNameField.setMaximumSize(namePromtSize);
        userNameField.setMinimumSize(namePromtSize);
        userNameField.setBorder(thinLineBorder);

        confirmButton.setMinimumSize(namePromtSize);
        confirmButton.setMaximumSize(namePromtSize);
        confirmButton.setFont(standardFont);
        confirmButton.setBackground(lightBlue);
        confirmButton.setBorder(thinLineBorder);
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userNameField.getText().trim().isEmpty()) {
                    userName = "Okänd Användare";
                } else {
                    userName = userNameField.getText().trim();
                }
                userNameWindow.dispose();
            }
        });

        userNamePanel.add(namePromtLabel);
        userNamePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        userNamePanel.add(userNameField);
        userNamePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        userNamePanel.add(confirmButton);
        userNameWindow.add(userNamePanel);
        userNameWindow.pack();
        userNameWindow.setLocationRelativeTo(this);
        userNameWindow.setVisible(true);
        setTitle("Quizkampen " + userName);

        return userName;
    }


    public void getCategories(String cat1, String cat2, String cat3) {
        remove();
        add(categoriesPanel);
        categoriesPanel.setBackground(lightGreen);
        categoriesPanel.add(categoriesLabel, BorderLayout.NORTH);
        categoriesPanel.add(categoriesButtonPanel, BorderLayout.CENTER);

        categoriesButtonPanel.setLayout(new GridLayout(3,1,0,0));
        categoriesLabel.setFont(headerFont);
        categoriesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        categoriesButtonPanel.add(buttonPanel1);
        categoriesButtonPanel.setBackground(lightGreen);
        buttonPanel1.setBackground(lightGreen);
        buttonPanel1.add(categoryButton1);
        buttonPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        categoryButton1.setText(cat1);

        categoriesButtonPanel.add(buttonPanel2);
        buttonPanel2.add(categoryButton2);
        buttonPanel2.setBackground(lightGreen);
        buttonPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        categoryButton2.setText(cat2);

        categoriesButtonPanel.add(buttonPanel3);
        buttonPanel3.add(categoryButton3);
        buttonPanel3.setBackground(lightGreen);
        buttonPanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        categoryButton3.setText(cat3);

        for (JButton button : categoryButtons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMinimumSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setFont(standardFont);
            button.setBorder(thinLineBorder);

            if (button.getText().contains("Djur & natur")) {
                button.setBackground(djurNaturColor);
            } else if (button.getText().contains("I labbet")) {
                button.setBackground(iLabbetColor);
            } else if (button.getText().contains("Sport")) {
                button.setBackground(sportColor);
            }  else if (button.getText().contains("Kropp & knopp")) {
            button.setBackground(kroppKnoppColor);
            } else if (button.getText().contains("Film")) {
                button.setBackground(filmColor);
            } else if (button.getText().contains("Böcker & ord")) {
                button.setBackground(bockerOrdColor);
            }
        }
        revalidate();
        repaint();
    }

    public void remove() {
        getContentPane().removeAll();
        revalidate();
        repaint();
    }

    public void getQuizWindow(String questionText, String correctAnswer, String[] inCorrectAnswers) {
        remove();
        question.setText("<html><div style='text-align: center;'>" + questionText + "</div></html>");
        question.setFont(headerFont);

        add(quizPanel);
        quizPanel.setLayout(new BorderLayout());

        quizPanel.add(questionAndResultPanel, BorderLayout.CENTER);
        quizPanel.setBackground(lightGreen);
        questionAndResultPanel.setBackground(lightGreen);

        questionAndResultPanel.setLayout(new BorderLayout());
        questionAndResultPanel.add(question, BorderLayout.NORTH);
        questionAndResultPanel.add(result, BorderLayout.CENTER);

        question.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        question.setHorizontalAlignment(JLabel.CENTER);
        result.setHorizontalAlignment(JLabel.CENTER);
        result.setText("");
        result.setFont(resultatFont);

        quizPanel.add(answerPanel, BorderLayout.SOUTH);
        answerPanel.setBackground(lightGreen);
        answerPanel.setLayout(new GridLayout(2, 2, 10, 10));
        answerButtons.add(answer1);
        answerButtons.add(answer2);
        answerButtons.add(answer3);
        answerButtons.add(answer4);
        int randomInt1 = (int) (Math.random() * 4);
        int j = 0;
        for (int i = 0; i < 4; i++) {
            if (i == randomInt1) {
                answerButtons.get(i).setText(correctAnswer);
            } else {
                answerButtons.get(i).setText(inCorrectAnswers[j]);
                j++;
            }
        }
        for (JButton button : answerButtons) {
            answerPanel.add(button);
            button.setPreferredSize(buttonSize);
        }
        revalidate();
        repaint();
    }

    public void setOpponentUserName(String opponentUserName) {
        this.opponentUserName = opponentUserName;
    }

    public void waitingForPlayer() {
        remove();
        waitingForPlayer2TextArea.setOpaque(false);
        waitingForPlayer2TextArea.setBackground(new Color(0, 0, 0, 0));

        waitingForPlayer1TextArea.setOpaque(false);
        waitingForPlayer1TextArea.setBackground(new Color(0, 0, 0, 0));

        

        waitingForPlayer1TextArea.setFont(standardFont);
        waitingForPlayer1TextArea.setPreferredSize(new Dimension(245, 100));
        waitingForPlayer2TextArea.setFont(standardFont);

        
        waitingForPlayer1Panel.setBackground(lightGreen);
        waitingForPlayer1Panel.setBorder(thinLineBorder);
        waitingForPlayer1Panel.add(waitingForPlayer1TextArea);
        waitingForPlayer1Panel.setPreferredSize(waitingPanelSize);
        waitingForPlayer1TextArea.setText("  " + userName + "\n\n  Senaste omgången: " + playerScore + "\n\n  Totalt: " + playerScoreTotal);
        waitingForPlayer2Panel.setBackground(lightBlue);
        waitingForPlayer2Panel.setBorder(thinLineBorder);
        waitingForPlayer2Panel.add(waitingForPlayer2TextArea);
        waitingForPlayer2Panel.setPreferredSize(waitingPanelSize);

        waitingForPlayer2TextArea.setText("  " + opponentUserName + "\n\n  Senaste omgången: " + opponentScore + "\n\n  Totalt: " + opponentScoreTotal);
  
        waitingForPlayerPanel.add(waitingForPlayer1Panel, BorderLayout.WEST);
        waitingForPlayerPanel.add(waitingForPlayer2Panel, BorderLayout.EAST);

        if (hasFinished) {
            endingOfGameTextArea.setFont(largeFont);
            endingOfGameTextArea.setOpaque(false);
            endingOfGameTextArea.setBackground(new Color(0, 0, 0, 0));
            if (playerScoreTotal > opponentScoreTotal) {
                endingOfGameTextArea.setText("Du vann!!");
                endingOfGamePanel.setBackground(winnerColor);
            } else if (playerScoreTotal < opponentScoreTotal) {
                endingOfGameTextArea.setText(("Du förlorade!"));
                endingOfGamePanel.setBackground(loserColor);
            } else {
                endingOfGameTextArea.setText("Det blev oavgjort!");
                endingOfGamePanel.setBackground(drawColor);
            }
            endingOfGamePanel.setPreferredSize(new Dimension (500, 70));
            endingOfGamePanel.setBorder(thinLineBorder);
            endingOfGamePanel.add(endingOfGameTextArea);
            waitingForPlayerPanel.add(endingOfGamePanel, BorderLayout.SOUTH);

            waitingForPlayer2TextArea.setText("  Resultat för " + opponentUserName + "\n\n  Sista omgången: " + opponentScore + "\n\n  Totalt: " + opponentScoreTotal);
            waitingForPlayer1TextArea.setText("  Resultat för " + userName + "\n\n  Sista omgången: " + playerScore + "\n\n  Totalt: " + playerScoreTotal);
        }

        add(waitingForPlayerPanel);

        waitingForPlayer1TextArea.setEditable(false);
        waitingForPlayer2TextArea.setEditable(false);
        waitingForPlayerResultTextArea.setEditable(false);

        revalidate();
        repaint();
        
     
    }

    public void setup() {
        setTitle("Quizkampen " + userName);
        setSize(standardSize);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        categoryButton1.setPreferredSize(buttonSize);
        categoryButton2.setPreferredSize(buttonSize);
        categoryButton3.setPreferredSize(buttonSize);
        categoryButtons.add(categoryButton1);
        categoryButtons.add(categoryButton2);
        categoryButtons.add(categoryButton3);

    }

    public GuiClass() {
        setup();
    }

    public void displayEndGameResults() {
        remove();
        hasFinished = true;
        waitingForPlayer();
    }
}
