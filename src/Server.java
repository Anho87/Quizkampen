import java.io.*;
import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class Server extends Thread {
    Socket player1Socket;
    Socket player2Socket;
    PrintWriter outPlayer1;
    PrintWriter outPlayer2;
    BufferedReader inPlayer1;
    BufferedReader inPlayer2;
    Path pathToCategory_kroppknopp = FileSystems.getDefault().getPath("src", "kropp&knopp.txt");
    Path pathToCategory_djurnatur = FileSystems.getDefault().getPath("src", "djur&natur.txt");
    Path pathToCategory_film = FileSystems.getDefault().getPath("src", "movieQuestions.txt");
    Path pathToCategory_sport = FileSystems.getDefault().getPath("src", "sportQuestions.txt");
    Path pathToCategory_ilabbet = FileSystems.getDefault().getPath("src", "ilabbet.txt");
    Path pathToCategory_bockerord = FileSystems.getDefault().getPath("src", "bocker&ord.txt");

    Category category_kroppknopp = new Category("Kropp & knopp", pathToCategory_kroppknopp);
    Category category_djurnatur = new Category("Djur & natur", pathToCategory_djurnatur);
    Category category_film = new Category("Film", pathToCategory_film);
    Category category_sport = new Category("Sport", pathToCategory_sport);
    Category category_ilabbet = new Category("I labbet", pathToCategory_ilabbet);
    Category category_bockerord = new Category("Böcker & ord", pathToCategory_bockerord);
    Category empty_category = new Category("Empty");
    ArrayList<Category> categories = new ArrayList<>();


    Settings settings = new Settings();

    int totalRounds = settings.getRounds();
    int questionsPerRound = settings.getQuestions();
    int scorePlayer1;
    int scorePlayer1Total;
    int scorePlayer2;
    int scorePlayer2Total;
    ArrayList<QuestionWithAnswers> questionsInLine = new ArrayList<>();

    boolean gameActive = false;

    //In och utströmmar skapas för vardera spelare och lägger till kategorier i categori listan.
    public Server(Socket player1, Socket player2) throws IOException {
        player1Socket = player1;
        player2Socket = player2;
        try {
            outPlayer1 = new PrintWriter(player1Socket.getOutputStream(), true);
            outPlayer2 = new PrintWriter(player2Socket.getOutputStream(), true);
            inPlayer1 = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
            inPlayer2 = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        categories.add(category_kroppknopp);
        categories.add(category_djurnatur);
        categories.add(category_film);
        categories.add(category_sport);
        categories.add(category_ilabbet);
        categories.add(category_bockerord);
    }

    public void run() {
        try {
            //Tar emot namn och skickar ut motståndarnas namn till spelaren
            String player1UserName = inPlayer1.readLine();
            String player2UserName = inPlayer2.readLine();
            outPlayer1.println(player2UserName);
            outPlayer2.println(player1UserName);
            gameActive = true;
            //Spel loopen startas
            while (gameActive) {
                for (int i = 0; i < totalRounds; i++) {
                    if (i % 2 == 0) {
                        //Spelare 1 får välja kategorier och svara på frågor först.
                        showCategoryOptions(outPlayer1);
                        String chosenCategory = inPlayer1.readLine();
                        for (int j = 1; j <= questionsPerRound; j++) {
                            showQuestions(setQuestion(chosenCategory), outPlayer1);
                            if (checkResult(inPlayer1.readLine())) {
                                scorePlayer1++;
                            }
                            Thread.sleep(2000);
                        }
                        scorePlayer1Total += scorePlayer1;
                        waitForOpponent(1);
                        //Spelare 2 får svara på frågorna
                        for (int j = 1; j <= questionsPerRound; j++) {
                            showQuestions(questionsInLine.get(0), outPlayer2);
                            if (checkResult(inPlayer2.readLine())) {
                                scorePlayer2++;
                            }
                            Thread.sleep(2000);
                            questionsInLine.remove(0);
                        }
                        //Båda spelarna får se den senaste rundans resultat
                        scorePlayer2Total += scorePlayer2;
                        waitForOpponent(1);
                        waitForOpponent(2);
                        scorePlayer1 = 0;
                        scorePlayer2 = 0;
                        Thread.sleep(3000);
                    } else {
                        //Spelare 2 får välja kategorier och svara på frågor först.
                        showCategoryOptions(outPlayer2);
                        String chosenCategory2 = inPlayer2.readLine();
                        for (int j = 1; j <= questionsPerRound; j++) {
                            showQuestions(setQuestion(chosenCategory2), outPlayer2);
                            if (checkResult(inPlayer2.readLine())) {
                                scorePlayer2++;
                            }
                            Thread.sleep(2000);
                        }
                        scorePlayer2Total += scorePlayer2;
                        waitForOpponent(2);
                        //Spelare 1 får svara på frågorna
                        for (int j = 1; j <= questionsPerRound; j++) {
                            showQuestions(questionsInLine.get(0), outPlayer1);
                            if (checkResult(inPlayer1.readLine())) {
                                scorePlayer1++;
                            }
                            Thread.sleep(2000);
                            questionsInLine.remove(0);
                        }
                        //Båda spelarna får se den senaste rundans resultat
                        scorePlayer1Total += scorePlayer1;
                        waitForOpponent(2);
                        waitForOpponent(1);
                        scorePlayer1 = 0;
                        scorePlayer2 = 0;
                        Thread.sleep(3000);
                    }
                }
                //Alla rundor har körts och spelarna får se resultatet
                outPlayer1.println("SHOW RESULT");
                outPlayer2.println("SHOW RESULT");
                //Spel loopen avslutas
                gameActive = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    //Metoden där man skickar spelaren till poäng fönstret där dom väntar på att motståndaren ska spela klart.
    //Spelarnas poäng och totala poäng skickas till klienterna.
    public void waitForOpponent(int player) {
        if (player == 1) {
            outPlayer1.println("WAIT");
            outPlayer1.println(scorePlayer1);
            outPlayer1.println(scorePlayer1Total);
            outPlayer1.println(scorePlayer2);
            outPlayer1.println(scorePlayer2Total);
        } else if (player == 2) {
            outPlayer2.println("WAIT");
            outPlayer2.println(scorePlayer2);
            outPlayer2.println(scorePlayer2Total);
            outPlayer2.println(scorePlayer1);
            outPlayer2.println(scorePlayer1Total);
        }
    }

    //Kategorierna slumpas och skrivs ut till den aktiva spelarens klient.
    private void showCategoryOptions(PrintWriter writer) {
        int randomInt1 = (int) (Math.random() * categories.size());
        int randomInt2 = (int) (Math.random() * categories.size());
        while (randomInt1 == randomInt2) {
            randomInt2 = (int) (Math.random() * categories.size());
        }
        int randomInt3 = (int) (Math.random() * categories.size());
        while (randomInt3 == randomInt1 || randomInt3 == randomInt2) {
            randomInt3 = (int) (Math.random() * categories.size());
        }
        Category cat1 = categories.get(randomInt1);
        Category cat2 = categories.get(randomInt2);
        Category cat3 = categories.get(randomInt3);

        writer.println("CHOOSE_CATEGORY");
        writer.println(cat1.getCategoryName());
        writer.println(cat2.getCategoryName());
        writer.println(cat3.getCategoryName());
    }
    
    //Frågorna slumpas beroende på vilken kategori som valts
    private QuestionWithAnswers setQuestion(String chosenCategory) {
        Category actualCategory = empty_category;
        for (Category category : categories) {
            if (category.getCategoryName().equals(chosenCategory)) {
                actualCategory = category;
                break;
            }
        }
        int randomInt = (int) (Math.random() * actualCategory.allQuestions.size());
        QuestionWithAnswers selectedQuestion = actualCategory.allQuestions.get(randomInt);
        actualCategory.allQuestions.remove(randomInt);
        questionsInLine.add(selectedQuestion);
        return selectedQuestion;
    }
    //Frågorna skickas ut till den aktiva spelarens klient
    private void showQuestions(QuestionWithAnswers qa, PrintWriter writer) {
        writer.println("RESET BUTTONS");
        String question = qa.getQuestion();
        String correctAnswer = qa.getCorrectAnswer();
        ArrayList<String> inCorrectAnswers = qa.getIncorrectAnswers();
        String inCorrectAnswersAsString = inCorrectAnswers.get(0) + ":" + inCorrectAnswers.get(1) + ":" + inCorrectAnswers.get(2);
        writer.println("GET_QUESTIONS");
        writer.println(question);
        writer.println(correctAnswer);
        writer.println(inCorrectAnswersAsString);
    }
    //Kollar om man svarat rätt på frågan
    public boolean checkResult(String s) {
        if (s.equals("true")) {
            return true;
        } else {
            return false;
        }
    }
}