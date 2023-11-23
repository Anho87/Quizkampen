package Server;

import Client.Client;

import javax.print.DocFlavor;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{


    Socket playerOneSocket;
    Socket playerTwoSocket;
    
    public Server(Socket playerOne,Socket playerTwo){
        playerOneSocket = playerOne;
        playerTwoSocket = playerTwo;
        
    }
    public void run(){

        try (
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ){
            
            multiWriter.addWriter(out);
            Object protocolOutPut;
            Object inputFromUser;

            while((inputFromUser = in.readObject()) != null){
                protocolOutPut = protocol.askQuestion((String) inputFromUser);
                if(protocolOutPut instanceof StringBuilder){
                    //System.out.println(protocolOutPut);
                    multiWriter.print(protocolOutPut);
                }
                out.writeObject(protocolOutPut);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
