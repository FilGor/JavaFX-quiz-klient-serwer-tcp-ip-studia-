package sample;

    import javafx.fxml.FXML;
    import javafx.scene.text.Text;
    import javafx.scene.text.TextFlow;

    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.nio.file.FileSystems;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.*;

    import java.util.stream.Collector;
    import java.util.stream.Collectors;
    import java.util.stream.IntStream;
    import java.util.stream.Stream;


public class Controller {

        @FXML
        private TextFlow SpaceForText;

    public Controller() throws IOException {
    }


    public void initialize() throws IOException {


        String filepath = "C:\\Users\\xfili\\Desktop\\spring\\anothertest\\" +
                "QuizKlientSerwerTCP-IP\\src\\sample\\questions_and_answers.txt";


        Map<String, String> mapFromFile = Files.lines(Paths.get(filepath))
                .filter(s -> s.matches(".*|.*"))
                .collect(Collectors.toMap(k -> k.split("\\|")[0], v -> v.split("\\|")[1]));

        for (String key : mapFromFile.keySet())
        {
            System.out.println(key + " " + mapFromFile.get(key));
        }



    }

    }
