package sample;

    import javafx.fxml.FXML;
    import javafx.scene.text.*;

public class Controller {


    Text txt = new Text();

    @FXML
    private TextFlow SpaceForText;


    public void initialize() {
        txt.setTextAlignment(TextAlignment.LEFT);
        SpaceForText.getChildren().add(txt);
    }
    public void setText(String text){
        txt.setText(text);
    }



    }
