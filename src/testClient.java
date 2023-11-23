// Klient-klassen
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class testClient {
    GuiClass guiClass = new GuiClass();
    private String thisUsername;
    private String opponentUsername;

    public testClient() throws IOException {
        String serverAddress = "localhost"; // Serverns IP-adress eller "localhost" om på samma maskin
        int portNumber = 12345; // Porten som servern lyssnar på

        try (Socket socket = new Socket(serverAddress, portNumber);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            guiClass.getStartWindow();
            thisUsername = guiClass.getUserName();
            writer.println(thisUsername);
            while (true) {
                guiClass.waitingForPlayer();
                if (reader.readLine() != null) {
                    opponentUsername = reader.readLine();
                }
            }






        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {

    }
}