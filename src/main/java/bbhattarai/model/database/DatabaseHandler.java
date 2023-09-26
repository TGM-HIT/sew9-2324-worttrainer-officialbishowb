package bbhattarai.model.database;

import bbhattarai.model.SpeicherStrategy;
import bbhattarai.model.User;
import bbhattarai.model.WordImage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler implements SpeicherStrategy {
    private Connection connection;

    public DatabaseHandler(Connection connection) {
        this.connection = connection;
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
        String insertQuery = "INSERT INTO word_image (word, image_url) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, wordImage.getWord());
            preparedStatement.setString(2, wordImage.getImageUrl());

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
        String insertQuery = "INSERT OR REPLACE INTO users (user_id, total_play, wins, losses, last_played_date) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setInt(2, user.getTotalPlay());
            preparedStatement.setInt(3, user.getWins());
            preparedStatement.setInt(4, user.getLosses());
            preparedStatement.setObject(5, user.getLastPlayedDate());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        }
    }

    public List<WordImage> getUnansweredWordImages(User user) throws SQLException {
        List<WordImage> wordImages = new ArrayList<>();
        String query = "SELECT word_image_id, word, image_url FROM word_image WHERE word_image_id NOT IN (SELECT word_image_id FROM user_answers WHERE user_id = ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getUserId());
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

        String query = "SELECT user_id, total_play, wins, losses, last_played_date FROM users WHERE username = ?";

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
                String lastPlayedDate = resultSet.getString("last_played_date");

                user = new User(userId, username, totalPlay, wins, losses, lastPlayedDate);
            }

        }

        return user;

    }
}