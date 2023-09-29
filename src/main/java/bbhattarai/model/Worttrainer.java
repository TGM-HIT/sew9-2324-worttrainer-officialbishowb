package bbhattarai.model;

import java.sql.SQLException;
import java.util.List;

public class Worttrainer {

    List<WordImage> wordImages;
    User user;
    SpeicherStrategy speicherStrategy;


    public Worttrainer(SpeicherStrategy speicherStrategy) {
        this.speicherStrategy = speicherStrategy;
    }

    public void setSpeicherStrategy(SpeicherStrategy speicherStrategy) {
        this.speicherStrategy = speicherStrategy;
    }

    public List<WordImage> getWordImages() {
        return wordImages;
    }


    public void setWordImages(List<WordImage> wordImages) {
        this.wordImages = wordImages;
    }


    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    public int getLatestUserId() throws SQLException {
        return speicherStrategy.getLatestUserId();
    }

    public boolean saveUserInfo(User user) throws SQLException {
        return speicherStrategy.saveUserInfo(user);
    }

    public boolean saveNewUser(User user) throws SQLException {
        return speicherStrategy.saveNewUser(user);
    }

    public boolean clearUserAnswers(User user) throws SQLException {
        return speicherStrategy.clearUserAnswers(user);
    }

    public List<WordImage> getUnansweredWordImages(User user) throws SQLException {
        return speicherStrategy.getUnansweredWordImages(user);
    }

    public List<WordImage> getAllWordImages() throws SQLException {
        return speicherStrategy.getWordImages();
    }

    public User getUser(String username) throws SQLException {
        return speicherStrategy.getUser(username);
    }


    public boolean addUserWordImageAnswer(User user, WordImage wordImage) throws SQLException {
        return speicherStrategy.addUserWordImageAnswer(user, wordImage);
    }


    public boolean saveWordImage(WordImage wordImage) throws SQLException {
        return speicherStrategy.saveWordImage(wordImage);
    }



}
