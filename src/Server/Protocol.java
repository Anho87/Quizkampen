package Server;

import java.util.ArrayList;
import java.util.List;

public class Protocol {
    final int INITIAL = 0;
    final int ASKQUESTION = 1;
    final int CHECKANSWER = 2;
    final int WAITING = 3;
    protected int state = ASKQUESTION;
    private int questionCounter = 0;
    private int numberOfPlayers = 0;
    Game game = new Game();

    Category currentCategory;
    QuestionWithAnswers questionWithAnswers;
    List<QuestionWithAnswers> questionWithAnswersList = new ArrayList<>();
    
    

    public Object askQuestion(String answerFromClient) {
        if(state == WAITING){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(answerFromClient);
            if (numberOfPlayers == 2){
                //numberOfPlayers = 0;
                return stringBuilder;
            }
        }
        if (state == ASKQUESTION) {
            state = CHECKANSWER;
            return game.category_sport.allQuestions.get(questionCounter).toString();
        } else if (state == CHECKANSWER) {
            if (answerFromClient.equalsIgnoreCase(game.category_sport.allQuestions.get(questionCounter).getCorrectAnswer())) {
                if(questionCounter == game.category_sport.allQuestions.size()){
                    state = WAITING;
                    return "Correct";
                }else{
                    state = ASKQUESTION;
                    questionCounter++;
                    return "Correct";
                }
            } else {
                if(questionCounter == game.category_sport.allQuestions.size()){
                    state = WAITING;
                    numberOfPlayers++;
                    return "Wrong";
                }else{
                    state = ASKQUESTION;
                    questionCounter++;
                    return "Wrong";
                }
            }
        }
        return null;
    }

    public void chooseCategory(String category) {
        currentCategory = new Category("category_sport");

    }
}
