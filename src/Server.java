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


    String chosenCategory;
    int answeredQuestionsThisRound = 0;
    int roundsPlayed = 0;
    static int scorePlayer1;
    static int scorePlayer2;
    QuestionWithAnswers currentQuestion;
    ArrayList <QuestionWithAnswers> questionsInLine = new ArrayList<>();

    boolean gameActive = false;

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
        categories.add(category_kroppknopp);categories.add(category_djurnatur);categories.add(category_film);
        categories.add(category_sport);categories.add(category_ilabbet);categories.add(category_bockerord);
    }

    public void run() {
        try {
            String player1UserName = inPlayer1.readLine();
            String player2UserName = inPlayer2.readLine();
            outPlayer1.println(player2UserName);
            outPlayer2.println(player1UserName);
            gameActive = true;
            while (gameActive) {
                for (int i = 0; i < totalRounds; i++) {
                    outPlayer1.println("Rond " + (i + 1) + " av " + totalRounds);
                    outPlayer2.println("Rond " + (i + 1) + " av " + totalRounds);
                    showCategoryOptions(outPlayer1);
                    String chosenCategory = inPlayer1.readLine();
                    for (int j = 0; j < questionsPerRound; j++) {
                        outPlayer1.println("Fråga " + (j + 1) + " av " + questionsPerRound);
                        outPlayer2.println("Fråga " + (j + 1) + " av " + questionsPerRound);
                        showQuestions(setQuestion(outPlayer1, chosenCategory), outPlayer1);
                        if (checkResult(inPlayer1.readLine())) {
                            scorePlayer1++;
                        }
                        Thread.sleep(2000);
                        outPlayer1.println("FRAME DISPOSE");
                    }
                    outPlayer1.println("WAIT");
                    for (int j = 0; j < 3; j++) {
                        showQuestions(questionsInLine.get(0), outPlayer2);
                        if (checkResult(inPlayer2.readLine())) {
                            scorePlayer2++;
                        }
                        Thread.sleep(2000);
                        questionsInLine.remove(0);
                    }
                    showCategoryOptions(outPlayer2);
                    String chosenCategory2 = inPlayer2.readLine();
                    outPlayer2.println("FRAME DISPOSE");
                    for (int j = 0; j < 3; j++) {
                        showQuestions(setQuestion(outPlayer2, chosenCategory2), outPlayer2);
                        if (checkResult(inPlayer2.readLine())) {
                            scorePlayer2++;
                        }
                        Thread.sleep(2000);
                        outPlayer2.println("FRAME DISPOSE");
                    }
                    outPlayer2.println("WAIT");
                    for (int j = 0; j < 3; j++) {
                        showQuestions(questionsInLine.get(0), outPlayer1);
                        if (checkResult(inPlayer1.readLine())) {
                            scorePlayer1++;
                        }
                        Thread.sleep(2000);
                        outPlayer1.println("FRAME DISPOSE");
                        questionsInLine.remove(0);
                    }
                    outPlayer1.println("WAIT");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void showCategoryOptions(PrintWriter writer) {
        writer.println("FRAME DISPOSE");
        int randomInt1 = (int) (Math.random() * categories.size());
        int randomInt2 = (int) (Math.random() * categories.size());
        if (randomInt1 == randomInt2) {
            randomInt2 = (int) (Math.random() * categories.size());
        }
        int randomInt3 = (int) (Math.random() * categories.size());
        if (randomInt3 == randomInt1 || randomInt3 == randomInt2) {
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

    private QuestionWithAnswers setQuestion (PrintWriter writer, String chosenCategory) {
        writer.println("FRAME DISPOSE");
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

    private void showQuestions(QuestionWithAnswers qa, PrintWriter writer) {
        String question = qa.getQuestion();
        String correctAnswer = qa.getCorrectAnswer();
        ArrayList<String> inCorrectAnswers = qa.getIncorrectAnswers();
        String inCorrectAnswersAsString = inCorrectAnswers.get(0) + ":" + inCorrectAnswers.get(1) + ":" + inCorrectAnswers.get(2);
        writer.println("GET_QUESTIONS");
        System.out.println(question);
        writer.println(question);
        System.out.println(correctAnswer);
        writer.println(correctAnswer);
        System.out.println(inCorrectAnswersAsString);
        writer.println(inCorrectAnswersAsString);
    }
    public boolean checkResult (String s) {
        if (s.equals("true")) {
            return true;
        }
        else {
            return false;
        }
    }
    public static int getPlayer1Points(){
        return scorePlayer1;
    }
    public static int getPlayer2Points(){
        return scorePlayer2;
    }

}