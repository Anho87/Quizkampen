import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class GuiClass extends JFrame {
    public static void main(String[] args) {
        GuiClass g = new GuiClass();
        g.waitingForPlayer();
    }

     String userName;
     String opponentUserName;

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
    private JLabel questionNumber = new JLabel("Fråga " + questionNr);
    private JLabel question = new JLabel("Fråga");
    private JLabel result = new JLabel();
    private JPanel answerPanel = new JPanel();
    JButton answer1 = new JButton("Svar 1");
    JButton answer2 = new JButton("Svar 2");
    JButton answer3 = new JButton("Svar 3");
    JButton answer4 = new JButton("Svar 4");
    private ArrayList<JButton> answerButtons = new ArrayList<>();

    JFrame waitingForPlayerFrame = new JFrame(userName);
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

int playerScore = 0;
int opponentScore = 0;



    public String setUserName() {
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
        waitingForPlayerFrame.setTitle(userName);
    }

    public void getStartWindow(){
        startFrame.add(startPanel);
        startPanel.add(startButtonPanel, BorderLayout.SOUTH);
        startButtonPanel.add(newGameButton);
        newGameButton.addActionListener(e -> getGameMenu());

        startFrame.setSize(300, 500);
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

        gameMenuFrame.setSize(300, 500);
        gameMenuFrame.setLocationRelativeTo(null);
        gameMenuFrame.setResizable(false);
        gameMenuFrame.setVisible(true);
        gameMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void getCategories(String cat1, String cat2, String cat3){ 
        categoriesFrame.add(categoriesPanel);
        categoriesPanel.add(categoriesLabel, BorderLayout.NORTH);
        categoriesPanel.add(categoriesButtonPanel, BorderLayout.SOUTH);

        categoriesButtonPanel.setLayout(new BoxLayout(categoriesButtonPanel, BoxLayout.Y_AXIS));

        categoryButton1.setText(cat1);
        categoryButton2.setText(cat2);
        categoryButton3.setText(cat3);
        categoriesButtonPanel.add(categoryButton1);
        categoryButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        categoriesButtonPanel.add(categoryButton2);
        categoryButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        categoriesButtonPanel.add(categoryButton3);
        categoryButton3.setAlignmentX(Component.CENTER_ALIGNMENT);

        /*AtomicReference<String> result = new AtomicReference<>("");

        categoryButton1.addActionListener(e -> result.set(cat1));
        categoryButton2.addActionListener(e -> result.set(cat2));
        categoryButton3.addActionListener(e -> result.set(cat3));*/

        categoriesFrame.setSize(300, 500);
        categoriesFrame.setLocationRelativeTo(null);
        categoriesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        categoriesFrame.setResizable(false);
        categoriesFrame.setVisible(true);
        System.out.println("here should categoryFrame display itself");
        categoriesFrame.revalidate();
        categoriesFrame.repaint();
        
    }

    public void getQuizWindow(String questionText, String correctAnswer, String [] inCorrectAnswers){
        question.setText(questionText);
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
        answerButtons.add(answer1);answerButtons.add(answer2);answerButtons.add(answer3);answerButtons.add(answer4);
        int randomInt1 = (int) (Math.random() * 4);
        int j = 0;
        for (int i = 0; i < 4; i++) {
            if (i == randomInt1) {
                answerButtons.get(i).setText(correctAnswer);
            }
            else {
                answerButtons.get(i).setText(inCorrectAnswers[j]);
                j++;
            }
        }
        for (JButton button : answerButtons) {
            answerPanel.add(button);
        }

        JMenuBar menuBar = createMenuBar();
        quizFrame.setJMenuBar(menuBar);

        quizFrame.setSize(300, 500);
        quizFrame.setLocationRelativeTo(null);


        quizFrame.setVisible(true);
        quizFrame.setResizable(false);
        quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setOpponentUserName (String opponentUserName) {
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

        waitingForPlayerFrame.setTitle(userName);

        Font nameFont = new Font(waitingForPlayer2TextArea.getFont().getFamily(), Font.PLAIN, 16);

        waitingForPlayer1TextArea.setFont(nameFont);
        waitingForPlayer2TextArea.setFont(nameFont);

        waitingForPlayerFrame.add(waitingForPlayer1Panel, BorderLayout.WEST);
        waitingForPlayer1Panel.add(waitingForPlayer1TextArea);
        waitingForPlayer1Panel.setPreferredSize(new Dimension(149, 400));
        waitingForPlayer1TextArea.setText( userName + "\n\nRound 1: " + playerScore + "\n\nRound 2: " + playerScore);
        waitingForPlayer1Panel.add(new JLabel("\nCorrect answers: " + playerScore), BorderLayout.SOUTH);


        waitingForPlayerFrame.add(waitingForPlayer2Panel, BorderLayout.EAST);
        waitingForPlayer2Panel.add(waitingForPlayer2TextArea);
        waitingForPlayer2Panel.setPreferredSize(new Dimension(151, 400));
        waitingForPlayer2TextArea.setText(opponentUserName + "\n\nRound 1: " + opponentScore + "\n\nRound 2: " + opponentScore);
        waitingForPlayer2Panel.add(new JLabel("\nCorrect answers: " + opponentScore), BorderLayout.SOUTH);


        waitingForPlayerFrame.add(waitingForPlayerResultPanel, BorderLayout.SOUTH);
        waitingForPlayerResultPanel.setPreferredSize(new Dimension(300, 50));
        waitingForPlayerResultPanel.setLayout(new GridLayout(1,2));

        waitingForPlayerResultPanel.add(waitingForPlayerResultTextArea);
        waitingForPlayerResultTextArea.setText(opponentUserName + " is Answering\nQuestions");

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
        waitingForPlayerFrame.setResizable(false);
        waitingForPlayerFrame.setVisible(true);


    }
}
