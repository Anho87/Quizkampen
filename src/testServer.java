// Server-klassen
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class testServer {

    public static void main(String[] args) {
        int portNumber = 12345; // Byt ut detta mot önskad port

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);
                // Starta en ny tråd för att hantera klienten
                testClientHandler clientHandler = new testClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}