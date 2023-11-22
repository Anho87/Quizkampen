package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements ActionListener {
    String ip = "127.0.0.1";
    int inPort = 44444;
    GuiClass guiClass;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private  JButton sourceButton = null;
    Color originalButtonColor;
    int questionsAnwered = 0;
    int score = 0;
    Client(){
        setup();
        try {
            Socket socket = new Socket(ip, inPort);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            Object serverOutPut;
            String serverOutPutToString;
            String[] questionsAndAnswerArray;

            while ((serverOutPut = in.readObject()) != null) {
                serverOutPutToString = (String) serverOutPut;
                if (questionsAnwered == 5){
                    out.writeObject(guiClass.getName() + score);
                }
                if(serverOutPut instanceof StringBuilder){
                    JOptionPane.showMessageDialog(null,serverOutPut);
                }
                if(serverOutPutToString.equalsIgnoreCase("Correct")){
                    score++;
                    sourceButton.setBackground(Color.GREEN);
                } else if (serverOutPutToString.equalsIgnoreCase("Wrong")) {
                    sourceButton.setBackground(Color.RED);
                } else {
                    questionsAnwered++;
                    guiClass.answer1.setBackground(originalButtonColor);
                    guiClass.answer2.setBackground(originalButtonColor);
                    guiClass.answer3.setBackground(originalButtonColor);
                    guiClass.answer4.setBackground(originalButtonColor);
                    questionsAndAnswerArray =  serverOutPutToString.split(":");
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
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
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
        try{
            if(e.getSource() == guiClass.answer1){
                sourceButton = guiClass.answer1;
                out.writeObject(guiClass.answer1.getText());
            }else if(e.getSource() == guiClass.answer2){
                sourceButton = guiClass.answer2;
                out.writeObject(guiClass.answer2.getText());
            }else if(e.getSource() == guiClass.answer3){
                sourceButton = guiClass.answer3;
                out.writeObject(guiClass.answer3.getText());
            }else if(e.getSource() == guiClass.answer4){
                sourceButton = guiClass.answer4;
                out.writeObject(guiClass.answer4.getText());
            } else if (e.getSource()== guiClass.newGameButton) {
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
