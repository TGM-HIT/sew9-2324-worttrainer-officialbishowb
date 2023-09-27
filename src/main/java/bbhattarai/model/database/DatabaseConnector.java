package bbhattarai.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {


    private static String DATABASE_URL = "jdbc:sqlite:";

    public static Connection connect() {
        String srcPath = System.getProperty("user.dir");
        String sqlScriptPath = srcPath + "/src/main/java/bbhattarai/model/database/worttrainer.db";
        Connection connection = null;
        try {
            DATABASE_URL = DATABASE_URL.concat(sqlScriptPath);
            connection = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }

    public static void disconnect(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected from the database");
            } catch (SQLException e) {
                System.err.println("Error disconnecting from the database: " + e.getMessage());
            }
        }
    }

    public static Connection getConnection() {
        return connect();
    }


    public static void main(String[] args) {
        DatabaseConnector.connect();
    }


}
