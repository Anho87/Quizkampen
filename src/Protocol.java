import java.util.ArrayList;
import java.util.List;

public class Protocol {
    final int INITIAL = 0;
    final int ASKQUESTION = 1;
    final int CHECKANSWER = 2;
    protected int state = INITIAL;
    private int questionCounter = 0;
    List<Question> questionList = new ArrayList<>();

    public String askQuestion(String answerFromClient) {

        if (state == INITIAL) {
            state = ASKQUESTION;
            makeQuestionList();
            return "Välkommen till Quizkampen!";
        } else if (state == ASKQUESTION) {
            state = CHECKANSWER;
            return questionList.get(questionCounter).toString();
        } else if (state == CHECKANSWER) {
            if(answerFromClient.equalsIgnoreCase(questionList.get(questionCounter).getRightAnswer())){
                state = ASKQUESTION;
                questionCounter++;
                return "Rätt!";
            }else{
                state = ASKQUESTION;
                questionCounter++;
                return "Fel svar!";
            }
        }
        return null;
    }
    
    public void makeQuestionList(){
        Question question1 = new Question("Vilken bokstav börjar Sverige på?", 
                "S","A", "B","C");
        Question question2 = new Question("I vilken kontinent ligger Sverige?", 
                "Europa", "Asien", "Afrika", "Nord Amerika");
        Question question3 = new Question("Vad heter Pettsons Katt?", 
                "Findus", "Peter", "Katten Janson", "Olof");
        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);
    }
}
