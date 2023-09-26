package bbhattarai.controlller;

import bbhattarai.model.Worttrainer;
import bbhattarai.view.WorttrainerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class WorttrainerController {
    private Worttrainer model;
    private WorttrainerView view;

    public WorttrainerController(Worttrainer model, WorttrainerView view) {
        this.model = model;
        this.view = view;


    }

    public static void main(String[] args) {
        Worttrainer model = new Worttrainer();
        WorttrainerView view = new WorttrainerView();
        WorttrainerController controller = new WorttrainerController(model, view);
        view.getLoginRegisterView().getLoginRegisterButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Login/Register button clicked");
            }
        });
    }
}
