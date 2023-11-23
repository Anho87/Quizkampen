package Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionWithAnswers implements Serializable {
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
