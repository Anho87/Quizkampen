package Server;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
    public ArrayList<QuestionWithAnswers> allQuestions = new ArrayList<>();

    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryName () {
        return categoryName;
    }

}
