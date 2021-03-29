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


public class Main extends Application {
    static Map<String, String> mapFromFile;
    static BlockingQueue<String> odpowiedzi = new ArrayBlockingQueue<String>(2);
    static String nickname;
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
    Parent root;
    {
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        //
    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("QUIZ-SERWER");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();

         Thread thread =new Thread(()->{

             nasluchiwanieOdpowiedzi(); //wyjatki przechwytywane w metodzie
         });
         thread.setDaemon(true); //aby watek zakonczyl sie razem z mainem (poniewaz jest w nim petla nieskonczona)
        thread.start();

    }
    @Override
    public void stop() {
        System.exit(0);
    } //aby zamkniecie okna zamykalo aplikacje

    public static void main(String[] args) throws IOException {
        String filepath = "C:\\Users\\xfili\\Desktop\\spring\\anothertest\\" +
                "QuizKlientSerwerTCP-IP\\src\\sample\\questions_and_answers.txt";

        mapFromFile = Files.lines(Paths.get(filepath)) //pobranie z pytan i odpowiedzi
                .filter(s -> s.matches(".*|.*")) //bierzemy pod uwage linie w ktorych mamy jakies znaki rozdzielone |
                .collect(Collectors.toMap(k -> k.split("\\|")[0], v -> v.split("\\|")[1]));//rozdzielenie
        launch(args);
    }
    public void nasluchiwanieOdpowiedzi()  {

       for(;;) {

            try(ServerSocket ss = new ServerSocket(8888); //utworzenie gniazda i portu
                Socket s = ss.accept(); // oczekiwanie na polaczenie
                InputStreamReader in = new InputStreamReader(s.getInputStream());
                BufferedReader bf = new BufferedReader(in);
            ) {
                String odpowiedz = bf.readLine();
                nickname = bf.readLine();
                odpowiedzi.put(odpowiedz);
            }catch(IOException| InterruptedException e ){
                e.printStackTrace();
           }
        }}


}
