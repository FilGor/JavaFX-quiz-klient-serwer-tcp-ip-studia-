package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import static sample.Controller.controller;


public class Main extends Application {
    static Map<String, String> mapFromFile;

    private BlockingQueue<String> odpowiedzi = new ArrayBlockingQueue<String>(1);
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("QUIZ-SERWER");
        primaryStage.setScene(new Scene(root, 600, 500));

        primaryStage.show();

        new Thread(()->{
            try {
                nasluchiwanieOdpowiedzi();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                obslugaOdpowiedzi();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }


    public static void main(String[] args) throws IOException {
        String filepath = "C:\\Users\\xfili\\Desktop\\spring\\anothertest\\" +
                "QuizKlientSerwerTCP-IP\\src\\sample\\questions_and_answers.txt";


        mapFromFile = Files.lines(Paths.get(filepath))
                .filter(s -> s.matches(".*|.*")) //bierzemy pod uwage linie w ktorych mamy jakies znaki rozdzielone |
                .collect(Collectors.toMap(k -> k.split("\\|")[0], v -> v.split("\\|")[1]));


        launch(args);
    }
    public void nasluchiwanieOdpowiedzi() throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(8888); //utworzenie gniazda i portu
        for(;;) {

            Socket s = ss.accept(); // oczekiwanie na polaczenie

            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            String str = bf.readLine();

            odpowiedzi.put(str);

           // System.out.println(str);
        }}

    public void obslugaOdpowiedzi() throws InterruptedException {
        for (String key : mapFromFile.keySet())
        {
            System.out.println(key);


            while(!odpowiedzi.take().equals(mapFromFile.get(key))){
                System.out.println("zle");
            }System.out.println("dziaa");

            }

        }
}
