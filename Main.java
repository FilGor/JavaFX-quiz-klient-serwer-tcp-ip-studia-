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
    private BlockingQueue<String> odpowiedzi = new ArrayBlockingQueue<String>(1);

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
    Controller controller = fxmlLoader.getController(); // <Controller>
    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("QUIZ-SERWER");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();

        new Thread(()->{
            try {
                obslugaOdpowiedzi();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

         Thread thread =new Thread(()->{

            try {
                nasluchiwanieOdpowiedzi();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
         thread.setDaemon(true);
        thread.start();

    }
    @Override
    public void stop() {
        System.exit(0);
    }


    public static void main(String[] args) throws IOException {
        String filepath = "C:\\Users\\xfili\\Desktop\\spring\\anothertest\\" +
                "QuizKlientSerwerTCP-IP\\src\\sample\\questions_and_answers.txt";


        mapFromFile = Files.lines(Paths.get(filepath))
                .filter(s -> s.matches(".*|.*")) //bierzemy pod uwage linie w ktorych mamy jakies znaki rozdzielone |
                .collect(Collectors.toMap(k -> k.split("\\|")[0], v -> v.split("\\|")[1]));


        launch(args);
    }
    String nickname;
    public void nasluchiwanieOdpowiedzi() throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(8888); //utworzenie gniazda i portu
       for(;;) {

            Socket s = ss.accept(); // oczekiwanie na polaczenie

            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            String odpowiedz = bf.readLine();
            nickname= bf.readLine();
            odpowiedzi.put(odpowiedz);

        }}
    String result = "";
    public void obslugaOdpowiedzi() throws InterruptedException {

        for (String key : mapFromFile.keySet())
        {
            result =result + "\n" + key;

            controller.setText(result);
            while(!odpowiedzi.take().equals(mapFromFile.get(key))){
                result =result + "\nZla odpowiedz";
                controller.setText(result);

            }
            result =result + "\nDobra odpowiedz " + nickname;
            controller.setText(result);
            odpowiedzi.clear();

            }

        }
}
