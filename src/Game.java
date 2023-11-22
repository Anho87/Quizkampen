import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {


    Path pathToCategory_kroppknopp = FileSystems.getDefault().getPath("src", "kropp&knopp.txt");
    Path pathToCategory_djurnatur = FileSystems.getDefault().getPath("src", "djur&natur.txt");
    Path pathToCategory_film = FileSystems.getDefault().getPath("src", "movieQuestions.txt");
    Path pathToCategory_sport = FileSystems.getDefault().getPath("src", "sportQuestions.txt");

    Category category_kroppknopp = new Category("Kropp & knopp");
    Category category_djurnatur = new Category("Djur & natur");
    Category category_film = new Category("Film");
    Category category_sport = new Category("Sport");
    ArrayList<Category> categories = new ArrayList<>();

    public Game() throws IOException {
        categories.add(new Category("Kropp & knopp"));
        categories.add(new Category("Djur & natur"));
        categories.add(new Category("Film"));
        categories.add(new Category("Sport"));

        addQuestionsToCategory(categories.get(0), pathToCategory_kroppknopp);
        addQuestionsToCategory(categories.get(1), pathToCategory_djurnatur);
        addQuestionsToCategory(categories.get(2), pathToCategory_film);
        addQuestionsToCategory(categories.get(3), pathToCategory_sport);

        //test
       /* System.out.println(category_djurnatur.allQuestions.get(0).getQuestion());
        System.out.println(category_djurnatur.allQuestions.get(0).getCorrectAnswer());
        System.out.println(category_film.allQuestions.get(0).getIncorrectAnswers());
        System.out.println(category_sport.allQuestions.get(0).getIncorrectAnswers());*/


    }

    public void addQuestionsToCategory (Category category, Path path) throws IOException {
        Scanner scan = new Scanner(path);
        while (scan.hasNextLine()) {
            String q = scan.nextLine();
            String an = scan.nextLine();
            String[] a = an.split(":");
            category.allQuestions.add(new QuestionWithAnswers(q, a[0], a[1], a[2], a[3]));
        }

    }
    public void showCategoryOptions () {
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
    }
    public List<Category> getRandomCategories() {
        Collections.shuffle(categories);
        return categories.subList(0, Math.min(categories.size(), 3));
    }

    public static void main(String[] args) throws IOException {
        try {
            Game game = new Game();

            // Testa att få slumpmässiga kategorier
            List<Category> randomCategories = game.getRandomCategories();
            System.out.println("Slumpade kategorier:");
            for (Category cat : randomCategories) {
                System.out.println(cat.getName());
                // Testa att skriva ut några frågor för varje kategori om sådana finns
            }

            // Ytterligare tester kan läggas till här...

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


