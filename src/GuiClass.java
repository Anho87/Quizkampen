import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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

    private JFrame waitingForPlayerFrame = new JFrame("Waiting for player...");
    private JPanel waitingForPlayer1Panel = new JPanel(new BorderLayout());
    private JPanel waitingForPlayer2Panel = new JPanel(new BorderLayout());
    private JPanel waitingForPlayerResultPanel = new JPanel(new BorderLayout());
    private JTextArea waitingForPlayerResultTextArea = new JTextArea();
    private JTextArea waitingForPlayer1TextArea = new JTextArea();
    private JTextArea waitingForPlayer2TextArea = new JTextArea();
    private JButton waitingForPlayerButton = new JButton("Din Tur");

    private void waitingForPlayer() {

        waitingForPlayer1Panel.setBackground(Color.RED);
        waitingForPlayer2Panel.setBackground(Color.BLUE);
        waitingForPlayerResultPanel.setBackground(Color.GREEN);


        waitingForPlayerFrame.add(waitingForPlayer1Panel, BorderLayout.EAST);
        waitingForPlayer1Panel.add(waitingForPlayer1TextArea);
        waitingForPlayer1Panel.setPreferredSize(new Dimension(150, 400));
        waitingForPlayer1TextArea.setText("Game 1: - x x\n\nGame 2: - x x");

        waitingForPlayerFrame.add(waitingForPlayer2Panel, BorderLayout.WEST);
        waitingForPlayer2Panel.add(waitingForPlayer2TextArea);
        waitingForPlayer2Panel.setPreferredSize(new Dimension(150, 400));
        waitingForPlayer2TextArea.setText("Game 1: x - -\n\nGame 2: - x -");

        waitingForPlayerFrame.add(waitingForPlayerResultPanel, BorderLayout.SOUTH);
        waitingForPlayerResultPanel.setPreferredSize(new Dimension(300, 100));
        waitingForPlayerResultPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        waitingForPlayerResultPanel.add(waitingForPlayerResultTextArea);
        waitingForPlayerResultTextArea.setText("Player 1 Has Won!!!");

        waitingForPlayerResultPanel.add(waitingForPlayerButton);
        waitingForPlayerButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        waitingForPlayerButton.setPreferredSize(new Dimension(100, 60));

        waitingForPlayer1TextArea.setEditable(false);
        waitingForPlayer2TextArea.setEditable(false);
        waitingForPlayerResultTextArea.setEditable(false);


        waitingForPlayerFrame.setSize(300, 500);
        waitingForPlayerFrame.setLocationRelativeTo(null);
        waitingForPlayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        waitingForPlayerFrame.setVisible(true);

    }


    private JFrame chatFrame = new JFrame("Chat - " + userName);
    private JPanel chatPanel = new JPanel(new BorderLayout());
    private JPanel inputPanel = new JPanel(new BorderLayout());
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JTextArea chatTextArea = new JTextArea();
    private JTextField chatInputField = new JTextField();
    private JButton sendChatButton = new JButton("Send");


    private String setUserName() {
        userName = JOptionPane.showInputDialog(null, "Ange ditt användarnamn: ");
        if (userName == null || userName.trim().isEmpty()) {
            userName = "Okänd Användare";
        }
        updateFrameTitles();
        return userName;
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
        newGameButton.addActionListener(new GameActionListener(newGameButton, g, startFrame));

        startFrame.setSize(300, 500);
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void getCategories() {
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

        checkAnswer();

        quizFrame.setSize(300, 500);
        quizFrame.setLocationRelativeTo(null);

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

        quizFrame.setJMenuBar(menuBar);

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
        chatFrame.setVisible(true);
    }


    public GuiClass() {
//        getUserName();
        //     getStartWindow();
        //getQuizWindow();
        //getGameMenu();
        //getCategories();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            GuiClass g = new GuiClass();
            //g.getUserName();
            //g.getStartWindow();
            //g.getQuizWindow();
            g.waitingForPlayer();
        });
    }
}
