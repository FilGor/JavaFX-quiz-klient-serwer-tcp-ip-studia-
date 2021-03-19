package sample;

    import javafx.fxml.FXML;
    import javafx.scene.text.TextFlow;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.net.ServerSocket;
    import java.net.Socket;
    import java.nio.file.Files;
    import java.nio.file.Paths;
    import java.util.*;
    import java.util.stream.Collectors;



public class Controller {

        @FXML
        private TextFlow SpaceForText;

    public void initialize() throws IOException {


        String filepath = "C:\\Users\\xfili\\Desktop\\spring\\anothertest\\" +
                "QuizKlientSerwerTCP-IP\\src\\sample\\questions_and_answers.txt";


        Map<String, String> mapFromFile = Files.lines(Paths.get(filepath))
                .filter(s -> s.matches(".*|.*")) //bierzemy pod uwage linie w ktorych mamy jakies znaki rozdzielone |
                .collect(Collectors.toMap(k -> k.split("\\|")[0], v -> v.split("\\|")[1]));

        for (String key : mapFromFile.keySet())
        {
            System.out.println(key + " " + mapFromFile.get(key));
        }


    }
    }
