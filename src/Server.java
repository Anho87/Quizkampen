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
        }
        categories.add(category_kroppknopp);categories.add(category_djurnatur);categories.add(category_film);categories.add(category_sport);
    }

    public void run() {
        try {
            String player1UserName = inPlayer1.readLine();
            String player2UserName = inPlayer2.readLine();
            outPlayer1.println(player2UserName);
            outPlayer2.println(player1UserName);
            gameActive = true;
            System.out.println("name collected "+player1UserName+" "+player2UserName+" "+gameActive);
            while (gameActive) {
                showCategoryOptions(outPlayer1);
                String chosenCategory = inPlayer1.readLine();
                System.out.println(chosenCategory);
                getQuestions(outPlayer1, chosenCategory);


            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void showCategoryOptions(PrintWriter writer) {
        System.out.println("first in showCatOpt, serverside");
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

        System.out.println("in showCatOpt, serverside");

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
        ArrayList<String> inCorrectAnswers = actualCategory.allQuestions.get(randomInt).getIncorrectAnswers();
        String inCorrectAnswersAsString = inCorrectAnswers.get(0) + ":" + inCorrectAnswers.get(1) + ":" + inCorrectAnswers.get(2);
        writer.println("GET_QUESTIONS");
        System.out.println(question);
        writer.println(question);
        System.out.println(correctAnswer);
        writer.println(correctAnswer);
        System.out.println(inCorrectAnswersAsString);
        writer.println(inCorrectAnswersAsString);


    }
}