import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import static java.awt.Color.*;
import java.util.Timer;
import java.util.TimerTask;



public class Client implements ActionListener {
    GuiClass guiClass = new GuiClass();
    String ip = "127.0.0.1";
    int port = 55355;
    ArrayList<JButton> answerButtonsList = new ArrayList<>();
    ArrayList<JButton> categoryButtonsList = new ArrayList<>();
    Socket sock = new Socket(ip, port);
    PrintWriter outToServer = new PrintWriter(sock.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    String thisPlayer;
    String opponentPlayer;
    String correctAnswer;
    Timer timer = new Timer();
    int timeLimit = 5000;
    
    //Spelaren får välja namn och actionlisteners och listor skapas
    public Client() throws IOException {
        thisPlayer = guiClass.setUserName();
        outToServer.println(thisPlayer);
        guiClass.waitingForPlayer();
        categoryButtonsList.add(guiClass.categoryButton1);
        categoryButtonsList.add(guiClass.categoryButton2);
        categoryButtonsList.add(guiClass.categoryButton3);
        answerButtonsList.add(guiClass.answer1);
        answerButtonsList.add(guiClass.answer2);
        answerButtonsList.add(guiClass.answer3);
        answerButtonsList.add(guiClass.answer4);
        guiClass.categoryButton1.addActionListener(this);
        guiClass.categoryButton2.addActionListener(this);
        guiClass.categoryButton3.addActionListener(this);
        guiClass.answer1.addActionListener(this);
        guiClass.answer2.addActionListener(this);
        guiClass.answer3.addActionListener(this);
        guiClass.answer4.addActionListener(this);
        guiClass.setup();
    }

    public void game() throws Exception {
        String fromServer = in.readLine();
        //Moståndarens namn sätts
        if (fromServer != null) {
            opponentPlayer = fromServer;
            guiClass.setOpponentUserName(opponentPlayer);

        }
        while (true) {
            fromServer = in.readLine();
            //Kategorierna läses in från servern och välj kategori fönstret öppnas
            if (fromServer.equals("CHOOSE_CATEGORY")) {
                String cat1 = in.readLine();
                String cat2 = in.readLine();
                String cat3 = in.readLine();
                guiClass.getCategories(cat1, cat2, cat3);
            } else if (fromServer.equals("GET_QUESTIONS")) {
                //Frågorna läses in från servern och fråge fönstret öppnas
                String question = in.readLine();
                correctAnswer = in.readLine();
                String incorrectAnswers = in.readLine();
                String[] incorrectAnswersAsArray = incorrectAnswers.split(":");
                guiClass.getQuizWindow(question, correctAnswer, incorrectAnswersAsArray);
                startTimer();
            } else if (fromServer.equals("WAIT")) {
                //Poängen läses in från servern och resultat fönstret öppnas
                guiClass.playerScore = Integer.parseInt(in.readLine());
                guiClass.playerScoreTotal = Integer.parseInt(in.readLine());
                guiClass.opponentScore = Integer.parseInt(in.readLine());
                guiClass.opponentScoreTotal = Integer.parseInt(in.readLine());
                guiClass.waitingForPlayer();
            } else if (fromServer.equals("RESET BUTTONS")) {
                //De 4 svars knapparnas färg ändras till vita
                for (JButton button : answerButtonsList) {
                    button.setBackground(white);
                }
                clickableButtons();
            }else if(fromServer.equals("SHOW RESULT")){
                //Resultat rutan öppnas
                guiClass.displayEndGameResults();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for (JButton jButton : categoryButtonsList) {
            if (e.getSource() == jButton) {
                outToServer.println(jButton.getText());
            }
        }
        timer.cancel();
        for (JButton button : answerButtonsList) {
            if (e.getSource() == button) {
                if (button.getText().contains(correctAnswer)) {
                    outToServer.println("true");
                    button.setBackground(green);
                }
                else {
                    outToServer.println("false");
                    button.setBackground(red);
                    for (JButton b: answerButtonsList) {
                        if (b.getText().contains(correctAnswer)){
                            b.setBackground(green);
                        }
                    }
                }
                unclickableButtons();
                break;
            }
        }
    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Om tiden går ut räknas det som fel svar
                outToServer.println("false");
                unclickableButtons();
            }
        }, timeLimit);
    }

    //Svars knapparna sätts så att man inte kan trycka på dem efter man svarat på en fråga
    public void unclickableButtons(){
        for (JButton button : answerButtonsList) {
            button.setEnabled(false);
        }
    }
    //Svars knapparna sätts så att man kan använda dom igen
    public void clickableButtons(){
        for (JButton button : answerButtonsList) {
            button.setEnabled(true);
        }
    }
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.game();
    }
}
