package bbhattarai.controlller;

import bbhattarai.model.Worttrainer;
import bbhattarai.view.WorttrainerView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.swing.*;
import java.sql.SQLException;

public class WorttrainerController {
    private Worttrainer model;
    private WorttrainerView view;

    public WorttrainerController(Worttrainer model, WorttrainerView view) {
        this.model = model;
        this.view = view;
    }

    public void handleLoginRegister(String username) {
        try {
            if (model.getUser(username) == null) {

                // Create a new user
                model.saveUserInfo(model.getUser());
                JOptionPane.showMessageDialog(null, "User created successfully");
            } else {
                // Go to the next view if the user exists and display his stats
                JOptionPane.showMessageDialog(null, "User exists");


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
