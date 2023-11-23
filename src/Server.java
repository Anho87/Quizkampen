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

    Category category_kroppknopp = new Category("Kropp & knopp");
    Category category_djurnatur = new Category("Djur & natur");
    Category category_film = new Category("Film");
    Category category_sport = new Category("Sport");
    Category empty_category = new Category("Empty");
    ArrayList<Category> categories = new ArrayList<>();


    /*Settings settings = new Settings();

    int rounds = settings.getRounds();
    int questionsPerRound = settings.getQuestions();*/
    String chosenCategory;
    int answeredQuestionsThisRound = 0;
    int roundsPlayed = 0;
    int scorePlayer1;
    int scorePlayer2;
    QuestionWithAnswers currentQuestion;

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
        }//In och utström skapas mellan spelarna och servern
    }

    public void run() {
        try {
            /*Om servern får in användarnamn från båda spelarna så skickas dem till varandra och servern sätter
            game till active*/
            String player1UserName = inPlayer1.readLine();
            String player2UserName = inPlayer2.readLine();
            outPlayer1.println(player2UserName);
            outPlayer2.println(player1UserName);
            gameActive = true;

            while(gameActive) {
                showCategoryOptions(outPlayer1);

                String chosenCategory = inPlayer1.readLine();

                getQuestions(outPlayer1, chosenCategory);


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String showCategoryOptions (PrintWriter writer) {
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
    private void getQuestions(PrintWriter writer, String chosenCategory) {
        Category actualCategory = empty_category;
        for (Category category : categories) {
            if (category.getCategoryName().equals(chosenCategory)) {
                actualCategory = category;
                break;
            }
        }
        int randomInt = (int) (Math.random() * actualCategory.allQuestions.size());
        String question = actualCategory.allQuestions.get(randomInt).getQuestion();
        String correctAnswer = actualCategory.allQuestions.get(randomInt).getCorrectAnswer();
        ArrayList <String> inCorrectAnswers = actualCategory.allQuestions.get(randomInt).getIncorrectAnswers();
        String inCorrectAnswersAsString = inCorrectAnswers.get(0) + ":" + inCorrectAnswers.get(1) + ":" + inCorrectAnswers.get(2);
        writer.println("GET_QUESTIONS");
        writer.println(question);
        writer.println(correctAnswer);
        writer.println(inCorrectAnswersAsString);


    }
}