import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Client  { //implements ActionListener
    GuiClass guiClass = new GuiClass();
    InetAddress ip = InetAddress.getLocalHost();
    int port = 55555;
    Socket sock = new Socket(ip, port);
    PrintWriter outToServer = new PrintWriter(sock.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    String thisPlayer;
    String opponentPlayer;
    String chosenCategory;

    public Client() throws IOException {
        //listor skapas av svarsknappar och kategoriknappar för att kunna loopa
        thisPlayer = guiClass.setUserName();
        outToServer.println(thisPlayer);
        //guiClass.waitingForPlayer();
        /*answerButtonsList.add(gameGui.answer1);
        answerButtonsList.add(gameGui.answer2);
        answerButtonsList.add(gameGui.answer3);
        answerButtonsList.add(gameGui.answer4);
        categoryButtonsList.add(gameGui.category1);
        categoryButtonsList.add(gameGui.category2);
        categoryButtonsList.add(gameGui.category3);
        categoryButtonsList.add(gameGui.category4);
        //action listeners kopplas till knapparna
        gameGui.answer1.addActionListener(this);
        gameGui.answer2.addActionListener(this);
        gameGui.answer3.addActionListener(this);
        gameGui.answer4.addActionListener(this);
        gameGui.category1.addActionListener(this);
        gameGui.category2.addActionListener(this);
        gameGui.category3.addActionListener(this);
        gameGui.category4.addActionListener(this);
        gameGui.nameField.addActionListener(this);
        gameGui.vidare.addActionListener(this);*/

    }

    public void game() throws Exception {
        String fromServer = in.readLine();
        if (fromServer != null) {
            opponentPlayer = fromServer;
            guiClass.setOpponentUserName(opponentPlayer);

        }
        while (true) {
            String actionToDo = in.readLine();
            if (actionToDo.equals("CHOOSE_CATEGORY")) {
                String cat1 = in.readLine();String cat2 = in.readLine(); String cat3 = in.readLine();
                chosenCategory = guiClass.getCategories(cat1, cat2, cat3);
                outToServer.println(chosenCategory);
            }
            else if (actionToDo.equals("GET_QUESTIONS")) {
                String question = in.readLine();
                String correctAnswer = in.readLine();
                String incorrectAnswers = in.readLine();
                String [] incorrectAnswersAsArray = incorrectAnswers.split(":");
                boolean answeredCorrectly = guiClass.getQuizWindow(question, correctAnswer, incorrectAnswersAsArray);
                if (answeredCorrectly) {
                    outToServer.println("Rätt");
                }
                else {
                    outToServer.println("Fel");
                }

            }
            else if (){

            }



        }
    }
    public static void main (String[] args) throws Exception {
        Client client = new Client();
        client.game();
    }



}
