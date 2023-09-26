package bbhattarai;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.model.Worttrainer;
import bbhattarai.model.database.DatabaseConnector;
import bbhattarai.model.database.DatabaseHandler;
import bbhattarai.view.WorttrainerView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Worttrainer model = new Worttrainer(new DatabaseHandler(DatabaseConnector.connect()));
        WorttrainerView view = new WorttrainerView();
        WorttrainerController controller = new WorttrainerController(model, view);
        view.setController(controller);
        primaryStage.setTitle("Worttrainer");
        primaryStage.setScene(new Scene(view.getLoginRegisterView().getView(), 300, 275));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
