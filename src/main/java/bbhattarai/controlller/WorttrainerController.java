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
                JOptionPane.showMessageDialog(null, "User created successfully");
            }else{
                JOptionPane.showMessageDialog(null, "Welcome back "+username+"!");
                this.model.setUser(user);
            }
            // Instantiate the NextView
            UserView nextView = new UserView(model.getUser(), this);

            // Set up the NextView as the new view to display
            view.setDisplayedView(nextView.getView());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                dialogResult = JOptionPane.showConfirmDialog(null, "You have answered all the questions. Do you want to play again?", "Warning", JOptionPane.YES_NO_OPTION);
            }

            if (dialogResult == JOptionPane.YES_OPTION) {
                try {
                    model.clearUserAnswers(user);
                    wordImages = model.getUnansweredWordImages(user);
                    // Clear user answers
                    user.resetStats();

                } catch (SQLException e) {
                    e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public void addUserAnsweredWordImage(User user, WordImage wordImage){
        try{
            model.addUserWordImage(user, wordImage);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void newimageWordInputEntry(WordImage wordImage){
        System.out.println("Adding new word image entry");
    }



}
