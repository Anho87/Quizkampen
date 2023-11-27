import java.util.Properties;

public class Settings {

    int round;
    int questions;

    public Settings(){
        Properties properties = new Properties();
        try {
            properties.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
