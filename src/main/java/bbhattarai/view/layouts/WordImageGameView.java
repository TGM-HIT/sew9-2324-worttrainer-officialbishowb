package bbhattarai.view.layouts;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.model.WordImage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class WordImageGameView {

    private BorderPane view;
    private WorttrainerController controller;
    private ImageView imageView;
    private TextField nameField;
    private Button nextButton;

    public WordImageGameView(WorttrainerController controller, List<WordImage> wordImageList) {
        this.controller = controller;
        view = new BorderPane();
        imageView = new ImageView(); // You can set the image using imageView.setImage(image)
        imageView.setImage(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/2000px-Google_2015_logo.svg.png"));
        nameField = new TextField();
        nameField.setPromptText("Enter name");

        nextButton = new Button("Next");
        nextButton.setOnAction(event -> {
            String enteredName = nameField.getText();
            // Handle the user's input and move to the next image
        });

        HBox inputBox = new HBox(nameField, nextButton);
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
}
