package bbhattarai.view.layouts;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.model.User;
import bbhattarai.model.WordImage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.util.List;

public class WordImageGameView {

    private BorderPane view;
    private WorttrainerController controller;
    private ImageView imageView;
    private TextField nameField;
    private Button nextImageButton;

    private List<WordImage> wordImages;

    private int currentImageIndex = 0;

    private boolean gameEnded = false;

    private User user;

    public WordImageGameView(WorttrainerController controller, List<WordImage> wordImages, User user) {
        this.wordImages = wordImages;
        this.user = user;
        this.controller = controller;
        view = new BorderPane();
        imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setFitWidth(400);

        nameField = new TextField();
        nameField.setPromptText("Enter the name of the image");
        nameField.setMinWidth(200);
        nameField.setMinHeight(50);

        if (wordImages.size() == 0 || wordImages.size() < 2) {
            gameEnded = true;
            nextImageButton = new Button("End game");
        }else{
            nextImageButton = new Button("Next");
        }
        nextImageButton.setMinWidth(100);
        nextImageButton.setMinHeight(50);
        nextImageButton.setStyle("-fx-font-size: 18px; -fx-background-color: orange; -fx-text-fill: white;");


        // Get a random index
        this.currentImageIndex = getRandomIndex();

        // Display the image
        imageView.setImage(new Image(wordImages.get(currentImageIndex).getImageUrl()));
        nameField.setText("");


        nextImageButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                nextImageButton.setStyle("-fx-font-size: 18px; -fx-background-color: green; -fx-text-fill: white;");
            } else {
                nextImageButton.setStyle("-fx-font-size: 18px; -fx-background-color: orange; -fx-text-fill: white;");
            }
        });


        nextImageButton.setOnAction(event -> {

                String enteredName = nameField.getText();

                if (enteredName.isEmpty()) {
                    // Set the border color to red
                    nameField.setStyle("-fx-border-color: red");
                    JOptionPane.showMessageDialog(null, "Input cannot be empty");
                    return;
                }


                // If the input is correct, then we call the handleCorrectAnswer method to add it to the database
                if (enteredName.equals(wordImages.get(currentImageIndex).getWord())) {
                    this.user.setWins(this.user.getWins() + 1);

                    // Add the user answered word image to the database
                    controller.addUserAnsweredWordImage(this.user, wordImages.get(currentImageIndex));

                    // Remove the word image from the list so that it is not displayed again
                    this.wordImages.remove(currentImageIndex);

                }else{
                    this.user.setLosses(this.user.getLosses() + 1);
                }

                // Update the game played
                this.user.setTotalPlay(this.user.getTotalPlay() + 1);


                // Select the next random index
                currentImageIndex = getRandomIndex();

                if (currentImageIndex == -1) {
                    gameEnded = true;
                    nextImageButton.setText("End game");
                }

                if (!gameEnded) {
                    // Display the next random image and reset the text field
                    imageView.setImage(new Image(wordImages.get(currentImageIndex).getImageUrl()));
                    nameField.setText("");
                }else{
                        controller.handleEndGame(this.user);

                }

                // Save the user to the database
                controller.saveUserData(this.user);


        });



        HBox inputBox = new HBox(nameField, nextImageButton);
        inputBox.setSpacing(10);
        inputBox.setAlignment(Pos.CENTER);

        VBox content = new VBox(imageView, inputBox);
        content.setSpacing(20);
        content.setAlignment(Pos.CENTER);

        view.setCenter(content);
    }

    public BorderPane getView() {
        return view;
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public String getEnteredName() {
        return nameField.getText();
    }

    public int getRandomIndex() {
        // Return -1 if the list is empty
        if (wordImages.size() == 0) {
            return -1;
        }
        return (int) (Math.random() * wordImages.size());
    }
}
