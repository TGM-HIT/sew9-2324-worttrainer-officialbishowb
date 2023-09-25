package bbhattarai.model.database;

import bbhattarai.model.User;
import bbhattarai.model.WordImage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
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

    public boolean saveWordImage(WordImage wordImage) throws SQLException{
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




}
