package bbhattarai;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.model.PersistentExpection;
import bbhattarai.model.Worttrainer;
import bbhattarai.model.database.DatabaseHandler;
import bbhattarai.view.WorttrainerView;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    private int currentWidth = 700;
    private int currentHeight = 400;


    @Override
    public void start(Stage primaryStage) {

        // Get the primary screen
        Screen screen = Screen.getPrimary();

        // Calculate the desired width and height (3/4 of the screen size)
        Rectangle2D bounds = screen.getVisualBounds();
        double screenWidth = bounds.getWidth();
        double screenHeight = bounds.getHeight();

        currentWidth = (int) (screenWidth * 0.75);
        currentHeight = (int) (screenHeight * 0.75);


        Worttrainer model = null;
        try {
            model = new Worttrainer(new DatabaseHandler());
        } catch (PersistentExpection e) {
            throw new RuntimeException(e);
        }
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

