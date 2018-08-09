package MyFarm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View/mainGame.fxml"));
        Scene mainGame = new Scene(root, 960, 540);
        primaryStage.setTitle("MyFarm");

        primaryStage.setScene(mainGame);
        primaryStage.setMinWidth(960);
        primaryStage.setMinHeight(540);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
