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


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 500));

        primaryStage.show();

        new Thread(()->{
            try {
                dd();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }


    public static void main(String[] args) {
        launch(args);
    }

    public void dd() throws IOException {
        ServerSocket ss = new ServerSocket(8888); //utworzenie gniazda i portu
for(;;) {

    Socket s = ss.accept(); // oczekiwanie na polaczenie

    InputStreamReader in = new InputStreamReader(s.getInputStream());
    BufferedReader bf = new BufferedReader(in);

    String str = bf.readLine();


    System.out.println(str);
}}

}
