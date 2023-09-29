package bbhattarai.model.database;

import bbhattarai.model.SpeicherStrategy;
import bbhattarai.model.User;
import bbhattarai.model.WordImage;

import java.io.*;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler implements SpeicherStrategy {
    private final Connection connection;

    public DatabaseHandler() {
        this.connection = DatabaseConnector.connect();
    }


    public void initTables(){
        String srcPath = System.getProperty("user.dir");
        String sql_script = srcPath + "/src/main/java/bbhattarai/model/database/tables.sql";
        try {
            InputStream inputStream = getClass().getResourceAsStream(sql_script);
            if (inputStream == null) {
                System.out.println("inputStream is null");
                return;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                connection.createStatement().execute(line);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    public List<WordImage> getWordImages() throws SQLException {
        List<WordImage> wordImages = new ArrayList<>();
        String query = "SELECT word_image_id, word, image_url FROM word_image";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int wordImageId = resultSet.getInt("word_image_id");
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

    public boolean saveWordImage(WordImage wordImage) throws SQLException {
        wordImage.setWordImageId(getLatestWordImageId()+1);
        String insertQuery = "INSERT INTO word_image (id, word, image_url) VALUES (?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, wordImage.getWordImageId());
            preparedStatement.setString(2, wordImage.getWord());
            preparedStatement.setString(3, wordImage.getImageUrl());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveUserInfo(User user) throws SQLException {
        String insertQuery = "INSERT OR REPLACE INTO users (user_id, username, total_play, wins, losses, last_played_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setInt(3, user.getTotalPlay());
            preparedStatement.setInt(4, user.getWins());
            preparedStatement.setInt(5, user.getLosses());
            preparedStatement.setTimestamp(6, user.getLastPlayedDate() != null ? Timestamp.valueOf(user.getLastPlayedDate()) : null);
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        }
    }

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

    public boolean clearUserAnswers(User user) throws SQLException {
        String deleteQuery = "DELETE FROM user_answers WHERE user_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, user.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        }
    }

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

                // Convert milliseconds to LocalDateTime
                LocalDateTime lastPlayedDateTime = Instant.ofEpochMilli(lastPlayedMillis)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                user = new User(userId, username, totalPlay, wins, losses, lastPlayedDateTime);
            }
        }

        return user;
    }


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

    public boolean saveNewUser(User user) throws SQLException {
        String insertQuery = "INSERT INTO users (user_id, username, total_play, wins, losses, last_played_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setInt(3, user.getTotalPlay());
            preparedStatement.setInt(4, user.getWins());
            preparedStatement.setInt(5, user.getLosses());
            preparedStatement.setTimestamp(6, user.getLastPlayedDate() != null ? Timestamp.valueOf(user.getLastPlayedDate()) : null);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        }
    }

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

    public boolean addUserWordImageAnswer(User user, WordImage wordImage) throws SQLException{
        String insertQuery = "INSERT INTO user_answers (id, user_id, word_image_id) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, getLatestUserAnswerId()+1);
            preparedStatement.setInt(2, user.getUserId());
            preparedStatement.setInt(3, wordImage.getWordImageId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        }
    }

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

    public static void main(String[] args) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.initTables();
    }
}