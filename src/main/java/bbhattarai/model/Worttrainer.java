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
    public Worttrainer( User user) {
        this.user = user;
    }

    public List<WordImage> getWordImages() {
        return wordImages;
    }


    public void setWordImages(List<WordImage> wordImages) {
        this.wordImages = wordImages;
    }


    public User getUser() {
        return user;
    }


    public boolean saveUserInfo(User user) throws SQLException {
        return speicherStrategy.saveUserInfo(user);
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




}
