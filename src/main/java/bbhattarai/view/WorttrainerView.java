package bbhattarai.view;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.view.layouts.LoginRegisterView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class WorttrainerView  {

    WorttrainerController controller;

    public void setController(WorttrainerController controller) {
        this.controller = controller;
    }
    public LoginRegisterView getLoginRegisterView() {
        return new LoginRegisterView(this.controller);
    }





}

