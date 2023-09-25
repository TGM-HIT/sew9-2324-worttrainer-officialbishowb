package bbhattarai.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String DATABASE_URL = "jdbc:sqlite:worttrainer.db";

    public static Connection connect() {
        Connection connection = null;
        try {
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

    public static void main(String[] args) {
        Connection connection = connect();
        DatabaseHandler databaseHandler = new DatabaseHandler(connection);


        disconnect(connection);
    }
}
