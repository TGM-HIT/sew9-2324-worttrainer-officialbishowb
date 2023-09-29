package bbhattarai;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.model.Worttrainer;
import bbhattarai.model.database.DatabaseHandler;
import bbhattarai.view.WorttrainerView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private int currentWidth = 700;
    private int currentHeight = 400;


    @Override
    public void start(Stage primaryStage) {
        Worttrainer model = new Worttrainer(new DatabaseHandler());
        WorttrainerView view = new WorttrainerView(primaryStage);
        WorttrainerController controller = new WorttrainerController(model, view);
        view.setController(controller);
        primaryStage.setTitle("Worttrainer");
        primaryStage.setWidth(currentWidth);
        primaryStage.setHeight(currentHeight);
        primaryStage.setScene(new Scene(view.getLoginRegisterView().getView()));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
