package MyFarm.Controller;

import MyFarm.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class StartupController {

    @FXML
    public Button startButton;
    @FXML
    public TextField nameTextField;

    public void initialize () {
        nameTextField.setPrefColumnCount(15);
        startButton.setDisable(true);
    }

    public void startGame() {
        Main main = new Main();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/mainGame.fxml"));
            loader.load();
            MainGameController mainGameController = loader.getController();
            mainGameController.setFarmer(nameTextField.getText().trim());
            mainGameController.setAssets();
            main.changeScene(loader.getRoot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void checkValidName(KeyEvent keyEvent) {
        int nameLength = nameTextField.getCharacters().toString().trim().length();
        if (nameLength > 0 && nameLength < 16)
            startButton.setDisable(false);
        else
            startButton.setDisable(true);
    }
    
    String musicFile = "Farmville.mp3";

    public void playThemeSong () {
    Media sound = new Media(new File(musicFile).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.play();
    }
}
