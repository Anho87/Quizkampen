import java.util.ArrayList;
import java.util.Collections;

public class QuestionWithAnswers {
    private String question;
    private String correctAnswer;private String answer2;private String answer3;private String answer4;
    private ArrayList<String> incorrectAnswers = new ArrayList<>();


    public QuestionWithAnswers(String question, String correctAnswer, String answer2, String answer3, String answer4) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        incorrectAnswers.add(answer2);incorrectAnswers.add(answer3);incorrectAnswers.add(answer4);


    }

    public String getCorrectAnswer () {
        return correctAnswer;
    }
    public ArrayList<String> getIncorrectAnswers () {
        return incorrectAnswers;
    }
    public String getQuestion () {
        return question;
    }
    public void shuffleAnswers () {
        Collections.shuffle(incorrectAnswers);
    }

}
