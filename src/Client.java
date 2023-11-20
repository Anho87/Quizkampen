import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    Client(){
        try(Socket socket = new Socket("127.0.0.1", 44444);

            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        ){

            String answer;
            String serverOutPut;
            
            while((serverOutPut = in.readLine()) != null){
                System.out.println(serverOutPut);
                answer  = userInput.readLine();
                out.println(answer);
            }
            

        }catch (UnknownHostException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
