package bbhattarai.view.layouts;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.view.WorttrainerView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;

import javax.swing.*;

public class LoginRegisterView {
    private BorderPane view;
    private TextField usernameField;
    private Button loginRegisterButton;

    WorttrainerController controller;

    public LoginRegisterView(WorttrainerController handler) {
        super();
        this.controller = handler;
        view = new BorderPane();

        usernameField = new TextField();
        usernameField.setPromptText("Nutzername eingeben");
        usernameField.setMinWidth(200);
        usernameField.setMinHeight(50);
        usernameField.setStyle("-fx-font-size: 18px;");

        loginRegisterButton = new Button("Login / Registrieren");
        loginRegisterButton.setStyle("-fx-font-size: 18px; -fx-background-color: black; -fx-text-fill: white;");
        loginRegisterButton.setMinWidth(250);
        loginRegisterButton.setMinHeight(30);
        VBox loginBox = new VBox(usernameField, loginRegisterButton);
        loginBox.setSpacing(10);
        loginBox.setMaxWidth(200);
        loginBox.setAlignment(Pos.CENTER);

        view.setCenter(loginBox);

        addListener();
    }

    public void addListener() {
        loginRegisterButton.setOnAction(event -> {
            String username = usernameField.getText();

           if (username.isEmpty()) {
                usernameField.setStyle("-fx-border-color: red;");
                JOptionPane.showMessageDialog(null, "Nutzername darf nicht leer sein");
                return;
            }
            loginRegisterButton.setText("Bitte warten...");
            this.controller.handleLoginRegister(username);
        });
    }
    public BorderPane getView() {
        return view;
    }
}
