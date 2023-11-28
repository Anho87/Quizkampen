import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import static java.awt.Color.*;
import static java.lang.Integer.parseInt;

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
    String chosenCategory;
    String correctAnswer;

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
        if (fromServer != null) {
            opponentPlayer = fromServer;
            guiClass.setOpponentUserName(opponentPlayer);

        }
        while (true) {
            fromServer = in.readLine();
            if (fromServer.equals("CHOOSE_CATEGORY")) {
                String cat1 = in.readLine();
                String cat2 = in.readLine();
                String cat3 = in.readLine();
                guiClass.getCategories(cat1, cat2, cat3);
            } else if (fromServer.equals("GET_QUESTIONS")) {
                String question = in.readLine();
                correctAnswer = in.readLine();
                String incorrectAnswers = in.readLine();
                String[] incorrectAnswersAsArray = incorrectAnswers.split(":");
                guiClass.getQuizWindow(question, correctAnswer, incorrectAnswersAsArray);
            } else if (fromServer.equals("WAIT")) {
                guiClass.playerScore = Integer.parseInt(in.readLine());
                
                guiClass.playerScoreTotal = Integer.parseInt(in.readLine());
                
                guiClass.opponentScore = Integer.parseInt(in.readLine());
                
                guiClass.opponentScoreTotal = Integer.parseInt(in.readLine());
                
                guiClass.waitingForPlayer();
                
            
                
                
                
                
                
                
            } else if (fromServer.equals("RESET BUTTONS")) {
                for (JButton button : answerButtonsList) {
                    button.setBackground(white);
                }
                clickableButtons();
            }else if(fromServer.equals("SHOW RESULT")){
                
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton jButton : categoryButtonsList) {
            if (e.getSource() == jButton) {
                outToServer.println(jButton.getText());
                //guiClass.waitingForPlayer();
            }
        }
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

    public void unclickableButtons(){
        for (JButton button : answerButtonsList) {
            button.setEnabled(false);
        }
    }
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
