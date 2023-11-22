package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionWithAnswers {
    private String question;
    private String correctAnswer;
    private String answer2;
    private String answer3;
    private String answer4;
    

    public QuestionWithAnswers(String question, String correctAnswer, String answer2, String answer3, String answer4) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }

    public String getCorrectAnswer () {
        return correctAnswer;
    }
  
    public String getQuestion () {
        return question;
    }

    @Override
    public String toString() {
        List<String> questionsAndAnswerList = new ArrayList<>();
        questionsAndAnswerList.add(correctAnswer);
        questionsAndAnswerList.add(answer2);
        questionsAndAnswerList.add(answer3);
        questionsAndAnswerList.add(answer4);
        Collections.shuffle(questionsAndAnswerList);
        return  question + ":" + 
                questionsAndAnswerList.get(0) + ":" +  questionsAndAnswerList.get(1) + ":" + 
                questionsAndAnswerList.get(2) + ":" + questionsAndAnswerList.get(3);
    }
}
