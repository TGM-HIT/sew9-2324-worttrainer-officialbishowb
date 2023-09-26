package bbhattarai.model;

import java.sql.SQLException;
import java.util.List;

public interface SpeicherStrategy {

    boolean saveUserInfo(User user) throws SQLException;
    List<WordImage> getWordImages() throws SQLException;
    boolean saveWordImage(WordImage wordImage) throws SQLException;

    List<WordImage> getUnansweredWordImages(User user) throws SQLException;
    boolean clearUserAnswers(User user) throws SQLException;

}
