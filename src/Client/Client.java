package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements ActionListener {
    String ip = "127.0.0.1";
    int inPort = 44444;
    GuiClass guiClass;
    PrintWriter out;
    BufferedReader in;
    private  JButton sourceButton = null;
    Color originalButtonColor;
    int questionsAnwered = 0;
    int score = 0;
    Client(){
        try {
            Socket socket = new Socket(ip, inPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String serverOutPut;
            String[] questionsAndAnswerArray;
            setup();
            while((serverOutPut = in.readLine()) != null){
                if (questionsAnwered == 5){
                    out.println(guiClass.getName() + score);
                }
                if(serverOutPut.equalsIgnoreCase("BothPlayersFinishedAnsweringQuestions")){
                    JOptionPane.showMessageDialog(null,serverOutPut);
                }
                if(serverOutPut.equalsIgnoreCase("Correct")){
                    score++;
                    sourceButton.setBackground(Color.GREEN);
                } else if (serverOutPut.equalsIgnoreCase("Wrong")) {
                    sourceButton.setBackground(Color.RED);
                } else {
                    questionsAnwered++;
                    guiClass.answer1.setBackground(originalButtonColor);
                    guiClass.answer2.setBackground(originalButtonColor);
                    guiClass.answer3.setBackground(originalButtonColor);
                    guiClass.answer4.setBackground(originalButtonColor);
                    questionsAndAnswerArray =  serverOutPut.split(":");
                    guiClass.setQuestion(questionsAndAnswerArray[0]);
                    guiClass.setAnswer1(questionsAndAnswerArray[1]);
                    guiClass.setAnswer2(questionsAndAnswerArray[2]);
                    guiClass.setAnswer3(questionsAndAnswerArray[3]);
                    guiClass.setAnswer4(questionsAndAnswerArray[4]);
                }
            }
            
        }catch (UnknownHostException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void setup() {
        guiClass = new GuiClass();
        originalButtonColor = guiClass.answer1.getBackground();
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
        if(e.getSource() == guiClass.answer1){
            sourceButton = guiClass.answer1;
            out.println(guiClass.answer1.getText());
        }else if(e.getSource() == guiClass.answer2){
            sourceButton = guiClass.answer2;
            out.println(guiClass.answer2.getText());
        }else if(e.getSource() == guiClass.answer3){
            sourceButton = guiClass.answer3;
            out.println(guiClass.answer3.getText());
        }else if(e.getSource() == guiClass.answer4){
            sourceButton = guiClass.answer4;
            out.println(guiClass.answer4.getText());
        } else if (e.getSource()== guiClass.newGameButton) {
            guiClass.getQuizWindow();
            guiClass.startFrame.dispose();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
