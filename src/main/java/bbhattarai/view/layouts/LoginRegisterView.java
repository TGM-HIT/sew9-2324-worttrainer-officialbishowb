package bbhattarai.view.layouts;

import bbhattarai.controlller.WorttrainerController;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;

public class LoginRegisterView {
    private BorderPane view;
    private TextField usernameField;
    private Button loginRegisterButton;

    WorttrainerController handler;

    public LoginRegisterView(WorttrainerController handler) {
        this.handler = handler;
        view = new BorderPane();

        usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        loginRegisterButton = new Button("Login / Register");

        VBox loginBox = new VBox(usernameField, loginRegisterButton);
        loginBox.setSpacing(10);
        loginBox.setMaxWidth(200);
        loginBox.setAlignment(Pos.CENTER);

        view.setCenter(loginBox);

        addListener();
    }

    public void addListener() {
        loginRegisterButton.setOnAction(event -> {
            this.handler.handleLoginRegister(usernameField.getText());
        });
    }
    public BorderPane getView() {
        return view;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public Button getLoginRegisterButton() {
        return loginRegisterButton;
    }
}
