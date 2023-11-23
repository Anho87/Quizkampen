package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client implements ActionListener {
    String ip = "127.0.0.1";
    int inPort = 44444;
    GuiClass guiClass;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private JButton sourceButton = null;
    Color originalButtonColor;
    int questionsAnswered = 0;
    int score = 0;
    String clickedButton = "";

    Client() {
        setup();
        try {
            Socket socket = new Socket(ip, inPort);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            Object serverOutPut;
            String serverOutPutToString;
            //String[] questionsAndAnswerList;

            while ((serverOutPut = in.readObject()) != null) {
                System.out.println(serverOutPut);
                if (serverOutPut instanceof StringBuilder) {
                    guiClass.quizFrame.dispose();
                    JOptionPane.showMessageDialog(null, serverOutPut);
                }
                if (serverOutPut instanceof String) {
                    serverOutPutToString = (String) serverOutPut;
                    String[] questionsAndAnswerList = serverOutPutToString.split(",");
                    String answers = answerQuestions(questionsAndAnswerList);
                    out.writeObject(answers);
                }
                
                
               /* if (questionsAnswered == 5){
                    out.writeObject(guiClass.userName + score);
                }
                if(serverOutPutToString.equalsIgnoreCase("Correct")){
                    score++;
                    sourceButton.setBackground(Color.GREEN);
                } else if (serverOutPutToString.equalsIgnoreCase("Wrong")) {
                    sourceButton.setBackground(Color.RED);
                } else {
                    //System.out.println(questionsAnswered);
                    questionsAnswered++;
                    guiClass.answer1.setBackground(originalButtonColor);
                    guiClass.answer2.setBackground(originalButtonColor);
                    guiClass.answer3.setBackground(originalButtonColor);
                    guiClass.answer4.setBackground(originalButtonColor);
                    questionsAndAnswerList =  serverOutPutToString.split(":");
                    guiClass.setQuestion(questionsAndAnswerList[0]);
                    guiClass.setAnswer1(questionsAndAnswerList[1]);
                    guiClass.setAnswer2(questionsAndAnswerList[2]);
                    guiClass.setAnswer3(questionsAndAnswerList[3]);
                    guiClass.setAnswer4(questionsAndAnswerList[4]);
                }*/
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public String answerQuestions(String[] s) {
        StringBuilder stringBuilder = new StringBuilder();
        
        return stringBuilder.toString();
    }
   

    private void setup() {
        guiClass = new GuiClass();
        JButton button = new JButton();
        originalButtonColor = button.getBackground();
        addActionListener();
    }

    public void addActionListener() {
        guiClass.answer1.addActionListener(this);
        guiClass.answer2.addActionListener(this);
        guiClass.answer3.addActionListener(this);
        guiClass.answer4.addActionListener(this);
        guiClass.newGameButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == guiClass.answer1) {
                
            } else if (e.getSource() == guiClass.answer2) {
               
            } else if (e.getSource() == guiClass.answer3) {
                
            } else if (e.getSource() == guiClass.answer4) {
                
            } else if (e.getSource() == guiClass.newGameButton) {
                out.writeObject("start game");
                guiClass.getQuizWindow();
                guiClass.startFrame.dispose();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
