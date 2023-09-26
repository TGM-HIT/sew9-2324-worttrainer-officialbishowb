package bbhattarai.model;

import java.time.LocalDateTime;

/**
 * Represents a user in the application.
 */
public class User {
    private int userId;

    private String username;
    private int totalPlay;
    private int wins;
    private int losses;
    private LocalDateTime lastPlayedDate;


    /**
     * Constructs a new User object with the given username.
     *
     * @param username The username of the user.
     */
    public User(String username) {
        this.username = username;
    }


    /**
     * Constructs a new User object with the given username and user id.
     *
     * @param userId   The user id of the user.
     * @param username The username of the user.
     */
    public User(int userId, String username, int totalPlay, int wins, int losses, String lastPlayedDate) {
        this.userId = userId;
        this.username = username;
        this.totalPlay = totalPlay;
        this.wins = wins;
        this.losses = losses;
        this.lastPlayedDate = LocalDateTime.parse(lastPlayedDate);
    }

    /**
     * Gets the user's unique identifier.
     *
     * @return The user's unique identifier.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user's unique identifier.
     *
     * @param userId The user's unique identifier.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

/**
     * Sets the username of the user.
     *
     * @param username The username of the user.
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the total number of plays for the user.
     *
     * @return The total number of plays.
     */
    public int getTotalPlay() {
        return totalPlay;
    }

    /**
     * Sets the total number of plays for the user.
     *
     * @param totalPlay The total number of plays.
     */
    public void setTotalPlay(int totalPlay) {
        this.totalPlay = totalPlay;
    }

    /**
     * Gets the number of wins for the user.
     *
     * @return The number of wins.
     */
    public int getWins() {
        return wins;
    }

    /**
     * Sets the number of wins for the user.
     *
     * @param wins The number of wins.
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Gets the number of losses for the user.
     *
     * @return The number of losses.
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Sets the number of losses for the user.
     *
     * @param losses The number of losses.
     */
    public void setLosses(int losses) {
        this.losses = losses;
    }

    /**
     * Gets the date and time when the user last played.
     *
     * @return The last played date and time.
     */
    public LocalDateTime getLastPlayedDate() {
        return lastPlayedDate;
    }

    /**
     * Sets the date and time when the user last played.
     *
     * @param lastPlayedDate The last played date and time.
     */
    public void setLastPlayedDate(LocalDateTime lastPlayedDate) {
        this.lastPlayedDate = lastPlayedDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", totalPlay=" + totalPlay +
                ", wins=" + wins +
                ", losses=" + losses +
                ", lastPlayedDate=" + lastPlayedDate +
                '}';
    }
}
