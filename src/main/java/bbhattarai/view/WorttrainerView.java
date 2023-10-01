package bbhattarai.view;

import bbhattarai.controlller.WorttrainerController;
import bbhattarai.model.User;
import bbhattarai.model.Worttrainer;
import bbhattarai.view.layouts.LoginRegisterView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import bbhattarai.controlller.WorttrainerController;
import bbhattarai.view.layouts.LoginRegisterView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Die Klasse `WorttrainerView` stellt die Benutzeroberfläche des Worttrainer-Programms dar.
 * Sie verwaltet die Anzeige von Ansichten und die Kommunikation mit dem Controller.
 */
public class WorttrainerView {

    private WorttrainerController controller; // Der Controller für die Benutzeroberfläche.

    private Stage primaryStage;

    /**
     * Konstruktor für die `WorttrainerView`-Klasse.
     *
     * @param primaryStage Die Hauptbühne (Stage) der Anwendung.
     */
    public WorttrainerView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public WorttrainerView() {

    }
    /**
     * Setzt den Controller für die Benutzeroberfläche.
     *
     * @param controller Der Controller, der gesetzt werden soll.
     */
    public void setController(WorttrainerController controller) {
        this.controller = controller;
    }

    /**
     * Gibt das Controller-Objekt für die Benutzeroberfläche zurück.
     *
     * @return Das Controller-Objekt für die Benutzeroberfläche.
     */
    public WorttrainerController getController() {
        return this.controller;
    }

    /**
     * Gibt die Ansicht für die Anmeldung und Registrierung zurück.
     *
     * @return Die Ansicht für die Anmeldung und Registrierung.
     */
    public LoginRegisterView getLoginRegisterView() {
        return new LoginRegisterView(controller);
    }

    /**
     * Setzt die angezeigte Ansicht auf die übergebene Ansicht.
     *
     * @param newView Die neue Ansicht, die angezeigt werden soll.
     */
    public void setDisplayedView(Parent newView) {
        // Holen Sie sich die aktuelle Breite und Höhe der primaryStage
        double currentWidth = primaryStage.getWidth();
        double currentHeight = primaryStage.getHeight();

        // Erstellen Sie eine neue Szene mit der neuen Ansicht und setzen Sie sie auf die aktuellen Abmessungen
        Scene scene = new Scene(newView, currentWidth, currentHeight);
        primaryStage.setScene(scene);

        // Setzen Sie die minimale Breite und Höhe
        primaryStage.setMinHeight(currentHeight);
        primaryStage.setMinWidth(currentWidth);

        primaryStage.show();
    }
}

