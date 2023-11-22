package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    Protocol protocol = new Protocol();
    private Socket socket;
    private MultiWriter multiWriter;
    
    public Server(Socket socket, MultiWriter multiWriter){
        this.socket = socket;
        this.multiWriter = multiWriter;
    }
    public void run(){

        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));){
            
            multiWriter.addWriter(out);
            String protocolOutPut = "";
            String inputFromUser;

            while((inputFromUser = in.readLine()) != null){
                protocolOutPut = protocol.askQuestion(inputFromUser);
                out.println(protocolOutPut);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    
    
    
    
    /*Server(){
        
        try(ServerSocket serverSocket = new ServerSocket(44444);
            Socket sock =  serverSocket.accept();
            PrintWriter out = new PrintWriter(sock.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()))
        ){
            (inputFromUser = in.readLine()) != null
            String inputFromUser;
            out.println(protocol.askQuestion(""));
            while(true){
                inputFromUser = in.readLine();
                out.println(protocol.askQuestion(inputFromUser));
            }


        }catch (IOException e){
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        new Server();
    }*/
}
