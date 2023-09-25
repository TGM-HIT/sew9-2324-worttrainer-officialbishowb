package bbhattarai.model;

/**
 * Repräsentiert ein Word-Image-Paar in der Datenbank.
 */
public class WordImage {
    private int wordImageId;
    private String word;
    private String imageUrl;

    /**
     * Konstruktor zur Erstellung eines WordImage-Objekts.
     *
     * @param wordImageId Die eindeutige ID des Word-Image-Paares.
     * @param word Das Wort, das dem Bild zugeordnet ist.
     * @param imageUrl Die URL des Bildes, das dem Wort zugeordnet ist.
     */
    public WordImage(int wordImageId, String word, String imageUrl) {
        this.wordImageId = wordImageId;
        this.word = word;
        this.imageUrl = imageUrl;
    }

    /**
     * Gibt die WordImage-ID zurück.
     *
     * @return Die WordImage-ID.
     */
    public int getWordImageId() {
        return wordImageId;
    }

    /**
     * Setzt die WordImage-ID.
     *
     * @param wordImageId Die WordImage-ID, die gesetzt werden soll.
     */
    public void setWordImageId(int wordImageId) {
        this.wordImageId = wordImageId;
    }

    /**
     * Gibt das zugeordnete Wort zurück.
     *
     * @return Das zugeordnete Wort.
     */
    public String getWord() {
        return word;
    }

    /**
     * Setzt das zugeordnete Wort.
     *
     * @param word Das Wort, das gesetzt werden soll.
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Gibt die URL des zugeordneten Bildes zurück.
     *
     * @return Die URL des zugeordneten Bildes.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Setzt die URL des zugeordneten Bildes.
     *
     * @param imageUrl Die URL des Bildes, die gesetzt werden soll.
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "WordImage{" +
                "wordImageId=" + wordImageId +
                ", word='" + word + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
