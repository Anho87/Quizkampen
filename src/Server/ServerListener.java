package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    private static MultiWriter multiWriter = new MultiWriter();
    private static int port = 44444;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                final Socket socketToClient = serverSocket.accept();
                Server server = new Server(socketToClient, multiWriter);
                server.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
