package bbhattarai.model;

import java.util.List;

/**
 * Die Klasse `Worttrainer` repräsentiert den Hauptcontroller für das Worttrainer-Programm.
 * Sie verwaltet Benutzer, Wortbilder und die Datenbankkommunikation über die bereitgestellte Speicherstrategie.
 */
public class Worttrainer {

    private List<WordImage> wordImages; // Die Liste der verfügbaren Wortbilder.
    private User user; // Der aktuelle Benutzer des Programms.
    private SpeicherStrategy speicherStrategy; // Die verwendete Speicherstrategie für die Datenbankkommunikation.

    /**
     * Konstruktor für die `Worttrainer`-Klasse.
     *
     * @param speicherStrategy Die Speicherstrategie, die für die Datenbankkommunikation verwendet wird.
     */
    public Worttrainer(SpeicherStrategy speicherStrategy) {
        this.speicherStrategy = speicherStrategy;
    }

    /**
     * Setzt den aktuellen Benutzer des Programms.
     *
     * @param user Der Benutzer, der festgelegt werden soll.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gibt den aktuellen Benutzer des Programms zurück.
     *
     * @return Der aktuelle Benutzer.
     */
    public User getUser() {
        return user;
    }

    /**
     * Ruft die neueste Benutzer-ID aus der Datenbank ab.
     *
     * @return Die neueste Benutzer-ID.
     * @throws PersistentExpection Falls ein Fehler auftritt.
     */
    public int getLatestUserId() throws PersistentExpection {
        return speicherStrategy.getLatestUserId();
    }

    /**
     * Speichert die Benutzerinformationen in der Datenbank.
     *
     * @param user Der Benutzer, dessen Informationen gespeichert werden sollen.
     * @throws PersistentExpection Falls ein Fehler auftritt.
     */
    public void saveUserInfo(User user) throws PersistentExpection {
        speicherStrategy.saveUserInfo(user);
    }

    /**
     * Speichert einen neuen Benutzer in der Datenbank.
     *
     * @param user Der neue Benutzer, der gespeichert werden soll.
     * @throws PersistentExpection Falls ein Fehler auftritt.
     */
    public void saveNewUser(User user) throws PersistentExpection {
        speicherStrategy.saveNewUser(user);
    }

    /**
     * Löscht die Antworten eines Benutzers aus der Datenbank.
     *
     * @param user Der Benutzer, dessen Antworten gelöscht werden sollen.
     * @throws PersistentExpection Falls ein Fehler auftritt.
     */
    public void clearUserAnswers(User user) throws PersistentExpection {
        speicherStrategy.clearUserAnswers(user);
    }

    /**
     * Ruft eine Liste von unbeantworteten Wortbildern für einen Benutzer aus der Datenbank ab.
     *
     * @param user Der Benutzer, für den die Liste der unbeantworteten Wortbilder abgerufen werden soll.
     * @return Die Liste der unbeantworteten Wortbilder.
     * @throws PersistentExpection Falls ein Fehler auftritt.
     */
    public List<WordImage> getUnansweredWordImages(User user) throws PersistentExpection {
        return speicherStrategy.getUnansweredWordImages(user);
    }

    /**
     * Ruft einen Benutzer anhand seines Benutzernamens aus der Datenbank ab.
     *
     * @param username Der Benutzername des gesuchten Benutzers.
     * @return Der gefundene Benutzer oder null, wenn der Benutzer nicht gefunden wurde.
     * @throws PersistentExpection Falls ein Fehler auftritt.
     */
    public User getUser(String username) throws PersistentExpection {
        return speicherStrategy.getUser(username);
    }

    /**
     * Fügt die Antwort eines Benutzers auf ein Wortbild in die Datenbank ein.
     *
     * @param user      Der Benutzer, der die Antwort gibt.
     * @param wordImage Das Wortbild, auf das geantwortet wird.
     * @throws PersistentExpection Falls ein Fehler auftritt.
     */
    public void addUserWordImageAnswer(User user, WordImage wordImage) throws PersistentExpection {
        speicherStrategy.addUserWordImageAnswer(user, wordImage);
    }

    /**
     * Speichert ein neues Wortbild in der Datenbank.
     *
     * @param wordImage Das neue Wortbild, das gespeichert werden soll.
     * @return True, wenn das Wortbild erfolgreich gespeichert wurde, andernfalls False.
     * @throws PersistentExpection Falls ein Fehler auftritt.
     */
    public void saveWordImage(WordImage wordImage) throws PersistentExpection {
        speicherStrategy.saveWordImage(wordImage);
    }
}
