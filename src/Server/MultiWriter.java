package Server;

import java.io.PrintWriter;


public class MultiWriter {

    private static PrintWriter[] writers = new PrintWriter[2];
    int nextAvailableIndex = -1;
    public void addWriter(PrintWriter p) {
        for (int i = 0; i < writers.length; i++){
            if(writers[i] == null){
                nextAvailableIndex = i;
            }
        }
        if (nextAvailableIndex != -1) {
            writers[nextAvailableIndex] = p;
        }
    }
    

    public void print(String s){
        for (PrintWriter writer : writers) {
            writer.println(s);
        }
    }

}
