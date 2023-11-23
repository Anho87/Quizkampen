package Server;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
    public ArrayList<QuestionWithAnswers> allQuestions = new ArrayList<>();

    public String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

}
