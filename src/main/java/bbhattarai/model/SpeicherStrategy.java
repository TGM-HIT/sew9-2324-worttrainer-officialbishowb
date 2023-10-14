package bbhattarai.model;

import java.util.List;


public interface SpeicherStrategy {

    void saveUserInfo(User user) throws PersistentExpection;
    void saveNewUser(User user) throws PersistentExpection;
    void saveWordImage(WordImage wordImage) throws PersistentExpection;
    List<WordImage> getUnansweredWordImages(User user) throws PersistentExpection;
    void clearUserAnswers(User user) throws PersistentExpection;

    User getUser(String username) throws PersistentExpection;

    int getLatestUserId() throws PersistentExpection;

    void addUserWordImageAnswer(User user, WordImage wordImage) throws PersistentExpection;

     int getLatestUserAnswerId() throws PersistentExpection;

}
