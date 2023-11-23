import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;

public class GuiClass extends JFrame {

    String userName;

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


    JFrame quizFrame = new JFrame("Quiz - " + userName);
    JPanel quizPanel = new JPanel();
    JPanel questionAndResultPanel = new JPanel();
    private int questionNr;
    private String correctAnswer = "Terminator";
    JLabel questionNumber = new JLabel("Fråga " + questionNr);
    JLabel question = new JLabel("I vilken film yttras orden 'I'll be back'?");
    JLabel result = new JLabel();
    JPanel answerPanel = new JPanel();
    JButton answer1 = new JButton("Terminator");
    JButton answer2 = new JButton("När Harry mötte Sally");
    JButton answer3 = new JButton("Hitta Nemo");
    JButton answer4 = new JButton("Gladiator");

    JFrame waitingForPlayerFrame = new JFrame("Waiting for player...");
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

    public void getStartWindow(){
        startFrame.add(startPanel);
        startPanel.add(startButtonPanel, BorderLayout.SOUTH);
        startButtonPanel.add(newGameButton);
        newGameButton.addActionListener(e -> getGameMenu());

        startFrame.setSize(300, 500);
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void getGameMenu() {
        gameMenuFrame.add(gameMenuPanel);
        gameMenuPanel.add(gameMenuButtonPanel, BorderLayout.SOUTH);
        gameMenuButtonPanel.add(randomPlayerButton);
        gameMenuButtonPanel.add(playAgainstAFriendButton);
        playAgainstAFriendButton.addActionListener(e -> getCategories());

        gameMenuFrame.setSize(300, 500);
        gameMenuFrame.setLocationRelativeTo(null);
        gameMenuFrame.setVisible(true);
        gameMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        categoryButton1.addActionListener(e -> getQuizWindow());
        categoryButton2.addActionListener(e -> getQuizWindow());
        categoryButton3.addActionListener(e -> getQuizWindow());

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

        JMenuBar menuBar = createMenuBar();
        quizFrame.setJMenuBar(menuBar);

        quizFrame.setSize(300, 500);
        quizFrame.setLocationRelativeTo(null);
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
       setUserName();
        getStartWindow();
        //getQuizWindow();
        //getGameMenu();
        //getCategories();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            GuiClass g = new GuiClass();

            //g.getStartWindow();
            //g.getQuizWindow();
            g.waitingForPlayer();
        });
    }













































































































    private void waitingForPlayer() {


        waitingForPlayerFrame.add(waitingForPlayer1Panel, BorderLayout.EAST);
        waitingForPlayer1Panel.add(waitingForPlayer1TextArea);
        waitingForPlayer1Panel.setPreferredSize(new Dimension(150, 400));
        waitingForPlayer1TextArea.setText("Game 1: - x x\n\nGame 2: - x x");

        waitingForPlayerFrame.add(waitingForPlayer2Panel, BorderLayout.WEST);
        waitingForPlayer2Panel.add(waitingForPlayer2TextArea);
        waitingForPlayer2Panel.setPreferredSize(new Dimension(150, 400));
        waitingForPlayer2TextArea.setText("Game 1: x - -\n\nGame 2: - x -");

        waitingForPlayerFrame.add(waitingForPlayerResultPanel, BorderLayout.SOUTH);
        waitingForPlayerResultPanel.setPreferredSize(new Dimension(300, 50));
        waitingForPlayerResultPanel.setLayout(new GridLayout(1,2));

        waitingForPlayerResultPanel.add(waitingForPlayerResultTextArea);
        waitingForPlayerResultTextArea.setText("Player 1 is Answering\nQuestions");

        waitingForPlayerResultPanel.add(waitingForPlayerButton);
        waitingForPlayerButton.setSize(50,30);
        waitingForPlayerButton.setEnabled(false);



        waitingForPlayer1TextArea.setEditable(false);
        waitingForPlayer2TextArea.setEditable(false);
        waitingForPlayerResultTextArea.setEditable(false);

        JMenuBar menuBar = createMenuBar();
        waitingForPlayerFrame.setJMenuBar(menuBar);


        waitingForPlayerFrame.setSize(300, 500);
        waitingForPlayerFrame.setLocationRelativeTo(null);
        waitingForPlayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        waitingForPlayerFrame.setVisible(true);

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


}
