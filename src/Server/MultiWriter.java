package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;


public class MultiWriter {

    private static ObjectOutputStream[] writers = new ObjectOutputStream[2];
    int nextAvailableIndex = -1;
    public void addWriter(ObjectOutputStream p) {
        for (int i = 0; i < writers.length; i++){
            if(writers[i] == null){
                nextAvailableIndex = i;
            }
        }
        if (nextAvailableIndex != -1) {
            writers[nextAvailableIndex] = p;
        }
    }
    

    public void print(Object s){
        for (ObjectOutputStream writer : writers) {
           try{
               writer.writeObject(s);
           }catch(IOException e) {
               e.printStackTrace();
           }
        }
    }

}
