package bbhattarai.view.layouts;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.model.User;
import bbhattarai.model.WordImage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class UserExistOrNotView {
    private BorderPane view;

    WorttrainerController controller;

    public UserExistOrNotView(User user, WorttrainerController controller) {
        this.controller = controller;
        view = new BorderPane();

        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(20);


        Button startGameButton = new Button("Start game");
        startGameButton.setStyle("-fx-font-size: 18px; -fx-background-color: green; -fx-text-fill: white;");

        startGameButton.setOnAction(event -> {
            startGameButton.setText("Loading word and images...");
            controller.handleStartGame(user);
        });
        content.getChildren().add(startGameButton);



        view.setCenter(content);
    }

    public BorderPane getView() {
        return view;
    }


}
