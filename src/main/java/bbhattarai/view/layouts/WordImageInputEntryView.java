package bbhattarai.view.layouts;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.model.WordImage;
import javafx.scene.control.Button;

import javax.swing.*;

public class WordImageInputEntryView {

    private WorttrainerController controller;


    private Button addImageButton;

    public WordImageInputEntryView(WorttrainerController controller) {
        this.controller = controller;


        addImageButton = new Button("New image and word entry");
        addImageButton.setStyle("-fx-font-size: 18px; -fx-background-color: orange; -fx-text-fill: white;");
        addImageButton.setMinWidth(100);
        addImageButton.setMinHeight(50);



        addImageButton.setOnAction(event -> {
            // Ask for the word and image url with a dialog
            String word = JOptionPane.showInputDialog("Enter the word");
            String imageUrl = JOptionPane.showInputDialog("Enter the image url");

            // If the user clicks cancel, return
            if (word == null || imageUrl == null) {
                return;
            }

            // If the user clicks ok, add the word and image url
            WordImage wordImage = new WordImage(word, imageUrl);
            controller.newimageWordInputEntry(wordImage);
        });
    }

    public Button getAddImageButton() {
        return addImageButton;
    }

}
