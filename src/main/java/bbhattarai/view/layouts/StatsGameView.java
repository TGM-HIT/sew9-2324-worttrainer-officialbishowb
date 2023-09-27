package bbhattarai.view.layouts;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.model.User;
import bbhattarai.model.WordImage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;

public class StatsGameView {
    private BorderPane view;
    private Text statsText;

    private Text statsHeader;
    private Button restartGameButton;

    private User user;

    WorttrainerController controller;

    public StatsGameView(User user, WorttrainerController controller) {
        this.user = user;
        this.controller = controller;
        view = new BorderPane();

        statsHeader = new Text("Your Stats");
        statsHeader.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Bold font
        Font boldFont = Font.font("Arial", FontWeight.BOLD, 12);
        Text totalWins = new Text(String.valueOf(user.getWins()));
        totalWins.setFont(boldFont);
        Text totalLosses = new Text(String.valueOf(user.getLosses()));
        totalLosses.setFont(boldFont);
        Text totalPlay = new Text(String.valueOf(user.getTotalPlay()));
        totalPlay.setFont(boldFont);

        statsText = new Text("Total Wins: " + totalWins.getText() + "\nTotal Losses: " + totalLosses.getText() + "\nTotal Play: " + totalPlay.getText());

        restartGameButton = new Button("Restart Game");
        restartGameButton.setStyle("-fx-font-size: 18px; -fx-background-color: green; -fx-text-fill: white;");

        // Add the stats header and stats text to a VBox and below that add the restart game button
        VBox content = new VBox(statsHeader, statsText, restartGameButton);
        content.setAlignment(Pos.CENTER);
        content.setSpacing(20);

        view.setCenter(content);


        restartGameButton.setOnAction(event -> {

            restartGameButton.setText("Restarting...");
            // Reset the user stats
            user.setTotalPlay(0);
            user.setWins(0);
            user.setLosses(0);


            // Save the user stats
            controller.saveUserData(user);

            // Start the game again
            controller.handleStartGame(user);

        });

    }


    public BorderPane getView() {
        return view;
    }

    public Text getStatsText() {
        return statsText;
    }




    public Button getRestartGameButton() {
        return restartGameButton;
    }
}
