package bbhattarai.model;

import java.time.LocalDateTime;


/**
 * Diese Klasse repräsentiert einen Benutzer in der Anwendung. Ein Benutzer kann Spiele spielen und Statistiken über seine Spiele führen.
 */
public class User {

    private int userId; // Die eindeutige ID des Benutzers.
    private String username; // Der Benutzername des Benutzers.
    private int totalPlay; // Die Gesamtanzahl der Spiele des Benutzers.
    private int wins; // Die Anzahl der Siege des Benutzers.
    private int losses; // Die Anzahl der Niederlagen des Benutzers.
    private LocalDateTime lastPlayedDate; // Das Datum und die Uhrzeit des letzten Spiels des Benutzers.

    /**
     * Konstruktor, der einen neuen Benutzer mit einem gegebenen Benutzernamen erstellt.
     *
     * @param username Der Benutzername des Benutzers.
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * Konstruktor, der einen neuen Benutzer mit allen angegebenen Eigenschaften erstellt.
     *
     * @param userId         Die eindeutige ID des Benutzers.
     * @param username       Der Benutzername des Benutzers.
     * @param totalPlay      Die Gesamtanzahl der Spiele des Benutzers.
     * @param wins           Die Anzahl der Siege des Benutzers.
     * @param losses         Die Anzahl der Niederlagen des Benutzers.
     * @param lastPlayedDate Das Datum und die Uhrzeit des letzten Spiels des Benutzers.
     */
    public User(int userId, String username, int totalPlay, int wins, int losses, LocalDateTime lastPlayedDate) {
        this.userId = userId;
        this.username = username;
        this.totalPlay = totalPlay;
        this.wins = wins;
        this.losses = losses;
        this.lastPlayedDate = lastPlayedDate;
    }

    /**
     * Gibt die eindeutige ID des Benutzers zurück.
     *
     * @return Die Benutzer-ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Legt die eindeutige ID des Benutzers fest.
     *
     * @param userId Die Benutzer-ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gibt den Benutzernamen des Benutzers zurück.
     *
     * @return Der Benutzername.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Legt den Benutzernamen des Benutzers fest.
     *
     * @param username Der Benutzername.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gibt die Gesamtanzahl der Spiele des Benutzers zurück.
     *
     * @return Die Gesamtanzahl der Spiele.
     */
    public int getTotalPlay() {
        return totalPlay;
    }

    /**
     * Legt die Gesamtanzahl der Spiele des Benutzers fest.
     *
     * @param totalPlay Die Gesamtanzahl der Spiele.
     */
    public void setTotalPlay(int totalPlay) {
        this.totalPlay = totalPlay;
    }

    /**
     * Gibt die Anzahl der Siege des Benutzers zurück.
     *
     * @return Die Anzahl der Siege.
     */
    public int getWins() {
        return wins;
    }

    /**
     * Legt die Anzahl der Siege des Benutzers fest.
     *
     * @param wins Die Anzahl der Siege.
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Gibt die Anzahl der Niederlagen des Benutzers zurück.
     *
     * @return Die Anzahl der Niederlagen.
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Legt die Anzahl der Niederlagen des Benutzers fest.
     *
     * @param losses Die Anzahl der Niederlagen.
     */
    public void setLosses(int losses) {
        this.losses = losses;
    }

    /**
     * Gibt das Datum und die Uhrzeit des letzten Spiels des Benutzers zurück.
     *
     * @return Das Datum und die Uhrzeit des letzten Spiels.
     */
    public LocalDateTime getLastPlayedDate() {
        return lastPlayedDate;
    }

    /**
     * Legt das Datum und die Uhrzeit des letzten Spiels des Benutzers fest.
     *
     * @param lastPlayedDate Das Datum und die Uhrzeit des letzten Spiels.
     */
    public void setLastPlayedDate(LocalDateTime lastPlayedDate) {
        this.lastPlayedDate = lastPlayedDate;
    }

    /**
     * Setzt die Statistiken des Benutzers zurück, einschließlich Gesamtanzahl der Spiele, Siege und Niederlagen.
     */
    public void resetStats() {
        this.totalPlay = 0;
        this.wins = 0;
        this.losses = 0;
    }

    /**
     * Gibt eine Zeichenfolgendarstellung des Benutzers zurück.
     *
     * @return Eine Zeichenfolge, die den Benutzer darstellt.
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", totalPlay=" + totalPlay +
                ", wins=" + wins +
                ", losses=" + losses +
                ", lastPlayedDate=" + lastPlayedDate +
                '}';
    }
}
