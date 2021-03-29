package sample;

    import javafx.fxml.FXML;
    import javafx.scene.text.*;

public class Controller {
    Text txt = new Text();
    @FXML
    private TextFlow SpaceForText;

    public void initialize() {
        txt.setTextAlignment(TextAlignment.LEFT);
        txt.setFont(Font.font(20));
        SpaceForText.getChildren().add(txt);

        new Thread(()->{
            try {
                obslugaOdpowiedzi();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    public void obslugaOdpowiedzi() throws InterruptedException {

        String result = "";
        for (String key : Main.mapFromFile.keySet()) // dopoki nie sprawdzimy calej hashmapy
        {
            result =result + "\n" + key +"?";
            txt.setText(result);//wypisywanie pytan

            while(!Main.odpowiedzi.take().equals(Main.mapFromFile.get(key))){ //sprawdzanie poprawnosci
                result =result + "\nZla odpowiedz";
                txt.setText(result);
            }
            result =result + "\nDobra odpowiedz " + Main.nickname;
            txt.setText(result);
            Main.odpowiedzi.clear();
        }
        result =result + "\n\nOdpowiedziano na wszystkie pytania!";
        txt.setText(result);
    }

    }
