package bbhattarai.model;

public class PersistentExpection extends Exception {
    public PersistentExpection(String message) {
        super(message);
    }

    public PersistentExpection(String message, Throwable cause) {
        super(message, cause);
    }
}
