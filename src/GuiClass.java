import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuiClass extends JFrame {

    String userName;
    String opponentUserName = "Motståndare";

    JFrame startFrame = new JFrame("Quizkampen - " + userName);
    JPanel startPanel = new JPanel(new BorderLayout());
    JPanel startButtonPanel = new JPanel(new GridLayout(1, 1));
    JButton newGameButton = new JButton("Starta nytt spel");


    JFrame gameMenuFrame = new JFrame("Spelmeny - " + userName);
    JPanel gameMenuPanel = new JPanel(new BorderLayout());
    JPanel gameMenuButtonPanel = new JPanel(new GridLayout(1, 2));
    JButton randomPlayerButton = new JButton("Slumpa spelare");
    JButton playAgainstAFriendButton = new JButton("Spela mot en vän");


    JFrame categoriesFrame = new JFrame("Kategorier - " + userName);
    JPanel categoriesPanel = new JPanel(new BorderLayout());
    JPanel categoriesButtonPanel = new JPanel(new GridLayout(3, 1));
    JLabel categoriesLabel = new JLabel("Kategorier", SwingConstants.CENTER);
    JButton categoryButton1 = new JButton("Kategori 1");
    JButton categoryButton2 = new JButton("Kategori 2");
    JButton categoryButton3 = new JButton("Kategori 3");
    ArrayList<JButton> categoryButtons = new ArrayList<>();


    JFrame quizFrame = new JFrame("Quiz - " + userName);
    JPanel quizPanel = new JPanel();
    JPanel questionAndResultPanel = new JPanel();
    private int questionNr;
    private JLabel questionNumber = new JLabel("Fråga " + questionNr);
    private JLabel question = new JLabel("fråga");
    private JLabel result = new JLabel();
    private JPanel answerPanel = new JPanel();
    JButton answer1 = new JButton("Svar 1");
    JButton answer2 = new JButton("Svar 2");
    JButton answer3 = new JButton("Svar 3");
    JButton answer4 = new JButton("Svar 4");
    private ArrayList<JButton> answerButtons = new ArrayList<>();

    JFrame waitingForPlayerFrame = new JFrame(userName);
    JPanel waitingForPlayerPanel = new JPanel(new BorderLayout());
    JPanel waitingForPlayer1Panel = new JPanel(new BorderLayout());
    JPanel waitingForPlayer2Panel = new JPanel(new BorderLayout());
    JPanel waitingForPlayerResultPanel = new JPanel(new BorderLayout());
    JTextArea waitingForPlayerResultTextArea = new JTextArea();
    JTextArea waitingForPlayer1TextArea = new JTextArea();
    JTextArea waitingForPlayer2TextArea = new JTextArea();
    JButton waitingForPlayerButton = new JButton("Din Tur");
    JMenuBar menuBar = new JMenuBar();
    JMenu chatMenu = new JMenu("Chat");
    JMenu changeColorMenu = new JMenu("Change color");
    JMenu quitMenu = new JMenu("Quit");
    JFrame chatFrame = new JFrame("Chat - " + userName);
    JPanel chatPanel = new JPanel(new BorderLayout());
    JPanel inputPanel = new JPanel(new BorderLayout());
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JTextArea chatTextArea = new JTextArea();
    JTextField chatInputField = new JTextField();
    JButton sendChatButton = new JButton("Send");


    //Layout-variabler
    Dimension standardSize = new Dimension(500, 600);
    Dimension waitingPanelSize = new Dimension(245, 600);
    Dimension buttonSize = new Dimension(200, 100);
    Font headerFont = new Font("Arial", Font.BOLD, 24);
    Font standardFont = new Font("Arial", Font.PLAIN, 18);
    Color lightGreen = new Color (204, 255, 229);
    Color lightBlue = new Color(204, 255, 255);
    Color djurNaturColor = new Color(178, 255, 102);
    Color bockerOrdColor = new Color(204, 153, 255);
    Color iLabbetColor = new Color(255, 178, 102);
    Color kroppKnoppColor = new Color(255, 255, 153);
    Color filmColor = new Color(255, 153, 204);
    Color sportColor = new Color(192, 192, 192);
    LineBorder thinLineBorder = new LineBorder(Color.BLACK, 2);


    JPanel endingOfGamePanel1 = new JPanel();
    JPanel endingOfGamePanel2 = new JPanel();
    JPanel endingOfGamePanel3 = new JPanel();
    int playerScore = 0;
    int playerScoreTotal = 0;
    int opponentScore = 0;
    int opponentScoreTotal = 0;

    JTextArea endingOfGameTextArea = new JTextArea();
    JTextArea endingOfGameTextArea2 = new JTextArea();
    JTextArea endingOfGameTextArea3 = new JTextArea();

    /*
    public String setUserName() {
        userName = JOptionPane.showInputDialog(null, "Ange ditt användarnamn: ");
        if (userName == null || userName.trim().isEmpty()) {
            userName = "Okänd Användare";
        }
        updateFrameTitles();
        return userName;
    }

    JLabel playerEndGameResultsText = new JLabel("                  "+userName + "'s Results\n" +
            "\nLast round: " + playerScore +
            "\n\nAmount Of Correct Answers: ");

    JLabel opponentEndGameResultsText = new JLabel("         "+opponentUserName + "'s Results\n" +
            "\nLast round: " + opponentScore +
            "\n\nAmount Of Correct Answers: ");

     */
    JLabel opponentEndGameResultsText = new JLabel();
    JLabel playerEndGameResultsText = new JLabel();
    JPanel buttonPanel1 = new JPanel();
    JPanel buttonPanel2 = new JPanel();
    JPanel buttonPanel3 = new JPanel();


    public String setUserName() {
        userName = JOptionPane.showInputDialog(null, "Ange ditt användarnamn: ");
        if (userName == null || userName.trim().isEmpty()) {
            userName = "Okänd Användare";
        }
        /*
        playerEndGameResultsText = new JLabel( "'s Results\n" +
                "\nLast round: " + playerScore +
                "\n\nAmount Of Correct Answers: ");

        opponentEndGameResultsText = new JLabel("'s Results\n" +
                "\nLast round: " + opponentScore +
                "\n\nAmount Of Correct Answers: ");

         */
        updateFrameTitles();
        return userName;
    }

    private void updateFrameTitles() {
        startFrame.setTitle("Quizkampen - " + userName);
        gameMenuFrame.setTitle("Spelmeny - " + userName);
        categoriesFrame.setTitle("Kategorier - " + userName);
        quizFrame.setTitle("Quiz " + userName);
        waitingForPlayerFrame.setTitle(userName);
    }

    public void getStartWindow() {
        startFrame.add(startPanel);
        startPanel.add(startButtonPanel, BorderLayout.SOUTH);
        startButtonPanel.add(newGameButton);
        newGameButton.addActionListener(e -> getGameMenu());

        startFrame.setSize(standardSize);
        startFrame.setLocationRelativeTo(null);
        startFrame.setResizable(false);
        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void getGameMenu() {
        gameMenuFrame.add(gameMenuPanel);
        gameMenuPanel.add(gameMenuButtonPanel, BorderLayout.SOUTH);
        gameMenuButtonPanel.add(randomPlayerButton);
        gameMenuButtonPanel.add(playAgainstAFriendButton);
        //playAgainstAFriendButton.addActionListener(e -> getCategories());

        gameMenuFrame.setSize(standardSize);
        gameMenuFrame.setLocationRelativeTo(null);
        gameMenuFrame.setResizable(false);
        gameMenuFrame.setVisible(true);
        gameMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        categoriesFrame.setSize(standardSize);
        categoriesFrame.setLocationRelativeTo(null);
        categoriesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        categoriesFrame.setResizable(false);


        //categoriesFrame.setVisible(true);

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
        /*if (questionText.length() > 40) {
            String subQuestionText = questionText.substring(0, 35);
            String subQuestionText2 = questionText.substring(35);
            question.setText(subQuestionText);
            question.setText(subQuestionText2);
        } else {
            question.setText(questionText);
        }*/
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

        JMenuBar menuBar = createMenuBar();
        quizFrame.setJMenuBar(menuBar);

        quizFrame.setSize(standardSize);
        quizFrame.setLocationRelativeTo(null);


        //quizFrame.setVisible(true);
        quizFrame.setResizable(false);
        quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        revalidate();
        repaint();
    }

    public void setOpponentUserName(String opponentUserName) {
        this.opponentUserName = opponentUserName;
    }

    private void getChat() {
        chatTextArea.setEditable(false);

        JScrollPane chatScrollPane = new JScrollPane(chatTextArea);
        chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        buttonPanel.add(sendChatButton);

        inputPanel.add(chatInputField, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.EAST);

        chatPanel.setLayout(new BorderLayout());
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);
        chatPanel.add(inputPanel, BorderLayout.SOUTH);

        sendChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sendMessage();
            }
        });

        sendChatButton.setPreferredSize(new Dimension(80, 35));

        chatFrame.add(chatPanel);
        chatFrame.setSize(250, 250);
        chatFrame.setLocationRelativeTo(null);
        chatFrame.setResizable(false);
        chatFrame.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu chatMenu = new JMenu("Chat");
        JMenu changeColorMenu = new JMenu("Change color");
        JMenu quitMenu = new JMenu("Quit");


        JMenuItem changeBodyColorItem = new JMenuItem("Change body color");
        JMenuItem changeTextColorItem = new JMenuItem("Change text color");

        changeColorMenu.add(changeBodyColorItem);
        changeColorMenu.add(changeTextColorItem);

        JMenuItem closeChatItem = new JMenuItem("Close");
        JMenuItem openChatItem = new JMenuItem("Open");

        chatMenu.add(closeChatItem);
        chatMenu.add(openChatItem);

        JMenuItem exitGameItem = new JMenuItem("Exit Game");

        quitMenu.add(exitGameItem);

        openChatItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getChat();
            }
        });

        closeChatItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatFrame.dispose();
            }
        });

        changeBodyColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser colorChooser = new JColorChooser();
                Color color = JColorChooser.showDialog(null, "Choose a color", Color.black);

                questionAndResultPanel.setBackground(color);
                //answerPanel.setBackground(color);
                quizPanel.setBackground(color);


            }
        });

        changeTextColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser colorChooser = new JColorChooser();
                Color color = JColorChooser.showDialog(null, "Choose a color", Color.black);
                question.setForeground(color);
                answer1.setForeground(color);
                answer2.setForeground(color);
                answer3.setForeground(color);
                answer4.setForeground(color);
                questionNumber.setForeground(color);
                questionAndResultPanel.setForeground(color);
                result.setForeground(color);


            }
        });

        exitGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getStartWindow();
                quizFrame.dispose();
            }
        });

        menuBar.add(chatMenu);
        menuBar.add(changeColorMenu);
        menuBar.add(quitMenu);

        return menuBar;
    }

    public void waitingForPlayer() {
        remove();
        //waitingForPlayerFrame.setTitle(userName);


        waitingForPlayerFrame.setTitle(userName);

        waitingForPlayer2TextArea.setOpaque(false);
        waitingForPlayer2TextArea.setBackground(new Color(0, 0, 0, 0));

        waitingForPlayer1TextArea.setOpaque(false);
        waitingForPlayer1TextArea.setBackground(new Color(0, 0, 0, 0));


        //Font nameFont = new Font(waitingForPlayer2TextArea.getFont().getFamily(), Font.PLAIN, 16);

        waitingForPlayer1TextArea.setFont(standardFont);
        waitingForPlayer1TextArea.setPreferredSize(new Dimension(245, 100));
        waitingForPlayer2TextArea.setFont(standardFont);

        //waitingForPlayerFrame.add(waitingForPlayer1Panel, BorderLayout.WEST);
        waitingForPlayer1Panel.setBackground(lightGreen);
        waitingForPlayer1Panel.setBorder(thinLineBorder);
        waitingForPlayer1Panel.add(waitingForPlayer1TextArea);
        waitingForPlayer1Panel.setPreferredSize(waitingPanelSize);
        waitingForPlayer1TextArea.setText("  " + userName + "\n\n  Senaste omgången: " + playerScore + "\n\n  Totalt: " + playerScoreTotal);
        //waitingForPlayer1Panel.add(new JLabel("\nCorrect answers: " + playerScore), BorderLayout.SOUTH);


        //add(waitingForPlayer2Panel, BorderLayout.EAST);
        waitingForPlayer2Panel.setBackground(lightBlue);
        waitingForPlayer2Panel.setBorder(thinLineBorder);
        waitingForPlayer2Panel.add(waitingForPlayer2TextArea);
        waitingForPlayer2Panel.setPreferredSize(waitingPanelSize);

        waitingForPlayer2TextArea.setText("  " + opponentUserName + "\n\n  Senaste omgången: " + opponentScore + "\n\n  Totalt: " + opponentScoreTotal);
        //waitingForPlayer2Panel.add(new JLabel("\nCorrect answers: " + opponentScore), BorderLayout.SOUTH);


        //add(waitingForPlayerResultPanel, BorderLayout.SOUTH);
        /*waitingForPlayerResultPanel.setPreferredSize(new Dimension(300, 50));
        waitingForPlayerResultPanel.setLayout(new GridLayout(1,2));

        waitingForPlayerResultPanel.add(waitingForPlayerResultTextArea);
        waitingForPlayerResultTextArea.setText(opponentUserName + " is Answering\nQuestions");

        waitingForPlayerResultPanel.add(waitingForPlayerButton);
        waitingForPlayerButton.setSize(50,30);
        waitingForPlayerButton.setEnabled(false);*/
        waitingForPlayerPanel.add(waitingForPlayer1Panel, BorderLayout.WEST);
        waitingForPlayerPanel.add(waitingForPlayer2Panel, BorderLayout.EAST);
        //waitingForPlayerPanel.add(waitingForPlayerResultPanel, BorderLayout.SOUTH);

        add(waitingForPlayerPanel);

        waitingForPlayer1TextArea.setEditable(false);
        waitingForPlayer2TextArea.setEditable(false);
        waitingForPlayerResultTextArea.setEditable(false);

        revalidate();
        repaint();

        //updateGUI();
       /* waitingForPlayerFrame.setSize(300, 500);
        waitingForPlayerFrame.setLocationRelativeTo(null);
        waitingForPlayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        waitingForPlayerFrame.setResizable(false);
        waitingForPlayerFrame.setVisible(true);*/
    }

    public void setup() {
        setTitle("Quizkampen " + userName);
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);
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
        
        add(endingOfGamePanel1, BorderLayout.SOUTH);
        add(endingOfGamePanel2, BorderLayout.WEST);
        add(endingOfGamePanel3, BorderLayout.EAST);

        endingOfGamePanel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        endingOfGamePanel2.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        endingOfGamePanel3.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));


        endingOfGameTextArea.setOpaque(false);
        endingOfGameTextArea.setBackground(new Color(0, 0, 0, 0));

        endingOfGameTextArea2.setOpaque(false);
        endingOfGameTextArea2.setBackground(new Color(0, 0, 0, 0));

        endingOfGameTextArea3.setOpaque(false);
        endingOfGameTextArea3.setBackground(new Color(0, 0, 0, 0));

        endingOfGamePanel1.setBackground(new Color(255, 204, 204));
        endingOfGamePanel2.setBackground(new Color(255, 204, 204));
        endingOfGamePanel3.setBackground(new Color(255, 204, 204));

        playerEndGameResultsText = new JLabel( "'s Results\n" +
                "\nLast round: " + playerScore +
                "\n\nAmount Of Correct Answers: ");

        opponentEndGameResultsText = new JLabel("'s Results\n" +
                "\nLast round: " + opponentScore +
                "\n\nAmount Of Correct Answers: ");

        endingOfGameTextArea2.setFont(new Font("Impact", Font.BOLD, 16));
        endingOfGameTextArea2.setText("                  " + userName + playerEndGameResultsText.getText() +  playerScoreTotal);

        endingOfGameTextArea3.setFont(new Font("Impact", Font.BOLD, 16));
        endingOfGameTextArea3.setText("         " + opponentUserName + opponentEndGameResultsText.getText() +  opponentScoreTotal);


        endingOfGameTextArea.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
        if (playerScoreTotal > opponentScoreTotal) {
            endingOfGameTextArea.setText("Congratulations You Won!!");
        } else if (playerScoreTotal < opponentScoreTotal) {
            endingOfGameTextArea.setText("You Lost!");
        } else {
            endingOfGameTextArea.setText("It's a Draw!");
        }

        endingOfGamePanel3.add(endingOfGameTextArea3);
        endingOfGamePanel2.add(endingOfGameTextArea2);
        endingOfGamePanel1.add(endingOfGameTextArea);

        endingOfGamePanel1.setPreferredSize(new Dimension(endingOfGamePanel1.getWidth(), 100));
        endingOfGamePanel2.setPreferredSize(new Dimension(250, endingOfGamePanel2.getHeight()));
        endingOfGamePanel3.setPreferredSize(new Dimension(250, endingOfGamePanel3.getHeight()));

        endingOfGameTextArea3.setEditable(false);
        endingOfGameTextArea2.setEditable(false);
        endingOfGameTextArea.setEditable(false);

        revalidate();
        repaint();
    }
}
