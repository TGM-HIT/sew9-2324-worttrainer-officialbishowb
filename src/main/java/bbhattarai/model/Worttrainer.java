package bbhattarai.model;

import java.util.List;

public class Worttrainer {

    List<WordImage> wordImages;
    User user;


    public Worttrainer(List<WordImage> wordImages, User user) {
        this.wordImages = wordImages;
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





}
