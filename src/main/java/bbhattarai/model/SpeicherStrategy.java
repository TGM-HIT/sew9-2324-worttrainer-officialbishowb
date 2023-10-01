package bbhattarai.model;

import java.sql.SQLException;
import java.util.List;

public interface SpeicherStrategy {

    void saveUserInfo(User user) throws SQLException;
    void saveNewUser(User user) throws SQLException;
    void saveWordImage(WordImage wordImage) throws SQLException;
    List<WordImage> getUnansweredWordImages(User user) throws SQLException;
    void clearUserAnswers(User user) throws SQLException;

    User getUser(String username) throws SQLException;

    int getLatestUserId() throws SQLException;

    void addUserWordImageAnswer(User user, WordImage wordImage) throws SQLException;

     int getLatestUserAnswerId() throws SQLException;

}
