package bbhattarai.view;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.view.layouts.LoginRegisterView;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class WorttrainerView  {

    WorttrainerController controller;

    Stage primaryStage;

    public WorttrainerView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setController(WorttrainerController controller) {
        this.controller = controller;
    }
    public LoginRegisterView getLoginRegisterView() {
        return new LoginRegisterView(this.controller);
    }

    public void setDisplayedView(Parent newView) {
        Scene scene = new Scene(newView);
        primaryStage.setScene(scene);

        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(400);

        primaryStage.show();
    }








}

