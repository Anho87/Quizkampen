import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    Protocol protocol = new Protocol();
    Server(){
        
        try(ServerSocket serverSocket = new ServerSocket(44444);
            Socket sock =  serverSocket.accept();
            PrintWriter out = new PrintWriter(sock.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()))
        ){
            
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
    }
}
