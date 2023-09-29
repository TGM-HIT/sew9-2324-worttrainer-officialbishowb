package bbhattarai.view;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.view.layouts.LoginRegisterView;
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
        // Get the current width and height of the primaryStage
        double currentWidth = primaryStage.getWidth();
        double currentHeight = primaryStage.getHeight();

        // Create a new scene with the new view and set it to the current dimensions
        Scene scene = new Scene(newView, currentWidth, currentHeight);
        primaryStage.setScene(scene);

        // Set the minimum width and height
        primaryStage.setMinHeight(currentHeight);
        primaryStage.setMinWidth(currentWidth);

        primaryStage.show();
    }









}

