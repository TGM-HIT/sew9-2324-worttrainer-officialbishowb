package bbhattarai.model.database;

import bbhattarai.model.SpeicherStrategy;
import bbhattarai.model.User;
import bbhattarai.model.WordImage;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler implements SpeicherStrategy {
    private final Connection connection;

    /**
     * Konstruktor der Klasse DatabaseHandler, der eine Verbindung zur Datenbank herstellt und die Datenbank initialisiert.
     *
     * @throws SQLException Falls ein Fehler beim Verbindungsaufbau oder der Initialisierung auftritt.
     */
    public DatabaseHandler() throws SQLException {
        this.connection = DatabaseConnector.connect();
        this.initDatabase();
    }

    /**
     * Initialisiert die Datenbank mithilfe eines DatabaseInitializer-Objekts.
     *
     * @throws SQLException Falls ein Fehler bei der Initialisierung der Datenbank auftritt.
     */
    public void initDatabase() throws SQLException {
        DatabaseInitializer databaseInitializer = new DatabaseInitializer(connection, this);
        databaseInitializer.initializeDatabase();
    }

    /**
     * Speichert ein WordImage-Objekt in der Datenbank.
     *
     * @param wordImage Das WordImage-Objekt, das in der Datenbank gespeichert werden soll.
     * @throws SQLException Falls ein Fehler beim Speichern des Objekts in der Datenbank auftritt.
     */
    public void saveWordImage(WordImage wordImage) throws SQLException {
        wordImage.setWordImageId(getLatestWordImageId() + 1);
        String insertQuery = "INSERT INTO word_image (id, word, image_url) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, wordImage.getWordImageId());
            preparedStatement.setString(2, wordImage.getWord());
            preparedStatement.setString(3, wordImage.getImageUrl());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Speichert Benutzerinformationen in der Datenbank oder ersetzt sie, wenn der Benutzer bereits existiert.
     *
     * @param user Das User-Objekt, dessen Informationen gespeichert werden sollen.
     * @throws SQLException Falls ein Fehler beim Speichern oder Aktualisieren der Benutzerinformationen in der Datenbank auftritt.
     */
    public void saveUserInfo(User user) throws SQLException {
        String insertQuery = "INSERT OR REPLACE INTO users (user_id, username, total_play, wins, losses, last_played_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setInt(3, user.getTotalPlay());
            preparedStatement.setInt(4, user.getWins());
            preparedStatement.setInt(5, user.getLosses());
            preparedStatement.setTimestamp(6, user.getLastPlayedDate() != null ? Timestamp.valueOf(user.getLastPlayedDate()) : null);

            preparedStatement.executeUpdate();
        }
    }

    /**
     * Ruft unausgeblendete WordImage-Objekte ab, die der Benutzer noch nicht beantwortet hat.
     *
     * @param user Das User-Objekt, für das unausgeblendete WordImage-Objekte abgerufen werden sollen.
     * @return Eine Liste von unausgeblendeten WordImage-Objekten, die der Benutzer noch nicht beantwortet hat.
     * @throws SQLException Falls ein Fehler beim Abrufen der Daten aus der Datenbank auftritt.
     */
    public List<WordImage> getUnansweredWordImages(User user) throws SQLException {
        List<WordImage> wordImages = new ArrayList<>();
        String query = "SELECT id, word, image_url FROM word_image WHERE id NOT IN (SELECT id FROM user_answers WHERE user_id = ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int wordImageId = resultSet.getInt("id");
                String word = resultSet.getString("word");
                String imageUrl = resultSet.getString("image_url");

                WordImage wordImage = new WordImage(wordImageId, word, imageUrl);
                wordImages.add(wordImage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wordImages;
    }


    /**
     * Löscht alle Benutzerantworten in der Datenbank, die einem bestimmten Benutzer zugeordnet sind.
     *
     * @param user Das User-Objekt, für das die Benutzerantworten gelöscht werden sollen.
     * @throws SQLException Falls ein Fehler beim Löschen der Benutzerantworten aus der Datenbank auftritt.
     */
    public void clearUserAnswers(User user) throws SQLException {
        String deleteQuery = "DELETE FROM user_answers WHERE user_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, user.getUserId());

            preparedStatement.executeUpdate();

        }
    }

    /**
     * Ruft Benutzerinformationen aus der Datenbank anhand des Benutzernamens ab.
     *
     * @param username Der Benutzername, für den Benutzerinformationen abgerufen werden sollen.
     * @return Ein User-Objekt, das die abgerufenen Benutzerinformationen enthält, oder null, wenn der Benutzer nicht gefunden wurde.
     * @throws SQLException Falls ein Fehler beim Abrufen der Daten aus der Datenbank auftritt.
     */
    public User getUser(String username) throws SQLException {
        User user = null;

        String query = "SELECT  user_id, total_play, wins, losses, last_played_date FROM users WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            if (username != null) {
                preparedStatement.setString(1, username);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int totalPlay = resultSet.getInt("total_play");
                int wins = resultSet.getInt("wins");
                int losses = resultSet.getInt("losses");
                long lastPlayedMillis = resultSet.getLong("last_played_date");

                // Konvertiert Millisekunden in LocalDateTime
                LocalDateTime lastPlayedDateTime = Instant.ofEpochMilli(lastPlayedMillis)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                user = new User(userId, username, totalPlay, wins, losses, lastPlayedDateTime);
            }
        }

        return user;
    }



    /**
     * Ruft die neueste Benutzer-ID aus der Datenbank ab.
     *
     * @return Die neueste Benutzer-ID in der Datenbank.
     * @throws SQLException Falls ein Fehler beim Abrufen der Daten aus der Datenbank auftritt.
     */
    public int getLatestUserId() throws SQLException {
        int latestUserId = 0;

        String query = "SELECT MAX(user_id) AS latest_user_id FROM users";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                latestUserId = resultSet.getInt("latest_user_id");
            }
        }

        return latestUserId;
    }

    /**
     * Speichert Benutzerinformationen in der Datenbank oder aktualisiert sie, wenn der Benutzer bereits vorhanden ist.
     *
     * @param user Das User-Objekt, das gespeichert oder aktualisiert werden soll.
     * @throws SQLException Falls ein Fehler beim Speichern oder Aktualisieren der Benutzerinformationen in der Datenbank auftritt.
     */
    public void saveNewUser(User user) throws SQLException {
        String insertQuery = "INSERT INTO users (user_id, username, total_play, wins, losses, last_played_date) VALUES (?, ?, ?, ?, ?, ?)";

        System.out.println("Saving new user");
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setInt(3, user.getTotalPlay());
            preparedStatement.setInt(4, user.getWins());
            preparedStatement.setInt(5, user.getLosses());
            preparedStatement.setTimestamp(6, user.getLastPlayedDate() != null ? Timestamp.valueOf(user.getLastPlayedDate()) : null);
            preparedStatement.executeUpdate();
        }
    }


    /**
     * Ruft die neueste Benutzerantwort-ID aus der Datenbank ab.
     *
     * @return Die neueste Benutzerantwort-ID in der Datenbank.
     * @throws SQLException Falls ein Fehler beim Abrufen der Daten aus der Datenbank auftritt.
     */
    public int getLatestUserAnswerId() throws SQLException{
        int latestUserAnswerId = 0;

        String query = "SELECT MAX(id) AS latest_user_answer_id FROM user_answers";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                latestUserAnswerId = resultSet.getInt("latest_user_answer_id");
            }
        }

        return latestUserAnswerId;
    }

    /**
     * Fügt die Benutzerantwort auf ein Wortbild in die Datenbank ein.
     *
     * @param user      Das User-Objekt, dem die Antwort zugeordnet ist.
     * @param wordImage Das WordImage-Objekt, auf das geantwortet wurde.
     * @throws SQLException Falls ein Fehler beim Speichern der Benutzerantwort in der Datenbank auftritt.
     */
    public void addUserWordImageAnswer(User user, WordImage wordImage) throws SQLException{
        String insertQuery = "INSERT INTO user_answers (id, user_id, word_image_id) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, getLatestUserAnswerId()+1);
            preparedStatement.setInt(2, user.getUserId());
            preparedStatement.setInt(3, wordImage.getWordImageId());

            preparedStatement.executeUpdate();

        }
    }

    /**
     * Ruft die neueste WordImage-ID aus der Datenbank ab.
     *
     * @return Die neueste WordImage-ID in der Datenbank.
     * @throws SQLException Falls ein Fehler beim Abrufen der Daten aus der Datenbank auftritt.
     */
    public int getLatestWordImageId() throws SQLException{
        int latestWordImageId = 0;

        String query = "SELECT MAX(id) AS latest_word_image_id FROM word_image";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                latestWordImageId = resultSet.getInt("latest_word_image_id");
            }
        }

        return latestWordImageId;
    }


    /**
     * Überprüft, ob Dummy-Daten in der Datenbank existieren.
     *
     * @return true, wenn es Dummy-Daten in der Datenbank gibt; false, wenn keine Dummy-Daten vorhanden sind.
     * @throws SQLException Falls ein Fehler beim Abrufen der Daten aus der Datenbank auftritt.
     */
    public boolean dummyDataExist() throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM word_image";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        }

        return false;
    }



}