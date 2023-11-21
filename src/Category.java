import javax.swing.*;
import java.util.ArrayList;

public class Category {
    public ArrayList<QuestionWithAnswers> allQuestions = new ArrayList<>();

    public String categoryName;
    private JButton categoryButton = new JButton(categoryName);

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public JButton getCategoryButton () {
        return categoryButton;
    }
}
