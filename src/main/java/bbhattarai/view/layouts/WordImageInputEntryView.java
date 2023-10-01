package bbhattarai.view.layouts;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.model.WordImage;
import javafx.scene.control.Button;

import javax.swing.*;

public class WordImageInputEntryView {

    private Button addImageButton;

    WorttrainerController controller;

    public WordImageInputEntryView(WorttrainerController controller) {
        this.controller = controller;

        addImageButton = new Button("Bild hinzufügen");
        addImageButton.setStyle("-fx-font-size: 18px; -fx-background-color: orange; -fx-text-fill: white;");
        addImageButton.setMinWidth(80);
        addImageButton.setMinHeight(25);



        addImageButton.setOnAction(event -> {
            // Ask for the word and image url with a dialog
            String word = JOptionPane.showInputDialog("Das Wort eingeben");
            String imageUrl = JOptionPane.showInputDialog("Gültige Bild URL eingeben (endend mit .jpg oder .png)");

            // If either of the input is null / empty, show an error message
            if (word == null || word.isEmpty() || imageUrl == null || imageUrl.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ungültige Eingabe");
                return;
            }

            // If the user clicks ok, add the word and image url
            WordImage wordImage = null;
            try{
                wordImage = new WordImage(word, imageUrl);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Ungültige Eingabe");
                return;
            }
            this.controller.newImageWordInputEntry(wordImage);
        });
    }

    public Button getAddImageButton() {
        return addImageButton;
    }

}
