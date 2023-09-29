package bbhattarai.view.layouts;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.model.User;
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

    private Button exitApplicationButton;

    private Button homeButton;
    private User user;

    WorttrainerController controller;

    public StatsGameView(User user, WorttrainerController controller) {
        this.user = user;
        this.controller = controller;
        view = new BorderPane();

        statsHeader = new Text("Deine Statistiken");
        statsHeader.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Bold font
        Font boldFont = Font.font("Arial", FontWeight.BOLD, 12);
        Text totalWins = new Text(String.valueOf(user.getWins()));
        totalWins.setFont(boldFont);
        Text totalLosses = new Text(String.valueOf(user.getLosses()));
        totalLosses.setFont(boldFont);
        Text totalPlay = new Text(String.valueOf(user.getTotalPlay()));
        totalPlay.setFont(boldFont);

        statsText = new Text("Gewonnen" + totalWins.getText() + "\nVerloren: " + totalLosses.getText() + "\nInsgesamt gespielt: " + totalPlay.getText());

        restartGameButton = new Button("Spiel neustarten");
        homeButton = new Button("Home");
        exitApplicationButton = new Button("Beenden");

        homeButton.setStyle("-fx-font-size: 18px; -fx-background-color: orange; -fx-text-fill: white;");
        restartGameButton.setStyle("-fx-font-size: 18px; -fx-background-color: green; -fx-text-fill: white;");
        exitApplicationButton.setStyle("-fx-font-size: 18px; -fx-background-color: red; -fx-text-fill: white;");

        // Add the stats header and stats text to a VBox and below that add the restart game button
        VBox content = new VBox(statsHeader, statsText, restartGameButton, homeButton, exitApplicationButton);
        content.setAlignment(Pos.CENTER);
        content.setSpacing(20);

        view.setCenter(content);


        restartGameButton.setOnAction(event -> {

            restartGameButton.setText("Wird neugestartet...");

            // Save the user stats
            controller.saveUserData(this.user);

            // Start the game again
            controller.handleStartGame(this.user,true);

        });

        exitApplicationButton.setOnAction(event -> {
            System.exit(0);
        });

        homeButton.setOnAction(event -> {
            controller.backToUserHome(this.user);
        });

    }


    public BorderPane getView() {
        return view;
    }

}
