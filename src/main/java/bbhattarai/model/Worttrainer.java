package bbhattarai.model;

import java.sql.SQLException;
import java.util.List;

public class Worttrainer {

    List<WordImage> wordImages;
    User user;
    SpeicherStrategy speicherStrategy;


    public Worttrainer() {
    }
    public Worttrainer( User user) {
        this.user = user;
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





}
