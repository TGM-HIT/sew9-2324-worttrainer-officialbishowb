package bbhattarai.view;

import bbhattarai.model.User;
import bbhattarai.model.WordImage;
import bbhattarai.view.layouts.LoginRegisterView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WorttrainerView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the login/register view
        LoginRegisterView loginRegisterView = new LoginRegisterView();

        Scene scene = new Scene(loginRegisterView.getView(), 400, 300);
        primaryStage.setTitle("Worttrainer App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public LoginRegisterView getLoginRegisterView() {
        LoginRegisterView loginRegisterView = new LoginRegisterView();
        return loginRegisterView;
    }
}

