package Server;

import javax.print.DocFlavor;
import java.io.*;
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
                    multiWriter.print(protocolOutPut);
                }
                out.writeObject(protocolOutPut);
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
