package bbhattarai.view.layouts;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.model.User;
import bbhattarai.model.WordImage;
import bbhattarai.view.WorttrainerView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class UserView extends WorttrainerView {
    private BorderPane view;
    private WorttrainerController controller;
    private User user;

    public UserView(User user, WorttrainerController controller) {
        super();
        this.user = user;
        this.controller = controller;
        view = new BorderPane();

        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(20);


        Button startGameButton = new Button("Spielen starten");
        startGameButton.setStyle("-fx-font-size: 18px; -fx-background-color: green; -fx-text-fill: white;");

        startGameButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                startGameButton.setStyle("-fx-font-size: 18px; -fx-background-color: green; -fx-text-fill: black;");
            } else {
                startGameButton.setStyle("-fx-font-size: 18px; -fx-background-color: green; -fx-text-fill: white;");
            }
        });

        // Set hover effect
        startGameButton.setOnMouseEntered(event -> {
            startGameButton.setStyle("-fx-font-size: 18px; -fx-background-color: green; -fx-text-fill: black;");
        });
        startGameButton.setOnMouseExited(event -> {
            startGameButton.setStyle("-fx-font-size: 18px; -fx-background-color: green; -fx-text-fill: white;");
        });

        startGameButton.setOnAction(event -> {
            this.controller.handleStartGame(this.user,false);
        });
        content.getChildren().add(startGameButton);


        // Add WordImageInputEntryView button to the right bottom corner
        WordImageInputEntryView wordImageInputEntryView = new WordImageInputEntryView(controller);
        view.setBottom(wordImageInputEntryView.getAddImageButton());
        view.setCenter(content);
    }

    public BorderPane getView() {
        return view;
    }


}
