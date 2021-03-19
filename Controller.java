package sample;

    import javafx.fxml.FXML;
    import javafx.scene.text.Text;
    import javafx.scene.text.TextFlow;

    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Paths;
    import java.util.*;

    import java.util.stream.Stream;


public class Controller {

        @FXML
        private TextFlow SpaceForText;

    public Controller() throws IOException {
    }


    public void initialize() throws IOException {
            Text text = new Text("Now this is a text node");
            SpaceForText.getChildren().add(text);

        String filepath = "C:\\Users\\xfili\\Desktop\\spring\\anothertest\\" +
                "QuizKlientSerwerTCP-IP\\src\\sample\\questions_and_answers.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filepath))) {

           stream.map(n->n.split("\\|.*"))
                   .flatMap(m-> Arrays.stream(m.clone()))
                   .forEach(System.out::println);


        } catch (IOException e) {
            e.printStackTrace();
        }



    }




    }
