import java.io.FileInputStream;
import java.util.Properties;

public class Settings {

    int rounds;
    int questions;

    public Settings(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/prop.properties"));
        } catch (Exception e) {
            System.out.println("Filen kunde inte hittas");
            rounds = 2;
            questions = 2;
        }
        rounds = Integer.parseInt(properties.getProperty("rounds", "2"));
        questions = Integer.parseInt(properties.getProperty("questions", "2"));
    }

    public int getRounds() {
        return rounds;
    }

    public int getQuestions() {
        return questions;
    }
}
