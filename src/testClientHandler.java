// ClientHandler-klassen (för att hantera varje klient i en egen tråd på servern)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class testClientHandler implements Runnable {

    private Socket clientSocket;
    private String p1userName;
    private String p2userName;

    public testClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

            System.out.println("Handling client: " + clientSocket);

            // Läs meddelande från klienten
            p1userName = reader.readLine();
            p2userName = reader.readLine();
            writer.println(p1userName);
            writer.println(p2userName);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Stäng klientanslutningen när tråden är klar
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}