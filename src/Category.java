import java.util.ArrayList;

public class Category {
    public ArrayList<QuestionWithAnswers> allQuestions = new ArrayList<>();

    public String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

}
