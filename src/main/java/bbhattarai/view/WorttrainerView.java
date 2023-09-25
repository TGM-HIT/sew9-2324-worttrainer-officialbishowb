package bbhattarai.view;

import bbhattarai.model.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WorttrainerView extends  Application {

    @Override
    public void start(Stage stage) {
        stage = WorttrainerView.initStage(stage);
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage initStage(Stage primaryStage) {
        primaryStage.setTitle("WortTrainer - Rechtschreibung lernen");
        return primaryStage;
    }

    public void loadSavedData(){

    }

    public static void main(String[] args) {
        launch();
    }

}
