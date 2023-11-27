import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Category implements Serializable {
    public ArrayList<QuestionWithAnswers> allQuestions = new ArrayList<>();
    private String categoryName;

    public Category(String categoryName, Path pathToQuestionFile) throws IOException {
        Scanner scan = new Scanner(pathToQuestionFile);
        this.categoryName = categoryName;

        while (scan.hasNext()) {
            String question = scan.nextLine();
            String allAnswers = scan.nextLine();
            String [] allAnswersAsArray = allAnswers.split(":");
            QuestionWithAnswers qa = new QuestionWithAnswers(question, allAnswersAsArray[0], allAnswersAsArray[1], allAnswersAsArray[2], allAnswersAsArray[3]);
            allQuestions.add(qa);
        }

    }
    public Category (String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryName () {
        return categoryName;
    }


}
