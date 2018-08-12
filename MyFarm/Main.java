package MyFarm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        gameStage = primaryStage;
        Scene startup = new Scene(FXMLLoader.load(getClass().getResource("View/startup.fxml")), 960, 540);
        gameStage.setScene(startup);
        gameStage.setTitle("My Farm");
        gameStage.setResizable(false);
        gameStage.show();
    }

    public void changeScene (Parent root) {
        gameStage.setScene(new Scene(root));
        gameStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
