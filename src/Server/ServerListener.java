package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    
    private static int port = 44444;
    public ServerListener(){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            while(true){
                Socket player1 = serverSocket.accept();
                Socket player2 = serverSocket.accept();
                System.out.println("two players connected");
                Server server = new Server(player1, player2);
                server.start();
            }
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
    new ServerListener();
    }
}
