package Server;

import Client.Client;

import javax.print.DocFlavor;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {


    Socket playerOneSocket;
    Socket playerTwoSocket;
    PrintWriter outPlayer1;
    PrintWriter outPlayer2;
    BufferedReader inPlayer1;
    BufferedReader inPlayer2;
    boolean gameActive = false;

    public Server(Socket playerOne, Socket playerTwo) {
        playerOneSocket = playerOne;
        playerTwoSocket = playerTwo;
        try {
            outPlayer1 = new PrintWriter(playerOneSocket.getOutputStream(), true);
            outPlayer2 = new PrintWriter(playerTwoSocket.getOutputStream(), true);
            inPlayer1 = new BufferedReader(new InputStreamReader(playerOneSocket.getInputStream()));
            inPlayer2 = new BufferedReader(new InputStreamReader(playerTwoSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        try {
            String player1UserName = inPlayer1.readLine();
            String player2UserName = inPlayer2.readLine();
            outPlayer1.println("Välkommen " + player1UserName + ". Du kommer att spela mot " + player2UserName + "!");
            outPlayer2.println("Välkommen " + player2UserName + ". Du kommer att spela mot " + player1UserName + "!");
            outPlayer1.println(player2UserName);
            outPlayer2.println(player1UserName);
            gameActive = true;
            
            while (gameActive){
                
            }


        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
