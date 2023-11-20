public class Protocol {
    final int INITIAL = 0;
    final int ASKQUESTION = 1;
    final int CHECKANSWER = 2;
    protected int state = INITIAL;

    public String askQuestion(String answerFromClient) {

        if (state == INITIAL) {
            state = ASKQUESTION;
            return "Välkommen till Quizkampen!";
        } else if (state == ASKQUESTION) {
            state = CHECKANSWER;
            return "Vilken bokstav börjar Sverige på? A: B: C: S:?";
        } else if (state == CHECKANSWER) {
            if(answerFromClient.equalsIgnoreCase("s")){
                state = INITIAL;
                return "Rätt!";
            }else{
                state = ASKQUESTION;
                return "Fel svar!";
            }
        }
        return null;
    }
}
