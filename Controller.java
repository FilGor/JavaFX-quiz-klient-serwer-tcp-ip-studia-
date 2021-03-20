package sample;

    import javafx.fxml.FXML;
    import javafx.scene.paint.Color;
    import javafx.scene.paint.Paint;
    import javafx.scene.text.Font;
    import javafx.scene.text.FontPosture;
    import javafx.scene.text.Text;
    import javafx.scene.text.TextFlow;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.net.ServerSocket;
    import java.net.Socket;
    import java.nio.file.Files;
    import java.nio.file.Paths;
    import java.util.*;
    import java.util.stream.Collector;
    import java.util.stream.Collectors;



public class Controller {


    @FXML
    private TextFlow SpaceForText;

    public static Controller controller;

    public void initialize() {


        Text text = new Text("Now this is a text node");
        SpaceForText.getChildren().add(text);



    }
    }
