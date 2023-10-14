package bbhattarai.controlller;

import bbhattarai.model.PersistentExpection;
import bbhattarai.model.User;
import bbhattarai.model.WordImage;
import bbhattarai.model.Worttrainer;
import bbhattarai.view.WorttrainerView;
import bbhattarai.view.layouts.StatsGameView;
import bbhattarai.view.layouts.UserView;
import bbhattarai.view.layouts.WordImageGameView;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Die Klasse WorttrainerController ist für die Steuerung der Interaktionen
 * zwischen dem Modell (Worttrainer) und der Ansicht (WorttrainerView) verantwortlich.
 */
public class WorttrainerController {
    private Worttrainer model;
    private WorttrainerView view;


    /**
     * Konstruiert einen neuen WorttrainerController mit dem angegebenen Modell und der Ansicht.
     *
     * @param model Das Worttrainer-Modell.
     * @param view  Die WorttrainerView-Ansicht.
     */
    public WorttrainerController(Worttrainer model, WorttrainerView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Behandelt den Anmelde- oder Registrierungsvorgang für einen Benutzer.
     *
     * @param username Der vom Benutzer eingegebene Benutzername.
     */
    public void handleLoginRegister(String username) {
        try {
            User user = model.getUser(username);
            if (user == null) {
                // Create a new user
                LocalDateTime now = LocalDateTime.now();
                 user = new User(model.getLatestUserId()+1,username, 0, 0, 0, now);
                this.model.setUser(user);
                model.saveNewUser(user);
                JOptionPane.showMessageDialog(null, "Benutzer erstellt");
            }else{
                JOptionPane.showMessageDialog(null, "Willkommen "+username+"!");
                this.model.setUser(user);
            }
            // Instantiate the NextView
            UserView nextView = new UserView(model.getUser(), this);
            // Set up the NextView as the new view to display
            view.setDisplayedView(nextView.getView());
        } catch (PersistentExpection e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Erstellen des Benutzers");
        }
    }

    /**
     * Wechselt zur Benutzer-Startseite.
     *
     * @param user Der Benutzer, für den die Startseite angezeigt wird.
     */
    public void backToUserHome(User user){
        // Instanziert die nächste Ansicht (NextView)
        UserView nextView = new UserView(user, this);
        // Setzt die NextView als die neue anzuzeigende Ansicht
        view.setDisplayedView(nextView.getView());
    }

    /**
     * Startet ein neues Spiel für den Benutzer.
     *
     * @param user                        Der Benutzer, der das Spiel startet.
     * @param isEndRestartButtonClicked   Gibt an, ob der "Spiel neu starten"-Button geklickt wurde.
     */
    public void handleStartGame(User user, boolean isEndRestartButtonClicked) {
        List<WordImage> wordImages = null;
        try {
            wordImages = model.getUnansweredWordImages(user);
        } catch (PersistentExpection e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Abrufen der Wortbilder");
        }
        if (wordImages.isEmpty()) {
            // Fragt den Benutzer, ob er noch einmal spielen möchte
            int dialogResult = JOptionPane.YES_OPTION;
            if (!isEndRestartButtonClicked) {
                dialogResult = JOptionPane.showConfirmDialog(null, "Du hast alle Fragen beantwortet. Möchtest du noch einmal spielen?", "Warnung", JOptionPane.YES_NO_OPTION);
            }

            if (dialogResult == JOptionPane.YES_OPTION) {
                try {
                    model.clearUserAnswers(user);
                    wordImages = model.getUnansweredWordImages(user);
                    // Löscht die Antworten des Benutzers
                    user.resetStats();

                } catch (PersistentExpection e) {
                    JOptionPane.showMessageDialog(null, "Fehler beim Löschen der Benutzerantworten");
                }
            } else {
                // Zeigt die neue Statistikansicht an
                StatsGameView nextView = new StatsGameView(user, this);
                view.setDisplayedView(nextView.getView());
                return;
            }
        }

        // Startet das Spiel mit der WordImageGameView als Ansicht
        WordImageGameView nextView = new WordImageGameView(this, wordImages, user);
        view.setDisplayedView(nextView.getView());
    }

    /**
     * Zeigt die Statistikansicht nach Spielende an.
     *
     * @param user Der Benutzer, dessen Statistik angezeigt wird.
     */
    public void handleEndGame(User user) {
        // Zeigt die neue Statistikansicht an
        StatsGameView nextView = new StatsGameView(user, this);
        view.setDisplayedView(nextView.getView());
    }

    /**
     * Speichert die Benutzerdaten.
     *
     * @param user Der Benutzer, dessen Informationen gespeichert werden.
     */
    public void saveUserData(User user) {
        try {
            model.saveUserInfo(user);
        } catch (PersistentExpection e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Speichern der Benutzerinformationen");
        }
    }

    /**
     * Fügt die Antwort des Benutzers auf ein Wortbild hinzu.
     *
     * @param user      Der Benutzer, der die Antwort hinzufügt.
     * @param wordImage Das WordImage-Objekt, auf das geantwortet wurde.
     */
    public void addUserAnsweredWordImage(User user, WordImage wordImage) {
        try {
            model.addUserWordImageAnswer(user, wordImage);
        } catch (PersistentExpection e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Speichern der Benutzerantworten");
        }
    }

    /**
     * Fügt eine neue Bildworteingabe in die Datenbank ein.
     *
     * @param wordImage Das WordImage-Objekt mit dem Wort und der Bild-URL.
     */
    public void newImageWordInputEntry(WordImage wordImage) {
        try {
            model.saveWordImage(wordImage);
            JOptionPane.showMessageDialog(null, "Wort und Bild gespeichert");
        } catch (PersistentExpection e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Speichern des Wortes und Bildes");
        }
    }




}
