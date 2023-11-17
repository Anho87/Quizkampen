import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    Server(){
        
        try(ServerSocket serverSocket = new ServerSocket(44444);
            Socket sock =  serverSocket.accept();
            PrintWriter out = new PrintWriter(sock.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()))
        ){

            while(true){
                String input = in.readLine();
                System.out.println(input);
            }


        }catch (IOException e){
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
