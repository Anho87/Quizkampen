import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class QuestionWithAnswers {
    private String question;
    private JButton correctAnswer;private JButton answer2;private JButton answer3;private JButton answer4;
    private ArrayList<JButton> incorrectAnswers = new ArrayList<>();


    public QuestionWithAnswers(String question, String correctAnswer, String answer2, String answer3, String answer4) {
        this.question = question;
        this.correctAnswer = new JButton(correctAnswer);
        this.answer2 = new JButton(answer2);
        this.answer3 = new JButton(answer3);
        this.answer4 = new JButton(answer4);
        incorrectAnswers.add(this.answer2);incorrectAnswers.add(this.answer3);incorrectAnswers.add(this.answer4);


    }

    public JButton getCorrectAnswer () {
        return correctAnswer;
    }
    public ArrayList<JButton> getIncorrectAnswers () {
        return incorrectAnswers;
    }
    public String getQuestion () {
        return question;
    }
    public void shuffleAnswers () {
        Collections.shuffle(incorrectAnswers);
    }

}
