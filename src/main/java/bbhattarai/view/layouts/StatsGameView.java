package bbhattarai.view.layouts;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;

public class StatsGameView {
    private BorderPane view;
    private Text statsText;
    private Button startGameButton;
    private Button continueGameButton;
    private Button restartGameButton;

    public StatsGameView() {
        view = new BorderPane();

        statsText = new Text(); // Display user stats here

        startGameButton = new Button("Start Game");
        continueGameButton = new Button("Continue Game");
        restartGameButton = new Button("Restart Game");

        VBox statsBox = new VBox(statsText);
        statsBox.setAlignment(Pos.CENTER);

        VBox gameBox = new VBox();
        gameBox.setAlignment(Pos.CENTER);
        gameBox.setSpacing(10);

        // Add buttons based on user status (new/old)
        gameBox.getChildren().addAll(startGameButton, continueGameButton, restartGameButton);

        view.setLeft(statsBox);
        view.setCenter(gameBox);
    }

    public BorderPane getView() {
        return view;
    }

    public Text getStatsText() {
        return statsText;
    }

    public Button getStartGameButton() {
        return startGameButton;
    }

    public Button getContinueGameButton() {
        return continueGameButton;
    }

    public Button getRestartGameButton() {
        return restartGameButton;
    }
}
