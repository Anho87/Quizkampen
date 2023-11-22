import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Path pathToCategory_kroppknopp = FileSystems.getDefault().getPath("src", "kropp&knopp.txt");
    Path pathToCategory_djurnatur = FileSystems.getDefault().getPath("src", "djur&natur.txt");
    Category category_kroppknopp = new Category("Kropp & knopp");
    Category category_djurnatur = new Category("Djur & natur");
    ArrayList<Category> categories = new ArrayList<>();

    public Game() throws IOException {
        categories.add(category_kroppknopp);
        categories.add(category_djurnatur);



        //test
        System.out.println(category_djurnatur.allQuestions.get(0).getQuestion());
        System.out.println(category_djurnatur.allQuestions.get(0).getCorrectAnswer());
        System.out.println(category_djurnatur.allQuestions.get(0).getIncorrectAnswers());


    }
}