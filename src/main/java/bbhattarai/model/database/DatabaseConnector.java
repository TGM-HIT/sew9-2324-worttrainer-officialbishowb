package bbhattarai.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Die Klasse DatabaseConnector stellt Methoden zur Verbindung und Trennung von der Datenbank bereit.
 */
public class DatabaseConnector {

    private static String DATABASE_URL = "jdbc:sqlite:";

    /**
     * Stellt eine Verbindung zur SQLite-Datenbank her.
     *
     * @return Die hergestellte Verbindung zur Datenbank.
     */
    public static Connection connect() {
        String srcPath = System.getProperty("user.dir");
        String sqlScriptPath = srcPath + "/src/main/java/bbhattarai/model/database/worttrainer.db";
        Connection connection = null;
        try {
            DATABASE_URL = DATABASE_URL.concat(sqlScriptPath);
            connection = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Verbindung zur Datenbank hergestellt");
        } catch (SQLException e) {
            System.err.println("Fehler beim Herstellen der Verbindung zur Datenbank: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Trennt die Verbindung zur Datenbank.
     *
     * @param connection Die Verbindung, die getrennt werden soll.
     */
    public static void disconnect(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Verbindung zur Datenbank getrennt");
            } catch (SQLException e) {
                System.err.println("Fehler beim Trennen der Verbindung zur Datenbank: " + e.getMessage());
            }
        }
    }

    /**
     * Ruft eine Verbindung zur Datenbank ab.
     *
     * @return Die hergestellte Verbindung zur Datenbank.
     */
    public static Connection getConnection() {
        return connect();
    }

    /**
     * Die Hauptmethode der Klasse, die eine Verbindung zur Datenbank herstellt.
     *
     * @param args Die Argumente, die beim Ausführen des Programms übergeben werden.
     */
    public static void main(String[] args) {
        DatabaseConnector.connect();
    }
}

