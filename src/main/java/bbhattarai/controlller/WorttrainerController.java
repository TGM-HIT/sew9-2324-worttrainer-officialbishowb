package bbhattarai.controlller;

import bbhattarai.model.User;
import bbhattarai.model.WordImage;
import bbhattarai.model.Worttrainer;
import bbhattarai.view.WorttrainerView;
import bbhattarai.view.layouts.StatsGameView;
import bbhattarai.view.layouts.UserView;
import bbhattarai.view.layouts.WordImageGameView;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class WorttrainerController {
    private Worttrainer model;
    private WorttrainerView view;

    public WorttrainerController(Worttrainer model, WorttrainerView view) {
        this.model = model;
        this.view = view;
    }

    public void handleLoginRegister(String username) {
        try {
            User user = model.getUser(username);
            if (user == null) {
                // Create a new user
                LocalDateTime now = LocalDateTime.now();
                 user = new User(model.getLatestUserId()+1,username, 0, 0, 0, now);
                this.model.setUser(user);
                model.saveNewUser(user);
                JOptionPane.showMessageDialog(null, "Benutzer erstellt");
            }else{
                JOptionPane.showMessageDialog(null, "Willkommen "+username+"!");
                this.model.setUser(user);
            }
            // Instantiate the NextView
            UserView nextView = new UserView(model.getUser(), this);
            // Set up the NextView as the new view to display
            view.setDisplayedView(nextView.getView());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Erstellen des Benutzers");
        }
    }

    public void backToUserHome(User user){
        // Instantiate the NextView
        UserView nextView = new UserView(user, this);
        // Set up the NextView as the new view to display
        view.setDisplayedView(nextView.getView());
    }


    public void handleStartGame(User user,boolean isEndRestartButtonClicked){
        List<WordImage> wordImages = null;
        try {
            wordImages = model.getUnansweredWordImages(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(wordImages.isEmpty()) {
            // Ask user if they want to play again
            int dialogResult = JOptionPane.YES_OPTION;
            if(!isEndRestartButtonClicked) {
                dialogResult = JOptionPane.showConfirmDialog(null, "Du hast alle Fragen beantwortet. Willst du noch einmal spielen?", "Warning", JOptionPane.YES_NO_OPTION);
            }

            if (dialogResult == JOptionPane.YES_OPTION) {
                try {
                    model.clearUserAnswers(user);
                    wordImages = model.getUnansweredWordImages(user);
                    // Clear user answers
                    user.resetStats();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Fehler beim Löschen der Benutzerantworten");
                }
            }else{
                // Display the new statsview
                StatsGameView nextView = new StatsGameView(user, this);
                view.setDisplayedView(nextView.getView());
                return;
            }
        }

        // Start the game with WordImageGameView as the view
        WordImageGameView nextView = new WordImageGameView(this, wordImages, user);
        view.setDisplayedView(nextView.getView());

    }


    public void handleEndGame(User user){
        // Display the new statsview
        StatsGameView nextView = new StatsGameView(user, this);
        view.setDisplayedView(nextView.getView());

    }

    public void saveUserData(User user){
            try {
            model.saveUserInfo(user);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Speichern der Benutzerinformationen");
        }
    }

    public void addUserAnsweredWordImage(User user, WordImage wordImage){
        try{
            model.addUserWordImageAnswer(user, wordImage);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Fehler beim Speichern der Benutzerantworten");
        }
    }


    public void newimageWordInputEntry(WordImage wordImage){
        try{
            boolean response = model.saveWordImage(wordImage);
            if(response){
                JOptionPane.showMessageDialog(null, "Wort und Bild erfolgreich gespeichert");
            }else{
                JOptionPane.showMessageDialog(null, "Fehler beim Speichern des Wortes und Bildes");
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Fehler beim Speichern des Wortes und Bildes" +
                    "");
        }
    }



}
